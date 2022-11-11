package com.example.bighomework.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bighomework.Entity.PieEntry;
import com.example.bighomework.R;

import java.util.ArrayList;

public class GradePie extends AppCompatActivity {
    private ImageButton returnBT;
    private PieView mPieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_pie);

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