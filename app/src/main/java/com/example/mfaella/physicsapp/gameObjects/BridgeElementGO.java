package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.joints.MyDistanceJoint;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.PolygonShape;


public class BridgeElementGO extends GameObject{

    private int price;
    private BridgeElementType bridgeElementType;
    private float density, friction, restitution;

    private static final float width = 4.5f, height = 0.5f;
    private static float screen_semi_width, screen_semi_height;
    private static int instances = 0;

    private final Canvas canvas;
    private final Paint paint = new Paint();
    private Bitmap bitmap;

    public BridgeElementGO(GameWorld gw, int price, BridgeElementType bridgeElementType, float x, float y, float density, float friction, float restitution) {
        super(gw);
        this.price = price;
        this.bridgeElementType = bridgeElementType;
        this.density = density;
        this.friction = friction;
        this.restitution = restitution;
        setElementImage(bridgeElementType);

        instances++;
        this.canvas = new Canvas(gw.getBuffer());
        this.screen_semi_width = gw.toPixelsXLength(width)/2;
        this.screen_semi_height = gw.toPixelsYLength(height)/2;

        BodyDef bodyDef = createBodyDef(gw,x,y);
        PolygonShape polygon = createPolygonShape();
        FixtureDef fixtureDef = createFixtureDef(polygon);
        body.createFixture(fixtureDef);

        // clean up native objects
        deleteAllObjects(bodyDef, polygon, fixtureDef);

    }


    @Override
    public void draw(Bitmap buf, float x, float y, float angle) {
        canvas.save();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        // Sprite
        //canvas.drawBitmap(bitmap, src, dest, null);
        canvas.rotate((float) Math.toDegrees(angle), x, y);
        canvas.drawRect(x- screen_semi_width, y- screen_semi_height, x + screen_semi_width, y + screen_semi_height, paint);

        canvas.restore();
    }

    @NonNull
    private BodyDef createBodyDef(GameWorld gw, float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.setPosition(x, y);
        bdef.setType(BodyType.dynamicBody);
        this.body = gw.getWorld().createBody(bdef);
        body.setSleepingAllowed(false);
        this.name = bridgeElementType + "N° "+instances;
        body.setUserData(this);
        return bdef;
    }

    @NonNull
    private PolygonShape createPolygonShape() {
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        return box;
    }

    @NonNull
    private FixtureDef createFixtureDef(PolygonShape box) {
        FixtureDef fixturedef = new FixtureDef();
        fixturedef.setShape(box);
        fixturedef.setFriction(friction);       // default 0.2
        fixturedef.setRestitution(restitution);    // default 0
        fixturedef.setDensity(density);     // default 0. Density is used to compute the mass properties of the parent body
        return fixturedef;
    }

    private void setElementImage(BridgeElementType bridgeElementType) {
        switch (bridgeElementType){
            case ROAD:
                paint.setARGB(255,255,0,0);
                break;
            case BEAM:
                paint.setARGB(255,204,204,0);
                break;
            default:
                paint.setARGB(255,0,0,0);
                break;
        }
    }

    private static void deleteAllObjects(BodyDef bodyDef, PolygonShape polygon, FixtureDef fixtureDef) {
        fixtureDef.delete();
        bodyDef.delete();
        polygon.delete();

    }

}
