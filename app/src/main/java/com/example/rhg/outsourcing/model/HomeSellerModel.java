package com.example.rhg.outsourcing.model;

/**
 * Created by remember on 2016/5/17.
 */
public class HomeSellerModel extends BaseSellerModel {
    private String foodType;
    private String sellerDistance;

    public HomeSellerModel(String storeName, int imageRid, String foodType, String sellerDistance) {
        super(storeName, imageRid);
        this.foodType = foodType;
        this.sellerDistance = sellerDistance;
    }

    @Override
    public String getFoodType() {
        return foodType;
    }

    @Override
    public String getSellerDistance() {
        return sellerDistance;
    }
}
