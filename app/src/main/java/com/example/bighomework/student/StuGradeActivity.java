package com.example.bighomework.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.bighomework.R;
import com.example.bighomework.dao.AccountData;
import com.example.bighomework.dao.ExamData;
import com.example.bighomework.model.Exam;
import com.example.bighomework.model.Grade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StuGradeActivity extends AppCompatActivity {
    private AccountData AD=new AccountData();
    private ExamData ED=new ExamData();
    private String account;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_grade);

        ImageButton returnBT = (ImageButton) findViewById(R.id.return_button);
        EditText input_subjectET = (EditText)findViewById(R.id.input_subject);
        Button find = (Button) findViewById(R.id.find);
        ListView subject_list=(ListView) findViewById(R.id.subject_list);

        account = getIntent().getStringExtra("account");
        try {
            name = AD.getNameByAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }

        returnBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    finish();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String input_subject = input_subjectET.getText().toString();




                } catch (RuntimeException e) {
                    input_subjectET.setText("");
                    e.printStackTrace();
                }
            }
        });
        List<Exam> ls = new ArrayList<Exam>();
        List<String> ls3 = new ArrayList<String>();
        try {
            ls=ED.getAllExams();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Iterator<Exam> t=ls.iterator();
        while(t.hasNext()) {
            Exam s=t.next();
            List<Grade> ls2;
            ls2=s.getGradeList();
            Iterator<Grade> t2=ls2.iterator();
            while(t2.hasNext()){
                Grade s2=t2.next();
                if(s2.getStuName().equals(name)){
                    ls3.add(s.getExamName()+"："+s2.getGrade());
                }
            }
        }
        ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,ls3);
        subject_list.setAdapter(adapter);
    }
}