package com.example.mfaella.physicsapp.activities;

import static com.example.mfaella.physicsapp.activities.MainActivity.mainTheme;

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

        if(mainTheme == null){
            MainActivity.audio = new AndroidAudio(this);
            mainTheme = MainActivity.audio.newMusic(getResources().getString(R.string.mainTheme));
        }

        if(!mainTheme.isPlaying()){
            mainTheme.play();
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
        mainTheme.stop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mainTheme.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!mainTheme.isPlaying()){
            mainTheme = MainActivity.audio.newMusic(getResources().getString(R.string.mainTheme));
        }
        mainTheme.play();
    }
}