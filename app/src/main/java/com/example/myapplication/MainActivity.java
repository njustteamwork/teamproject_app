package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cunoraz.gifview.library.GifView;
import com.example.myapplication.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private int COUNTS = 5;// 点击次数
    private long[] mHits = new long[COUNTS];//记录点击次数
    private long DURATION = 2000;//有效时间


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GifView gifView = findViewById(R.id.main_gif);
        TextView hospital_icon = findViewById(R.id.hospital_icon);

        hospital_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.showToast(MainActivity.this, "系统正在守护您的健康与隐私", Toast.LENGTH_SHORT);

                //将mHints数组内的所有元素左移一个位置
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                //获得当前系统已经启动的时间
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
                    // 相关逻辑操作
                    //CustomToast.showToast(MainActivity.this, "快速点击5次！", Toast.LENGTH_SHORT);
                    Intent intentExtra = new Intent(MainActivity.this, ExtraTestActivity.class);
                    startActivity(intentExtra);
                    //初始化点击次数
                    mHits = new long[COUNTS];
                }
            }
        });
        gifView.setGifResource(R.mipmap.main_gif);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu main_activity_menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, main_activity_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_login:
                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.activity_setting:
                Intent intentSetting = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intentSetting);
                break;
            case R.id.activity_test:
                Intent intentTest = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intentTest);
                break;
            default:
                CustomToast.showToast(MainActivity.this, "click", Toast.LENGTH_SHORT);
                break;
        }
        return true;
    }


}
