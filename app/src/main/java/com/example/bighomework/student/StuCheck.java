package com.example.bighomework.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.bighomework.R;
import com.example.bighomework.dao.AccountData;
import com.example.bighomework.dao.ExamData;
import com.example.bighomework.model.Exam;
import com.example.bighomework.model.Grade;
import com.example.bighomework.model.Sign;
import com.example.bighomework.service.SignInService;
import com.example.bighomework.teacher.GradeAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StuCheck extends AppCompatActivity {
    private ListView listView;
    private AccountData AD=new AccountData();
    private ExamData ED=new ExamData();
    private String account;
    private String name;
    private List<Sign> check_name_list=new ArrayList<>();
    private List<String> check_list=new ArrayList<>();
    private CheckAdapter checkAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_check);

        ImageButton returnBT = (ImageButton) findViewById(R.id.return_button);
        listView = (ListView) findViewById(R.id.check_list);

        account = getIntent().getStringExtra("account");
        try {
            name = AD.getNameByAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }


        returnBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    finish();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            check_name_list = SignInService.getAllSigns();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0 ; i < check_name_list.size() ; i ++){
            check_list.add(check_name_list.get(i).getSignName());
        }
        checkAdapter = new CheckAdapter(this, check_list,account);
        listView.setAdapter(checkAdapter);
    }
}