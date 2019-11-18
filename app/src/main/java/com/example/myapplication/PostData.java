package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class PostData {
    public static void postData(String myData, final Context myContext){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://10.0.2.2:8080/postData";
        RequestParams params = new RequestParams();
        params.put("data",myData);
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
}
