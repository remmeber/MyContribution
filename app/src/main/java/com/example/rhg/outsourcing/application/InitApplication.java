package com.example.rhg.outsourcing.application;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.activity.BaseActivity;
import com.example.rhg.outsourcing.datebase.LikeDBHelper;
import com.example.rhg.outsourcing.datebase.ShoppingCartDBHelper;
import com.example.rhg.outsourcing.locationservice.LocationService;
import com.example.rhg.outsourcing.utils.NetUtil;
import com.example.rhg.outsourcing.utils.SharePreferenceUtil;
import com.example.rhg.outsourcing.utils.ToastHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * desc:APP的入口，定义全局变量
 * author：remember
 * time：2016/5/28 16:22
 * email：1013773046@qq.com
 */
public class InitApplication extends Application {
    private static InitApplication initApplication;
    public static boolean isNetworkAvailable;
    private HashMap<String, WeakReference<BaseActivity>> activityList = new HashMap<String, WeakReference<BaseActivity>>();

    public void addActivity(BaseActivity activity) {
        if (null != activity) {
            Log.i("RHG", "********* add Activity " + activity.getClass().getName());
            activityList.put(activity.getClass().getName(), new WeakReference<>(activity));
        }
    }

    public void removeActivity(BaseActivity activity) {
        if (null != activity) {
            Log.i("RHG", "********* remove Activity " + activity.getClass().getName());
            activityList.remove(activity.getClass().getName());
        }
    }

    public int getAcitivityCount() {
        return activityList.size();
    }

    public void exit() {

        for (String key : activityList.keySet()) {
            WeakReference<BaseActivity> activity = activityList.get(key);
            if (activity != null && activity.get() != null) {
                Log.i("RHG", "********* Exit " + activity.get().getClass().getSimpleName());
                activity.get().finish();
            }
        }

        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    public LocationService locationService;
    public Vibrator mVibrator;

    public static RefWatcher getRefWatcher(Context context) {
        InitApplication application = (InitApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        initBDMap();
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        initApplication = this;
        initSharePreferenceUtil();
        initDBHelper();
        initToast();
        initImageLoader();
    }

    private void initBDMap() {
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        if (NetUtil.isConnected(getApplicationContext()))
            isNetworkAvailable = true;
    }

    private void initSharePreferenceUtil() {
        SharePreferenceUtil.getInstance().init(getApplicationContext());
    }

    private void initToast() {
        ToastHelper.getInstance().init(getApplicationContext());
    }

    private void initDBHelper() {
        ShoppingCartDBHelper.init(getApplicationContext());
        LikeDBHelper.init(getApplicationContext());
    }

    public static InitApplication getInstance() {
        if (initApplication == null)
            initApplication = new InitApplication();
        return initApplication;
    }

    //------网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.recommend_default_icon_2)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

}
