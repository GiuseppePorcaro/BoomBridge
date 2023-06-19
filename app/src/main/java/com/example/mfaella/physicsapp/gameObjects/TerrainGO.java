package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.mfaella.physicsapp.GameWorld;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.Fixture;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.PolygonShape;

import java.security.cert.PolicyNode;

public class TerrainGO extends GameObject{

    private static int instances = 0;
    private final float density = 0.5f;
    private final float friction = 0.1f;
    private final float restitution = 0.1f;


    private Canvas canvas;
    private Paint paint = new Paint();


    public TerrainGO(GameWorld gw, float x, float y) {
        super(gw);

        //BISOGNA CAPIRE STO CAZZO DI SISTEMA DI COORDINATE COME FUNZIONA

        instances++;
        this.posX = x;
        this.posY = y;
        this.width = 2f;
        this.height = 2.0f;
        canvas = new Canvas(gw.getBuffer());
        this.screen_semi_width = gw.toPixelsXLength(width)/2;
        this.screen_semi_height = gw.toPixelsYLength(height)/2;

        BodyDef bodyDef = new BodyDef();
        bodyDef.setType(BodyType.staticBody);
        bodyDef.setPosition(x,y);
        this.body = gw.getWorld().createBody(bodyDef);
        body.setSleepingAllowed(false);
        this.name = "Terreno "+ instances;
        body.setUserData(this);


        PolygonShape box = new PolygonShape();
        box.setAsBox(width/2, height/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.setShape(box);
        fixtureDef.setFriction(friction);       // default 0.2
        fixtureDef.setRestitution(restitution);    // default 0
        fixtureDef.setDensity(density);     // default 0
        body.createFixture(fixtureDef);

        //clearing
        bodyDef.delete();
        box.delete();
    }

    @Override
    public void draw(Bitmap buf, float x, float y, float angle) {

        paint.setARGB(255,0,255,0);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        //Bisogna disegnare una immagine

        Paint textPaint = new Paint();
        textPaint.setARGB(255,0,0,0);
        canvas.drawText("Posizione in pixel: ("+x+","+y+")",30f,30f,textPaint);
        canvas.drawText("Posizione in metri: ("+gw.toMetersX(x)+","+gw.toMetersY(y)+")",30f,60f,textPaint);


        canvas.drawText("Posizione presa dal body: ("+this.getBody().getPositionX()+","+this.getBody().getPositionY()+")",30f,90f,textPaint);

        canvas.drawRect(x- screen_semi_width, y- screen_semi_height, x + screen_semi_width, y + screen_semi_height, paint);

    }
}
