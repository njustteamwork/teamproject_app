package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.dataprocessor.DataForm;
import com.example.myapplication.dataprocessor.EncryptedDataForm;
import com.example.myapplication.dataprocessor.HTTPRequest;
import com.example.myapplication.paillier.PaillierEncryptor;
import com.example.myapplication.paillier.PaillierPublicKey;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

//数据传输压力测试Activity
public class ExtraTestActivity extends AppCompatActivity {

    static Random r = new Random();
    public static String getName(){
        int number = r.nextInt(3);
        switch(number){
            case 0: return "Alex";
            case 1: return "Leo";
            case 2: return "Carl";
        }
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extratest);


        final TextView textView = findViewById(R.id.extra_tv);
        SharedPreferences sp = getSharedPreferences("fogUrl", Context.MODE_PRIVATE);
        if(!sp.contains("url")){
            textView.setText("尚未获得雾节点地址！请稍后再试。");
            HTTPRequest.setFogUrl(ExtraTestActivity.this);
        }
        else {
            textView.setText("压力测试中……");
            final Thread thread = new Thread(){
                public void run() {
                    Looper.prepare();
                    Gson gson = new Gson();
                    String eUserJson;
                    SharedPreferences sp = getSharedPreferences("publicKey",MODE_PRIVATE);
                    if(!sp.contains("keyString"))
                        HTTPRequest.setPublicKey(ExtraTestActivity.this);
                    PaillierPublicKey paillierPublicKey = PaillierPublicKey.paillierJsonToPublicKey(sp.getString("keyString",null));
                    PaillierEncryptor paillierEncryptor = new PaillierEncryptor(paillierPublicKey);
                    List<EncryptedDataForm> list = new ArrayList<>();
                    int originHeartRate  = 90;
                    int originTemperature = 360;
                    int timeToTry = 100;
                    int timeToSleep = 1000;

                    for(int s = 0;s<timeToTry;s++){
                        DataForm df = new DataForm();
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DAY_OF_MONTH,-ExtraTestActivity.r.nextInt(14));
                        Date date = calendar.getTime();
                        df.setDate(date);
                        df.setUserName(ExtraTestActivity.getName());
                        df.setUserHeartRate(originHeartRate+r.nextInt(11));
                        df.setUserTemperature(originTemperature+r.nextInt(11));
                        try {
                            Thread.sleep(timeToSleep);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        EncryptedDataForm edf = new EncryptedDataForm(df,paillierEncryptor);
                        eUserJson = gson.toJson(edf);
                        System.out.println("post eUserData: " + eUserJson);
                        HTTPRequest.postData(eUserJson,ExtraTestActivity.this);
                    }
                    textView.setText("压力测试完成");
                    Looper.loop();
                }
            };
            thread.start();
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
