package com.example.rhg.outsourcing.mvp.api;

import android.util.Log;

import com.example.rhg.outsourcing.application.InitApplication;
import com.example.rhg.outsourcing.utils.NetUtil;
import com.example.rhg.outsourcing.utils.ToastHelper;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * desc:service管理类
 * author：remember
 * time：2016/5/28 16:53
 * email：1013773046@qq.com
 */
public class QFoodApiMamager {
    private static QFoodApiMamager mInstant;

    private QFoodApiService QFoodApiService;

    public static QFoodApiMamager getInstant() {
        if (mInstant == null)
            mInstant = new QFoodApiMamager();
        return mInstant;
    }

    private QFoodApiMamager() {
        OkHttpClient okHttpClient = new OkHttpClient();
        File cacheFile = new File(InitApplication.getInstance().getExternalCacheDir(), "QFood_Cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        okHttpClient.setCache(cache);
        okHttpClient.setReadTimeout(5000, TimeUnit.MILLISECONDS);
        okHttpClient.interceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        Retrofit retrofit = new Retrofit.Builder()
//                .client(ProgressHelper.addProgress(builder).build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(QFoodApi.BASE_URL)
                .build();
        this.QFoodApiService = retrofit.create(QFoodApiService.class);
    }

    public QFoodApiService getQFoodApiService() {
        return QFoodApiService;
    }

    private final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isConnected(InitApplication.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                ToastHelper.getInstance()._toast("no network");
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isConnected(InitApplication.getInstance())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=86400")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
}
