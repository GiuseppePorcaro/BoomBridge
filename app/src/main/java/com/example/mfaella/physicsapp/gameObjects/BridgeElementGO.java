package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;

import com.example.mfaella.physicsapp.GameWorld;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.Fixture;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.PolygonShape;


public class BridgeElementGO extends GameObject{

    private int price;
    private BridgeElementType bridgeElementType;

    private static final float width = 2.5f, height = 2.5f, density = 0.5f;
    private static float screen_semi_width, screen_semi_height;
    private static int instances = 0;

    private final Canvas canvas;
    private final Paint paint = new Paint();

    // VEDERE SE ESISTONO ATTRIBUTI DI RIGIDITA' O DURABILITA' PER GLI OGGETTI
    // VERIFICARE ANCHE SE è GIA' IMPLEMENTATO NEL MOTORE FISICO LA POSSIBILITA' CHE LE GIUNTURE SI SPEZZANO SOTTO UN PESO TROPPO ECCESSIVO
    //private float rigidità
    //private float durabilità
    //private float massa;


    public BridgeElementGO(GameWorld gw, int price, BridgeElementType bridgeElementType, float x, float y) {
        super(gw);
        this.price = price;
        this.bridgeElementType = bridgeElementType;

        instances++;
        this.name = bridgeElementType + "N° "+instances;
        this.canvas = new Canvas(gw.getBuffer());

        BodyDef bodyDef = createBodyDef(gw,x,y);
        PolygonShape beam = createPolygonShape();
        FixtureDef fixtureDef = createFixtureDef(beam);
        body.createFixture(fixtureDef);

        // clean up native objects
        // CONTROLLARE QUESTI TRE METODI CHE UNO DI ESSI POTREBBE FAR CRASHARE TUTTO
        fixtureDef.delete();
        bodyDef.delete();
        beam.delete();

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
        fixturedef.setFriction(0.1f);       // default 0.2
        fixturedef.setRestitution(0.4f);    // default 0
        fixturedef.setDensity(density);     // default 0. Density is used to compute the mass properties of the parent body
        return fixturedef;
    }


    @Override
    public void draw(Bitmap buf, float x, float y, float angle) {


    }
}
