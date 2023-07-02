package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.R;

public class BackgroundGO extends GameObject{

    Canvas canvas;
    private Rect rect;
    private Bitmap bitmap;

    public BackgroundGO(GameWorld gw) {
        super(gw);

        canvas = new Canvas(gw.getBuffer());
        rect = new Rect(0,0,gw.getBufferWidth(),gw.getBufferHeight());

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;

        bitmap = BitmapFactory.decodeResource(gw.getActivity().getResources(), R.drawable.background_tutorial, o);
    }

    @Override
    public void draw(Bitmap buf, float x, float y, float angle) {

        //TO DO: use a background image instead of just color
        //canvas.drawBitmap(BitmapFactory.decodeResource(gw.getActivity().getResources(),R.drawable.background_tutorial),0,0,null);


        canvas.drawBitmap(bitmap,null,rect,null);
        //canvas.drawColor(gw.getActivity().getColor(R.color.backgroundColor));
    }
}
