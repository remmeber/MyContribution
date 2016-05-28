package com.example.rhg.outsourcing.application;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.example.rhg.outsourcing.R;
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
/**
 *desc:APP的入口，定义全局变量
 *author：remember
 *time：2016/5/28 16:22
 *email：1013773046@qq.com
 */
public class InitApplication extends Application {
    private static InitApplication initApplication;
    public static boolean isNetworkAvailable;
    public LocationService locationService;
    public Vibrator mVibrator;

    @Override
    public void onCreate() {
        initBDMap();
        super.onCreate();
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
