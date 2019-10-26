package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int TestNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getButton = findViewById(R.id.getButton);
        Button doButton = findViewById(R.id.doButton);
        Button sendButton = findViewById(R.id.sendButton);


        TextView lockicon = findViewById(R.id.lock_icon);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestNum = new Random().nextInt(10000);
                CustomToast.showToast(MainActivity.this,"数据就暂定为"+TestNum+"吧",Toast.LENGTH_SHORT);
            }
        });

        doButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.showToast(MainActivity.this,"此功能也未实现",Toast.LENGTH_SHORT);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.showToast(MainActivity.this,"此功能同样未实现",Toast.LENGTH_SHORT);
            }
        });

        lockicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.showToast(MainActivity.this,"别点了，安全的很",Toast.LENGTH_SHORT);
            }
        });
    }


}
