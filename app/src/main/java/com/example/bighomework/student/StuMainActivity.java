package com.example.bighomework.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.bighomework.R;
import com.example.bighomework.adapter.AdAdapter;

public class StuMainActivity extends FragmentActivity {
    private ViewPager2 adVP;
    private static final int NUM_PAGES = 3;
    private AdAdapter adPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_stu);
        adVP = findViewById(R.id.ad_ViewPager);
        adVP.setAdapter(new AdAdapter(this));

    }
}