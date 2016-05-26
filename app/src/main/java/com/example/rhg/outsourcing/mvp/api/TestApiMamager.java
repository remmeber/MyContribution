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
 * Created by remember on 2016/5/25.
 */
public class TestApiMamager {
    private static TestApiMamager mInstant;

    private TestApiService testApiService;

    public static TestApiMamager getInstant() {
        if (mInstant == null)
            mInstant = new TestApiMamager();
        return mInstant;
    }

    private TestApiMamager() {
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
                .baseUrl(TestApi.BASE_URL)
                .build();
        this.testApiService = retrofit.create(TestApiService.class);
    }

    public TestApiService getTestApiService(){
        return testApiService;
    }
}
