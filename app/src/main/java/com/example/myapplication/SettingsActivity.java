package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.example.myapplication.data.LoginDataSource;
import com.example.myapplication.data.LoginRepository;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button clearUserData = findViewById(R.id.clearUserData);
        Button showUserData = findViewById(R.id.showUserData);

        clearUserData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginDataSource().logout(SettingsActivity.this);
                CustomToast.showToast(SettingsActivity.this,"You clicked the clearUserDataButton",Toast.LENGTH_SHORT);
            }
        });

        showUserData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences userSP = getSharedPreferences("userData",MODE_PRIVATE);
                String name = userSP.getString("name",null);
                String UUID = userSP.getString("UUID",null);
                CustomToast.showToast(SettingsActivity.this,name==null?"无用户名":("name:"+name+" UUID:"+UUID), Toast.LENGTH_SHORT);
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


    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}