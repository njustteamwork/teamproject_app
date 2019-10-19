package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


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
                Toast.makeText(MainActivity.this,"此功能还未实现",Toast.LENGTH_SHORT).show();
            }
        });

        doButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"此功能也未实现",Toast.LENGTH_SHORT).show();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"此功能同样未实现",Toast.LENGTH_SHORT).show();
            }
        });

        lockicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"别点了，安全的很",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
