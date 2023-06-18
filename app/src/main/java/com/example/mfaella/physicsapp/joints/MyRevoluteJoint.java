package com.example.mfaella.physicsapp.joints;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.google.fpl.liquidfun.Body;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.Joint;
import com.google.fpl.liquidfun.PolygonShape;
import com.google.fpl.liquidfun.RevoluteJointDef;
import com.google.fpl.liquidfun.Vec2;

/**
 *
 * Created by mfaella on 27/02/16.
 */
public class MyRevoluteJoint
{
    Joint joint;

    public MyRevoluteJoint(GameWorld gw, GameObject a, GameObject b)
    {
        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.setBodyA(a.getBody());
        jointDef.setBodyB(b.getBody());
        jointDef.setLocalAnchorA(a.getPosX()+(b.getWidth()/2)+a.getWidth()/2, a.getPosY()-a.getHeight()/2);
        jointDef.setLocalAnchorB(b.getPosX()-b.getWidth(), b.getPosY()-b.getHeight());
        // add friction
        jointDef.setEnableMotor(true);
        jointDef.setMotorSpeed(1.5f);
        jointDef.setMaxMotorTorque(80f);
        joint = gw.getWorld().createJoint(jointDef);

        jointDef.delete();
    }
}
