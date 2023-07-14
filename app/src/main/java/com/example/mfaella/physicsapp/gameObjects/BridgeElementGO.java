package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.SparseArray;

import androidx.annotation.NonNull;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.R;
import com.example.mfaella.physicsapp.joints.MyDistanceJoint;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.CircleShape;
import com.google.fpl.liquidfun.Color;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.Joint;
import com.google.fpl.liquidfun.PolygonShape;


public class BridgeElementGO extends GameObject{

    private BridgeElementType bridgeElementType;
    private final float density  = 0.5f, friction = 0.1f, restitution = 0.2f;

    private static int instances = 0;
    private final Canvas canvas;
    private final Paint paint = new Paint();
    private Bitmap bitmap;
    private final RectF dest = new RectF();
    private float initAngle;

    public BridgeElementGO(GameWorld gw, BridgeElementType bridgeElementType, float x, float y,float initAngle, float width,float height) {
        super(gw);
        this.bridgeElementType = bridgeElementType;
        this.width = width;
        this.height = height;
        this.initAngle = initAngle;

        instances++;
        this.canvas = new Canvas(gw.getBuffer());
        this.screen_semi_width = gw.toPixelsXLength(width)/2;
        this.screen_semi_height = gw.toPixelsYLength(height)/2;

        BodyDef bodyDef = createBodyDef(gw,x,y);
        PolygonShape polygon = createPolygonShape();
        FixtureDef fixtureDef = createFixtureDef(polygon);
        body.createFixture(fixtureDef);

        deleteAllObjects(bodyDef, polygon, fixtureDef);
        setElementImage(bridgeElementType);

    }


    @Override
    public void draw(Bitmap buf, float x, float y, float angle) {
        canvas.save();

        this.posX = x;
        this.posY = y;

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        canvas.rotate((float) Math.toDegrees(angle), x, y);
        dest.left = x - screen_semi_width;
        dest.bottom = y + screen_semi_height;
        dest.right = x + screen_semi_width;
        dest.top = y - screen_semi_height;

        canvas.drawBitmap(bitmap, null, dest, null);

        canvas.restore();
    }

    @NonNull
    private BodyDef createBodyDef(GameWorld gw, float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.setPosition(x, y);
        bdef.setAngle((float) Math.toRadians(initAngle));
        bdef.setType(BodyType.dynamicBody);
        this.body = gw.getWorld().createBody(bdef);
        body.setSleepingAllowed(false);
        this.name = bridgeElementType + " N ° "+instances;
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
        fixturedef.setFriction(friction);
        fixturedef.setRestitution(restitution);
        fixturedef.setDensity(density);
        return fixturedef;
    }

    private void setElementImage(BridgeElementType bridgeElementType) {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;
        o.inMutable = true;
        switch (bridgeElementType){
            case ROAD:
                bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.road, o);
                break;
            case BEAM:
                bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.trave, o);
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

    @Override
    public void delete() {
        bitmap.eraseColor(0);
    }
}
