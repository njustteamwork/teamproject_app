package com.example.myapplication.dataprocessor;

import com.example.myapplication.paillier.PaillierCalculator;
import com.example.myapplication.paillier.PaillierEncryptor;
import com.example.myapplication.paillier.PaillierKeyGenerator;
import com.example.myapplication.paillier.PaillierPublicKey;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DPTest {
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

    public static void main(String[] args) throws Exception {
        /*
        DataForm df = new DataForm();
        df.setUserName("Yang");
        Gson gson = new Gson();
        String jsonDataForm = gson.toJson(df);
        System.out.println(jsonDataForm);
        df = gson.fromJson(jsonDataForm, DataForm.class);
        PaillierPublicKey paillierPublicKey = new PaillierKeyGenerator().getPaillierPublicKey();
        PaillierEncryptor paillierEncryptor = new PaillierEncryptor(paillierPublicKey);
        EncryptedDataForm encryptedDataForm = new EncryptedDataForm(df,paillierEncryptor);
        String gsonEDataForm = encryptedDataForm.getJsonStringEDataForm();
        System.out.println(gsonEDataForm);
         */

        Gson gson = new Gson();
        String userJson;
        String eUserJson;
        PaillierPublicKey paillierPublicKey = PaillierPublicKey.paillierJsonToPublicKey("{\"n\":7037996759611275900405487329144489085210900622405788623915340046554895678557675360099993502545810105916795350348201798995744651664108236879690390748857833,\"nSquare\":49533398388298819693190911443085500113137594389227717398938303574532356291531019850234314622241175041250992063305927006862844026670633749958420794136365527887009273250901790502746504678689585917463571409706569379921923499464969602901871572009667889989146252127852333968575165007138552703354893437794045455889,\"g\":47,\"bitLength\":512,\"timeStamp\":1580452220178}");
        PaillierEncryptor paillierEncryptor = new PaillierEncryptor(paillierPublicKey);
        List<EncryptedDataForm> list = new ArrayList<>();

        int originHeartRate  = 90;
        int originTemperature = 360;
        int timeToTry = 3000;
        int timeToSleep = 1200;

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test_list"));
        for(int s = 0;s<timeToTry;s++){
            DataForm df = new DataForm();
            df.setUserName(getName());
            df.setUserHeartRate(originHeartRate+r.nextInt(11));
            df.setUserTemperature(originTemperature+r.nextInt(11));
            Thread.sleep(timeToSleep);
            userJson = gson.toJson(df);
            System.out.println(userJson+"   "+s);
            EncryptedDataForm edf = new EncryptedDataForm(df,paillierEncryptor);
            list.add(edf);
        }
        EncryptedDataForm[] array = new EncryptedDataForm[list.size()];
        list.toArray(array);
        out.writeObject(array);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("test_list"));
        EncryptedDataForm[] array1 = (EncryptedDataForm[]) in.readObject();
        List<EncryptedDataForm> list1 = Arrays.asList(array1);
        int count=0;
        for(EncryptedDataForm edf :list1){
            System.out.println(gson.toJson(edf) + "   " + count++);
        }
        System.out.println("done!");

    }


}
