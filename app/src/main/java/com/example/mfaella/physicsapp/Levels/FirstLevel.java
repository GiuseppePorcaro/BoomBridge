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

import java.util.ArrayList;

public class FirstLevel implements Level{

    GameObject a;
    GameObject b;

    ArrayList<GameObject> roadJoints = new ArrayList<>();
    ArrayList<GameObject> scaffoldingJoints = new ArrayList<>();

    float roadWidth = 7.45f;
    float roadHeigth = 0.5f;
    float beamWidth = 5.0f;
    float beamHeight = 0.5f;

    @Override
    public void createLevel(GameWorld gw) {

        gw.setPlayButtonPressed(false);
        gw.setBudget(1000);
        gw.setTotalBudget(1000);
        gw.setBeamPrice(100);
        gw.setTimer(60);
        gw.setTimerToWin(5);
        gw.addGameObject(new BackgroundGO(gw));
        addTree(gw);
        gw.addGameObject(new StaticBudgetBoxGO(gw));
        gw.addGameObject(new StaticTimerGO(gw));
        gw.addGameObject(new TerroristGO(gw,-17,0.0f));

        gw.addGameObject(new ButtonGO(gw, ButtonType.PLAY,-22,-13));
        gw.addGameObject(new ButtonGO(gw, ButtonType.RESET,-18,-13));
        gw.addGameObject(new ButtonGO(gw,ButtonType.EXIT,22,-13));

        //Terrain
        GameObject terrainSx = gw.addGameObject(new TerrainGO(gw,-20,10));
        GameObject terrainDx = gw.addGameObject(new TerrainGO(gw,20,10));

        //Bridge
        createRoadBridge(gw, terrainDx,terrainSx);
        createBridgeScaffolding(gw,terrainDx,terrainSx);

        gw.addGameObject(new BombGO(gw,3.6f,0f,1.5f,1.5f));



    }

    private void addTree(GameWorld gw){
        gw.addGameObject(new StaticTreeGO(gw,-23,0.0f, 3.0f,5.0f));
        gw.addGameObject(new StaticTreeGO(gw,-21,-.7f, 3.0f,8.0f));
        gw.addGameObject(new StaticTreeGO(gw,21,-0.4f, 4.0f,7.0f));
    }

    private void createBridgeScaffolding(GameWorld gw, GameObject terrainDx, GameObject terrainSx){
        GameObject first, second;

        first = gw.addGameObject(new BridgeElementGO(gw,BridgeElementType.BEAM,terrainSx.getBody().getPositionX()+ terrainSx.getWidth()/2,terrainSx.getBody().getPositionY()+(beamHeight/2)- terrainSx.getHeight()/2-beamWidth/2,-90,beamWidth,beamHeight));
        second = gw.addGameObject(new BridgeElementGO(gw , BridgeElementType.BEAM,first.getBody().getPositionX()+(roadWidth), first.getBody().getPositionY(),-90,beamWidth,beamHeight));
        gw.addJoint(new MyRevoluteJoint(gw, terrainSx, first,terrainSx.getWidth()/2, -terrainSx.getHeight()/2,-beamWidth/2,-beamHeight/2,true).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw,second,roadJoints.get(0),-beamWidth/2,0,0,0,false).getJoint());
        addScaffoldingJoint(gw, first, second);

        for(int i = 0; i < 2; i++){
            first = gw.addGameObject(new BridgeElementGO(gw,BridgeElementType.BEAM,second.getBody().getPositionX(), second.getBody().getPositionY(),-90,beamWidth,beamHeight));
            second = gw.addGameObject(new BridgeElementGO(gw,  BridgeElementType.BEAM,first.getBody().getPositionX()+(roadWidth), first.getBody().getPositionY(),-90,beamWidth,beamHeight));
            gw.addJoint(new MyRevoluteJoint(gw,first,roadJoints.get(i),-beamWidth/2,0,0,0,false).getJoint());
            gw.addJoint(new MyRevoluteJoint(gw,second,roadJoints.get(i+1),-beamWidth/2,0,0,0,false).getJoint());
            addScaffoldingJoint(gw, first, second);
        }

