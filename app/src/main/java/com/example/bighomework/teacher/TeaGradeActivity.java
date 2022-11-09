package com.example.bighomework.teacher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.bighomework.R;
import com.example.bighomework.model.Grade;

import java.util.ArrayList;
import java.util.List;


public class TeaGradeActivity extends AppCompatActivity {

    private Context mContext = TeaGradeActivity.this;

    private String[] names = {"吕布","赵云","典韦","关羽","马超","张飞","黄忠","许褚","孙策","太史慈","夏侯惇","张辽","夏侯渊","张郃"};
    private Double[] grades = {110.0,109.0,109.0,108.0,108.0,107.0,107.0,107.0,106.0,106.0,105.0,105.0,104.0,104.0};
    private List<Grade> grade_datas = new ArrayList<>();
    private GradeAdapter gradeAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_grade);
        ImageButton returnBT = (ImageButton) findViewById(R.id.return_button);

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

        EditText input_studentET = (EditText)findViewById(R.id.input_student);
        Button find = (Button) findViewById(R.id.find);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String input_student = input_studentET.getText().toString();
                } catch (RuntimeException e) {
                    input_studentET.setText("");
                    e.printStackTrace();
                }
            }
        });

        initData();
        gradeAdapter = new GradeAdapter(this,grade_datas);
        listView.setAdapter(gradeAdapter);
    }

    private void initData() {
        for (int i = 0; i < names.length; i++) {
            grade_datas.add(new Grade(names[i],grades[i]));
        }

    }

    public void BtnClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.add_grade_dialog, null);
        EditText stu_name = (EditText) v.findViewById(R.id.input_stu_name);
        EditText stu_grade = (EditText) v.findViewById(R.id.input_grade);
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
                String input_name = stu_name.getText().toString();
                String input_grade = stu_grade.getText().toString();
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