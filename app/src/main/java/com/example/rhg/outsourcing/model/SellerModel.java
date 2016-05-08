package com.example.rhg.outsourcing.model;

/**
 * Created by remember on 2016/5/6.
 */
public class SellerModel {
    private String storeName;
    private String foodType;
    private String sellerDistance;
    private String demandMoney;
    private String deliverMoney;
    private int imageRid;//TODO 后期需要换成URL

    //TODO-------------------------for Recommend----------------------------------------------------
    public SellerModel(String storeName, String foodType, String sellerDistance, int imageRid) {
        this.storeName = storeName;
        this.foodType = foodType;
        this.sellerDistance = sellerDistance;
        this.imageRid = imageRid;
    }
    //TODO--------------------------for store-------------------------------------------------------
    public SellerModel(String storeName, String foodType, String sellerDistance, String demandMoney, String deliverMoney, int imageRid) {
        this.storeName = storeName;
        this.foodType = foodType;
        this.sellerDistance = sellerDistance;
        this.demandMoney = demandMoney;
        this.deliverMoney = deliverMoney;
        this.imageRid = imageRid;
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
}
