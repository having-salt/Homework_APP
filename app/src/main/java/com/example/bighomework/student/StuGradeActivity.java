package com.example.bighomework.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.bighomework.R;

import java.util.ArrayList;
import java.util.List;

public class StuGradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_grade);

        ImageButton returnBT = (ImageButton) findViewById(R.id.return_button);
        EditText input_subjectET = (EditText)findViewById(R.id.input_subject);
        Button find = (Button) findViewById(R.id.find);
        ListView subject_list=(ListView) findViewById(R.id.subject_list);


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

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String input_subject = input_subjectET.getText().toString();




                } catch (RuntimeException e) {
                    input_subjectET.setText("");
                    e.printStackTrace();
                }
            }
        });

        List<String> ls = new ArrayList<String>();
        ls.add("数据结构：93");
        ls.add("微积分： 95");
        ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,ls);
        subject_list.setAdapter(adapter);
    }
}