package com.rhg.outsourcing.mvp.api;

import com.rhg.outsourcing.bean.FavorableFoodUrlBean;
import com.rhg.outsourcing.bean.BannerTypeUrlBean;
import com.rhg.outsourcing.bean.GoodsDetailUrlBean;
import com.rhg.outsourcing.bean.HotGoodsSearchUrlBean;
import com.rhg.outsourcing.bean.HotGoodsUrlBean;
import com.rhg.outsourcing.bean.MerchantUrlBean;
import com.rhg.outsourcing.bean.OrderUrlBean;
import com.rhg.outsourcing.bean.RecommendListUrlBean;
import com.rhg.outsourcing.bean.SearchUrlBean;
import com.rhg.outsourcing.bean.ShopDetailUriBean;
import com.rhg.outsourcing.bean.SignInBean;
import com.rhg.outsourcing.bean.TestBean;
import com.rhg.outsourcing.bean.TextTypeBean;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
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
    @FormUrlEncoded
    @POST("Table/Json.php")
//table : restaurants,order：0.按销量 1.按距离 2.按评分
    Observable<MerchantUrlBean> getAllShop(@Field("Table") String table,
                                           @Field("Order") int order);

    /*店铺详情*/
    @FormUrlEncoded
    @POST("Table/Json.php")
    //table:food; merchantId:商家Id号
    Observable<ShopDetailUriBean> getShopDetail(@Field("Table") String food,
                                                @Field("Restaurant") int merchantId);

    /*商品详情*/
    @FormUrlEncoded
    @POST("Table/Json.php")
//table:foodmessage Foodid:food id号
    Observable<GoodsDetailUrlBean> getGoodsDetail(@Field("Table") String table,
                                                  @Field("Foodid") int foodId);

    /*订单详情*/
    @FormUrlEncoded
    @POST("Table/Json.php")
    //order:foodmessage;userId:用户ID;style:0.全部、1.待付款、2.进行中、3.已完成、、4.已退款
    Observable<OrderUrlBean> getOrderDetail(@Field("Table") String order,
                                            @Field("Client") String userId,
                                            @Field("Style") String style);

    /*upload head image*/
    @Multipart
    @POST("Clientpic2.php")
    //return: success error
    Observable<TestBean> UploadHeadImage(@Part MultipartBody.Part file,
                                         @Part("Client") RequestBody userName,
                                         @Part("Pwd") RequestBody pwd);

    /*get head image*/
    @POST("Pic/ClientPic/{userName}.jpg")
    Observable<String> getHeadImage(@Path("userName") String userName);

    /*user sign in*/
    @FormUrlEncoded
    @POST("Table/Json.php")
    Observable<SignInBean> userSignIn(@Field("Table") String client,
                                      @Field("Client") String userName,
                                      @Field("Pwd") String pwd);

    /*添加地址*/
    @FormUrlEncoded
    @POST("JsonSQL/AddAddress.php")
//    Observable<String> addAddress(@Body AddressBean addressUrlBean);
    Observable<String> addAddress(@Field("Client") String userName,
                                  @Field("Pwd") String pwd,
                                  @Field("Name") String name,
                                  @Field("Phone") String phone,
                                  @Field("Address") String address);

    @FormUrlEncoded
    @POST("Table/Json.php")
    Observable<SearchUrlBean> getSearchResult(@Field("Table") String searchRestaurants,
                                              @Field("Restaurantkey") String searchContent,
                                              @Field("Order") String style);

    @FormUrlEncoded
    @POST("Table/Json.php")
    /*style:0表示默认 1表示按销量 2表示按距离 3表示按评分*/
    Observable<HotGoodsUrlBean> getHotGoods(@Field("Table") String hotFood,
                                            @Field("Order") String style);

    @FormUrlEncoded
    @POST("Table/Json.php")
    /*style:0表示默认 1表示按销量 2表示按距离 3表示按评分*/
    Observable<HotGoodsSearchUrlBean> getHotGoodsForSearch(@Field("Table") String hotFood,
                                                           @Field("Hotfoodkey") String searchContent,
                                                           @Field("Order") String style);
}