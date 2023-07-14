package com.example.mfaella.physicsapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.badlogic.androidgames.framework.impl.AndroidAudio;
import com.example.mfaella.physicsapp.R;

public class ChooseLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        hideNotificationBar();

        if(MainActivity.mainTheme == null){
            MainActivity.audio = new AndroidAudio(this);
            MainActivity.mainTheme = MainActivity.audio.newMusic("menuTheme.mp4");
        }

        if(!MainActivity.mainTheme.isPlaying()){
            MainActivity.mainTheme.play();
        }
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

    public void howToPlayButtonPressed(View view){
        Intent intent = new Intent(this, HowToPlayActivity.class);
        startActivity(intent);
    }

    public void startGameActivity(int level){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("level",level);
        startActivity(intent);
        MainActivity.mainTheme.stop();
    }
}