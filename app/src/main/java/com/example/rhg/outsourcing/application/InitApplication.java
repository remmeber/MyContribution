package com.example.rhg.outsourcing.application;

import android.app.Application;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.datebase.LikeDBHelper;
import com.example.rhg.outsourcing.datebase.ShoppingCartDBHelper;
import com.example.rhg.outsourcing.utils.SharePreferenceUtil;
import com.example.rhg.outsourcing.utils.ToastHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

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
        initToast();
        initImageLoader();
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
