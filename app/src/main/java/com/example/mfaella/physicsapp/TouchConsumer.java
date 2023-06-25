package com.example.mfaella.physicsapp;

import android.util.Log;

import com.badlogic.androidgames.framework.Input;
import com.example.mfaella.physicsapp.gameObjects.DynamicBoxGO;
import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.google.fpl.liquidfun.AABB;
import com.google.fpl.liquidfun.Body;
import com.google.fpl.liquidfun.Fixture;
import com.google.fpl.liquidfun.Joint;
import com.google.fpl.liquidfun.MouseJoint;
import com.google.fpl.liquidfun.MouseJointDef;
import com.google.fpl.liquidfun.QueryCallback;
<<<<<<< Updated upstream
import com.google.fpl.liquidfun.SWIGTYPE_p_b2AABB;
=======
import com.google.fpl.liquidfun.Vec2;
>>>>>>> Stashed changes

/**
 * Takes care of user interaction: pulls objects using a Mouse Joint.
 */
public class TouchConsumer {

    // keep track of what we are dragging
    private MouseJoint mouseJoint;
    private int activePointerID;
    private Fixture touchedFixture;

    private GameWorld gw;
    private QueryCallback touchQueryCallback = new TouchQueryCallback(gw.getWorld().,true);

    // physical units, semi-side of a square around the touch point
    private final static float POINTER_SIZE = 0.5f;

    private class TouchQueryCallback extends QueryCallback {


        protected TouchQueryCallback(long cPtr, boolean cMemoryOwn) {
            super(cPtr, cMemoryOwn);
        }

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

        // if we are already dragging with another finger, discard this event
        if (mouseJoint != null) return;

        float x = gw.toMetersX(event.x), y = gw.toMetersY(event.y);

        Log.d("MultiTouchHandler", "touch down at " + x + ", " + y);

        touchedFixture = null;
<<<<<<< Updated upstream
        SWIGTYPE_p_b2AABB aabb = new SWIGTYPE_p_b2AABB();
        gw.getWorld().queryAABB(touchQueryCallback, aabb);
        if (touchedFixture != null) {
=======
        AABB aabb = new AABB();

        Vec2 p1 = new Vec2(x - POINTER_SIZE, y - POINTER_SIZE);
        Vec2 p2 = new Vec2(x + POINTER_SIZE, y + POINTER_SIZE);
        aabb.setLowerBound(p1);
        aabb.setUpperBound(p2);
        gw.getWorld().queryAABB(touchQueryCallback, aabb);
        /*if (touchedFixture != null) {
>>>>>>> Stashed changes
            // From fixture to GO
            Body touchedBody = touchedFixture.getBody();
            Object userData = touchedBody.getUserData();
            if (userData != null) {
                GameObject touchedGO = (GameObject) userData;
                activePointerID = pointerId;
                Log.d("MultiTouchHandler", "touched game object " + touchedGO.getName());
                setupMouseJoint(x, y, touchedBody);
                // splitBox(touchedGO, touchedBody);
            }
        }*/
    }

    // If a DynamicBox is touched, it splits into two
    private void splitBox(GameObject touchedGO, Body touchedBody) {
        if (touchedGO instanceof DynamicBoxGO) {
            gw.getWorld().destroyBody(touchedBody);
            gw.objects.remove(touchedGO);
            gw.addGameObject(new DynamicBoxGO(gw, touchedBody.getPositionX(), touchedBody.getPositionY()));
            gw.addGameObject(new DynamicBoxGO(gw, touchedBody.getPositionX(), touchedBody.getPositionY()));
        }
    }

    // Set up a mouse joint between the touched GameObject and the touch coordinates (x,y)
    private void setupMouseJoint(float x, float y, Body touchedBody) {
        MouseJointDef mouseJointDef = new MouseJointDef();
        mouseJointDef.setBodyA(touchedBody); // irrelevant but necessary
        mouseJointDef.setBodyB(touchedBody);
        mouseJointDef.setMaxForce(500 * touchedBody.getMass());
<<<<<<< Updated upstream
        //mouseJointDef.setTarget(x, y);
        //mouseJoint = gw.getWorld().createMouseJoint(mouseJointDef);
=======

        Vec2 p1 = new Vec2(x, y);
        mouseJointDef.setTarget(p1);
        mouseJoint = (MouseJoint) gw.getWorld().createJoint(mouseJointDef);
>>>>>>> Stashed changes
    }

    private void consumeTouchUp(Input.TouchEvent event) {
        if (mouseJoint != null && event.pointer == activePointerID) {
            Log.d("MultiTouchHandler", "Releasing joint");
            gw.getWorld().destroyJoint(mouseJoint);
            mouseJoint = null;
            activePointerID = 0;
        }
    }

    private void consumeTouchMove(Input.TouchEvent event) {
        float x = gw.toMetersX(event.x), y = gw.toMetersY(event.y);
        if (mouseJoint!=null && event.pointer == activePointerID) {
            Log.d("MultiTouchHandler", "active pointer moved to " + x + ", " + y);
<<<<<<< Updated upstream
            //mouseJoint.setTarget(x, y);
=======
            Vec2 p1 = new Vec2(x, y);
            mouseJoint.setTarget(p1);
>>>>>>> Stashed changes
        }
    }
}
