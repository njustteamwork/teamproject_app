package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dataprocessor.Collector;
import com.example.myapplication.dataprocessor.DataForm;
import com.example.myapplication.dataprocessor.EncryptedDataForm;
import com.example.myapplication.dataprocessor.HTTPRequest;
import com.example.myapplication.paillier.PaillierEncryptor;
import com.example.myapplication.paillier.PaillierPublicKey;
import com.google.gson.Gson;

public class TestActivity extends AppCompatActivity {

    String userJson = null;
    Gson gson = new Gson();
    DataForm df = null;
    EncryptedDataForm encryptedDataForm= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button getButton = findViewById(R.id.getButton);
        Button doButton = findViewById(R.id.doButton);
        Button sendButton = findViewById(R.id.sendButton);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        TextView hospital_icon = findViewById(R.id.hospital_icon);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collector collector = new Collector(TestActivity.this);
                df = collector.collectData();
                userJson = gson.toJson(df);
                CustomToast.showToast(TestActivity.this,"数据就暂定为"+userJson+"吧！",Toast.LENGTH_SHORT);
            }
        });

        doButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (df == null){
                    CustomToast.showToast(TestActivity.this,"请先获取数据！",Toast.LENGTH_SHORT);
                    return;
                }
                //PaillierPublicKey paillierPublicKey = PaillierPublicKey.paillierJsonToPublicKey("{\"n\":7037996759611275900405487329144489085210900622405788623915340046554895678557675360099993502545810105916795350348201798995744651664108236879690390748857833,\"nSquare\":49533398388298819693190911443085500113137594389227717398938303574532356291531019850234314622241175041250992063305927006862844026670633749958420794136365527887009273250901790502746504678689585917463571409706569379921923499464969602901871572009667889989146252127852333968575165007138552703354893437794045455889,\"g\":47,\"bitLength\":512,\"timeStamp\":1580452220178}");
                SharedPreferences sp = getSharedPreferences("publicKey",MODE_PRIVATE);
                if(!sp.contains("keyString"))
                    HTTPRequest.setPublicKey(TestActivity.this);
                PaillierPublicKey paillierPublicKey = PaillierPublicKey.paillierJsonToPublicKey(sp.getString("keyString",null));
                PaillierEncryptor paillierEncryptor = new PaillierEncryptor(paillierPublicKey);
                encryptedDataForm = new EncryptedDataForm(df,paillierEncryptor);
                System.out.println(gson.toJson(encryptedDataForm));
                CustomToast.showToast(TestActivity.this,"数据已加密！",Toast.LENGTH_SHORT);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HTTPRequest.postData(gson.toJson(encryptedDataForm),TestActivity.this);
            }
        });

        hospital_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.showToast(TestActivity.this,"系统正在守护您的健康与隐私",Toast.LENGTH_SHORT);
            }
        });
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
