package com.example.rhg.outsourcing.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.application.InitApplication;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.locationservice.LocationService;
import com.example.rhg.outsourcing.locationservice.MyLocationListener;
import com.example.rhg.outsourcing.mvp.view.BaseView;
import com.example.rhg.outsourcing.utils.NetUtil;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

/**
 * desc:工程的基类，所有的子Activity都要继承它
 * author：remember
 * time：2016/5/28 16:13
 * email：1013773046@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView, View.OnClickListener {
    private static final String ACTION_NETWORK_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    private static final String ACTION_PUSH_DATA = "fm.data.push.action";
    private static final String ACTION_NEW_VERSION = "apk.update.action";

    //TODO 百度地图
    private LocationService locationService;
    private MyLocationListener mLocationListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitApplication.getInstance().addActivity(this);
        RefWatcher refWatcher = InitApplication.getRefWatcher(this);
        refWatcher.watch(this);

        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        dataReceive(getIntent());
        firstLoc();
        loadingData();
        initView(getRootView(this));
        initData();
//        bindData(loadData());
    }

    View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    public void loadingData() {
    }


    private void firstLoc() {
        if ((locationService = GetMapService()) != null) {
            if ((mLocationListener = getLocationListener()) != null) {
                locationService.registerListener(mLocationListener);
                locationService.setLocationOption(locationService.getDefaultLocationClientOption());
                mLocationListener.getLocation(locationService);
            }
        }
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


    public void reStartLocation() {
        if (locationService == null)
            locationService = GetMapService();
        if (mLocationListener == null) {
            Log.d("RHG", "Location listener is null");
            locationService.registerListener(mLocationListener = getLocationListener());
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else {
            mLocationListener.getLocation(locationService);
        }
        if (AppConstants.DEBUG)
            Log.i("RHG", "重启定位");
    }

    public MyLocationListener getLocationListener() {
        return null;
    }

    public void getLocation(LocationService locationService, MyLocationListener mLocationListener) {
    }


    /*默认不定位，如果需要定位，子类需要重写该方法*/
    public LocationService GetMapService() {
        return null;
    }

    public void dataReceive(Intent intent) {
    }


    @Override
    protected void onStop() {
        if (locationService != null) {
            locationService.unregisterListener(mLocationListener); //注销掉监听
            locationService.stop(); //停止定位服务
        }
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        InitApplication.getInstance().removeActivity(this);
        ButterKnife.unbind(this);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (ACTION_NETWORK_CHANGE.equals(action)) {
                InitApplication.isNetworkAvailable = NetUtil.isConnected(getApplicationContext());
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

    protected abstract void initView(View view);

    protected abstract void initData();

    //todo 横竖屏切换，键盘等
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (InitApplication.getInstance().getAcitivityCount() == 1) {
            InitApplication.getInstance().exit();
            return;
        }
        finish();
    }

    @Override
    public void showData(Object o) {
        if (o instanceof String) {
            String _str = (String) o;
            if (_str.contains("location")) {
                String[] location_str = _str.split(",");
                if (location_str[1].equals("error"))
                    showLocFailed(location_str[2]);
                else {
                    if (locationService != null) {
                        locationService.stop();
                        if (AppConstants.DEBUG)
                            Log.i("RHG", "停止定位");
                    }
                    showLocSuccess(location_str[2]);
                }
            } else {
                showSuccess(_str);
            }
            return;
        }
        showSuccess(o);

    }

    public void showLocSuccess(String s) {
    }

    public void showLocFailed(String s) {
    }

    protected abstract void showSuccess(Object s);

    protected abstract void showError(Object s);


}
