package com.example.mfaella.physicsapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.badlogic.androidgames.framework.Music;
import com.badlogic.androidgames.framework.Sound;
import com.badlogic.androidgames.framework.impl.AndroidAudio;
import com.example.mfaella.physicsapp.R;

public class MainActivity extends AppCompatActivity {


    public static Music mainTheme = null;
    public static AndroidAudio audio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        hideNotificationBar();


        audio = new AndroidAudio(this);
        mainTheme = audio.newMusic(getResources().getString(R.string.mainTheme));
        mainTheme.setLooping(true);
        mainTheme.play();

    }

    public void playButtonPressed(View view){
        Intent intent = new Intent(this, ChooseLevelActivity.class);
        startActivity(intent);
    }

    public void aboutButtonPressed(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    private void hideNotificationBar(){
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
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
        mainTheme.play();
    }
}