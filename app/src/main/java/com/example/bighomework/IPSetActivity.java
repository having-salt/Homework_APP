package com.example.bighomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.bighomework.service.LoginService;
import com.example.bighomework.student.StuMainActivity;
import com.example.bighomework.teacher.TeaMainActivity;

public class IPSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipset);

        EditText IP_serET = (EditText)findViewById(R.id.IP_services);
        EditText ser_portET = (EditText)findViewById(R.id.service_port);

        Button submitBT = (Button) findViewById(R.id.submit);

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

        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String service_IP = IP_serET.getText().toString();
                    String service_port = ser_portET.getText().toString();




                } catch (RuntimeException e) {
                    IP_serET.setText("");
                    ser_portET.setText("");
                    e.printStackTrace();
                }
            }
        });


    }
}