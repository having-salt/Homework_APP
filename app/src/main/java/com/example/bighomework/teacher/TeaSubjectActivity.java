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
import com.example.bighomework.dao.AccountData;
import com.example.bighomework.dao.ExamData;
import com.example.bighomework.model.Exam;
import com.example.bighomework.model.Grade;
import com.example.bighomework.student.StuGradeActivity;
import com.example.bighomework.student.StuMainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeaSubjectActivity extends AppCompatActivity {
    private List<Exam> ls = new ArrayList<Exam>();
    private String exams[];
    private ListView listView;
    private ExamAdapter examAdapter;
    private AccountData AD=new AccountData();
    private ExamData ED=new ExamData();
    private String account;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_subject);
        ImageButton returnBT = (ImageButton) findViewById(R.id.return_button);
        listView = (ListView) findViewById(R.id.exam_list);

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
                    if(input_student.equals("")){
                        initData();
                    }else{
                        for(int i=0;i<ls.size();i++){
                            if(ls.get(i).getExamName().equals(input_student)){
                                ls=null;
                                ls.add(new Exam(input_student));
                                break;
                            }
                        }
                    }

                } catch (RuntimeException e) {
                    input_examET.setText("");
                    e.printStackTrace();
                }
            }
        });


        examAdapter = new ExamAdapter(this, ls,account);
        listView.setAdapter(examAdapter);
    }

    private void initData() {
        for (int i = 0; i < exams.length; i++) {
            ls.add(new Exam(exams[i]));
        }
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
                try {
                    ED.addExam(input_name,name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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