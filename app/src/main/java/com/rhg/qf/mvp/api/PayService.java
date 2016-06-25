package com.rhg.qf.mvp.api;

import com.rhg.qf.bean.OrderXml;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 作者：rememberon 2016/6/25
 * 邮箱：1013773046@qq.com
 */
public interface PayService {
    @POST("pay/unifiedorder")
    Observable<OrderXml> getPrepayId(@QueryMap Map<String, String> orderParams);
}
