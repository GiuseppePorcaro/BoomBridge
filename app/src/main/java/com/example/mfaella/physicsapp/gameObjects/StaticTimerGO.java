package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

import com.example.mfaella.physicsapp.GameWorld;

public class StaticTimerGO extends GameObject{

    private Canvas canvas;
    private Paint paint = new Paint();

    private float x,y;

    public StaticTimerGO(GameWorld gw) {
        super(gw);

        this.name = "Budget";

        canvas = new Canvas(gw.getBuffer());
        paint.setARGB(255,255,255,255);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        paint.setTypeface(Typeface.SANS_SERIF);
        paint.setTextAlign(Paint.Align.CENTER);

        DisplayMetrics metrics = new DisplayMetrics();
        gw.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        x = gw.getBufferWidth()/2+400;
        y = 100;


    }

    @Override
    public void draw(Bitmap buf, float x, float y, float angle) {
        canvas.save();
        int deltaTime = (int)gw.getDeltaTime();
        float timer = gw.getTimer();
        if(timer - deltaTime < 10){
            paint.setARGB(255,255,0,0);
        } else if (timer - deltaTime > 10 && timer - deltaTime < timer/2) {
            paint.setARGB(255,255,255,0);
        }

        if(deltaTime <= timer && gw.isPlayButtonPressed() == false){
            canvas.drawText("TIMER: "+deltaTime+"s / "+(int)timer+"s",this.x,this.y,paint);
        }

        if(deltaTime >= timer || gw.isPlayButtonPressed() == true){
            paint.setARGB(255,0,255,0);
            float timerBombsExploded = 5;
            int deltaTimeBombsExploded = (int) gw.getDeltaTimeFromBombsExploded();
            if(deltaTimeBombsExploded <= timerBombsExploded){
                canvas.drawText("TO WIN: "+deltaTimeBombsExploded+"s / "+(int)timerBombsExploded+"s",this.x,this.y,paint);
            }

        }

        canvas.restore();
    }

    @Override
    public void delete() {

    }
}
