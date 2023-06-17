package com.example.mfaella.physicsapp.Levels;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.gameObjects.DynamicBoxGO;

public class Tutorial implements Level{


    @Override
    public void createLevel(GameWorld gw) {
        gw.addGameObject(new DynamicBoxGO(gw, 5, 0));
    }
}
