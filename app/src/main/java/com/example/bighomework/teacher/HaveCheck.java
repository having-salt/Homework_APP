package com.example.bighomework.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.bighomework.R;
import com.example.bighomework.service.SignInService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HaveCheck extends AppCompatActivity {
    private List<String> stu_name = new ArrayList<>();
    private String account;
    private String sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.have_check);

        ImageButton returnBT = (ImageButton) findViewById(R.id.return_button);
        ListView listView = (ListView) findViewById(R.id.have_check_list);
        account = getIntent().getStringExtra("account");
        sign = getIntent().getStringExtra("sign");
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
        try {
            stu_name = SignInService.getResult(sign);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(stu_name.size()!=0){
            Log.d("tag","有东西"+stu_name.get(0));
        }else{
            Log.d("tag","没东西");
        }

        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,stu_name));
    }
}