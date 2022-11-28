package com.example.bighomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bighomework.service.LoginService;
import com.example.bighomework.student.StuMainActivity;
import com.example.bighomework.teacher.TeaMainActivity;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_tab);
        EditText accountET = (EditText)findViewById(R.id.login_account);
        EditText passwordET = (EditText)findViewById(R.id.login_password);

        Button login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String account = accountET.getText().toString();
                    String password = passwordET.getText().toString();

                    boolean isTea = new LoginService().isTeacher(account);
                    Intent intent;
                    if(isTea){
                        intent = new Intent(LoginActivity.this, TeaMainActivity.class);
                    }else{
                        intent = new Intent(LoginActivity.this, StuMainActivity.class);
                    }
                    intent.putExtra("account",account);
                    startActivity(intent);
                } catch (RuntimeException e) {
                    accountET.setText("");
                    passwordET.setText("");
                    e.printStackTrace();
                }
            }
        });

        Button sign_up = (Button) findViewById(R.id.sign_up);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}