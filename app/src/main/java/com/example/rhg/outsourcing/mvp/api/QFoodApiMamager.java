package com.example.rhg.outsourcing.mvp.api;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 *desc:service管理类
 *author：remember
 *time：2016/5/28 16:53
 *email：1013773046@qq.com
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
        okHttpClient.setReadTimeout(5000,TimeUnit.MILLISECONDS);
        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request());
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
//                .client(ProgressHelper.addProgress(builder).build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(QFoodApi.BASE_URL)
                .build();
        this.QFoodApiService = retrofit.create(QFoodApiService.class);
    }

    public QFoodApiService getQFoodApiService(){
        return QFoodApiService;
    }
}
