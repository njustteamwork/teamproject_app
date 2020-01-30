package com.example.myapplication.dataprocessor;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Collector {
    /**
     * 应用Android传感器收集所需信息
     */
    Context mContext;
    DataForm df = new DataForm();

    public Collector(Context mContext){
        this.mContext = mContext;
        SharedPreferences userSP = mContext.getSharedPreferences("userData",MODE_PRIVATE);
        df.setUserName(userSP.getString("name",null));
    }

    public DataForm collectData(){
        df.setUserHeartRate(90);
        df.setUserTemperature(365);
        return df;
    }

}
