package com.example.rhg.outsourcing.mvp.api;

import com.example.rhg.outsourcing.bean.FavorableFoodUrlBean;
import com.example.rhg.outsourcing.bean.BannerTypeUrlBean;
import com.example.rhg.outsourcing.bean.RecommendListUrlBean;
import com.example.rhg.outsourcing.bean.TextTypeBean;


import retrofit.http.GET;
import rx.Observable;

/**
 *desc:APP网络请求接口类
 *author：remember
 *time：2016/5/28 16:54
 *email：1013773046@qq.com
 */
public interface QFoodApiService {
    /*首页API*/
    @GET("json/head.html")
    Observable<BannerTypeUrlBean> getBannerUrl();

    /*@GET("json/order.html")
    Observable<OrderBean> getOrder();*/
    @GET("json/message.html")
    Observable<TextTypeBean> getMessage();

    @GET("json/food.html")
    Observable<FavorableFoodUrlBean> getFavorableFood();

    @GET("json/restaurant.html")
    Observable<RecommendListUrlBean> getRecommendList();
    /*首页API*/


}
