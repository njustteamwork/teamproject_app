package com.example.myapplication.dataprocessor;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.example.myapplication.CustomToast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class HTTPRequest {
    private static AsyncHttpClient client = new AsyncHttpClient();
    public static void postData(String myEncryptedData, final Context myContext){
        SharedPreferences sp =  myContext.getSharedPreferences("forUrl",Context.MODE_PRIVATE);
        String url = null;
        if(sp.contains("url"))
             url = sp.getString("url",null)+"/data/postEData";
        else{
            CustomToast.showToast(myContext,"尚未获得雾节点地址！",Toast.LENGTH_SHORT);
            return;
        }
        RequestParams params = new RequestParams();
        params.put("data",myEncryptedData);
        client.post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                CustomToast.showToast(myContext,"Success", Toast.LENGTH_SHORT);
                System.out.println(responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                CustomToast.showToast(myContext,"Failure, code "+ statusCode, Toast.LENGTH_SHORT);
            }
        });
    }

    public static void setFogUrl(final Context myContext){
        String url = "http://10.0.2.2:8081/data/getFogUrl"; //云服务器地址
        client.post(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SharedPreferences sp = myContext.getSharedPreferences("fogUrl",myContext.MODE_PRIVATE);
                sp.edit().clear().apply();
                CustomToast.showToast(myContext,"请求雾节点地址失败！代码"+statusCode, Toast.LENGTH_SHORT);
                System.out.println("失败？？？");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                CustomToast.showToast(myContext,"请求雾节点地址成功！Url:"+responseString, Toast.LENGTH_SHORT);
                SharedPreferences sp = myContext.getSharedPreferences("fogUrl",myContext.MODE_PRIVATE);
                sp.edit().clear().apply();
                sp.edit().putString("url",responseString).putString("date",new Date().toString()).apply();
                System.out.println("sp存储地址"+ sp.getString("url",null));
            }
        });
    }
}
