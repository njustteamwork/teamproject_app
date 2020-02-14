package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.dataprocessor.EncryptedDataForm;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;

public class ExtraTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extratest);


        Thread thread = new Thread() {
            public void run() {
                TextView textView = findViewById(R.id.extra_tv);

                /*
                try {

                    InputStream in = getResources().openRawResource(R.raw.test_list);
                    FileOutputStream output = null;
                    output = ExtraTestActivity.this.openFileOutput("TEST_LIST", Context.MODE_PRIVATE);
                    byte[] data = new byte[1024];
                    int inData = in.read(data);
                    while (inData != -1) {
                        output.write(data, 0, inData);
                        inData = in.read(data);
                    }
                    output.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                 */

                ObjectInputStream oin = null;
                EncryptedDataForm[] array1 = null;
                Gson gson = new Gson();
                ScrollView scrollView = findViewById(R.id.sv);
                try {
                    oin = new ObjectInputStream(ExtraTestActivity.this.openFileInput("TEST_LIST"));
                    array1 = (EncryptedDataForm[]) oin.readObject();
                    List<EncryptedDataForm> list1 = Arrays.asList(array1);
                    int count = 0;
                    for (EncryptedDataForm edf : list1) {
                        String ts = gson.toJson(edf) + "   " + count++;
                        System.out.println(ts);
                        textView.append(count+"   " + ts +  "\n");
                        Thread.sleep(30);
                        if(count%3==0)
                            textView.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

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
