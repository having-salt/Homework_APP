package com.example.bighomework.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.bighomework.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeaGradeActivity extends AppCompatActivity {

    private ListView gradeLV;

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

        EditText input_stu_nameET = (EditText)findViewById(R.id.input_stu_name);
        EditText input_gradeET = (EditText)findViewById(R.id.input_grade);
        Button add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String input_stu_name = input_stu_nameET.getText().toString();
                    String input_grade = input_gradeET.getText().toString();



                } catch (RuntimeException e) {
                    input_stu_nameET.setText("");
                    input_gradeET.setText("");
                    e.printStackTrace();
                }
            }
        });

        EditText input_deleteET = (EditText)findViewById(R.id.input_delete);
        Button deleteBT = (Button) findViewById(R.id.delete);

        deleteBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String input_delete = input_deleteET.getText().toString();



                } catch (RuntimeException e) {
                    input_deleteET.setText("");
                    e.printStackTrace();
                }
            }
        });
        ListView grade_list=(ListView) findViewById(R.id.grade_list);
        List<String> ls = new ArrayList<String>();
        ls.add("张三：93");
        ls.add("李四： 95");
        ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,ls);
        grade_list.setAdapter(adapter);
    }
}