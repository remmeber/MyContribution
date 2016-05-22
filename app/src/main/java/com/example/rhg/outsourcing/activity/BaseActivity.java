package com.example.rhg.outsourcing.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.rhg.outsourcing.view.BaseView;

/**
 * Created by remember on 2016/5/18.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView, View.OnClickListener {
    private static final String ACTION_NETWORK_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    private static final String ACTION_PUSH_DATA = "fm.data.push.action";
    private static final String ACTION_NEW_VERSION = "apk.update.action";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataReceive(getIntent());
        setContentView(getLayoutResId());
        initView();
        initData();
//        bindData(loadData());
    }

    public void dataReceive(Intent intent) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_NETWORK_CHANGE);
        filter.addAction(ACTION_PUSH_DATA);
        filter.addAction(ACTION_NEW_VERSION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_NETWORK_CHANGE.equals(action)) {
                //TODO 网络发生变化
            } else if (ACTION_PUSH_DATA.equals(action)) {
                Bundle b = intent.getExtras();
                //TODO 数据改变
            } else if (ACTION_NEW_VERSION.equals(action)) {
                //TODO 版本发生变化
            }
        }
    };

    public int getLayoutResId() {
        return 0;
    }

    protected abstract void initView();

    protected abstract void initData();

    //todo 横竖屏切换，键盘等
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("RHG", "changed");
    }


}
