package com.example.bighomework.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bighomework.Entity.PieEntry;
import com.example.bighomework.R;
import com.example.bighomework.dao.AccountData;
import com.example.bighomework.dao.ExamData;
import com.example.bighomework.model.Exam;
import com.example.bighomework.model.Grade;

import java.util.ArrayList;
import java.util.List;

public class GradePie extends AppCompatActivity {
    private ImageButton returnBT;
    private PieView mPieView;
    private AccountData AD=new AccountData();
    private ExamData ED=new ExamData();
    private String account;
    private String name;
    private String exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_pie);

        account = getIntent().getStringExtra("account");
        exam = getIntent().getStringExtra("exam");
        try {
            name = AD.getNameByAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }

        float a=0;
        float b=0;
        float c=0;
        float d=0;
        float e=0;
        List<Exam> exams = null;
        try {
            exams=ED.getAllExams();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for(int i=0;i<exams.size();i++){
            if(exams.get(i).getExamName().equals(exam)){
                List<Grade> grades=exams.get(i).getGradeList();
                for(int j=0;j<grades.size();j++){
                    if(0.0<=grades.get(j).getGrade()&&grades.get(j).getGrade()<60.0){
                        a+=1.0;
                    }else if(60.0<=grades.get(j).getGrade()&&grades.get(j).getGrade()<70.0){
                        b+=1.0;
                    }else if(70.0<=grades.get(j).getGrade()&&grades.get(j).getGrade()<80.0){
                        c+=1.0;
                    }else if(80.0<=grades.get(j).getGrade()&&grades.get(j).getGrade()<90.0){
                        d+=1.0;
                    }else {
                        e+=1.0;
                    }
                }
            }
        }

        returnBT=(ImageButton)findViewById(R.id.return_button);
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

        mPieView = findViewById(R.id.pieView);
        initPieView(a,b,c,d,e);
    }

    private void initPieView(float a,float b,float c,float d,float e) {
        mPieView.setColors(createColors());
        mPieView.setData(createData(a,b,c,d,e));
    }

    private ArrayList<PieEntry> createData(float a,float b,float c,float d,float e) {
        ArrayList<PieEntry> pieLists = new ArrayList<>();
        pieLists.add(new PieEntry(a, "不及格"));
        pieLists.add(new PieEntry(b, "及格"));
        pieLists.add(new PieEntry(c, "良"));
        pieLists.add(new PieEntry(d, "优秀"));
        pieLists.add(new PieEntry(e, "完美"));
        return pieLists;
    }

    private ArrayList<Integer> createColors() {
        ArrayList<Integer> colorLists = new ArrayList<>();
        colorLists.add(Color.parseColor("#EBBF03"));
        colorLists.add(Color.parseColor("#ff4d4d"));
        colorLists.add(Color.parseColor("#8d5ff5"));
        colorLists.add(Color.parseColor("#41D230"));
        colorLists.add(Color.parseColor("#4FAAFF"));
        return colorLists;
    }
}