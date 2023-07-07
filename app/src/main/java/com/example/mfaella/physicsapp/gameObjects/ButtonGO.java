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
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.PolygonShape;

public class ButtonGO extends GameObject{

    private Canvas canvas;
    private final RectF dest = new RectF();
    private ButtonType buttonType;
    private Bitmap bitmap;
    private Paint paint = new Paint();

    public ButtonGO(GameWorld gw, ButtonType buttonType, float x, float y) {
        super(gw);

        canvas = new Canvas(gw.getBuffer());

        this.width = 2.2f;
        this.height = 2.2f;
        this.buttonType = buttonType;
        this.screen_semi_width = gw.toPixelsXLength(width)/2;
        this.screen_semi_height = gw.toPixelsYLength(height)/2;

        BodyDef bdef = createBodyDef(gw, x,y);
        PolygonShape box = createPolygonShape();
        FixtureDef fixturedef = createFixtureDef(box);
        body.createFixture(fixturedef);

        bdef.delete();
        box.delete();
        fixturedef.delete();

        setButtonImage();

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

    private void setButtonImage(){
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;
        switch (buttonType){
            case PLAY:
                bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.bottone_play, o);
                break;
            case RESET:
                paint.setARGB(255,0,255,0);
                break;
            case EXIT:
                bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.bottone_exit, o);
            default:
                paint.setARGB(255,0,0,0);
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
        fixturedef.setFriction(0.0f);       // default 0.2
        fixturedef.setRestitution(0.0f);    // default 0
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
        this.name = "Bottone start";
        body.setUserData(this);
        return bdef;
    }

    @Override
    public void delete() {
        paint.setARGB(0,0,0,0);
    }

}
