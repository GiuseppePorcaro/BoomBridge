package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.joints.MyDistanceJoint;
import com.google.fpl.liquidfun.Body;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.CircleShape;
import com.google.fpl.liquidfun.FixtureDef;

public class BridgeJointElementGO extends GameObject{

    private Canvas canvas;
    private Body body;
    private static int instances = 0;

    public BridgeJointElementGO(GameWorld gw, float x, float y, GameObject a, GameObject b) {
        super(gw);

        this.canvas = new Canvas(gw.getBuffer());
        instances++;


        createBodyDef(gw,x,y);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(1.0f);
        FixtureDef fixtureDef = createFixtureDefJoint();
        body.createFixture(fixtureDef);

        fixtureDef.delete();
        circleShape.delete();

        float aPositionX = a.body.getPositionX();
        float aPositionY = a.body.getPositionY();
        float bPositionX = b.body.getPositionX();
        float bPositionY = b.body.getPositionY();

        //new MyDistanceJoint(gw, a.body,this.body,aPositionX+a.width/2,0,0,0);
        //new MyDistanceJoint(gw,this.body,b.body,0,0,bPositionX-b.width/2,0);

    }

    private void createBodyDef(GameWorld gw, float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.setPosition(x,y);
        bdef.setType(BodyType.dynamicBody);
        this.body = gw.getWorld().createBody(bdef);
        body.setSleepingAllowed(false);
        this.name =  "Circle Joint N° "+instances;
        body.setUserData(this);
    }

    private FixtureDef createFixtureDefJoint() {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(5f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.setShape(circleShape);
        fixtureDef.setDensity(0.0f);
        fixtureDef.setFriction(0.0f);
        fixtureDef.setRestitution(0.0f);

        return fixtureDef;
    }


    @Override
    public void draw(Bitmap buf, float x, float y, float angle) {
        canvas.save();
        Paint paint = new Paint();
        paint.setARGB(255,0,0,255);

        canvas.drawCircle(x,y,50.0f,paint);
        canvas.restore();
    }

    @Override
    public void delete() {

    }

}
