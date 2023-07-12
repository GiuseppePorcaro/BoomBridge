package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.R;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.Filter;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.PolygonShape;

public class TerroristGO extends GameObject{

    private Bitmap[] frames;
    private Canvas canvas;
    private Paint paint;

    BitmapFactory.Options o;

    RectF dest;



    public TerroristGO(GameWorld gw, float x, float y) {
        super(gw);

        canvas = new Canvas(gw.getBuffer());
        dest = new RectF();
        paint = new Paint();
        paint.setARGB(255,255,0,0);
        paint.setStyle(Paint.Style.STROKE);
        this.width = 2.5f;
        this.height = 2.5f;
        this.screen_semi_width = gw.toPixelsXLength(width)/2;
        this.screen_semi_height = gw.toPixelsYLength(height)/2;

        BodyDef bodyDef = createBodyDef(gw,x,y);
        PolygonShape bombShape = createPolygonShape();
        FixtureDef fixtureDef = createFixtureDef(bombShape);
        body.createFixture(fixtureDef);

        o = new BitmapFactory.Options();
        o.inScaled = false;
        o.inMutable = true;

        bodyDef.delete();
        fixtureDef.delete();
        bombShape.delete();

    }

    @Override
    public void draw(Bitmap buf, float x, float y, float angle) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.terrorist_idle_000, o);

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
        fixturedef.setFriction(0.5f);
        fixturedef.setRestitution(0.0f);
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
        this.name = "Terrorist";
        body.setUserData(this);
        return bdef;
    }


    @Override
    public void delete() {
        //nothing to do
    }
}
