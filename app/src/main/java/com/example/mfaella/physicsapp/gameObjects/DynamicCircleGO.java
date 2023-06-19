package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.mfaella.physicsapp.GameWorld;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.CircleShape;
import com.google.fpl.liquidfun.Fixture;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.Shape;

public class DynamicCircleGO extends GameObject {

    private static float screen_semi_width;
    private final Canvas canvas;
    private final Paint paint = new Paint();
    private static final float density = 0.5f;
    private int instances = 0;

    private CircleShape circle;

    public DynamicCircleGO(GameWorld gw, float x, float y) {
        super(gw);

        instances++;

        canvas = new Canvas(gw.getBuffer());
        this.screen_semi_width = gw.toPixelsXLength(width)/2;
        this.screen_semi_height = gw.toPixelsYLength(height)/2;

        //definisco il body dell'oggetto
        BodyDef bodyDef = new BodyDef();
        bodyDef.setPosition(x,y);
        bodyDef.setType(BodyType.staticBody);

        //Creo il body dell'oggetto nel mondo
        this.body = gw.getWorld().createBody(bodyDef);
        body.setSleepingAllowed(false);
        this.name = "Cerchio" + instances;
        body.setUserData(this);


        circle = new CircleShape();
        circle.setPosition(x,y);

        FixtureDef fixturedef = new FixtureDef();
        fixturedef.setShape(circle);
        fixturedef.setFriction(0.1f);       // default 0.2
        fixturedef.setRestitution(0.6f);    // default 0
        fixturedef.setDensity(1.0f);     // default 0
        body.createFixture(fixturedef);



        int color = Color.argb(200, 255, 0, 0);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        // clean up native objects


    }

    public CircleShape getShape() {
        return circle;
    }

    @Override
    public void draw(Bitmap buffer, float x, float y, float angle) {
        canvas.save();

        this.posX = x;
        this.posY = y;
        canvas.drawCircle(x,y,5,paint);

        canvas.restore();
    }
}
