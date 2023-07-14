package com.example.mfaella.physicsapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.badlogic.androidgames.framework.Input;
import com.example.mfaella.physicsapp.gameObjects.BridgeElementGO;
import com.example.mfaella.physicsapp.gameObjects.BridgeElementType;
import com.example.mfaella.physicsapp.gameObjects.DynamicJointGO;
import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.example.mfaella.physicsapp.joints.MyRevoluteJoint;
import com.google.fpl.liquidfun.AABB;
import com.google.fpl.liquidfun.Body;
import com.google.fpl.liquidfun.Fixture;
import com.google.fpl.liquidfun.QueryCallback;
import com.google.fpl.liquidfun.Vec2;

public class TouchConsumer {

    private Fixture touchedFixture;

    private GameWorld gw;
    private QueryCallback touchQueryCallback = new TouchQueryCallback();

    private Vec2 p1;
    private Vec2 p2;
    private AABB aabb;

    private Vec2 startingPoint = new Vec2();
    private Vec2 endPoint = new Vec2();
    private Vec2 dxy;
    private GameObject firstTouchedGO = null;
    private final static float POINTER_SIZE = 0.2f;

    Canvas canvas;
    Paint paint;

    private class TouchQueryCallback extends QueryCallback
    {
        public boolean reportFixture(Fixture fixture) {
            touchedFixture = fixture;
            return true;
        }
    }

