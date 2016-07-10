package com.rhg.qf.mvp.api;

import com.rhg.qf.bean.AddressUrlBean;
import com.rhg.qf.bean.BannerTypeUrlBean;
import com.rhg.qf.bean.BaseBean;
import com.rhg.qf.bean.DeliverOrderUrlBean;
import com.rhg.qf.bean.DeliverStateBean;
import com.rhg.qf.bean.FavorableFoodUrlBean;
import com.rhg.qf.bean.GoodsDetailUrlBean;
import com.rhg.qf.bean.HeadMerchantUrlBean;
import com.rhg.qf.bean.HotFoodUrlBean;
import com.rhg.qf.bean.MerchantUrlBean;
import com.rhg.qf.bean.OrderUrlBean;
import com.rhg.qf.bean.ShopDetailUriBean;
import com.rhg.qf.bean.SignInBean;
import com.rhg.qf.bean.TestBean;
import com.rhg.qf.bean.TextTypeBean;

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

    @FormUrlEncoded
    @POST("Table/Json.php")
    Observable<MerchantUrlBean> getHomeMerchants(@Field("Table") String headrestaurants,
                                                 @Field("X") String x,
                                                 @Field("Y") String y);

    /*所有店铺head*/
    @FormUrlEncoded
    @POST("Table/Json.php")
    Observable<HeadMerchantUrlBean> getHeadMerchant(@Field("Table") String toprestaurants);

    /*所有店铺body*/
    @FormUrlEncoded
    @POST("Table/Json.php")
//table : restaurants,order：0.按销量 1.按距离 2.按评分
    Observable<MerchantUrlBean> getBodyMerchants(@Field("Table") String table,
                                                 @Field("Order") int order,
                                                 @Field("X") String longitude,
                                                 @Field("Y") String latitude);

    /*店铺详情*/
    @FormUrlEncoded
    @POST("Table/Json.php")
    //table:food; merchantId:商家Id号
    Observable<ShopDetailUriBean> getShopDetail(@Field("Table") String food,
                                                @Field("Restaurant") int merchantId);

    /*商品详情*/
    @FormUrlEncoded
    @POST("Table/FoodJson.php")
//table:foodmessage Foodid:food id号
    Observable<GoodsDetailUrlBean> getGoodsDetail(@Field("Table") String table,
                                                  @Field("Foodid") int foodId);

    /*用户订单详情*/
    @FormUrlEncoded
    @POST("Table/Json.php")
    //order:foodmessage;userId:用户ID;style:0.全部、1.待付款、2.进行中、3.已完成、、4.已退款
    Observable<OrderUrlBean> getOrderDetail(@Field("Table") String order,
                                            @Field("Client") String userId,
                                            @Field("Style") String style);

    /*跑腿员订单详情 API19*/
    @FormUrlEncoded
    @POST("Table/Json.php")
    Observable<DeliverOrderUrlBean> getDeliverOrder(@Field("Table") String deliverorder,
                                                    @Field("Deliver") String deliverId);

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
    @POST("Table/JsonSQL/UpdateAddress.php")
    Observable<String> addAddress(@Field("Client") String userName,
                                  @Field("Pwd") String pwd,
                                  @Field("Name") String name,
                                  @Field("Phone") String phone,
                                  @Field("Address") String address);

    /*获取地址*/
    @FormUrlEncoded
    @POST("Table/Json.php")
    Observable<AddressUrlBean> getAddress(@Field("Table") String address,
                                          @Field("ID") String userId);


    /*修改地址*/
    @FormUrlEncoded
    @POST("Table/JsonSQL/UpdateAddress.php")
    Observable<String> updateAddress(@Field("Client") String userName,
                                     @Field("ID") String id,
                                     @Field("Name") String name,
                                     @Field("Phone") String phone,
                                     @Field("Address") String address,
                                     @Field("Pwd") String pwd);

    /*
     *desc 厅搜索接口，入口位于主页和所有店铺页
     *author rhg
     *time 2016/7/7 17:12
     *email 1013773046@qq.com
     */
    @FormUrlEncoded
    @POST("Table/Json.php")
    Observable<MerchantUrlBean> getRestaurantSearchResult(@Field("Table") String searchRestaurants,
                                                          @Field("Restaurantkey") String searchContent,
                                                          @Field("Order") String style);

    @FormUrlEncoded
    @POST("Table/Json.php")
    /*style:0表示默认 1表示按销量 2表示按距离 3表示按评分*/
    Observable<HotFoodUrlBean> getHotGoods(@Field("Table") String hotFood,
                                           @Field("Order") String style);

    /*
     *desc 热销单品搜索，入口位于热销单品页面
     *author rhg
     *time 2016/7/7 17:12
     *email 1013773046@qq.com
     */
    @FormUrlEncoded
    @POST("Table/Json.php")
    /*style:0表示默认 1表示按销量 2表示按距离 3表示按评分*/
    Observable<HotFoodUrlBean> getHotGoodsForSearch(@Field("Table") String hotFood,
                                                    @Field("Hotfoodkey") String searchContent,
                                                    @Field("Order") String order);

    /*跑腿员订单配送查询 API20*/
    @FormUrlEncoded
    @POST("Table/Json.php")
    Observable<DeliverStateBean> getDeliverState(@Field("Table") String orderStyle,
                                                 @Field("Ordernumber") String orderId);


    @FormUrlEncoded
    @POST("Table/JsonSQL/Orderover.php")
    /*style =0 :请求退单；style = 1:完成订单
     TODO 用户修改订单状态 */
    Observable<BaseBean> modifyUserOrderState(@Field("ID") String orderId,
                                              @Field("Style") String style);


    @FormUrlEncoded
    @POST("Table/JsonSQL/ Updateorder.php")
    /*TODO 修改跑腿员订单的状态 */
    Observable<BaseBean> modifyDeliverOrderState(@Field("ID") String orderId,
                                                 @Field("Message") String content);


    @FormUrlEncoded
    @POST("Table/JsonSQL/ ClientHope.php")
    /*TODO 自主点单 */
    Observable<BaseBean> diyOrderFood(@Field("ID") String orderId);

    @FormUrlEncoded
    @POST("Table/JsonSQL/AddDeliver.php")
    /*TODO 跑腿员信息补充 */
    Observable<BaseBean> diyOrderFood(@Field("Name") String name,
                                      @Field("PersonId") String personId,
                                      @Field("Phonenumber") String phoneNum,
                                      @Field("Pwd") String pwd,
                                      @Field("Area") String area,
                                      @Field("ClientID") String clientId);
    /*@POST(QFoodApi.WXPAY_CREATE_ORDER_URL)
    Observable<String> getPrepayId(@QueryMap Map<String, String> orderParams);*/
}
