package com.example.bighomework;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.bighomework.dao.AccountData;

import java.util.ArrayList;
import java.util.List;

public class InformationSetActivity extends AppCompatActivity {
    private Button chooseBT;
    private ImageView iv_image;
    private AccountData AD=new AccountData();
    private String account;
    private String name;
    private String type;
    private String school;
    private String password;
    private String sentence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_set);
        Log.e(this.getClass().getName(), "onCreate");
        ImageButton returnBT = (ImageButton) findViewById(R.id.return_button);

        account = getIntent().getStringExtra("account");
        type = getIntent().getStringExtra("type");
        try {
            name = AD.getNameByAccount(account);
            school = AD.getSchoolByAccount(account);
            password = AD.getPasswordByAccount(account);
            sentence = AD.getSentenceByAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Spinner collegeSpinner = (Spinner) findViewById(R.id.college);
        collegeSpinner.setPrompt("请选择你的学院");
        List collegeList=new ArrayList();
        collegeList.add("计算机学院");
        collegeList.add("环境学院");
        collegeList.add("土木学院");
        collegeList.add("航天学院");
        collegeList.add("机电学院");
        collegeList.add("其他");
        ArrayAdapter collegeAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,collegeList);
        collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        collegeSpinner.setAdapter(collegeAdapter);

        String new_school=collegeSpinner.getSelectedItem().toString();

        chooseBT =findViewById(R.id.chooseBT);
        iv_image =findViewById(R.id.iv_image);
        EditText input_name=(EditText)findViewById(R.id.input_name);
        EditText fill_sentence=(EditText)findViewById(R.id.fill_sentence);
        Button sure=(Button)findViewById(R.id.sure);

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
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    AD.deleteAccount(account);
                    String new_name=input_name.getText().toString();
                    String new_sentence=fill_sentence.getText().toString();
                    AD.addAccount(account,type,password,new_name,new_sentence,new_school);


                } catch (RuntimeException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Log.e(this.getClass().getName(), "Result:" +result.toString());
                        if (result != null) {
                            // 得到图片的全路径
                            Uri uri = result.getData().getData();
                            iv_image.setImageURI(uri);
                            try {
                                AD.updateImg(uri.getPath(),account);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        chooseBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 2);
            }
        });
    }
}