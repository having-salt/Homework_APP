package com.example.bighomework.teacher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
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
import java.util.List;
import java.util.Map;


public class TeaGradeActivity extends AppCompatActivity {

    private Context mContext = TeaGradeActivity.this;
    private List<Grade> grade_datas = new ArrayList<>();
    private List<String> stu_name = new ArrayList<>();
    private List<Double> grade_name = new ArrayList<>();
    private GradeAdapter gradeAdapter;
    private ListView listView;
    private AccountData AD=new AccountData();
    private ExamData ED=new ExamData();
    private String account;
    private String name;
    private String exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_grade);
        ImageButton returnBT = (ImageButton) findViewById(R.id.return_button);
        listView = (ListView) findViewById(R.id.grade_list);

        account = getIntent().getStringExtra("account");
        exam = getIntent().getStringExtra("exam");
        try {
            name = AD.getNameByAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Exam> exams = null;
        List<Grade> grade = null;
        try {
            exams = ED.getAllExams();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < exams.size();i++){
            if(exams.get(i).getExamName().equals(exam)){
                grade=exams.get(i).getGradeList();
                for(int j=0;j<grade.size();j++){
                    stu_name.add(grade.get(j).getStuName());
                    grade_name.add(grade.get(j).getGrade());
                }
            }
        }
        initData(grade_datas);

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

        EditText input_studentET = (EditText) findViewById(R.id.input_student);
        Button find = (Button) findViewById(R.id.find);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String input_student = input_studentET.getText().toString();
                    if(input_student.equals("")){
                        initData(grade_datas);
                    }else{
                        for(int i=0;i<grade_datas.size();i++){
                            if(grade_datas.get(i).getStuName().equals(input_student)){
                                double x=grade_datas.get(i).getGrade();
                                grade_datas=null;
                                grade_datas.add(new Grade(input_student,x));
                                break;
                            }
                        }
                    }
                } catch (RuntimeException e) {
                    input_studentET.setText("");
                    e.printStackTrace();
                }
            }
        });


        gradeAdapter = new GradeAdapter(this, grade_datas,exam);
        listView.setAdapter(gradeAdapter);
    }

    private void initData(List<Grade> grade_datas) {
        for (int i = 0; i < stu_name.size(); i++) {
            Log.d("tag","have_grade"+"."+stu_name.get(i)+"+"+grade_name.get(i));
            grade_datas.add(new Grade(stu_name.get(i),grade_name.get(i)));
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
                try {
                    ED.addGrade(exam,new Grade(input_name,Double.parseDouble(input_grade)));
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