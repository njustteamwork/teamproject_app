package com.example.myapplication.dataprocessor;

import com.example.myapplication.paillier.PaillierEncryptor;
import com.google.gson.Gson;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 加密后的数据实体类
 */
public class EncryptedDataForm implements Serializable {
    private Date date;
    private String username;
    private BigInteger userTemperature;
    private BigInteger userHeartRate;
    private long keyTimeStamp;

    public EncryptedDataForm(DataForm dataForm, PaillierEncryptor paillierEncryptor){
        date = dataForm.getDate();
        username = dataForm.getUserName();
        userTemperature = paillierEncryptor.encryptIt(dataForm.getUserTemperature());
        userHeartRate = paillierEncryptor.encryptIt(dataForm.getUserHeartRate());
        keyTimeStamp = paillierEncryptor.getKeyTimeStamp();
    }

    public String getJsonStringEDataForm(){
        Gson gson = new Gson();
        String jsonStringEDataForm = gson.toJson(this, EncryptedDataForm.class);
        return jsonStringEDataForm;
    }
}


