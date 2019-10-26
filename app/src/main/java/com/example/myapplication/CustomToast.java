package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

public class CustomToast {
    private static Toast mToast;
    public static void showToast(Context mContext, String text, int duration) {
        if (mToast != null){
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        else
            mToast = Toast.makeText(mContext, text, duration);
        mToast.show();
}
    public static void showToast(Context mContext, int resId, int duration) {
        showToast(mContext, mContext.getResources().getString(resId), duration);
    }
}