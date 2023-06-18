package com.example.mfaella.physicsapp.gameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.R;

public class BackgroundGO extends GameObject{

    Canvas canvas;
    public BackgroundGO(GameWorld gw) {
        super(gw);

        canvas = new Canvas(gw.getBuffer());
    }

    @Override
    public void draw(Bitmap buf, float x, float y, float angle) {

        //TO DO: use a background image instead of just color
        canvas.drawColor(gw.getActivity().getColor(R.color.backgroundColor));
    }
}
