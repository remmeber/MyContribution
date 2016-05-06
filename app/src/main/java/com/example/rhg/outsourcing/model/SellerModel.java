package com.example.rhg.outsourcing.model;

/**
 * Created by remember on 2016/5/6.
 */
public class SellerModel {
    private String sellerName;
    private String foodType;
    private String sellerDistance;
    private int imageRid;

    public SellerModel(String sellerName, String foodType, String sellerDistance, int imageRid) {
        this.sellerName = sellerName;
        this.foodType = foodType;
        this.sellerDistance = sellerDistance;
        this.imageRid = imageRid;
    }

    public String getSellerName() {
        return sellerName;
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
}
