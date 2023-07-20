package com.example.mfaella.physicsapp.Levels;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.gameObjects.BackgroundGO;
import com.example.mfaella.physicsapp.gameObjects.BombGO;
import com.example.mfaella.physicsapp.gameObjects.BridgeElementGO;
import com.example.mfaella.physicsapp.gameObjects.BridgeElementType;
import com.example.mfaella.physicsapp.gameObjects.ButtonGO;
import com.example.mfaella.physicsapp.gameObjects.ButtonType;
import com.example.mfaella.physicsapp.gameObjects.DynamicJointGO;
import com.example.mfaella.physicsapp.gameObjects.GameObject;
import com.example.mfaella.physicsapp.gameObjects.StaticBudgetBoxGO;
import com.example.mfaella.physicsapp.gameObjects.StaticTimerGO;
import com.example.mfaella.physicsapp.gameObjects.StaticTreeGO;
import com.example.mfaella.physicsapp.gameObjects.TerrainGO;
import com.example.mfaella.physicsapp.gameObjects.TerroristGO;
import com.example.mfaella.physicsapp.joints.MyRevoluteJoint;
import com.google.fpl.liquidfun.Vec2;

import java.util.ArrayList;

public class SecondLevel implements Level{

    private GameObject a;
    private GameObject b;
    ArrayList<GameObject> roadJoints = new ArrayList<>();

    ArrayList<GameObject> scaffoldingJoints = new ArrayList<>();
    float roadWidth = 7.5f;
    float roadHeigth = 0.5f;
    float beamWidth = 8.7f;
    float beamHeight = 0.5f;

    @Override
    public void createLevel(GameWorld gw) {
        gw.setPlayButtonPressed(false);
        gw.setBudget(600);
        gw.setTotalBudget(600);
        gw.setBeamPrice(100);
        gw.setTimer(40);
        gw.setTimerToWin(5);
        gw.addGameObject(new BackgroundGO(gw));
        addTree(gw);
        gw.addGameObject(new StaticBudgetBoxGO(gw));
        gw.addGameObject(new StaticTimerGO(gw));
        gw.addGameObject(new TerroristGO(gw,-16,0.0f));

        gw.addGameObject(new ButtonGO(gw, ButtonType.PLAY,-22,-13));
        gw.addGameObject(new ButtonGO(gw, ButtonType.RESET,-18,-13));
        gw.addGameObject(new ButtonGO(gw,ButtonType.EXIT,22,-13));

        //Terrain
        GameObject terrainSx = gw.addGameObject(new TerrainGO(gw,-20,10));
        GameObject terrainDx = gw.addGameObject(new TerrainGO(gw,20,10));
        GameObject terrainCenter = gw.addGameObject(new TerrainGO(gw,0,14.3f));
        gw.addGameObject(new BombGO(gw,5.5f,2.9f,1.5f,1.5f));
        gw.addGameObject(new BombGO(gw,-4.8f,4f,1.5f,1.5f));

        Vec2 point = new Vec2(-14.76f,12.42f);
        createRoadBridge(gw,terrainDx,terrainSx);

        GameObject beam1 = gw.addGameObject(new BridgeElementGO(gw , BridgeElementType.BEAM,-12f, 9,-45,beamWidth,beamHeight));
        GameObject beam2 = gw.addGameObject(new BridgeElementGO(gw , BridgeElementType.BEAM,-4.4f,4.3f,-20,beamWidth,beamHeight));
        gw.addJoint(new MyRevoluteJoint(gw, terrainSx, beam1,terrainSx.getBody().getLocalPoint(point).getX(),terrainSx.getBody().getLocalPoint(point).getY(),-beamWidth/2,0,false).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw,beam2,roadJoints.get(1),beamWidth/2,0,0,0,false).getJoint());
        addJointElement(gw,beam1,beam2,scaffoldingJoints,beamWidth);

