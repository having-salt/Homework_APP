package com.example.bighomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.bighomework.service.LoginService;
import com.example.bighomework.student.StuMainActivity;
import com.example.bighomework.teacher.TeaMainActivity;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_tab);

        SharedPreferences.Editor account_xml= getSharedPreferences("account_xml",MODE_PRIVATE).edit();
        SharedPreferences account_read =getSharedPreferences("account_xml",MODE_PRIVATE);

        CheckBox cb = findViewById(R.id.is_remember);

        EditText accountET = (EditText)findViewById(R.id.login_account);
        EditText passwordET = (EditText)findViewById(R.id.login_password);
        accountET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(account_read.getString(accountET.getText().toString(),null)!=null){
                    passwordET.setText(account_read.getString(accountET.getText().toString(),""));
                }
            }
        });

        Button login = (Button) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String account = accountET.getText().toString();
                    String password = passwordET.getText().toString();

                    boolean isTea = new LoginService().isTeacher(account);
                    if(cb.isChecked()&&account_read.getString(accountET.getText().toString(),null)==null){
                        account_xml.putString(account,password);
                        account_xml.commit();
                    }
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

    @Override
    protected void onStop() {
        super.onStop();
        EditText accountET = (EditText)findViewById(R.id.login_account);
        EditText passwordET = (EditText)findViewById(R.id.login_password);
        accountET.setText("");
        passwordET.setText("");
    }
}