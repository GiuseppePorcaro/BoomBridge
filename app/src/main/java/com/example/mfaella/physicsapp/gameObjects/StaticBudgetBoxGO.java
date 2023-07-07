package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.R;
import com.google.fpl.liquidfun.BodyDef;
import com.google.fpl.liquidfun.BodyType;
import com.google.fpl.liquidfun.FixtureDef;
import com.google.fpl.liquidfun.PolygonShape;

public class StaticBudgetBoxGO extends GameObject{


    private Canvas canvas;
    private Paint paint = new Paint();

    private float x,y;

    public StaticBudgetBoxGO(GameWorld gw)
    {
        super(gw);

        this.name = "Budget";

        canvas = new Canvas(gw.getBuffer());
        paint.setARGB(255,255,255,255);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        paint.setTypeface(Typeface.DEFAULT);
        paint.setTextAlign(Paint.Align.CENTER);

        DisplayMetrics metrics = new DisplayMetrics();
        gw.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        x = gw.getBufferWidth()/2;
        y = 100;
        System.out.println("cREATO");

    }


    @Override
    public void draw(Bitmap buffer, float x, float y, float angle) {
        canvas.save();
        canvas.drawText("Budget: "+gw.getBudget(),this.x,this.y,paint);
        canvas.restore();
    }

    @Override
    public void delete() {
        //canvas.drawColor(0, PorterDuff.Mode.CLEAR);
    }
}
