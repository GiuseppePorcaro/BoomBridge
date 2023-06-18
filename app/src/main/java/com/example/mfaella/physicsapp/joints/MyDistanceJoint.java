package com.example.mfaella.physicsapp.joints;

import com.example.mfaella.physicsapp.GameWorld;
import com.google.fpl.liquidfun.Body;
import com.google.fpl.liquidfun.DistanceJointDef;
import com.google.fpl.liquidfun.Joint;
import com.google.fpl.liquidfun.RopeJoint;
import com.google.fpl.liquidfun.RopeJointDef;

public class MyDistanceJoint {

    Joint joint;

    public MyDistanceJoint(GameWorld gw, Body a, Body b)
    {
        RopeJointDef ropeJointDef = new RopeJointDef();

        ropeJointDef.setBodyA(a);
        ropeJointDef.setBodyB(b);
        ropeJointDef.setMaxLength(5);

        ropeJointDef.setLocalAnchorA(a.getPositionX(), a.getPositionY());
        ropeJointDef.setLocalAnchorB(b.getPositionX(), b.getPositionY());

        joint = gw.getWorld().createJoint(ropeJointDef);
        ropeJointDef.delete();
    }
}
