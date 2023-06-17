package com.example.mfaella.physicsapp.Levels;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.gameObjects.DynamicTriangleGO;

public class FirstLevel implements Level{

    @Override
    public void createLevel(GameWorld gw) {
        gw.addGameObject(new DynamicTriangleGO(gw, 7, 3));
    }
}
