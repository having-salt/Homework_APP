package com.example.bighomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        EditText accountET = (EditText)findViewById(R.id.login_account);
        EditText passwordET = (EditText)findViewById(R.id.login_password);
        EditText sure_passwordET = (EditText)findViewById(R.id.sure_password);

        Button sign_up = (Button) findViewById(R.id.sign_up);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(passwordET.getText().toString().equals(sure_passwordET.getText().toString())){
                        String account = accountET.getText().toString();
                        String password = passwordET.getText().toString();
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    }

                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}