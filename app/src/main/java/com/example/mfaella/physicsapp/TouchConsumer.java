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

    //------
    private Vec2 startingPoint = new Vec2();
    private Vec2 endPoint = new Vec2();
    private Vec2 dxy;
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

    /**
        scale{X,Y} are the scale factors from pixels to physics simulation coordinates
    */
    public TouchConsumer(GameWorld gw) {
        this.gw = gw;
        dxy = new Vec2();

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
        Vec2 p1 = new Vec2(x - POINTER_SIZE, y - POINTER_SIZE);
        Vec2 p2 = new Vec2(x + POINTER_SIZE, y + POINTER_SIZE);
        AABB aabb = new AABB();
        aabb.setLowerBound(p1);
        aabb.setUpperBound(p2);
        gw.getWorld().queryAABB(touchQueryCallback, aabb);
        if (touchedFixture != null) {
            // From fixture to GO
            Body touchedBody = touchedFixture.getBody();
            Object userData = touchedBody.getUserData();
            if (userData != null) {
                GameObject touchedGO = (GameObject) userData;
                activePointerID = pointerId;
                if(touchedGO.getName().contains("BEAM")){
                    startingPoint.setX(x);
                    startingPoint.setY(y);
                }
            }
        }
    }

    // If a DynamicBox is touched, it splits into two
    /*private void splitBox(GameObject touchedGO, Body touchedBody) {
        if (touchedGO instanceof DynamicBoxGO) {
            gw.getWorld().destroyBody(touchedBody);
            gw.objects.remove(touchedGO);
            gw.addGameObject(new DynamicBoxGO(gw, touchedBody.getPositionX(), touchedBody.getPositionY()));
            gw.addGameObject(new DynamicBoxGO(gw, touchedBody.getPositionX(), touchedBody.getPositionY()));
        }
    }*/

    // Set up a mouse joint between the touched GameObject and the touch coordinates (x,y)
    //Probably to remove
    private void setupMouseJoint(float x, float y, Body touchedBody) {
        MouseJointDef mouseJointDef = new MouseJointDef();
        mouseJointDef.setBodyA(touchedBody); // irrelevant but necessary
        mouseJointDef.setBodyB(touchedBody);
        mouseJointDef.setMaxForce(500 * touchedBody.getMass());

        Vec2 p = new Vec2(x, y);
        mouseJointDef.setTarget(p);
        mouseJoint = gw.getWorld().createMouseJoint(mouseJointDef);
    }

    private void consumeTouchUp(Input.TouchEvent event) {

        float x = gw.toMetersX(event.x), y = gw.toMetersY(event.y);
        touchedFixture = null;
        Vec2 p1 = new Vec2(x - POINTER_SIZE, y - POINTER_SIZE);
        Vec2 p2 = new Vec2(x + POINTER_SIZE, y + POINTER_SIZE);
        AABB aabb = new AABB();
        aabb.setLowerBound(p1);
        aabb.setUpperBound(p2);
        gw.getWorld().queryAABB(touchQueryCallback, aabb);
        if (touchedFixture != null) {
            Body touchedBody = touchedFixture.getBody();
            Object userData = touchedBody.getUserData();
            if (userData != null) {
                GameObject touchedGO = (GameObject) userData;
                if(touchedGO.getName().contains("BEAM")){


                    //BISOGNA CONTROLLARE I CALCOLI DATO CHE I BEAM VENGONO MESSI UN PO' A CAZZO DI CANE
                    //UNA PRIMA COSA CHE POTREI PROVARE è DISEGNARE UN BEAM SENZA CHE PRIMA SI CLICCHI SU UN ALTRO BEAM E SI RILASCI SU UN ALTRO BEAM. QUINDI PROVARE A CREARNE 1 IN MODO LIBERO
                    float bodyX = (startingPoint.getX() + endPoint.getX()/2);
                    float bodyY = (startingPoint.getY() + endPoint.getY()/2);
                    float distance = (float) Math.sqrt(Math.pow(endPoint.getX()-startingPoint.getX(),2)+Math.pow(endPoint.getY()- startingPoint.getY(),2 ));
                    GameObject newBeam = gw.addGameObject(new BridgeElementGO(gw,100, BridgeElementType.BEAM, bodyX, bodyY, (float) Math.atan2(dxy.getY(),dxy.getX()),0.01f,0.01f,0.01f,distance,0.5f));
                    GameObject g = null;
                    g = gw.addGameObject(new DynamicBoxGO(gw, newBeam.getBody().getPositionX()+(distance/2), newBeam.getBody().getPositionY()-0.5f/2,1.0f,1.0f));
                    new MyRevoluteJoint(gw, newBeam, g, distance/2,0,0,0,false);
                    new MyRevoluteJoint(gw, touchedGO, g,5.0f ,0,0,0,false);

                }
            }
        }

        //controllo start button
        if (93 < event.x && event.x < 194 && 93 < event.y && event.y < 194 && GameWorld.isPlayButtonPressed == false) {
            Body body = gw.getWorld().getBodyList();
            GameWorld.isPlayButtonPressed = true;
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

    private void consumeTouchMove(Input.TouchEvent event) {
        float x = gw.toMetersX(event.x), y = gw.toMetersY(event.y);

        endPoint.setX(x);
        endPoint.setY(y);

        dxy.setX(endPoint.getX() - startingPoint.getX());
        dxy.setY(endPoint.getY() - startingPoint.getY());

    }
}
