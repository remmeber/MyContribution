package com.example.rhg.outsourcing.application;

import android.app.Application;

import com.example.rhg.outsourcing.datebase.DBHelper;
import com.example.rhg.outsourcing.utils.SharePreferenceUtil;

/**
 * Created by remember on 2016/5/9.
 */
public class InitApplication extends Application {
    private static InitApplication initApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication = this;
        initSharePreferenceUtil();
        initDBHelper();
    }

    private void initSharePreferenceUtil() {
        SharePreferenceUtil.getInstance().init(getApplicationContext());
    }

    private void initDBHelper() {
        DBHelper.init(getApplicationContext());
    }

    public static InitApplication getInstance() {
        if (initApplication == null)
            initApplication = new InitApplication();
        return initApplication;
    }
}