        beamWidth = 6.5f;
        beam1 = gw.addGameObject(new BridgeElementGO(gw , BridgeElementType.BEAM,terrainSx.getBody().getPositionX()+(beamWidth/2)+ terrainSx.getWidth()/2-0.2f, terrainSx.getBody().getPositionY()+(beamWidth/2)- terrainSx.getHeight()/2-1.3f,23,beamWidth,beamHeight));
        gw.addJoint(new MyRevoluteJoint(gw, terrainSx, beam1,terrainSx.getWidth()/2, -terrainSx.getHeight()/2,-beamWidth/2,-beamHeight/2,true).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw, beam1, scaffoldingJoints.get(0), beamWidth/2, 0,0,0,false).getJoint());


        beamWidth = 3.4f;
        beam1 = gw.addGameObject(new BridgeElementGO(gw , BridgeElementType.BEAM,terrainSx.getBody().getPositionX()+terrainSx.getWidth()/2+roadWidth-0.6f, 4.2f,-65,beamWidth,beamHeight));
        gw.addJoint(new MyRevoluteJoint(gw, beam1, roadJoints.get(0), -beamWidth/2,0,0,0,false).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw, beam1, scaffoldingJoints.get(0), beamWidth/2, 0,0,0,false).getJoint());


        beamWidth = 4.2f;
        point.setX(0);
        point.setY(7);
        beam1 = gw.addGameObject(new BridgeElementGO(gw, BridgeElementType.BEAM,0.0f,4.7f,-90,beamWidth,beamHeight));
        gw.addJoint(new MyRevoluteJoint(gw,beam1, roadJoints.get(1), beamWidth/2,0,0,0,false).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw,beam1, terrainCenter, -beamWidth/2,0,terrainCenter.getBody().getLocalPoint(point).getX(),terrainCenter.getBody().getLocalPoint(point).getY()-0.4f,false).getJoint());


        point = new Vec2(14.76f,12.42f);
        beamWidth = 8.7f;
        beam1 = gw.addGameObject(new BridgeElementGO(gw , BridgeElementType.BEAM,12f, 9,40,beamWidth,beamHeight));
        beam2 = gw.addGameObject(new BridgeElementGO(gw , BridgeElementType.BEAM,4.4f,4.3f,20,beamWidth,beamHeight));
        gw.addJoint(new MyRevoluteJoint(gw, terrainDx, beam1,terrainDx.getBody().getLocalPoint(point).getX(),terrainDx.getBody().getLocalPoint(point).getY(),beamWidth/2,0,false).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw,beam2,roadJoints.get(1),-beamWidth/2,0,0,0,false).getJoint());
        addJointElement(gw,beam2,beam1,scaffoldingJoints,beamWidth);

        beamWidth = 6.5f;
        beam1 = gw.addGameObject(new BridgeElementGO(gw , BridgeElementType.BEAM,terrainDx.getBody().getPositionX()-(beamWidth/2)-terrainDx.getWidth()/2+0.2f, terrainDx.getBody().getPositionY()+(beamWidth/2)- terrainDx.getHeight()/2-1.3f,-23,beamWidth,beamHeight));
        gw.addJoint(new MyRevoluteJoint(gw, terrainDx, beam1,-terrainDx.getWidth()/2, -terrainDx.getHeight()/2,-beamWidth/2,-beamHeight/2,true).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw, beam1, scaffoldingJoints.get(1), beamWidth/2, 0,0,0,false).getJoint());


        beamWidth = 3.4f;
        beam1 = gw.addGameObject(new BridgeElementGO(gw , BridgeElementType.BEAM,terrainDx.getBody().getPositionX()-terrainDx.getWidth()/2-roadWidth+0.6f, 4.2f,65,beamWidth,beamHeight));
        gw.addJoint(new MyRevoluteJoint(gw, beam1, roadJoints.get(2), -beamWidth/2,0,0,0,false).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw, beam1, scaffoldingJoints.get(1), beamWidth/2, 0,0,0,false).getJoint());


    }

    private void addTree(GameWorld gw){
        gw.addGameObject(new StaticTreeGO(gw,-23,0.0f, 3.0f,5.0f));
        gw.addGameObject(new StaticTreeGO(gw,-21,-.7f, 3.0f,8.0f));
        gw.addGameObject(new StaticTreeGO(gw,21,-0.4f, 4.0f,7.0f));
    }

    private void createRoadBridge(GameWorld gw, GameObject terrainDx, GameObject terrainSx) {

        b = gw.addGameObject(new BridgeElementGO(gw,  BridgeElementType.ROAD, terrainSx.getBody().getPositionX()+(roadWidth/2)+ terrainSx.getWidth()/2, terrainSx.getBody().getPositionY()+(roadHeigth/2)- terrainSx.getHeight()/2,0,roadWidth,roadHeigth));

        gw.addJoint(new MyRevoluteJoint(gw, terrainSx, b,terrainSx.getWidth()/2, -terrainSx.getHeight()/2,-roadWidth/2,-roadHeigth/2,true).getJoint());

        a = gw.addGameObject(new BridgeElementGO(gw, BridgeElementType.ROAD,b.getBody().getPositionX()+(roadWidth/2)+b.getWidth()/2, b.getBody().getPositionY(),0,roadWidth,roadHeigth));
        addJointElement(gw, b, a,roadJoints,roadWidth);

        b = gw.addGameObject(new BridgeElementGO(gw,  BridgeElementType.ROAD,a.getBody().getPositionX()+(roadWidth/2)+a.getWidth()/2, a.getBody().getPositionY(),0,roadWidth,roadHeigth));
        addJointElement(gw, a, b,roadJoints,roadWidth);

        GameObject last = gw.addGameObject(new BridgeElementGO(gw,  BridgeElementType.ROAD,b.getBody().getPositionX()+(roadWidth/2)+b.getWidth()/2, b.getBody().getPositionY(),0,roadWidth,roadHeigth));
        addJointElement(gw, b, last,roadJoints,roadWidth);
        gw.addJoint(new MyRevoluteJoint(gw, terrainDx, last,-terrainDx.getWidth()/2, -terrainDx.getHeight()/2,roadWidth/2,-roadHeigth/2,true).getJoint());
    }

    private void addJointElement(GameWorld gw, GameObject a, GameObject b, ArrayList<GameObject> listaJoints, float width) {
        GameObject g = null;
        g = gw.addGameObject(new DynamicJointGO(gw,a.getBody().getPositionX()+(width/2), a.getBody().getPositionY(),1.0f,1.0f));
        listaJoints.add(g);
        gw.addJoint(new MyRevoluteJoint(gw, a, g, width/2,0,0,0,false).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw, b, g, -(width/2),0,0,0,false).getJoint());
    }
}
