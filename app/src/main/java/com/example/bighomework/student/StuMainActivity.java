package com.example.bighomework.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bighomework.HelpPageActivity;
import com.example.bighomework.IPSetActivity;
import com.example.bighomework.InformationSetActivity;
import com.example.bighomework.LoginActivity;
import com.example.bighomework.R;
import com.example.bighomework.adapter.AdAdapter;
import com.example.bighomework.dao.AccountData;
import com.example.bighomework.service.LoginService;
import com.example.bighomework.teacher.TeaMainActivity;

public class StuMainActivity extends FragmentActivity {
    private String account;
    private ViewPager2 adVP;
    private static final int NUM_PAGES = 3;
    private AdAdapter adPagerAdapter;
    private AccountData AD=new AccountData();
    private String name;
    private String school;
    private String sentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account = getIntent().getStringExtra("account");
        setContentView(R.layout.activity_main_stu);
        adVP = findViewById(R.id.stu_ad_ViewPager);
        adVP.setAdapter(new AdAdapter(this));

        try {
            name=AD.getNameByAccount(account);
            school=AD.getSchoolByAccount(account);
            sentence=AD.getSentenceByAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView stu_name = (TextView) findViewById(R.id.stu_name);
        TextView stu_college = (TextView) findViewById(R.id.stu_college);
        TextView stu_sentence = (TextView) findViewById(R.id.stu_sentence);
        stu_name.setText("姓名："+name);
        stu_college.setText("所在学院："+school);
        stu_sentence.setText("座右铭："+sentence);

        ImageButton stu_find = (ImageButton) findViewById(R.id.stu_find);
        stu_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent=new Intent(StuMainActivity.this, StuGradeActivity.class);
                    intent.putExtra("account",account);
                    startActivity(intent);
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
                    Intent intent=new Intent(StuMainActivity.this, IPSetActivity.class);
                    intent.putExtra("account",account);
                    startActivity(intent);
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
                    String type = "stu";
                    Intent intent=new Intent(StuMainActivity.this, InformationSetActivity.class);
                    intent.putExtra("account",account);
                    intent.putExtra("type",type);
                    startActivity(intent);
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
                    Intent intent=new Intent(StuMainActivity.this, HelpPageActivity.class);
                    intent.putExtra("account",account);
                    startActivity(intent);
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
                    Intent intent=new Intent(StuMainActivity.this, CreditPie.class);
                    intent.putExtra("account",account);
                    startActivity(intent);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageView stu_photo = (ImageView) findViewById(R.id.stu_photo);
        try {
            AD.downloadImg(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stu_photo.setImageResource(R.drawable.photo);


    }
    @Override
    protected void onStart() {
        super.onStart();
        ImageView iv = findViewById(R.id.stu_photo);
        iv.setImageResource(R.drawable.photo);
        TextView name = findViewById(R.id.stu_name);
        TextView school = findViewById(R.id.stu_college);
        TextView sentence = findViewById(R.id.stu_sentence);
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