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
import com.google.fpl.liquidfun.Joint;
import com.google.fpl.liquidfun.PolygonShape;
import com.google.fpl.liquidfun.RevoluteJointDef;

public class Tutorial implements Level{

    GameObject a;
    GameObject b;

    float roadWidth = 3.65f;
    float roadHeigth = 0.5f;

    @Override
    public void createLevel(GameWorld gw) {

        gw.addGameObject(new BackgroundGO(gw));

        //Terrain
        GameObject terrainSx = gw.addGameObject(new TerrainGO(gw,-20,10));
        GameObject terrainDx = gw.addGameObject(new TerrainGO(gw,20,10));

        b = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD, terrainSx.getBody().getPositionX()+(roadWidth/2)+ terrainSx.getWidth()/2, terrainSx.getBody().getPositionY()+(roadHeigth/2)- terrainSx.getHeight()/2,0.1f, 0.3f, 0.5f,roadWidth,roadHeigth));
        new MyRevoluteJoint(gw, terrainSx, b,terrainSx.getWidth()/2, -terrainSx.getHeight()/2,-roadWidth/2,-roadHeigth/2);
        createBridge(gw, terrainDx);

        /*a = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD, 0,0,0.1f, 0.3f, 0.5f,roadWidth,roadHeigth));
        b= gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD, roadWidth/2,0,0.1f, 0.3f, 0.5f,roadWidth,roadHeigth));
        new MyRevoluteJoint(gw,a,b,roadWidth/2,0,-roadWidth/2,0);*/

        //gw.addGameObject(new DynamicBoxGO(gw,-10f,-10f));

        //b = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,terrainSx.getBody().getPositionX()+(4.0f/2)+terrainSx.getWidth()/2, terrainSx.getBody().getPositionY()+(0.5f/2)-terrainSx.getHeight()/2,0.1f, 0.3f, 0.5f,4.0f,0.5f));


        //GameObject b = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,terrainDx.getPosX()-(4.5f)-terrainDx.getWidth()/2,terrainDx.getPosY()-terrainDx.getHeight()/2, 0.1f, 0.3f, 0.5f,4.5f,1.0f));
        //new MyRevoluteJoint(gw, terrainSx, a);


        //GameObject c = gw.addGameObject(gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,a.getPosX()-(4.5f)-a.getWidth()/2,a.getPosY()-terrainDx.getHeight()/2, 0.1f, 0.3f, 0.5f,4.5f,1.0f)));
    }

    private void createBridge(GameWorld gw, GameObject terrainDx) {

        for(int i = 0; i < 3; i++){

            a = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,b.getBody().getPositionX()+(roadWidth/2)+b.getWidth()/2, b.getBody().getPositionY(),0.1f, 0.3f, 0.5f,roadWidth,roadHeigth));
            new MyRevoluteJoint(gw,a,b,-roadWidth/2,0,roadWidth/2,0);

            b = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,a.getBody().getPositionX()+(roadWidth/2)+a.getWidth()/2, a.getBody().getPositionY(),0.1f, 0.3f, 0.5f,roadWidth,roadHeigth));

            new MyRevoluteJoint(gw,a,b,roadWidth/2,0,-roadWidth/2,0);
        }
        GameObject last = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,b.getBody().getPositionX()+(roadWidth/2)+b.getWidth()/2, b.getBody().getPositionY(),0.1f, 0.3f, 0.5f,roadWidth,roadHeigth));
        new MyRevoluteJoint(gw,last,b,-roadWidth/2,0,roadWidth/2,0);
        new MyRevoluteJoint(gw, terrainDx, last,-terrainDx.getWidth()/2, -terrainDx.getHeight()/2,roadWidth/2,-roadHeigth/2);
    }
}
