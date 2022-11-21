package com.example.bighomework.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.bighomework.R;
import com.example.bighomework.dao.AccountData;
import com.example.bighomework.dao.ExamData;

import java.util.List;
import java.util.Map;

public class CountExam extends AppCompatActivity {

    private ImageButton returnBT;
    private ListView LV;
    private String[] exams;
    private AccountData AD=new AccountData();
    private ExamData ED=new ExamData();
    private String account;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_exam);

        account = getIntent().getStringExtra("account");
        try {
            name = AD.getNameByAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> exam_to_name = null;
        try {
            exam_to_name = ED.getExamToTea();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> exam_name=null;
        for (String key : exam_to_name.keySet()) {
            if(key.equals(name)){
                exam_name.add(exam_to_name.get(key));
            }
        }
        for(int i=0;i<exam_name.size();i++){
            exams[i]=exam_name.get(i);
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

        LV=findViewById(R.id.count_exam_list);
        LV.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,exams));
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long  id) {
                Intent intent=new Intent(CountExam.this, GradePie.class);
                intent.putExtra("account",account);
                intent.putExtra("exam",exam_name.get(position));
                startActivity(intent);
            }
        });
    }
}