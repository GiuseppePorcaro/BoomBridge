package com.example.mfaella.physicsapp.activities;

import static com.example.mfaella.physicsapp.activities.MainActivity.mainTheme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import com.example.mfaella.physicsapp.R;

public class WinActivity extends AppCompatActivity {

    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        hideNotificationBar();

        ratingBar = (RatingBar) findViewById(R.id.winActivityRatingBar);
        ratingBar.setNumStars(3);
        ratingBar.setIsIndicator(true);

        float timer = getIntent().getFloatExtra("timer",0);
        float usedTime = getIntent().getFloatExtra("usedTime",0);
        float budget = getIntent().getFloatExtra("budget",0);
        float totalBudget = getIntent().getFloatExtra("totalBudget",0);

        float rating = 1;

        if(usedTime <= timer/2){
            rating++;
        }

        if(budget <= totalBudget/2){
            rating++;
        }

        ratingBar.setRating(rating);

        if(!mainTheme.isPlaying()){
            mainTheme.play();
        }

    }

    public void closeButtonWinActivityPressed(View view){
        finish();
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