        first = gw.addGameObject(new BridgeElementGO(gw,BridgeElementType.BEAM,second.getBody().getPositionX(), second.getBody().getPositionY(),-90,beamWidth,beamHeight));
        second = gw.addGameObject(new BridgeElementGO(gw,  BridgeElementType.BEAM,first.getBody().getPositionX()+(roadWidth), first.getBody().getPositionY(),-90,beamWidth,beamHeight));
        gw.addJoint(new MyRevoluteJoint(gw,first,roadJoints.get(2),-beamWidth/2,0,0,0,false).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw,second,terrainDx,-beamWidth/2,0, -terrainDx.getWidth()/2, -terrainDx.getHeight()/2, false).getJoint());
        addScaffoldingJoint(gw, first, second);

        first = gw.addGameObject(new BridgeElementGO(gw,BridgeElementType.BEAM,terrainSx.getBody().getPositionX()+ terrainSx.getWidth()/2+roadWidth,terrainSx.getBody().getPositionY()+(beamHeight/2)- terrainSx.getHeight()/2-beamWidth/2,0,roadWidth,beamHeight));
        gw.addJoint(new MyRevoluteJoint(gw, first, scaffoldingJoints.get(0),-roadWidth/2,0,0,0,false).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw, first, scaffoldingJoints.get(1),roadWidth/2,0,0,0,false).getJoint());

        first = gw.addGameObject(new BridgeElementGO(gw,BridgeElementType.BEAM,first.getBody().getPositionX()+ terrainSx.getWidth()/2+roadWidth,terrainSx.getBody().getPositionY()+(beamHeight/2)- terrainSx.getHeight()/2-beamWidth/2,0,roadWidth,beamHeight));
        gw.addJoint(new MyRevoluteJoint(gw, first, scaffoldingJoints.get(1),-roadWidth/2,0,0,0,false).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw, first, scaffoldingJoints.get(2),roadWidth/2,0,0,0,false).getJoint());

        first = gw.addGameObject(new BridgeElementGO(gw,BridgeElementType.BEAM,first.getBody().getPositionX()+ terrainSx.getWidth()/2+roadWidth,terrainSx.getBody().getPositionY()+(beamHeight/2)- terrainSx.getHeight()/2-beamWidth/2,0,roadWidth,beamHeight));
        gw.addJoint(new MyRevoluteJoint(gw, first, scaffoldingJoints.get(2),-roadWidth/2,0,0,0,false).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw, first, scaffoldingJoints.get(3),roadWidth/2,0,0,0,false).getJoint());

    }

    private void addScaffoldingJoint(GameWorld gw, GameObject first, GameObject second) {
        GameObject g = null;
        g = gw.addGameObject(new DynamicJointGO(gw, first.getBody().getPositionX()+(roadWidth/2), first.getBody().getPositionY()-beamWidth/2,1.0f,1.0f));
        scaffoldingJoints.add(g);
        gw.addJoint(new MyRevoluteJoint(gw, first, g, beamWidth/2,0,0,0,false).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw, second, g, (beamWidth/2),0,0,0,false).getJoint());
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

    private void addJointElement(GameWorld gw, GameObject b, GameObject a, ArrayList<GameObject> listaJoints, float width) {
        GameObject g = null;
        g = gw.addGameObject(new DynamicJointGO(gw,b.getBody().getPositionX()+(width/2), b.getBody().getPositionY(),1.0f,1.0f));
        listaJoints.add(g);
        gw.addJoint(new MyRevoluteJoint(gw, b, g, width/2,0,0,0,false).getJoint());
        gw.addJoint(new MyRevoluteJoint(gw, a, g, -(width/2),0,0,0,false).getJoint());
    }


}
