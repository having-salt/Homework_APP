package com.example.bighomework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class IntroActivity extends AppCompatActivity {
    ImageView logo;
    LottieAnimationView anime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        logo = findViewById(R.id.logo);
        anime = findViewById(R.id.anime);

        logo.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        anime.animate().translationY(1600).setDuration(1000).setStartDelay(4000);
    }
}