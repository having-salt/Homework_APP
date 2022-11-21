package com.example.bighomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bighomework.service.LoginService;
import com.example.bighomework.dao.AccountData;

public class SignUpActivity extends AppCompatActivity {
    private AccountData AD=new AccountData();
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
                        boolean isTea = new LoginService().isTeacher(account);
                        if(isTea){
                            AD.addAccount(account,"tea",password,null);
                        }else{
                        }
                            AD.addAccount(account,"stu",password,null);
                    }

                } catch (RuntimeException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button returnBT = (Button) findViewById(R.id.return_button);

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
    }
}