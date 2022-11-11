package com.example.bighomework.student;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bighomework.Entity.PieEntry;
import com.example.bighomework.R;
import com.example.bighomework.teacher.PieView;

import java.util.ArrayList;

public class CreditPie extends AppCompatActivity {
    private ImageButton returnBT;
    private PieView mPieView;
    private TextView creditTV;
    private TextView sumTV;
    private double grade[]={100.0,99.0,90.0,85.0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_pie);

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

        mPieView = findViewById(R.id.pieView);
        initPieView();

        creditTV=(TextView)findViewById(R.id.credit);
        sumTV=(TextView)findViewById(R.id.grade_sum);

        double sum=0.0;
        for(int i=0;i<grade.length;i++){
            sum=sum+grade[i];
        }

        sumTV.setText(String.valueOf(sum));
        creditTV.setText(String.valueOf(sum/grade.length));
    }

    private void initPieView() {
        mPieView.setColors(createColors());
        mPieView.setData(createData());
    }

    private ArrayList<PieEntry> createData() {
        ArrayList<PieEntry> pieLists = new ArrayList<>();
        pieLists.add(new PieEntry(20.00F, "不及格"));
        pieLists.add(new PieEntry(20.00F, "及格"));
        pieLists.add(new PieEntry(20.00F, "良"));
        pieLists.add(new PieEntry(20.00F, "优秀"));
        pieLists.add(new PieEntry(20.00F, "完美"));
        return pieLists;
    }

    private ArrayList<Integer> createColors() {
        ArrayList<Integer> colorLists = new ArrayList<>();
        colorLists.add(Color.parseColor("#EBBF03"));
        colorLists.add(Color.parseColor("#ff4d4d"));
        colorLists.add(Color.parseColor("#8d5ff5"));
        colorLists.add(Color.parseColor("#41D230"));
        colorLists.add(Color.parseColor("#4FAAFF"));
        return colorLists;
    }
}