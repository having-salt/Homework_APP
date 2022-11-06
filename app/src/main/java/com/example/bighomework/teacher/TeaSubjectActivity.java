package com.example.bighomework.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import com.example.bighomework.LoginActivity;
import com.example.bighomework.R;
import com.example.bighomework.student.StuMainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeaSubjectActivity extends AppCompatActivity {
    private ListView examLV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_subject);
        ImageButton returnBT = (ImageButton) findViewById(R.id.return_button);

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

        EditText input_examET = (EditText) findViewById(R.id.input_exam);
        Button find = (Button) findViewById(R.id.find);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String input_student = input_examET.getText().toString();


                } catch (RuntimeException e) {
                    input_examET.setText("");
                    e.printStackTrace();
                }
            }
        });

        EditText input_exam_nameET = (EditText) findViewById(R.id.input_exam_name);
        Button add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String input_stu_name = input_exam_nameET.getText().toString();


                } catch (RuntimeException e) {
                    input_exam_nameET.setText("");
                    e.printStackTrace();
                }
            }
        });

        EditText input_deleteET = (EditText) findViewById(R.id.input_delete);
        Button deleteBT = (Button) findViewById(R.id.delete);

        deleteBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String input_delete = input_deleteET.getText().toString();


                } catch (RuntimeException e) {
                    input_deleteET.setText("");
                    e.printStackTrace();
                }
            }
        });
        ListView exam_list=(ListView) findViewById(R.id.exam_list);
        List<String> ls = new ArrayList<String>();
        ls.add("数据结构");
        ls.add("微积分");
        ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,ls);
        exam_list.setAdapter(adapter);
        exam_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(TeaSubjectActivity.this, TeaGradeActivity.class));
            }
        });
    }
}