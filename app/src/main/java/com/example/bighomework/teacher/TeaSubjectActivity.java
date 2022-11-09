package com.example.bighomework.teacher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import com.example.bighomework.model.Exam;
import com.example.bighomework.model.Grade;
import com.example.bighomework.student.StuMainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeaSubjectActivity extends AppCompatActivity {
    private List<Exam> ls = new ArrayList<Exam>();
    private String exams[] ={"微积分","数据结构","移动互联网","软件构造","大学物理"};
    private ListView listView;
    private ExamAdapter examAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_subject);
        ImageButton returnBT = (ImageButton) findViewById(R.id.return_button);
        listView = (ListView) findViewById(R.id.exam_list);
        initData();
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



        examAdapter = new ExamAdapter(this,ls);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(TeaSubjectActivity.this, TeaGradeActivity.class));
            }
        });
        listView.setAdapter(examAdapter);
    }

    private void initData() {
        for (int i = 0; i < exams.length; i++) {
            ls.add(new Exam(exams[i]));
        }
    }
    public void jumpClick(View view){
            startActivity(new Intent(TeaSubjectActivity.this, TeaGradeActivity.class));
    }

    public void BtnClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.add_exam_dialog, null);
        EditText exam_name = (EditText) v.findViewById(R.id.input_exam_name);
        Button btn_sure = (Button) v.findViewById(R.id.dialog_btn_sure);
        Button btn_cancel = (Button) v.findViewById(R.id.dialog_btn_cancel);
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(v);

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String input_name = exam_name.getText().toString();
                dialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }
}