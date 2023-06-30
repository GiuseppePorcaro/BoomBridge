package com.example.mfaella.physicsapp;

import android.util.Log;

import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.google.fpl.liquidfun.Fixture;
import com.google.fpl.liquidfun.RayCastCallback;
import com.google.fpl.liquidfun.Vec2;

public class GameLoop extends Thread {
    public volatile int counter;
    private GameWorld gw;

    public GameLoop(GameWorld gw)
    {
        this.gw = gw;
    }

    private void testRayCasting() {
        Log.i("MyThread", "Objects across the short middle line:");
        RayCastCallback listener = new RayCastCallback() {
          @Override
          public float reportFixture(Fixture f, Vec2 point, Vec2 normal, float fraction) {
              Log.i("MyThread", ((GameObject)f.getBody().getUserData()).getName() + " (" + fraction + ")");
              return 1;
          }
        };
        Vec2 p1 = new Vec2(-10, 0);
        Vec2 p2 = new Vec2(10, 0);
        gw.getWorld().rayCast(listener,p1 , p2);
    }

    @Override
    public void run() {

        while (true) {
            try {
                sleep(1000/60);
                counter++;
                Log.i("MyThread", "counter: " + counter);
                // inverts gravity
                /* float gravity_x = -4 + 8*(counter%2),
                        gravity_y = 0;
                   gw.setGravity(gravity_x, gravity_y); */
                testRayCasting();
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
