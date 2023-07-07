package com.example.mfaella.physicsapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

import com.badlogic.androidgames.framework.Input;
import com.badlogic.androidgames.framework.Sound;
import com.badlogic.androidgames.framework.impl.TouchHandler;
import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.google.fpl.liquidfun.Body;
import com.google.fpl.liquidfun.Fixture;
import com.google.fpl.liquidfun.Joint;
import com.google.fpl.liquidfun.ParticleSystem;
import com.google.fpl.liquidfun.ParticleSystemDef;
import com.google.fpl.liquidfun.Vec2;
import com.google.fpl.liquidfun.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The game objects and the viewport.
 *
 * Created by mfaella on 27/02/16.
 */
public class GameWorld {
    // Rendering
    ImageView screenImageView;
    int bufferWidth = 1920, bufferHeight = 1080;    // actual pixels
    private Bitmap buffer;
    private Canvas canvas;
    private Paint particlePaint;

    // Simulation
    List<GameObject> objects;
    List<Joint> joints;
    List<GameObject> newBeamsAddedByPlayer;
    List<Joint> newJointsAddedByPlayer;
    private World world;
    final Box physicalSize;
    final Box screenSize;

    final Box currentView;
    private MyContactListener contactListener;
    private TouchConsumer touchConsumer;
    private TouchHandler touchHandler;

    // Particles
    ParticleSystem particleSystem;
    private static final int MAXPARTICLECOUNT = 1000;
    private static final float PARTICLE_RADIUS = 0.3f;

    // Parameters for world simulation
    private static final float TIME_STEP = 1 / 50f; // 50 fps
    private static final int VELOCITY_ITERATIONS = 8;
    private static final int POSITION_ITERATIONS = 3;
    private static final int PARTICLE_ITERATIONS = 3;

    //For the game
    private boolean isPlayButtonPressed;
    private int budget;
    private int beamPrice;
    public boolean playerHasLost;
    final Activity activity;



    public GameWorld(Box physicalSize, Box screenSize, Activity theActivity) {
        this.physicalSize = physicalSize;
        this.screenSize = screenSize;
        this.activity = theActivity;
        this.world = new World(0, 0);
        this.buffer = Bitmap.createBitmap(bufferWidth, bufferHeight, Bitmap.Config.ARGB_8888);
        this.currentView = physicalSize;

        playerHasLost = false;

        // The particle system
        ParticleSystemDef psysdef = new ParticleSystemDef();
        this.particleSystem = world.createParticleSystem(psysdef);
        particleSystem.setRadius(PARTICLE_RADIUS);
        particleSystem.setMaxParticleCount(MAXPARTICLECOUNT);
        psysdef.delete();

        // stored to prevent GC
        contactListener = new MyContactListener(this);
        world.setContactListener(contactListener);

        touchConsumer = new TouchConsumer(this);
        this.canvas = new Canvas(buffer);
        this.objects = new ArrayList<>();
        this.joints = new ArrayList<>();
        this.newBeamsAddedByPlayer = new ArrayList<>();
        this.newJointsAddedByPlayer = new ArrayList<>();
    }

    /*private void setWindowMetrics(){
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        System.out.println("Width: "+metrics.widthPixels+" - Height: "+metrics.heightPixels+" - dpi: "+metrics.xdpi);

        //this.bufferWidth = metrics.widthPixels;
        //this.bufferHeight = metrics.heightPixels;
    }*/

    public synchronized GameObject addGameObject(GameObject obj)
    {
        objects.add(obj);
        return obj;
    }

    public synchronized GameObject addNewBeam(GameObject obj)
    {
        newBeamsAddedByPlayer.add(obj);
        return obj;
    }

    public synchronized Joint addNewBeamJoint(Joint joint)
    {
        newJointsAddedByPlayer.add(joint);
        return joint;
    }

    public synchronized void addParticleGroup(GameObject obj)
    {
        objects.add(obj);
    }

    // To distance sounds from each other
    private long timeOfLastSound = 0;

    public synchronized void update(float elapsedTime)
    {
        // advance the physics simulation
        world.step(elapsedTime, VELOCITY_ITERATIONS, POSITION_ITERATIONS, PARTICLE_ITERATIONS);

        // Handle collisions
        handleCollisions(contactListener.getCollisions());

        // Handle touch events
        for (Input.TouchEvent event: touchHandler.getTouchEvents())
            touchConsumer.consumeTouchEvent(event);


        handleJoints(elapsedTime);

        removeOldFragments();

        checkPlayerHasLost();

    }

