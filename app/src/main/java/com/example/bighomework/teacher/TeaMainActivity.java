package com.example.bighomework.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bighomework.HelpPageActivity;
import com.example.bighomework.IPSetActivity;
import com.example.bighomework.InformationSetActivity;
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

        ImageButton tea_out = (ImageButton) findViewById(R.id.tea_out);
        tea_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    finish();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton tea_setting = (ImageButton) findViewById(R.id.tea_setting);
        tea_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    startActivity(new Intent(TeaMainActivity.this, IPSetActivity.class));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton tea_information = (ImageButton) findViewById(R.id.tea_information);
        tea_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    startActivity(new Intent(TeaMainActivity.this, InformationSetActivity.class));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton tea_help = (ImageButton) findViewById(R.id.tea_help);
        tea_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    startActivity(new Intent(TeaMainActivity.this, HelpPageActivity.class));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton tea_edit = (ImageButton) findViewById(R.id.tea_edit);
        tea_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    startActivity(new Intent(TeaMainActivity.this, TeaSubjectActivity.class));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}