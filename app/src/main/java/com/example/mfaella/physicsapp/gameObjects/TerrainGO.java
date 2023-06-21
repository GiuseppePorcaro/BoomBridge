package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.util.SparseArray;

import com.example.mfaella.physicsapp.GameWorld;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.Fixture;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.PolygonShape;
import com.google.fpl.liquidfun.SWIGTYPE_p_b2AABB;
import com.google.fpl.liquidfun.SWIGTYPE_p_void;

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

        instances++;
        this.posX = x;
        this.posY = y;
        this.width = 10.0f;
        this.height = 15.0f;
        canvas = new Canvas(gw.getBuffer());
        this.screen_semi_width = gw.toPixelsXLength(width)/2;
        this.screen_semi_height = gw.toPixelsYLength(height)/2;

        BodyDef bodyDef = new BodyDef();
        bodyDef.setType(BodyType.staticBody);
        bodyDef.setPosition(x,y);
        this.body = gw.getWorld().createBody(bodyDef);
        body.setSleepingAllowed(false);
        this.name = "Terreno "+ instances;
        SWIGTYPE_p_void swigtype_p_void = new SWIGTYPE_p_void();
        body.setUserData(swigtype_p_void);


        PolygonShape box = new PolygonShape();
        box.setAsBox(width/2, height/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.setShape(box);
        fixtureDef.setFriction(friction);
        fixtureDef.setRestitution(restitution);
        fixtureDef.setDensity(density);
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

        canvas.drawRect(x- screen_semi_width, y- screen_semi_height, x + screen_semi_width, y + screen_semi_height, paint);

    }
}

/*
*
* Paint textPaint = new Paint();
        textPaint.setARGB(255,0,0,0);
        canvas.drawText("Posizione in pixel: ("+x+","+y+")",30f,30f,textPaint);
        canvas.drawText("Posizione in metri: ("+gw.toMetersX(x)+","+gw.toMetersY(y)+")",30f,60f,textPaint);


        canvas.drawText("Posizione presa dal body: ("+this.getBody().getPositionX()+","+this.getBody().getPositionY()+")",30f,90f,textPaint);
* */
