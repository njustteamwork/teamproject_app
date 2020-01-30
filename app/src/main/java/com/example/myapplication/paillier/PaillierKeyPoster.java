package com.example.myapplication.paillier;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.CustomToast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class PaillierKeyPoster {
    public static void postPublicKey(final Context mContext, PaillierPublicKey paillierPublicKey){
        String keyString = paillierPublicKey.getJsonStringPublicKey();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://10.0.2.2:8080/postPublicKey";
        RequestParams params = new RequestParams();
        params.put("paillierJsonStringPublicKey",keyString);
        client.post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                CustomToast.showToast(mContext,"Success", Toast.LENGTH_SHORT);
                System.out.println(responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                CustomToast.showToast(mContext,"Failure, code "+ statusCode, Toast.LENGTH_SHORT);
            }
        });
    }

    public static void postPrivateKey(final Context mContext, PaillierPrivateKey  paillierPrivateKey){
        String keyString = paillierPrivateKey.getJsonStringPrivateKey();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://10.0.2.2:8080/postPrivateKey";
        RequestParams params = new RequestParams();
        params.put("paillierJsonStringPrivateKey",keyString);
        client.post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                CustomToast.showToast(mContext,"Success", Toast.LENGTH_SHORT);
                System.out.println(responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                CustomToast.showToast(mContext,"Failure, code "+ statusCode, Toast.LENGTH_SHORT);
            }
        });
    }
}
