package com.example.bighomework.teacher;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bighomework.R;
import com.example.bighomework.adapter.AdAdapter;

public class TeaMainActivity extends AppCompatActivity {

    private ViewPager2 adVP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tea);
        adVP = findViewById(R.id.tea_ad_ViewPager);
        adVP.setAdapter(new AdAdapter(this));
    }
}