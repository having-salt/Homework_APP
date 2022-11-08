package com.example.bighomework.teacher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.bighomework.R;

import java.util.ArrayList;
import java.util.List;

public class TeaGradeActivity extends AppCompatActivity {

    private ListView gradeLV;
    private List<String> ls = new ArrayList<String>();

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


        ListView grade_list=(ListView) findViewById(R.id.grade_list);
        ls.add("吕布：110");
        ls.add("赵云：109");
        ls.add("典韦：108");
        ls.add("关羽：108");
        ls.add("马超：107");
        ls.add("张飞：107");
        ls.add("黄忠：106");
        ls.add("许褚：106");
        ls.add("孙策：105");
        ls.add("太史慈：105");
        ls.add("夏侯惇：104");
        ls.add("张辽：104");
        ls.add("夏侯渊：103");
        ls.add("张郃：103");
        ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,ls);
        grade_list.setAdapter(adapter);
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