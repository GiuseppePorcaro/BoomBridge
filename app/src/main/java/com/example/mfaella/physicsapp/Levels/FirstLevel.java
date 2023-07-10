package com.example.mfaella.physicsapp.Levels;

import com.example.mfaella.physicsapp.GameWorld;
import com.example.mfaella.physicsapp.gameObjects.BackgroundGO;
import com.example.mfaella.physicsapp.gameObjects.DynamicTriangleGO;
import com.example.mfaella.physicsapp.gameObjects.StaticBudgetBoxGO;
import com.example.mfaella.physicsapp.gameObjects.StaticTimerGO;
import com.example.mfaella.physicsapp.gameObjects.StaticTreeGO;

public class FirstLevel implements Level{

    @Override
    public void createLevel(GameWorld gw) {
        gw.setPlayButtonPressed(false);
        gw.setBudget(1000);
        gw.setBeamPrice(100);
        gw.setTimer(60);
        gw.setTimerToWin(5);
        gw.addGameObject(new BackgroundGO(gw));
        addTree(gw);
        gw.addGameObject(new StaticBudgetBoxGO(gw));
        gw.addGameObject(new StaticTimerGO(gw));
    }

    private void addTree(GameWorld gw){
        gw.addGameObject(new StaticTreeGO(gw,-23,0.0f, 3.0f,5.0f));
        gw.addGameObject(new StaticTreeGO(gw,-21,-.7f, 3.0f,8.0f));
        gw.addGameObject(new StaticTreeGO(gw,21,-0.4f, 4.0f,7.0f));
    }
}
