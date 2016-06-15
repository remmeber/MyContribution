package com.example.rhg.outsourcing.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * desc:网络请求工具
 * author：remember
 * time：2016/6/11 14:04
 * email：1013773046@qq.com
 */
public class NetUtil {
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE
        );
        if (null != connectivityManager) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED)
                    return true;
            }
        }
        return false;
    }
}
