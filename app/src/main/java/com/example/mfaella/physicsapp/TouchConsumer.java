package com.example.mfaella.physicsapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.badlogic.androidgames.framework.Input;
import com.example.mfaella.physicsapp.gameObjects.BombGO;
import com.example.mfaella.physicsapp.gameObjects.BridgeElementGO;
import com.example.mfaella.physicsapp.gameObjects.BridgeElementType;
import com.example.mfaella.physicsapp.gameObjects.DynamicBoxGO;
import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.example.mfaella.physicsapp.joints.MyRevoluteJoint;
import com.google.fpl.liquidfun.AABB;
import com.google.fpl.liquidfun.Body;
import com.google.fpl.liquidfun.DistanceInput;
import com.google.fpl.liquidfun.Fixture;
import com.google.fpl.liquidfun.MouseJoint;
import com.google.fpl.liquidfun.MouseJointDef;
import com.google.fpl.liquidfun.QueryCallback;
import com.google.fpl.liquidfun.Vec2;

import java.util.Vector;

/**
 * Takes care of user interaction: pulls objects using a Mouse Joint.
 */
public class TouchConsumer {

    // keep track of what we are dragging
    private MouseJoint mouseJoint;
    private int activePointerID;
    private Fixture touchedFixture;

    private GameWorld gw;
    private QueryCallback touchQueryCallback = new TouchQueryCallback();

    private Vec2 p1;
    private Vec2 p2;

    private AABB aabb;

    //------
    private Vec2 startingPoint = new Vec2();
    private Vec2 endPoint = new Vec2();
    private Vec2 dxy;
    private GameObject firstTouchedGO = null;
    //------

    // physical units, semi-side of a square around the touch point
    private final static float POINTER_SIZE = 0.5f;

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
        int pointerId = event.pointer;

        float x = gw.toMetersX(event.x), y = gw.toMetersY(event.y);


        touchedFixture = null;
        if(gw.getBudget() > gw.getBeamPrice() && gw.isPlayButtonPressed() == false){
            p1.setX(x - POINTER_SIZE); p1.setY(y - POINTER_SIZE);
            p2.setX(x + POINTER_SIZE); p2.setY(y + POINTER_SIZE);
            aabb.setLowerBound(p1); aabb.setUpperBound(p2);
            gw.getWorld().queryAABB(touchQueryCallback, aabb);
            if (touchedFixture != null) {
                Body touchedBody = touchedFixture.getBody();
                Object userData = touchedBody.getUserData();
                if (userData != null) {
                    GameObject touchedGO = (GameObject) userData;
                    activePointerID = pointerId;
                    if(touchedGO.getName().contains("BEAM")  || touchedGO.getName().contains("ROAD")){
                        startingPoint.setX(x);
                        startingPoint.setY(y);
                        firstTouchedGO = touchedGO;
                    }
                }
            }
        }
    }

    private void consumeTouchUp(Input.TouchEvent event) {

        float x = gw.toMetersX(event.x), y = gw.toMetersY(event.y);
        if(gw.getBudget() > gw.getBeamPrice() && gw.isPlayButtonPressed() == false){
            endPoint.setX(x);
            endPoint.setY(y);

            dxy.setX(endPoint.getX() - startingPoint.getX());
            dxy.setY(endPoint.getY() - startingPoint.getY());
            touchedFixture = null;

            p1.setX(x - POINTER_SIZE); p1.setY(y - POINTER_SIZE);
            p2.setX(x + POINTER_SIZE); p2.setY(y + POINTER_SIZE);
            aabb.setLowerBound(p1); aabb.setUpperBound(p2);

            gw.getWorld().queryAABB(touchQueryCallback, aabb);
            if (touchedFixture != null) {
                Body touchedBody = touchedFixture.getBody();
                Object userData = touchedBody.getUserData();
                if (userData != null) {
                    GameObject touchedGO = (GameObject) userData;
                    if(touchedGO.getName().contains("BEAM") || touchedGO.getName().contains("ROAD")){
                        addNewBeam(touchedGO);
                        gw.setBudget(gw.getBudget()-gw.getBeamPrice());
                    }
                }
            }
        }

        //controllo start button
        if (93 < event.x && event.x < 194 && 93 < event.y && event.y < 194 && gw.isPlayButtonPressed() == false) {
            Body body = gw.getWorld().getBodyList();
            gw.setPlayButtonPressed(true);
            while(body != null){
                GameObject g = (GameObject) body.getUserData();
                System.out.println(g.getName());
                if(g.getName().contains("Bomb")){
                    BombGO.denotaneBomb(gw,g.getBody().getPositionX(),g.getBody().getPositionY());
                }
                body = body.getNext();
            }
        }
    }

    private void addNewBeam(GameObject touchedGO) {
        float bodyX = ((startingPoint.getX() + endPoint.getX())/2);
        float bodyY = ((startingPoint.getY() + endPoint.getY())/2);
        float distance = (float) Math.sqrt(Math.pow(endPoint.getX()-startingPoint.getX(),2)+Math.pow(endPoint.getY()- startingPoint.getY(),2 ));

        GameObject newBeam = gw.addGameObject(new BridgeElementGO(gw,100, BridgeElementType.BEAM, bodyX, bodyY, (float) Math.toDegrees(Math.atan2(dxy.getY(),dxy.getX())),0.01f,0.01f,0.01f,distance,0.5f));
        GameObject firstJoint = gw.addGameObject(new DynamicBoxGO(gw, newBeam.getBody().getPositionX()+(distance/2), newBeam.getBody().getPositionY(),1.0f,1.0f));
        GameObject secondJoint = gw.addGameObject(new DynamicBoxGO(gw,newBeam.getBody().getPositionX()-(distance/2), newBeam.getBody().getPositionY(),1.0f,1.0f));
        new MyRevoluteJoint(gw, newBeam, firstJoint, distance/2,0,0,0,false);
        new MyRevoluteJoint(gw, newBeam, secondJoint, -distance/2,0,0,0, false);

        Vec2 localPointLastTouchedGO = touchedGO.getBody().getLocalPoint(new Vec2(endPoint.getX(), endPoint.getY()));
        Vec2 localPointFirstTouchedGO = firstTouchedGO.getBody().getLocalPoint(new Vec2(startingPoint.getX(), startingPoint.getY()));

        new MyRevoluteJoint(gw, firstJoint, touchedGO,0,0,localPointLastTouchedGO.getX(),localPointLastTouchedGO.getY(), false);
        new MyRevoluteJoint(gw, secondJoint, firstTouchedGO, 0,0, localPointFirstTouchedGO.getX(), localPointFirstTouchedGO.getY(), false);

        firstTouchedGO = null;
    }

    private void consumeTouchMove(Input.TouchEvent event) {
        float x = gw.toMetersX(event.x), y = gw.toMetersY(event.y);
        endPoint.setX(x);
        endPoint.setY(y);

    }
}
