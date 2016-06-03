package com.example.rhg.outsourcing.mvp.api;

import com.example.rhg.outsourcing.bean.FavorableFoodUrlBean;
import com.example.rhg.outsourcing.bean.BannerTypeUrlBean;
import com.example.rhg.outsourcing.bean.MerchantUrlBean;
import com.example.rhg.outsourcing.bean.RecommendListUrlBean;
import com.example.rhg.outsourcing.bean.TextTypeBean;
import com.squareup.okhttp.RequestBody;


import java.io.File;
import java.util.List;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * desc:APP网络请求接口类
 * author：remember
 * time：2016/5/28 16:54
 * email：1013773046@qq.com
 */
public interface QFoodApiService {
    /*首页API*/
    @GET("json/head.html")
    Observable<BannerTypeUrlBean> getBannerUrl();

    @GET("json/message.html")
    Observable<TextTypeBean> getMessage();

    @GET("json/food.html")
    Observable<FavorableFoodUrlBean> getFavorableFood();

    @GET("json/restaurant.html")
    Observable<RecommendListUrlBean> getRecommendList();

    /*所有店铺*/
    @GET("Table/Json.php")
//table : restaurants,order：0.按销量 1.按距离 2.按评分
    Observable<MerchantUrlBean> getAllShop(@Query("Table") String table,
                                                              @Query("Order") int order);

    /*商品详情*/
    @GET("Table/Json.php")
//table:foodmessage restaurant:id号
    Observable<String> getGoodsDetail(@Query("Table") String table,
                                      @Query("Restaurant") int id);

    /*订单详情*/
    @GET("Table/json.php")
    //order:
    Observable<String> getOrderDetail(@Query("Table") String order,
                                      @Query("Client") int id,
                                      @Query("Style") int style);

    /*upload head image*/
    @Multipart
    @FormUrlEncoded
    @POST("Clientpic.php")
    //return: success error
    Observable<String> upLoadHeadImage(@Field("Pic") File file,
                                       @Field("Client") String userName,
                                       @Field("Pwd") String pwd);

    /*get head image*/
    @GET("Pic/ClientPic/{userName}.jpg")
    Observable<String> getHeadImage(@Path("userName") String userName);

    /*user sign in*/
    @GET("Table/Json.php")
    Observable<String> userSignIn(@Query("Table") String client,
                                  @Query("Client") String userName,
                                  @Query("Pwd") String pwd);

    @GET("JsonSQL/AddAddress.php")
    Observable<String> addAddress(@Query("Client") String userName,
                                  @Query("Pwd") String pwd,
                                  @Query("Name") String name,
                                  @Query("Phone") String phone,
                                  @Query("Address") String address);
    /*@GET("json/order.html")
    Observable<OrderBean> getOrder();*/
}
