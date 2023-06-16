package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;

import com.example.mfaella.physicsapp.Box;
import com.example.mfaella.physicsapp.GameWorld;
import com.google.fpl.liquidfun.Body;

/**
 * Created by mfaella on 27/02/16.
 */
public abstract class GameObject {

    Body body;
    protected String name;
    protected GameWorld gw;

    public GameObject(GameWorld gw)
    {
        this.gw = gw;
    }

    public boolean draw(Bitmap buffer)
    {
        if (body!=null) {
            // Physical position of the center
            float x = body.getPositionX(),
                  y = body.getPositionY(),
                  angle = body.getAngle();
            // Log.d("GameObject", "x=" + x + "\t y=" + y);
            // Cropping
            Box view = gw.getCurrentView();
            if (x > view.xmin && x < view.xmax &&
                y > view.ymin && y < view.ymax) {
                // Screen position
                float screen_x = gw.toPixelsX(x),
                      screen_y = gw.toPixelsY(y);
                this.draw(buffer, screen_x, screen_y, angle);
                return true;
            } else
                return false;
        } else {
            this.draw(buffer, 0, 0, 0);
            return true;
        }
    }

    public abstract void draw(Bitmap buf, float x, float y, float angle);

    public String getName() {
        return name;
    }

    public Body getBody() {
        return body;
    }

    @Override
    public String toString() {
        return name;
    }
}