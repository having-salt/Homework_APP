package com.example.bighomework.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bighomework.HelpPageActivity;
import com.example.bighomework.IPSetActivity;
import com.example.bighomework.InformationSetActivity;
import com.example.bighomework.R;
import com.example.bighomework.adapter.AdAdapter;
import com.example.bighomework.dao.AccountData;
import com.example.bighomework.student.StuGradeActivity;
import com.example.bighomework.student.StuMainActivity;

public class TeaMainActivity extends AppCompatActivity {

    private ViewPager2 adVP;
    private String account;
    private AccountData AD=new AccountData();
    private String name;
    private String school;
    private String sentence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account = getIntent().getStringExtra("account");
        setContentView(R.layout.activity_main_tea);
        adVP = findViewById(R.id.tea_ad_ViewPager);
        adVP.setAdapter(new AdAdapter(this));

        try {
            name=AD.getNameByAccount(account);
            school=AD.getSchoolByAccount(account);
            sentence=AD.getSentenceByAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView tea_name = (TextView) findViewById(R.id.tea_name);
        TextView tea_college = (TextView) findViewById(R.id.tea_college);
        TextView tea_sentence = (TextView) findViewById(R.id.tea_sentence);
        tea_name.setText("姓名："+name);
        tea_college.setText("所在学院："+school);
        tea_sentence.setText("座右铭："+sentence);

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
                    Intent intent=new Intent(TeaMainActivity.this, IPSetActivity.class);
                    intent.putExtra("account",account);
                    startActivity(intent);
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
                    Intent intent=new Intent(TeaMainActivity.this, InformationSetActivity.class);
                    String type = "tea";
                    intent.putExtra("account",account);
                    intent.putExtra("type",type);
                    startActivity(intent);
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
                    Intent intent=new Intent(TeaMainActivity.this, HelpPageActivity.class);
                    intent.putExtra("account",account);
                    startActivity(intent);
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
                    Intent intent=new Intent(TeaMainActivity.this, TeaSubjectActivity.class);
                    intent.putExtra("account",account);
                    startActivity(intent);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton tea_statistics = (ImageButton) findViewById(R.id.tea_statistics);
        tea_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent=new Intent(TeaMainActivity.this, CountExam.class);
                    intent.putExtra("account",account);
                    startActivity(intent);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton tea_start_sign = (ImageButton) findViewById(R.id.tea_start_sign);
        tea_start_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent=new Intent(TeaMainActivity.this, TeaCheck.class);
                    intent.putExtra("account",account);
                    startActivity(intent);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageView tea_photo = (ImageView) findViewById(R.id.tea_photo);
        try {
            AD.downloadImg(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tea_photo.setImageResource(R.drawable.photo);
    }
    @Override
    protected void onStart() {
        super.onStart();
        ImageView iv = findViewById(R.id.tea_photo);
        iv.setImageResource(R.drawable.photo);
        TextView name = findViewById(R.id.tea_name);
        TextView school = findViewById(R.id.tea_college);
        TextView sentence = findViewById(R.id.tea_sentence);
        AccountData ad = new AccountData();
        try {
            name.setText(String.format("姓名： %s",ad.getNameByAccount(account)));
            school.setText(String.format("所在学院： %s",ad.getSchoolByAccount(account)));
            sentence.setText(String.format("座右铭： %s",ad.getSentenceByAccount(account)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}