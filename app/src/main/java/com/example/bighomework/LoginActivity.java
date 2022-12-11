package com.example.bighomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.bighomework.dao.AccountData;
import com.example.bighomework.service.LoginService;
import com.example.bighomework.student.StuMainActivity;
import com.example.bighomework.teacher.TeaMainActivity;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_tab);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }//强制在主线程请求http

        EditText accountET = (EditText) findViewById(R.id.login_account);
        EditText passwordET = (EditText) findViewById(R.id.login_password);
        passwordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                SharedPreferences nw = getSharedPreferences("remember_xml", MODE_PRIVATE);
                String password = nw.getString(accountET.getText().toString(),"");
                if(!password.isEmpty()&&b){
                    passwordET.setText(password);
                }
            }
        });
        Button login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String account = accountET.getText().toString();
                    String password = passwordET.getText().toString();
                    if (!new AccountData().isCorrect(account, password)) {
                        accountET.setText("");
                        passwordET.setText("");
                        return;
                    }
                    CheckBox is_remember = findViewById(R.id.is_remember);
                    if(is_remember.isChecked()){
                        SharedPreferences.Editor remember_xml = getSharedPreferences("remember_xml", MODE_PRIVATE).edit();
                        remember_xml.putString(account, password);
                        remember_xml.apply();
                    }
                    boolean isTea = new LoginService().isTeacher(account);
                    Intent intent;
                    if (isTea) {
                        intent = new Intent(LoginActivity.this, TeaMainActivity.class);
                    } else {
                        intent = new Intent(LoginActivity.this, StuMainActivity.class);
                    }
                    intent.putExtra("account", account);
                    startActivity(intent);
                } catch (RuntimeException e) {
                    accountET.setText("");
                    passwordET.setText("");
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button sign_up = (Button) findViewById(R.id.sign_up);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}