package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.R;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.CircleShape;
import com.google.fpl.liquidfun.Color;
import com.google.fpl.liquidfun.Filter;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.PolygonShape;
import com.google.fpl.liquidfun.Vec2;

import java.util.ArrayList;

public class BombGO extends GameObject{

    private Canvas canvas;
    private Paint paint = new Paint();
    private Bitmap bitmap;
    private static final float blastPower = 420.0f;

    private static final int numRays = 36;
    private final RectF dest = new RectF();
    private static int instances = 0;

    public BombGO(GameWorld gw, float x, float y, float width, float heigth) {
        super(gw);

        canvas = new Canvas(gw.getBuffer());
        instances++;
        paint.setARGB(255,255,0,0);
        paint.setStyle(Paint.Style.STROKE);
        this.width = width;
        this.height = heigth;
        this.screen_semi_width = gw.toPixelsXLength(width)/2;
        this.screen_semi_height = gw.toPixelsYLength(height)/2;

        BodyDef bodyDef = createBodyDef(gw,x,y);
        PolygonShape bombShape = createPolygonShape();
        FixtureDef fixtureDef = createFixtureDef(bombShape);
        body.createFixture(fixtureDef);

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;
        o.inMutable = true;
        bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.bomb, o);

        bodyDef.delete();
        fixtureDef.delete();
        bombShape.delete();

    }

    public static void denotaneBomb(GameWorld gw, float x, float y){
        for (int i = 0; i < numRays; i++) {
            float angle = (float) (Math.toRadians((i / (float)numRays) * 360));
            Vec2 rayDir = new Vec2((float) Math.sin(angle), (float) Math.cos(angle));
            gw.addGameObject(new BombFragmentGO(gw,x,y,rayDir,blastPower,numRays));

        }
    }

    @Override
    public void draw(Bitmap buf, float x, float y, float angle) {
        canvas.save();
        canvas.rotate((float) Math.toDegrees(angle), x, y);
        dest.left = x - screen_semi_width;
        dest.bottom = y + screen_semi_height;
        dest.right = x + screen_semi_width;
        dest.top = y - screen_semi_height;
        canvas.drawBitmap(bitmap, null, dest, null);
        canvas.restore();
    }

    @NonNull
    private PolygonShape createPolygonShape() {
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        return box;
    }

    @NonNull
    private FixtureDef createFixtureDef(PolygonShape box) {

        Filter filter = new Filter();
        //filter.setGroupIndex((short) -1);
        FixtureDef fixturedef = new FixtureDef();
        fixturedef.setShape(box);
        //fixturedef.setIsSensor(true);
        fixturedef.setFriction(0.1f);
        fixturedef.setRestitution(0.4f);
        fixturedef.setDensity(0.5f);
        return fixturedef;
    }

    @NonNull
    private BodyDef createBodyDef(GameWorld gw, float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.setPosition(x, y);
        bdef.setType(BodyType.dynamicBody);
        this.body = gw.getWorld().createBody(bdef);
        body.setSleepingAllowed(false);
        this.name = "Bomb" + instances;
        body.setUserData(this);
        return bdef;
    }



    @Override
    public void delete() {
        bitmap.eraseColor(0);
    }
}
