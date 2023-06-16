package com.example.mfaella.physicsapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.badlogic.androidgames.framework.Audio;
import com.badlogic.androidgames.framework.Music;
import com.badlogic.androidgames.framework.impl.AndroidAudio;
import com.badlogic.androidgames.framework.impl.MultiTouchHandler;
import com.example.mfaella.physicsapp.gameObjects.DynamicBoxGO;
import com.example.mfaella.physicsapp.gameObjects.DynamicTriangleGO;
import com.example.mfaella.physicsapp.gameObjects.EnclosureGO;
import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.example.mfaella.physicsapp.gameObjects.MarblesGO;
import com.example.mfaella.physicsapp.gameObjects.TerrainGO;
import com.example.mfaella.physicsapp.joints.MyRevoluteJoint;

import java.nio.ByteOrder;

public class MainActivity extends Activity {

    private GameLoop t; // just for fun, unrelated to the rest
    private AndroidFastRenderView renderView;
    private Audio audio;
    private Music backgroundMusic;
    private MultiTouchHandler touch;

    // boundaries of the physical simulation
    private static final float XMIN = -25, XMAX = 25, YMIN = -15, YMAX = 15;

    // the tag used for logging
    public static String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.loadLibrary("liquidfun");
        System.loadLibrary("liquidfun_jni");
        
        createGame();

    }

    private void createGame(){

        TAG = getString(R.string.app_name);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Sound
        audio = new AndroidAudio(this);
        CollisionSounds.init(audio);
        backgroundMusic = audio.newMusic("soundtrack.mp3");
        backgroundMusic.play();

        // Game world
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Box physicalSize = new Box(XMIN, YMIN, XMAX, YMAX),
                screenSize   = new Box(0, 0, metrics.widthPixels, metrics.heightPixels);
        GameWorld gw = new GameWorld(physicalSize, screenSize, this);
        gw.setGravity(0.0f, 9.8f); //mi permette di impostare il punto di gravità. Con questi valori di x e y riesco ad avere sempre lo stesso punto di gravità indipendentemente dall'orientamento del telefono

        gw.addGameObject(new EnclosureGO(gw, XMIN, XMAX, YMIN, YMAX));
        gw.addGameObject(new DynamicBoxGO(gw, 0, 0));
        gw.addGameObject(new DynamicBoxGO(gw, 5, 0));
        gw.addGameObject(new DynamicTriangleGO(gw, 7, 3));
        gw.addGameObject(new MarblesGO(gw, 0, 5));

        GameObject a = gw.addGameObject(new DynamicBoxGO(gw, 0, -2));
        GameObject b = gw.addGameObject(new DynamicBoxGO(gw, 1, -3));
        new MyRevoluteJoint(gw, a.getBody(), b.getBody());
        // new MyPrismaticJoint(gw, a.body, b.body);

        //gw.addGameObject(new DynamicCircleGO(gw,0,0));
        gw.addGameObject(new TerrainGO(gw,-20.5f,10f));
        gw.addGameObject(new TerrainGO(gw,20.5f,10f));


        // Just for info
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        float refreshRate = display.getRefreshRate();
        Log.i(getString(R.string.app_name), "Refresh rate =" + refreshRate);

        // Accelerometer
        SensorManager smanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (smanager.getSensorList(Sensor.TYPE_ACCELEROMETER).isEmpty()) {
            Log.i(getString(R.string.app_name), "No accelerometer");
        } else {
            Sensor accelerometer = smanager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            if (!smanager.registerListener(new AccelerometerListener(gw), accelerometer, SensorManager.SENSOR_DELAY_NORMAL))
                Log.i(getString(R.string.app_name), "Could not register accelerometer listener");
        }

        // View
        renderView = new AndroidFastRenderView(this, gw);
        setContentView(renderView);

        // Touch
        touch = new MultiTouchHandler(renderView, 1, 1);
        // Setter needed due to cyclic dependency
        gw.setTouchHandler(touch);

        // Unrelated to the rest, just to show interaction with another thread
        t = new GameLoop(gw);
        t.start();

        Log.i(getString(R.string.app_name), "onCreate complete, Endianness = " +
                (ByteOrder.nativeOrder()==ByteOrder.BIG_ENDIAN? "Big Endian" : "Little Endian"));
    }



    @Override
    public void onPause() {
        super.onPause();
        Log.i("Main thread", "pause");
        renderView.pause(); // stops the main loop
        backgroundMusic.pause();

        // persistence example
        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(getString(R.string.important_info), t.counter);
        editor.commit();
        Log.i("Main thread", "saved counter " + t.counter);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Main thread", "stop");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Main thread", "resume");

        renderView.resume(); // starts game loop in a separate thread
        backgroundMusic.play();

        // persistence example
        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        int counter = pref.getInt(getString(R.string.important_info), -1); // default value
        Log.i("Main thread", "read counter " + counter);
        t.counter = counter;
    }
}
