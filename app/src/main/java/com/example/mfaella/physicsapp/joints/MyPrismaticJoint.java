package com.example.mfaella.physicsapp.joints;

import com.example.mfaella.physicsapp.GameWorld;
import com.google.fpl.liquidfun.Body;
import com.google.fpl.liquidfun.Joint;
import com.google.fpl.liquidfun.PrismaticJointDef;
import com.google.fpl.liquidfun.RevoluteJointDef;
import com.google.fpl.liquidfun.Vec2;

/**
 *
 * Created by mfaella on 27/02/16.
 */
public class MyPrismaticJoint
{
    Joint joint;

    public MyPrismaticJoint(GameWorld gw, Body a, Body b)
    {
        PrismaticJointDef jointDef = new PrismaticJointDef();
        jointDef.setBodyA(a);
        jointDef.setBodyB(b);
        Vec2 p1 = new Vec2(-1f, -1f);
        Vec2 p2 = new Vec2(1f,1f);
        jointDef.setLocalAnchorA(p1);
        jointDef.setLocalAnchorB(p1);
        jointDef.setLocalAxisA(p2);
        // add friction
        jointDef.setEnableMotor(true);
        jointDef.setMotorSpeed(2f);
        jointDef.setMaxMotorForce(10f);
        joint = gw.getWorld().createJoint(jointDef);

        jointDef.delete();
    }
}