    public TouchConsumer(GameWorld gw) {
        this.gw = gw;
        dxy = new Vec2();
        p1 = new Vec2();
        p2 = new Vec2();
        aabb = new AABB();
        canvas = new Canvas(gw.getBuffer());
        paint = new Paint();
        paint.setARGB(255,0,0,0);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(10.0f);
        paint.setTypeface(Typeface.SANS_SERIF);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public void consumeTouchEvent(Input.TouchEvent event)
    {
        switch (event.type) {
            case Input.TouchEvent.TOUCH_DOWN:
                consumeTouchDown(event);
                break;
            case Input.TouchEvent.TOUCH_UP:
                consumeTouchUp(event);
                break;
            case Input.TouchEvent.TOUCH_DRAGGED:
                consumeTouchMove(event);
                break;
        }
    }

    private void consumeTouchDown(Input.TouchEvent event) {
        float x = gw.toMetersX(event.x), y = gw.toMetersY(event.y);
        touchedFixture = null;

        System.out.println("Posizione: "+event.x+","+event.y+" - Posizione to meter: "+x+","+y);
        getAABB(x, y);
        gw.getWorld().queryAABB(touchQueryCallback, aabb);
        if (touchedFixture != null) {
            Body touchedBody = touchedFixture.getBody();
            Object userData = touchedBody.getUserData();
            if (userData != null) {
                GameObject touchedGO = (GameObject) userData;
                if(isTouchedElementValid(touchedGO)){
                    startingPoint.setX(x);
                    startingPoint.setY(y);
                    firstTouchedGO = touchedGO;
                }
            }
        }
    }

    private void consumeTouchUp(Input.TouchEvent event) {
        touchedFixture = null;
        float x = gw.toMetersX(event.x), y = gw.toMetersY(event.y);
        System.out.println(event.x+" - "+event.y);

        endPoint.setX(x);
        endPoint.setY(y);

        dxy.setX(endPoint.getX() - startingPoint.getX());
        dxy.setY(endPoint.getY() - startingPoint.getY());


        getAABB(x, y);

        gw.getWorld().queryAABB(touchQueryCallback, aabb);
        if (touchedFixture != null) {
            Body touchedBody = touchedFixture.getBody();
            Object userData = touchedBody.getUserData();
            if (userData != null) {
                GameObject touchedGO = (GameObject) userData;
                if(isTouchedElementValid(touchedGO) && firstTouchedGO != null && !areTouchedGOBothTerrain(firstTouchedGO,touchedGO)){
                    float bodyX = ((startingPoint.getX() + endPoint.getX())/2);
                    float bodyY = ((startingPoint.getY() + endPoint.getY())/2);

                    float distance = getDistance();
                    int price = getPrice(distance);

                    if(isNewBeamPossible(price) && distance >= 0.2f){
                        addNewBeam(touchedGO, bodyX, bodyY, distance, price);
                    }
                    firstTouchedGO = null;
                }
            }

        }

        //controllo start button
        if (isPlayButtonAreaPressed(event)) {
            gw.detonateBombs();
        }

        if(isResetButtonAreaPressed(event)){
            gw.resetGame();
        }

        if(isExitButtonPressed(event)){
            gw.getActivity().finish();
        }
    }

    private float getDistance() {
        return (float) Math.sqrt(Math.pow(endPoint.getX() - startingPoint.getX(), 2) + Math.pow(endPoint.getY() - startingPoint.getY(), 2));
    }

    public boolean areTouchedGOBothTerrain(GameObject firstTouchedGO, GameObject secondTouchedGO){
        return firstTouchedGO.getName().contains("Terrain") && secondTouchedGO.getName().contains("Terrain");
    }

    private void addNewBeam(GameObject touchedGO, float bodyX, float bodyY, float distance, int price) {
        GameObject newBeam = gw.addNewBeam(new BridgeElementGO(gw, BridgeElementType.BEAM, bodyX, bodyY, (float) Math.toDegrees(Math.atan2(dxy.getY(),dxy.getX())), distance,0.5f));
        GameObject firstJoint = gw.addNewBeam(new DynamicJointGO(gw, newBeam.getBody().getPositionX()+(distance /2), newBeam.getBody().getPositionY(),1.0f,1.0f));
        GameObject secondJoint = gw.addNewBeam(new DynamicJointGO(gw,newBeam.getBody().getPositionX()-(distance /2), newBeam.getBody().getPositionY(),1.0f,1.0f));
        gw.addNewBeamJoint(new MyRevoluteJoint(gw, newBeam, firstJoint, distance /2,0,0,0,false).getJoint());
        gw.addNewBeamJoint(new MyRevoluteJoint(gw, newBeam, secondJoint, -distance /2,0,0,0, false).getJoint());

        Vec2 localPointLastTouchedGO = touchedGO.getBody().getLocalPoint(new Vec2(endPoint.getX(), endPoint.getY()));
        Vec2 localPointFirstTouchedGO = firstTouchedGO.getBody().getLocalPoint(new Vec2(startingPoint.getX(), startingPoint.getY()));

        gw.addNewBeamJoint(new MyRevoluteJoint(gw, firstJoint, touchedGO,0,0,localPointLastTouchedGO.getX(),localPointLastTouchedGO.getY(), false).getJoint());
        gw.addNewBeamJoint(new MyRevoluteJoint(gw, secondJoint, firstTouchedGO, 0,0, localPointFirstTouchedGO.getX(), localPointFirstTouchedGO.getY(), false).getJoint());

        gw.setBudget(gw.getBudget()- price);
    }

    public int getPrice(float distance){
        float beamStandardPrice = gw.getBeamPrice();
        float beamStandardLength = 5.0f;

        return (int) ((beamStandardPrice*distance)/beamStandardLength);
    }

    private boolean isExitButtonPressed(Input.TouchEvent event) {
        return 2198 < event.x && event.x < 2300 && 93 < event.y && event.y < 194;
    }

    private boolean isResetButtonAreaPressed(Input.TouchEvent event) {
        return 280 < event.x && event.x < 386 && 93 < event.y && event.y < 194 && gw.isPlayButtonPressed() == false;
    }

    private boolean isPlayButtonAreaPressed(Input.TouchEvent event) {
        return 93 < event.x && event.x < 194 && 93 < event.y && event.y < 194 && gw.isPlayButtonPressed() == false;
    }




    private void consumeTouchMove(Input.TouchEvent event) {
        float x = gw.toMetersX(event.x), y = gw.toMetersY(event.y);
        endPoint.setX(x);
        endPoint.setY(y);

    }

    private boolean isNewBeamPossible(float price) {
        return gw.getBudget() >= price && gw.isPlayButtonPressed() == false;
    }
    private void getAABB(float x, float y) {
        p1.setX(x - POINTER_SIZE);
        p1.setY(y - POINTER_SIZE);
        p2.setX(x + POINTER_SIZE);
        p2.setY(y + POINTER_SIZE);
        aabb.setLowerBound(p1);
        aabb.setUpperBound(p2);
    }

    private static boolean isTouchedElementValid(GameObject touchedGO) {
        return touchedGO.getName().contains("BEAM") || touchedGO.getName().contains("ROAD") || touchedGO.getName().contains("JOINT") || touchedGO.getName().contains("Terrain");
    }

}
