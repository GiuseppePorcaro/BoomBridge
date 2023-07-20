package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.mfaella.physicsapp.GameWorld;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.Color;
import com.google.fpl.liquidfun.Filter;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.PolygonShape;
import com.google.fpl.liquidfun.Vec2;

import java.sql.Ref;

public class BombFragmentGO extends GameObject{

    private Canvas canvas;
    private Paint paint;
    private static int instances = 0;
    private final float density = 100;
    private final RectF dest = new RectF();

    public BombFragmentGO(GameWorld gw, float x, float y, Vec2 rayDir, float blastPower, float numRays) {
        super(gw);

        instances++;
        canvas = new Canvas(gw.getBuffer());
        this.name = "Fragment " + instances;
        this.width = 0.15f;
        this.height = 0.15f;
        this.screen_semi_width = gw.toPixelsXLength(width)/2;
        this.screen_semi_height = gw.toPixelsYLength(height)/2;

        BodyDef bd = new BodyDef();
        bd.setType(BodyType.dynamicBody);

        bd.setFixedRotation(true);
        bd.setBullet(true);
        bd.setLinearDamping(0.5f);
        bd.setGravityScale(0);
        bd.setPosition(x,y);
        rayDir.setX(blastPower*rayDir.getX());
        rayDir.setY(blastPower*rayDir.getY());
        bd.setLinearVelocity(rayDir);
        body = gw.getWorld().createBody(bd);
        body.setUserData(this);

        PolygonShape circleShape = new PolygonShape();
        circleShape.setAsBox(width/2,height/2); // very small

        FixtureDef fd = new FixtureDef();
        fd.setShape(circleShape);
        fd.setDensity(density / (float)numRays); // very high - shared across all particles
        fd.setFriction(0); // friction not necessary
        fd.setRestitution(0.99f); // high restitution to reflect off obstacles
        Filter filter = new Filter();
        filter.setGroupIndex((short) -1);
        fd.setFilter(filter);
        body.createFixture(fd);

        paint = new Paint();
        paint.setARGB(255,129,129,129);

        bd.delete();
        fd.delete();
        circleShape.delete();
    }

    @Override
    public void draw(Bitmap buf, float x, float y, float angle) {
        canvas.save();
        canvas.rotate((float) Math.toDegrees(angle), x, y);
        dest.left = x - screen_semi_width;
        dest.bottom = y + screen_semi_height;
        dest.right = x + screen_semi_width;
        dest.top = y - screen_semi_height;

        canvas.drawRect(x- screen_semi_width, y- screen_semi_height, x + screen_semi_width, y + screen_semi_height, paint);
        canvas.restore();
    }

    @Override
    public void delete() {
        paint.setARGB(0,0,0,0);
    }


}
