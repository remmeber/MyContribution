package com.example.rhg.outsourcing.model;

/**
 * Created by remember on 2016/5/6.
 */
public class BaseSellerModel {
    private String storeName;
    private int imageRid;//TODO 后期需要换成URL
    //for home page
    private String foodType;
    private String sellerDistance;
    //for seller page
    private String demandMoney;
    private String deliverMoney;
    //for my order
    private String tv_state;
    private String tv_orderTime;
    private String tv_orderNumber;
    private String tv_totalCount;

    public BaseSellerModel(String storeName, int imageRid) {
        this.storeName = storeName;
        this.imageRid = imageRid;
    }

    //TODO-------------------------for Recommend----------------------------------------------------
    public BaseSellerModel(String storeName, String foodType, String sellerDistance, int imageRid) {
        this.storeName = storeName;
        this.foodType = foodType;
        this.sellerDistance = sellerDistance;
        this.imageRid = imageRid;
    }

    //TODO--------------------------for store-------------------------------------------------------
    public BaseSellerModel(String storeName, String foodType, String sellerDistance, String demandMoney, String deliverMoney, int imageRid) {
        this.storeName = storeName;
        this.foodType = foodType;
        this.sellerDistance = sellerDistance;
        this.demandMoney = demandMoney;
        this.deliverMoney = deliverMoney;
        this.imageRid = imageRid;
    }

    public BaseSellerModel(String storeName, int imageRid
            , String tv_state, String tv_orderTime, String tv_orderNumber, String tv_totalCount) {
        this.storeName = storeName;
        this.imageRid = imageRid;
        this.tv_state = tv_state;
        this.tv_orderTime = tv_orderTime;
        this.tv_orderNumber = tv_orderNumber;
        this.tv_totalCount = tv_totalCount;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getSellerDistance() {
        return sellerDistance;
    }

    public int getImageRid() {
        return imageRid;
    }

    public String getDemandMoney() {
        return demandMoney;
    }

    public String getDeliverMoney() {
        return deliverMoney;
    }

    public String getTv_state() {
        return tv_state;
    }

    public String getTv_orderTime() {
        return tv_orderTime;
    }

    public String getTv_orderNumber() {
        return tv_orderNumber;
    }

    public String getTv_totalCount() {
        return tv_totalCount;
    }
}
