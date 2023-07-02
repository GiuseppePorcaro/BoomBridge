package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.R;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.Pair;
import com.google.fpl.liquidfun.PolygonShape;

import java.util.concurrent.ThreadLocalRandom;

public class StaticTreeGO extends GameObject{

    private Canvas canvas;
    private Bitmap bitmap;
    private int instances = 0;
    private final RectF dest = new RectF();

    private Paint paint = new Paint();

    public StaticTreeGO(GameWorld gw,float x, float y, float width, float heigth) {
        super(gw);
        this.width = width;
        this.height = heigth;
        this.screen_semi_width = gw.toPixelsXLength(width)/2;
        this.screen_semi_height = gw.toPixelsYLength(height)/2;

        instances++;
        canvas = new Canvas(gw.getBuffer());

        paint.setARGB(255,255,0,0);


        BodyDef bodyDef = createBodyDef(gw,x,y);
        PolygonShape box = createPolygonShape();
        FixtureDef fixtureDef = createFixtureDef(box);
        body.createFixture(fixtureDef);

        setTree();

        bodyDef.delete();
        box.delete();
        fixtureDef.delete();

    }

    private void setTree(){
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;
        int randomTree = ThreadLocalRandom.current().nextInt(1, 4 + 1);
        switch (randomTree){
            case 1:
                bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.albero1, o);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.albero2, o);
                break;
            case 3:
                bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.albero3, o);
                break;
            case 4:
                bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.albero1, o);
                break;
            default:
                bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.albero1, o);
        }
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
        fixturedef.setIsSensor(true);
        fixturedef.setFriction(0.1f);       // default 0.2
        fixturedef.setRestitution(0.4f);    // default 0
        fixturedef.setDensity(0.0f);     // default 0. Density is used to compute the mass properties of the parent body
        return fixturedef;
    }

    @NonNull
    private BodyDef createBodyDef(GameWorld gw, float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.setPosition(x, y);
        bdef.setType(BodyType.staticBody);
        this.body = gw.getWorld().createBody(bdef);
        body.setSleepingAllowed(false);
        this.name = "Tree" + instances;
        body.setUserData(this);
        return bdef;
    }

    @Override
    public void draw(Bitmap buffer, float x, float y, float angle) {
        canvas.save();
        canvas.rotate((float) Math.toDegrees(angle), x, y);
        dest.left = x - screen_semi_width;
        dest.bottom = y + screen_semi_height;
        dest.right = x + screen_semi_width;
        dest.top = y - screen_semi_height;
        // Sprite
        canvas.drawBitmap(bitmap, null, dest, null);
        // Simple box
        //canvas.drawRect(x- screen_semi_width, y- screen_semi_height, x + screen_semi_width, y + screen_semi_height, paint);
        canvas.restore();
    }


}
