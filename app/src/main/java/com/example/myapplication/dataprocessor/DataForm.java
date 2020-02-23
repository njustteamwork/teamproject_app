package com.example.myapplication.dataprocessor;

import java.util.Date;

public class DataForm {
    private Date date = new Date();// 获取当前时间
    private String userName;
    private int userTemperature = 99999;  //摄氏度 一位小数*10 例 36.5 -> 365
    private int userHeartRate = 99999; //心率

    public void setDate(Date date){this.date = date;}
    public void setUserName(String userName){this.userName = userName;}
    public void setUserTemperature(int userTemperature) {this.userTemperature = userTemperature;}
    public void setUserHeartRate(int userHeartRate) {this.userHeartRate = userHeartRate;}
    public Date getDate(){return date;}
    public String getUserName(){return userName;}
    public int getUserTemperature(){return userTemperature;}
    public int getUserHeartRate(){return userHeartRate;}
}
