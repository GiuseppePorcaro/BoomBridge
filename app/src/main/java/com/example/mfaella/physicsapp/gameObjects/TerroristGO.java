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
import com.google.fpl.liquidfun.Vec2;

public class TerroristGO extends GameObject{

    private Canvas canvas;
    private Paint paint;
    private BitmapFactory.Options o;
    private RectF dest;



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
        filter.setGroupIndex((short) -1);
        FixtureDef fixturedef = new FixtureDef();
        fixturedef.setFilter(filter);
        fixturedef.setShape(box);
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

    public void terroristAI(int level){
        float posx = this.getBody().getPositionX();
        if(posx < 20){
            this.getBody().setLinearVelocity(new Vec2(2.5f,-0.1f));
            switch (level){
                case 0:
                    if(0.3<posx && posx < 0.4){
                        gw.addGameObject(new BombGO(gw,0.3f,1.0f,1.5f,1.5f));
                        gw.playerCanPlay = true;
                    }
                    break;
                case 1:
                    if(-4.8 < posx && posx < -4.7){
                        gw.addGameObject(new BombGO(gw,-4.8f,4f,1.5f,1.5f));
                    }
                    if(5.4 < posx && posx < 5.5){
                        gw.addGameObject(new BombGO(gw,5.5f,2.9f,1.5f,1.5f));
                        gw.playerCanPlay = true;
                    }
                    break;
            }
        }
    }


    @Override
    public void delete() {
        //nothing to do
    }
}
