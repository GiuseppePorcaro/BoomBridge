package com.example.mfaella.physicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        hideNotificationBar();
    }

    private void hideNotificationBar(){
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void tutorialButtonPressed(View view){
        startGameActivity(0);
    }

    public void levelOneButtonPressed(View view){
        startGameActivity(1);
    }

    public void levelTwoButtonPressed(View view){
        startGameActivity(2);
    }

    public void levelThreeButtonPressed(View view){
        startGameActivity(3);
    }

    public void startGameActivity(int level){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level",level);
        startActivity(intent);
    }
}