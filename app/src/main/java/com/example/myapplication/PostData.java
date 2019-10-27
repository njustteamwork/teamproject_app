package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class PostData {
    public static void postData(String myData, final Context myContext){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "targeturl.com";
        RequestParams params = new RequestParams();
        params.put("data",myData);
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                CustomToast.showToast(myContext,"Success", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                CustomToast.showToast(myContext,"Failure", Toast.LENGTH_SHORT);
            }
        });
    }
}