    private void checkPlayerHasLost(){
        if(playerHasLost == true){
            playerHasLost = false;
            Intent i = new Intent(activity, GameOverActivity.class);
            activity.startActivity(i);
            activity.finish();
        }
    }

    public void resetGame(){
        for(GameObject g: newBeamsAddedByPlayer){
            world.destroyBody(g.getBody());
        }

        /**/
    }

    private void removeOldFragments(){
        Body body = getWorld().getBodyList();
        while(body != null){
            GameObject g = (GameObject) body.getUserData();
            if(g.getName().contains("Fragment") && body.getLinearVelocity().length() == 0){
                g.delete();
                world.destroyBody(body);
            }
            body = body.getNext();
        }
    }

    private void handleJoints(float elapsedTime) {
        for(int i = 0; i < joints.size(); i++){
            Joint j = joints.get(i);
            Vec2 reactionForceVec = j.getReactionForce(1/ elapsedTime);
            float reactionForce = reactionForceVec.lengthSquared();
            float maxMass = 15000;
            float maxForce = maxMass * 9.8f;
            if(Math.abs(reactionForce) > Math.abs(maxForce)){
                joints.remove(i);
                this.getWorld().destroyJoint(j);
                j = null;
            }
        }
    }

    public synchronized void render()
    {
        // clear the screen (with black)
        canvas.drawARGB(255, 0, 0, 0);
        for (GameObject obj: objects)
            obj.draw(buffer);
        for (GameObject obj: newBeamsAddedByPlayer)
            obj.draw(buffer);
        // drawParticles();
    }

    private void handleCollisions(Collection<Collision> collisions) {
        for (Collision event: collisions) {
            Sound sound = CollisionSounds.getSound(event.a.getClass(), event.b.getClass());
            if (sound!=null) {
                long currentTime = System.nanoTime();
                if (currentTime - timeOfLastSound > 500_000_000) {
                    timeOfLastSound = currentTime;
                    sound.play(0.7f);
                }
            }
        }

    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public boolean isPlayButtonPressed() {
        return isPlayButtonPressed;
    }

    public void setPlayButtonPressed(boolean playButtonPressed) {
        isPlayButtonPressed = playButtonPressed;
    }

    // Conversions between screen coordinates and physical coordinates


    public int getBeamPrice() {
        return beamPrice;
    }

    public void setBeamPrice(int beamPrice) {
        this.beamPrice = beamPrice;
    }

    public float toMetersX(float x) { return currentView.xmin + x * (currentView.width/screenSize.width); }
    public float toMetersY(float y) { return currentView.ymin + y * (currentView.height/screenSize.height); }

    public float toPixelsX(float x) { return (x-currentView.xmin)/currentView.width*bufferWidth; }
    public float toPixelsY(float y) { return (y-currentView.ymin)/currentView.height*bufferHeight; }

    public float toPixelsXLength(float x)
    {
        return x/currentView.width*bufferWidth;
    }
    public float toPixelsYLength(float y)
    {
        return y/currentView.height*bufferHeight;
    }

    public synchronized void setGravity(float x, float y)
    {
        world.setGravity(x, y);
    }

    public Bitmap getBuffer() {
        return buffer;
    }

    public int getBufferWidth() {
        return bufferWidth;
    }

    public int getBufferHeight() {
        return bufferHeight;
    }

    public World getWorld() {
        return world;
    }

    public Box getCurrentView() {
        return currentView;
    }

    public ParticleSystem getParticleSystem() {
        return particleSystem;
    }

    public Activity getActivity() {
        return activity;
    }

    public boolean isPlayerHasLost() {
        return playerHasLost;
    }

    public void setPlayerHasLost(boolean playerHasLost) {
        this.playerHasLost = playerHasLost;
    }

    @Override
    public void finalize()
    {
        world.delete();
    }

    public void setTouchHandler(TouchHandler touchHandler) {
        this.touchHandler = touchHandler;
    }

    public void addJoint(Joint joint) {
        joints.add(joint);
    }
}
