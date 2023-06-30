package com.example.mfaella.physicsapp.joints;

import com.example.mfaella.physicsapp.GameWorld;
import com.google.fpl.liquidfun.Body;
import com.google.fpl.liquidfun.DistanceJointDef;
import com.google.fpl.liquidfun.Joint;
import com.google.fpl.liquidfun.RopeJoint;
import com.google.fpl.liquidfun.RopeJointDef;
import com.google.fpl.liquidfun.Vec2;

public class MyDistanceJoint {

    Joint joint;

    public MyDistanceJoint(GameWorld gw, Body a, Body b)
    {
        RopeJointDef ropeJointDef = new RopeJointDef();

        ropeJointDef.setBodyA(a);
        ropeJointDef.setBodyB(b);
        ropeJointDef.setMaxLength(5);

        Vec2 p = new Vec2(a.getPositionX(), a.getPositionY());
        Vec2 p2 = new Vec2(b.getPositionX(), b.getPositionY());

        ropeJointDef.setLocalAnchorA(p);
        ropeJointDef.setLocalAnchorB(p2);

        joint = gw.getWorld().createJoint(ropeJointDef);
        ropeJointDef.delete();
    }
}
