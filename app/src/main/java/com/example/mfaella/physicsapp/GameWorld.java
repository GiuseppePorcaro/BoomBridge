package com.example.mfaella.physicsapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.badlogic.androidgames.framework.Input;
import com.badlogic.androidgames.framework.Sound;
import com.badlogic.androidgames.framework.impl.AndroidAudio;
import com.badlogic.androidgames.framework.impl.TouchHandler;
import com.example.mfaella.physicsapp.activities.GameOverActivity;
import com.example.mfaella.physicsapp.activities.WinActivity;
import com.example.mfaella.physicsapp.gameObjects.BombGO;
import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.example.mfaella.physicsapp.gameObjects.TerroristGO;
import com.google.fpl.liquidfun.Body;
import com.google.fpl.liquidfun.Joint;
import com.google.fpl.liquidfun.ParticleSystem;
import com.google.fpl.liquidfun.ParticleSystemDef;
import com.google.fpl.liquidfun.Vec2;
import com.google.fpl.liquidfun.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameWorld {
    int bufferWidth = 1920, bufferHeight = 1080;
    private Bitmap buffer;
    final Activity activity;

    // Simulation
    List<GameObject> objects;
    List<Joint> joints;
    private List<GameObject> newBeamsAddedByPlayer;
    private List<Joint> newJointsAddedByPlayer;
    private World world;
    final Box physicalSize, screenSize, currentView;
    private MyContactListener contactListener;
    private TouchConsumer touchConsumer;
    private TouchHandler touchHandler;

    private static final int VELOCITY_ITERATIONS = 8;
    private static final int POSITION_ITERATIONS = 3;
    private static final int PARTICLE_ITERATIONS = 3;

    //For the game
    private boolean isPlayButtonPressed, playerHasLost, playerHasWin ;
    private int totalBudget;
    private int budget;
    private int beamPrice;
    private float startingTime;
    private float startingTimeWhenBombExploded;
    private float deltaTimeFromBombsExploded;
    private float deltaTime;
    public TerroristGO terrorist;
    public boolean playerCanPlay;
    private float timer = 60; //Standard time
    private float timerToWin = 5;
    private boolean refreshLevel;

    private int level;


    public GameWorld(Box physicalSize, Box screenSize, Activity theActivity) {
        this.physicalSize = physicalSize;
        this.screenSize = screenSize;
        this.activity = theActivity;
        this.world = new World(0, 0);
        this.buffer = Bitmap.createBitmap(bufferWidth,bufferHeight, Bitmap.Config.ARGB_8888);
        this.currentView = physicalSize;
        this.startingTime = System.nanoTime() / 1000000000f;

        // stored to prevent GC
        contactListener = new MyContactListener(this);
        world.setContactListener(contactListener);

        touchConsumer = new TouchConsumer(this);
        this.objects = new ArrayList<>();
        this.joints = new ArrayList<>();
        this.newBeamsAddedByPlayer = new ArrayList<>();
        this.newJointsAddedByPlayer = new ArrayList<>();

        //Setting all condition
        playerHasLost = false;
        playerHasWin = false;
        refreshLevel = false; //To know if engine has to refresh level
        playerCanPlay = false;
    }

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

        checkBodiesToRemove();

        checkPlayerHasLost();

        checkPlayerHasWin();

        clock();

        executeTerrorisAI();
    }

    private void executeTerrorisAI(){
        terrorist.terroristAI(level);
    }


    private void clock(){
        deltaTime = (System.nanoTime() / 1000000000f) - startingTime;

        if(deltaTime >= timer && isPlayButtonPressed == false){
            setPlayButtonPressed(true);
            detonateBombs();
        }

        if(isPlayButtonPressed()){
            deltaTimeFromBombsExploded = (System.nanoTime() / 1000000000f) - startingTimeWhenBombExploded;

            if(deltaTimeFromBombsExploded >= timerToWin){
                playerHasWin = true;
            }
        }
    }

    private void checkPlayerHasWin(){
        if(playerHasWin == true){
            playerHasWin = false;
            Intent i = new Intent(activity, WinActivity.class);
            i.putExtra("usedTime",deltaTime);
            i.putExtra("budget",budget);
            i.putExtra("totalBudget",totalBudget);
            i.putExtra("timer",timer);
            activity.startActivity(i);
            activity.finish();
        }
    }
    private void checkPlayerHasLost(){
        Body body = getWorld().getBodyList();
        while(body!=null){
            GameObject g = (GameObject) body.getUserData();
            if((g.getName().contains("ROAD") && g.getPosY() > toPixelsY(5))){
                playerHasLost = true;
            }
            body = body.getNext();
        }
        if(playerHasLost == true){
            playerHasLost = false;
            Intent i = new Intent(activity, GameOverActivity.class);
            activity.startActivity(i);
            activity.finish();
        }
    }

    private void checkBodiesToRemove(){
        removeBombFragments();

        if(refreshLevel == true){
            removeAllBodiesAddedByPlayer();
            if(newBeamsAddedByPlayer.size() == 0 && newJointsAddedByPlayer.size() == 0){
                refreshLevel = false;
            }
        }
    }

    public void removeAllBodiesAddedByPlayer(){
        for(int i = 0; i < newJointsAddedByPlayer.size(); i++){
            Joint j = newJointsAddedByPlayer.remove(i);
            j.delete();
        }

        for(int i = 0; i < newBeamsAddedByPlayer.size(); i++){
            GameObject g = newBeamsAddedByPlayer.remove(i);
            g.delete();
            world.destroyBody(g.getBody());
        }
        budget = totalBudget;

    }

    public void resetGame(){
        this.refreshLevel = true;
    }

    private void removeBombFragments() {
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
            float maxMass = 150000;
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
        for (GameObject obj: objects)
            obj.draw(buffer);
        for (GameObject obj: newBeamsAddedByPlayer)
            obj.draw(buffer);
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

    public void detonateBombs(){
        Body body = getWorld().getBodyList();
        setPlayButtonPressed(true);
        AndroidAudio audio = new AndroidAudio(activity);
        Sound sound;
        while(body != null){
            GameObject g = (GameObject) body.getUserData();
            if(g.getName().contains("Bomb")){
                sound = audio.newSound("explosionEffect.mp3");
                sound.play(20f);
                BombGO.denotaneBomb(this,g.getBody().getPositionX(),g.getBody().getPositionY());
                g.delete();
            }
            body = body.getNext();
        }
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setTotalBudget(int totalBudget) {
        this.totalBudget = totalBudget;
    }

    public boolean isPlayButtonPressed() {
        return isPlayButtonPressed;
    }

    public void setPlayButtonPressed(boolean playButtonPressed) {
        startingTimeWhenBombExploded = System.nanoTime() / 1000000000f;
        isPlayButtonPressed = playButtonPressed;
    }

    public float getDeltaTimeFromBombsExploded() {
        return deltaTimeFromBombsExploded;
    }
    public int getBeamPrice() {
        return beamPrice;
    }

    public void setBeamPrice(int beamPrice) {
        this.beamPrice = beamPrice;
    }

    public float getDeltaTime() {
        return deltaTime;
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

    public Activity getActivity() {
        return activity;
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

    public void setTimer(float timer) {
        this.timer = timer;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimerToWin(float timerToWin) {
        this.timerToWin = timerToWin;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
