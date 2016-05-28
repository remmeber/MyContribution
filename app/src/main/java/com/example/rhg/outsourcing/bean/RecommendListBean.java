package com.example.rhg.outsourcing.bean;
/**
 *desc:主页推荐店铺的数据本地数据模型，继承自{@link BaseSellerBean}
 *author：remember
 *time：2016/5/28 16:37
 *email：1013773046@qq.com
 */
public class RecommendListBean extends BaseSellerBean {
    private String foodType;
    private String sellerDistance;

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getSellerDistance() {
        return sellerDistance;
    }

    public void setSellerDistance(String sellerDistance) {
        this.sellerDistance = sellerDistance;
    }
}
