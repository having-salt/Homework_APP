package com.example.bighomework.teacher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.bighomework.R;
import com.example.bighomework.model.Sign;
import com.example.bighomework.service.SignInService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeaCheck extends AppCompatActivity {
    private List<Sign> sign_list = new ArrayList<>();
    private List<String> sign_name_list = new ArrayList<>();
    private String account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_check);
        ImageButton returnBT = (ImageButton) findViewById(R.id.return_button);
        ListView listView = (ListView) findViewById(R.id.check_list);
        account = getIntent().getStringExtra("account");
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
            sign_list = SignInService.getAllSigns();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<sign_list.size();i++){
            sign_name_list.add(sign_list.get(i).getSignName());
        }



        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,sign_name_list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long  id) {
                String check_name=sign_name_list.get(position);
                Intent intent=new Intent(TeaCheck.this, HaveCheck.class);
                intent.putExtra("account",account);
                intent.putExtra("sign",sign_name_list.get(position));
                startActivity(intent);
            }
        });
    }

    public void BtnClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.add_check_dialog, null);
        EditText check_name = (EditText) v.findViewById(R.id.input_check_name);
        EditText check_time = (EditText) v.findViewById(R.id.input_time);
        Button btn_sure = (Button) v.findViewById(R.id.dialog_btn_sure);
        Button btn_cancel = (Button) v.findViewById(R.id.dialog_btn_cancel);
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(v);

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String input_check = check_name.getText().toString();
                String input_time = check_time.getText().toString();
                try {
                    SignInService.addSign(input_check, Long.parseLong(input_time));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }
}