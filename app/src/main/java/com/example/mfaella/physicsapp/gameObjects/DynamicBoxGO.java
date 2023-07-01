package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.R;
import com.example.mfaella.physicsapp.joints.MyDistanceJoint;
import com.example.mfaella.physicsapp.joints.MyRevoluteJoint;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.Fixture;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.PolygonShape;

/**
 * A moving box.
 *
 * Created by mfaella on 27/02/16.
 */
public class DynamicBoxGO extends GameObject
{
    private static final float width = 1.0f, height = 1.0f, density = 0.0f;
    private static float screen_semi_width, screen_semi_height;
    private static int instances = 0;

    private final Canvas canvas;
    private final Paint paint = new Paint();

    public DynamicBoxGO(GameWorld gw, float x, float y)
    {
        super(gw);

        instances++;

        this.canvas = new Canvas(gw.getBuffer()); // Is this needed?
        this.screen_semi_width = gw.toPixelsXLength(width)/2;
        this.screen_semi_height = gw.toPixelsYLength(height)/2;

        // a body definition: position and type
        BodyDef bdef = createBodyDef(gw, x, y);

        PolygonShape box = createPolygonShape();
        FixtureDef fixturedef = createFixtureDef(box);
        body.createFixture(fixturedef);

        int green = (int)(255*Math.random());
        int color = Color.argb(200, 255,0, 0);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        // clean up native objects
        fixturedef.delete();
        bdef.delete();
        box.delete();

        Fixture f = body.getFixtureList();



        // Prevents scaling
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;

        bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.joint, o);
    }

    @NonNull
    private static PolygonShape createPolygonShape() {
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        return box;
    }

    @NonNull
    private static FixtureDef createFixtureDef(PolygonShape box) {
        FixtureDef fixturedef = new FixtureDef();
        fixturedef.setShape(box);
        fixturedef.setIsSensor(true);
        fixturedef.setFriction(0.1f);       // default 0.2
        fixturedef.setRestitution(0.4f);    // default 0
        fixturedef.setDensity(density);     // default 0. Density is used to compute the mass properties of the parent body
        return fixturedef;
    }

    @NonNull
    private BodyDef createBodyDef(GameWorld gw, float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.setPosition(x, y);
        bdef.setType(BodyType.dynamicBody);
        this.body = gw.getWorld().createBody(bdef);
        body.setSleepingAllowed(false);
        this.name = "Box" + instances;
        body.setUserData(this);
        return bdef;
    }

    private final Rect src = new Rect();
    private final RectF dest = new RectF();
    private Bitmap bitmap;

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
