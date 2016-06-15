package com.example.rhg.outsourcing.mvp.api;

import android.util.Log;

import com.example.rhg.outsourcing.application.InitApplication;
import com.example.rhg.outsourcing.utils.NetUtil;
import com.example.rhg.outsourcing.utils.ToastHelper;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
        /*OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient.Builder builder= okHttpClient.newBuilder();
        File cacheFile = new File(InitApplication.getInstance().getExternalCacheDir(), "QFood_Cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        builder.cache(cache);*/
       /* HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(signingInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();*/
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
//                        Response response = chain.proceed(request);
                        /*BufferedSink bufferedSink = new Buffer();
                        Log.i("RHG", request.body().writeTo(bufferedSink));*/
                        return chain.proceed(request);
                    }
                }).build();
        Retrofit retrofit = new Retrofit.Builder()
//                .client(ProgressHelper.addProgress(builder).build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(QFoodApi.BASE_URL)
                .client(okHttpClient)
                .build();
        this.QFoodApiService = retrofit.create(QFoodApiService.class);
    }

    public QFoodApiService getQFoodApiService() {
        return QFoodApiService;
    }

    private final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            Log.i("RHG", request.body().toString());
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
