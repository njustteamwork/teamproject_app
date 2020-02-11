package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dataprocessor.Collector;
import com.example.myapplication.dataprocessor.DataForm;
import com.example.myapplication.dataprocessor.PostData;
import com.example.myapplication.ui.login.LoginActivity;
import com.google.gson.Gson;

import java.util.Random;

public class TestActivity extends AppCompatActivity {

    int TestNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button getButton = findViewById(R.id.getButton);
        Button doButton = findViewById(R.id.doButton);
        Button sendButton = findViewById(R.id.sendButton);


        TextView hospital_icon = findViewById(R.id.hospital_icon);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestNum = new Random().nextInt(10000);
                //CustomToast.showToast(MainActivity.this,"数据就暂定为"+TestNum+"吧",Toast.LENGTH_SHORT);
                Collector collector = new Collector(TestActivity.this);
                DataForm df = collector.collectData();
                Gson gson = new Gson();
                String userJson = gson.toJson(df);
                CustomToast.showToast(TestActivity.this,"数据就暂定为"+userJson+"吧",Toast.LENGTH_SHORT);
            }
        });

        doButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.showToast(TestActivity.this,"此功能也未实现",Toast.LENGTH_SHORT);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CustomToast.showToast(MainActivity.this,"此功能同样未实现",Toast.LENGTH_SHORT);
                PostData.postData(String.valueOf(TestNum),TestActivity.this);
            }
        });

        hospital_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.showToast(TestActivity.this,"别点了，安全的很",Toast.LENGTH_SHORT);
            }
        });
    }

}
