package com.example.mfaella.physicsapp.Levels;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.gameObjects.BridgeElementGO;
import com.example.mfaella.physicsapp.gameObjects.BridgeElementType;
import com.example.mfaella.physicsapp.gameObjects.DynamicBoxGO;
import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.example.mfaella.physicsapp.gameObjects.TerrainGO;
import com.example.mfaella.physicsapp.joints.MyDistanceJoint;

public class Tutorial implements Level{


    @Override
    public void createLevel(GameWorld gw) {

        //Terrain
        gw.addGameObject(new TerrainGO(gw,-20.5f,10f));
        gw.addGameObject(new TerrainGO(gw,20.5f,10f));

        GameObject a = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.BEAM,5.0f,5.0f, 0.5f, 0.1f, 0.3f));

        //gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,2.0f,2.0f, 0.5f, 0.1f, 0.3f));
        gw.addGameObject(new DynamicBoxGO(gw,5,5));


    }
}
