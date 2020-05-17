package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

/**
 * 自定义androidToast
 */
public class CustomToast {
    private static Toast mToast;

    public static void showToast(Context mContext, String text, int duration) {
        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(mContext, text, duration);
        mToast.show();
    }

    public static void showToast(Context mContext, int resId, int duration) {
        showToast(mContext, mContext.getResources().getString(resId), duration);
    }
}