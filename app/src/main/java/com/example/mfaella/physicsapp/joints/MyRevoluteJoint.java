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
import com.google.fpl.liquidfun.RevoluteJoint;
import com.google.fpl.liquidfun.RevoluteJointDef;
import com.google.fpl.liquidfun.Vec2;

public class MyRevoluteJoint
{
    Joint joint;

    public MyRevoluteJoint(GameWorld gw, GameObject firstBody, GameObject secondBody, float xA, float yA, float xB, float yB, boolean hasCollision)
    {

        Body a = firstBody.getBody();
        Body b = secondBody.getBody();

        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.setBodyA(a);
        jointDef.setBodyB(b);

        Vec2 p1 = new Vec2(xA,yA);
        Vec2 p2 = new Vec2(xB,yB);

        jointDef.setLocalAnchorA(p1);
        jointDef.setLocalAnchorB(p2);
        jointDef.setCollideConnected(hasCollision);
        jointDef.setEnableLimit(false);

        joint =gw.getWorld().createJoint(jointDef);

        jointDef.delete();
    }

    public Joint getJoint() {
        return joint;
    }
}
