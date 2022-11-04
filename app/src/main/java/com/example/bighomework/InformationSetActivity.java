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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class InformationSetActivity extends AppCompatActivity {
    private Button chooseBT;
    private ImageView iv_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_set);
        Log.e(this.getClass().getName(), "onCreate");
        ImageButton returnBT = (ImageButton) findViewById(R.id.return_button);
        chooseBT =findViewById(R.id.chooseBT);
        iv_image =findViewById(R.id.iv_image);
        RadioGroup stu_or_tea_radio = (RadioGroup)findViewById(R.id.stu_or_tea_radio);
        RadioButton radioButton = (RadioButton)findViewById(stu_or_tea_radio.getCheckedRadioButtonId());
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
                    String name=input_name.getText().toString();
                    String sentence=fill_sentence.getText().toString();
                    String stu_or_tea=radioButton.getText().toString();



                } catch (RuntimeException e) {
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
                            Log.e(this.getClass().getName(), "Uri:" + String.valueOf(uri));
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