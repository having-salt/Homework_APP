package com.example.bighomework.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.bighomework.HelpPageActivity;
import com.example.bighomework.IPSetActivity;
import com.example.bighomework.InformationSetActivity;
import com.example.bighomework.LoginActivity;
import com.example.bighomework.R;
import com.example.bighomework.adapter.AdAdapter;
import com.example.bighomework.service.LoginService;
import com.example.bighomework.teacher.TeaMainActivity;

public class StuMainActivity extends FragmentActivity {
    private String account;
    private ViewPager2 adVP;
    private static final int NUM_PAGES = 3;
    private AdAdapter adPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account = getIntent().getStringExtra("account");
        setContentView(R.layout.activity_main_stu);
        adVP = findViewById(R.id.stu_ad_ViewPager);
        adVP.setAdapter(new AdAdapter(this));

        ImageButton stu_find = (ImageButton) findViewById(R.id.stu_find);
        stu_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    startActivity(new Intent(StuMainActivity.this, StuGradeActivity.class));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton stu_out = (ImageButton) findViewById(R.id.stu_out);
        stu_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    finish();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton stu_setting = (ImageButton) findViewById(R.id.stu_setting);
        stu_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    startActivity(new Intent(StuMainActivity.this, IPSetActivity.class));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton stu_information = (ImageButton) findViewById(R.id.stu_information);
        stu_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    startActivity(new Intent(StuMainActivity.this, InformationSetActivity.class));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton stu_help = (ImageButton) findViewById(R.id.stu_help);
        stu_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    startActivity(new Intent(StuMainActivity.this, HelpPageActivity.class));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton stu_gpa = (ImageButton) findViewById(R.id.stu_gpa);
        stu_gpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    startActivity(new Intent(StuMainActivity.this, CreditPie.class));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}