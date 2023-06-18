package com.example.mfaella.physicsapp.Levels;

import android.graphics.Canvas;

import com.badlogic.androidgames.framework.Game;
import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.R;
import com.example.mfaella.physicsapp.gameObjects.BackgroundGO;
import com.example.mfaella.physicsapp.gameObjects.BridgeElementGO;
import com.example.mfaella.physicsapp.gameObjects.BridgeElementType;
import com.example.mfaella.physicsapp.gameObjects.DynamicBoxGO;
import com.example.mfaella.physicsapp.gameObjects.DynamicCircleGO;
import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.example.mfaella.physicsapp.gameObjects.MarblesGO;
import com.example.mfaella.physicsapp.gameObjects.TerrainGO;
import com.example.mfaella.physicsapp.joints.MyDistanceJoint;
import com.example.mfaella.physicsapp.joints.MyRevoluteJoint;
import com.google.fpl.liquidfun.Fixture;
import com.google.fpl.liquidfun.PolygonShape;

public class Tutorial implements Level{


    @Override
    public void createLevel(GameWorld gw) {

        gw.addGameObject(new BackgroundGO(gw));

        //Terrain
        GameObject terrainSx = gw.addGameObject(new TerrainGO(gw,-20.5f,10f));
        GameObject terrainDx = gw.addGameObject(new TerrainGO(gw,20.5f,10f));

        GameObject a = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,terrainSx.getPosX()+(4.5f)+terrainSx.getWidth()/2,terrainSx.getPosY()-terrainSx.getHeight()/2, 0.1f, 0.3f, 0.5f,4.5f,1.0f));

        GameObject b = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,terrainDx.getPosX()-(4.5f)-terrainDx.getWidth()/2,terrainDx.getPosY()-terrainDx.getHeight()/2, 0.1f, 0.3f, 0.5f,4.5f,1.0f));
        //new MyRevoluteJoint(gw, terrainSx, a);


        GameObject c = gw.addGameObject(gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,a.getPosX()-(4.5f)-a.getWidth()/2,a.getPosY()-terrainDx.getHeight()/2, 0.1f, 0.3f, 0.5f,4.5f,1.0f)));
    }
}
