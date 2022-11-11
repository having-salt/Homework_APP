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

public class CountExam extends AppCompatActivity {

    private ImageButton returnBT;
    private ListView LV;
    private String[] exams={"数据结构","微积分","大学物理","移动互联网"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_exam);

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
                startActivity(new Intent(CountExam.this, GradePie.class));
            }
        });
    }
}