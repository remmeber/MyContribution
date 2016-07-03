package com.rhg.qf.application;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.rhg.qf.R;
import com.rhg.qf.activity.BaseActivity;
import com.rhg.qf.datebase.AccountDBHelper;
import com.rhg.qf.locationservice.LocationService;
import com.rhg.qf.utils.AccountUtil;
import com.rhg.qf.utils.ToastHelper;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.socialize.PlatformConfig;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * desc:APP的入口，定义全局变量
 * author：remember
 * time：2016/5/28 16:22
 * email：1013773046@qq.com
 */
public class InitApplication extends Application {
    public final static String QQID = "1105497604";
    public final static String QQKEY = "MdCq3ttlP0xlAPIg";
    public final static String WXID = "wxb066167618e700e6";/*已签名*/
    public final static String WXKEY = "2a2d1cc89b6214c427001d3b1e5dad24";/*已签名*/
    private static InitApplication initApplication;
    public LocationService locationService;
    public Vibrator mVibrator;
    private HashMap<String, WeakReference<BaseActivity>> activityList = new HashMap<String, WeakReference<BaseActivity>>();
    private HashMap<String, WeakReference<Object>> objectList = new HashMap<>();
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        InitApplication application = (InitApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    public static InitApplication getInstance() {
        if (initApplication == null)
            initApplication = new InitApplication();
        return initApplication;
    }

    public void addObject(Object object) {
        if (null != object) {
            Log.i("RHG", "********* add Object " + object.getClass().getName());
            objectList.put(object.getClass().getName(), new WeakReference<>(object));

        }
    }

    public void removeObject(Object object) {
        if (null != object) {
            Log.i("RHG", "********* remove Activity " + object.getClass().getName());
            objectList.remove(object.getClass().getName());
        }
    }

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

    @Override
    public void onCreate() {
        initBDMap();
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        initApplication = this;
        initAccountUtil();
        initDBHelper();
        initToast();
        initImageLoader();
        thirdConfig();
    }

    /**
     * desc:第三方配置
     * author：remember
     * time：2016/6/15 22:06
     * email：1013773046@qq.com
     */
    private void thirdConfig() {
        PlatformConfig.setWeixin(WXID, WXKEY);
        PlatformConfig.setQQZone(QQID, QQKEY);
    }

    /***
     * 初始化定位sdk，建议在Application中创建
     */
    private void initBDMap() {
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
    }

    private void initAccountUtil() {
        AccountUtil.getInstance().init(getApplicationContext());
    }

    private void initToast() {
        ToastHelper.getInstance().init(getApplicationContext());
    }

    private void initDBHelper() {
        AccountDBHelper.init(getApplicationContext());
//        LikeDBHelper.init(getApplicationContext());
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
