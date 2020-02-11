package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cunoraz.gifview.library.GifView;
import com.example.myapplication.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GifView gifView = (GifView)findViewById(R.id.main_gif);
        TextView hospital_icon = findViewById(R.id.hospital_icon);

        hospital_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.showToast(MainActivity.this,"别点了，安全的很",Toast.LENGTH_SHORT);
            }
        });
        gifView.setGifResource(R.mipmap.main_gif);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu main_activity_menu){
        getMenuInflater().inflate(R.menu.main_activity_menu,main_activity_menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.activity_login:
                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.activity_setting:
                Intent intentSetting = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intentSetting);
                break;
            case R.id.activity_test:
                Intent intentTest = new Intent(MainActivity.this,TestActivity.class);
                startActivity(intentTest);
                break;
            default:
                CustomToast.showToast(MainActivity.this,"click", Toast.LENGTH_SHORT);
                break;
        }
        return true;
    }


}
