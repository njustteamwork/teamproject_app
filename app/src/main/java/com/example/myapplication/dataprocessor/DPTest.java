package com.example.myapplication.dataprocessor;

import com.example.myapplication.paillier.PaillierCalculator;
import com.example.myapplication.paillier.PaillierEncryptor;
import com.example.myapplication.paillier.PaillierKeyGenerator;
import com.example.myapplication.paillier.PaillierPublicKey;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DPTest {
    public static void main(String[] args){
        DataForm df = new DataForm();
        df.setUserName("Yang");
        Gson gson = new Gson();
        String jsonDataForm = gson.toJson(df);
        System.out.println(jsonDataForm);
        /*
        List<DataForm> list = new ArrayList<>();
        for(int i=0 ;i<5; i++){
            list.add(new DataForm());
        }
        userJson = gson.toJson(list);
        System.out.println(userJson);
        */
        df = gson.fromJson(jsonDataForm, DataForm.class);
        PaillierPublicKey paillierPublicKey = new PaillierKeyGenerator().getPaillierPublicKey();
        PaillierEncryptor paillierEncryptor = new PaillierEncryptor(paillierPublicKey);
        EncryptedDataForm encryptedDataForm = new EncryptedDataForm(df,paillierEncryptor);
        String gsonEDataForm = encryptedDataForm.getJsonStringEDataForm();
        System.out.println(gsonEDataForm);
    }


}
