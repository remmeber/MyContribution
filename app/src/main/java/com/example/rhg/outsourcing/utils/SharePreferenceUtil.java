package com.example.rhg.outsourcing.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by remember on 2016/5/9.
 */
public class SharePreferenceUtil {
    private static SharePreferenceUtil sharePreferenceUtil;

    private SharedPreferences spInfo;

    public SharePreferenceUtil() {

    }

    public static SharePreferenceUtil getInstance() {
        if (sharePreferenceUtil == null) {
            sharePreferenceUtil = new SharePreferenceUtil();
        }
        return sharePreferenceUtil;
    }

    /**
     * 必须初始化
     */
    public void init(Context context) {
        this.spInfo = context.getSharedPreferences("user_info", 0);
    }

    /**
     * 单例模式中获取唯一的AppContext实例
     *
     * @return
     */

    public static SharePreferenceUtil getSharePreference(Context context) {
        sharePreferenceUtil = new SharePreferenceUtil(context);
        return sharePreferenceUtil;
    }

    public SharedPreferences.Editor getEditor() {
        return this.spInfo.edit();
    }

    public SharedPreferences getSP() {
        return this.spInfo;
    }

    public SharePreferenceUtil(Context paramContext) {
        this.spInfo = paramContext.getSharedPreferences("user_info", 0);
    }
}