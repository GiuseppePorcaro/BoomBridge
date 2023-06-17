package com.example.mfaella.physicsapp.Levels;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.gameObjects.DynamicBoxGO;
import com.example.mfaella.physicsapp.gameObjects.TerrainGO;

public class Tutorial implements Level{


    @Override
    public void createLevel(GameWorld gw) {

        //Terrain
        gw.addGameObject(new TerrainGO(gw,-20.5f,10f));
        gw.addGameObject(new TerrainGO(gw,20.5f,10f));


    }
}
