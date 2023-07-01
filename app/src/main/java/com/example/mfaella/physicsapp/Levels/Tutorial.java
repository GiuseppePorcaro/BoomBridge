package com.example.mfaella.physicsapp.Levels;

import android.graphics.Canvas;

import com.badlogic.androidgames.framework.Game;
import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.R;
import com.example.mfaella.physicsapp.gameObjects.BackgroundGO;
import com.example.mfaella.physicsapp.gameObjects.BridgeElementGO;
import com.example.mfaella.physicsapp.gameObjects.BridgeElementType;
import com.example.mfaella.physicsapp.gameObjects.BridgeJointElementGO;
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

import java.util.ArrayList;

public class Tutorial implements Level{

    GameObject a;
    GameObject b;

    ArrayList<GameObject> joints = new ArrayList<>();

    float roadWidth = 7.45f;
    float roadHeigth = 0.5f;
    float beamWidth = 5.0f;
    float beamHeight = 0.5f;

    @Override
    public void createLevel(GameWorld gw) {

        gw.addGameObject(new BackgroundGO(gw));

        //Terrain
        GameObject terrainSx = gw.addGameObject(new TerrainGO(gw,-20,10));
        GameObject terrainDx = gw.addGameObject(new TerrainGO(gw,20,10));

        createRoadBridge(gw, terrainDx,terrainSx);

        createBridgeScaffolding(gw,terrainDx,terrainSx);

    }

    private void createBridgeScaffolding(GameWorld gw, GameObject terrainDx, GameObject terrainSx){

    }

    private void createRoadBridge(GameWorld gw, GameObject terrainDx, GameObject terrainSx) {

        b = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD, terrainSx.getBody().getPositionX()+(roadWidth/2)+ terrainSx.getWidth()/2, terrainSx.getBody().getPositionY()+(roadHeigth/2)- terrainSx.getHeight()/2,0,0.1f, 0.3f, 0.1f,roadWidth,roadHeigth));

        new MyRevoluteJoint(gw, terrainSx, b,terrainSx.getWidth()/2, -terrainSx.getHeight()/2,-roadWidth/2,-roadHeigth/2,true);

        a = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,b.getBody().getPositionX()+(roadWidth/2)+b.getWidth()/2, b.getBody().getPositionY(),0,0.1f, 0.3f, 0.1f,roadWidth,roadHeigth));
        addJointElement(gw, b, a);

        b = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,a.getBody().getPositionX()+(roadWidth/2)+a.getWidth()/2, a.getBody().getPositionY(),0,0.1f, 0.3f, 0.1f,roadWidth,roadHeigth));
        addJointElement(gw, a, b);

        GameObject last = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.ROAD,b.getBody().getPositionX()+(roadWidth/2)+b.getWidth()/2, b.getBody().getPositionY(),0,0.1f, 0.3f, 0.5f,roadWidth,roadHeigth));
        addJointElement(gw, b, last);
        new MyRevoluteJoint(gw, terrainDx, last,-terrainDx.getWidth()/2, -terrainDx.getHeight()/2,roadWidth/2,-roadHeigth/2,true);
    }

    private void addJointElement(GameWorld gw, GameObject b, GameObject a) {
        GameObject g = null;
        g = gw.addGameObject(new DynamicBoxGO(gw,b.getBody().getPositionX()+(roadWidth/2), b.getBody().getPositionY()));
        joints.add(g);
        new MyRevoluteJoint(gw, b, g, roadWidth/2,0,0,0,false);
        new MyRevoluteJoint(gw, a, g, -(roadWidth/2),0,0,0,false);
    }


}


/*
* GameObject firstBeam = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.BEAM,b.getBody().getPositionX()-roadWidth/2,b.getBody().getPositionY()-beamWidth/2,-45,0.0f,0.3f,0.1f,beamWidth,beamHeight));
            GameObject seconBeam = gw.addGameObject(new BridgeElementGO(gw, 0, BridgeElementType.BEAM,b.getBody().getPositionX()-roadWidth/2,b.getBody().getPositionY()-beamWidth/2,45,0.0f,0.3f,0.1f,beamWidth,beamHeight));
            new MyRevoluteJoint(gw,firstBeam,seconBeam,beamWidth/2,0,-beamWidth/2,0,true);
            new MyRevoluteJoint(gw,firstBeam,b,-beamWidth/2,0,-roadWidth/2,0,true);
            new MyRevoluteJoint(gw,b,seconBeam,roadWidth/2,0,beamWidth/2,0,true);
*
* */
