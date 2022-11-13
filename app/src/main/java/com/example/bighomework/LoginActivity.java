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
                    if(isTea){
                        Intent intent = new Intent(LoginActivity.this, TeaMainActivity.class);
                        intent.putExtra("account",account);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(LoginActivity.this, StuMainActivity.class);
                        intent.putExtra("account",account);
                        startActivity(intent);
                    }
                } catch (RuntimeException e) {
                    accountET.setText("");
                    passwordET.setText("");
                    e.printStackTrace();
                }
            }
        });

    }

}