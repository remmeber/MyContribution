package com.example.rhg.outsourcing.mvp.api;

import com.example.rhg.outsourcing.bean.TestBean;


import retrofit.http.GET;
import rx.Observable;

/**
 * Created by remember on 2016/5/25.
 */
public interface TestApiService {
    @GET("json/head.html")
    Observable<TestBean> getResult();
}
