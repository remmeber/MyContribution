package com.example.rhg.outsourcing.model;

/**
 * Created by remember on 2016/5/17.
 */
public class AllStoreModel extends HomeSellerModel {
    private String demandMoney;
    private String deliverMoney;

    public AllStoreModel(String storeName, int imageRid, String foodType, String sellerDistance, String demandMoney, String deliverMoney) {
        super(storeName, imageRid, foodType, sellerDistance);
        this.demandMoney = demandMoney;
        this.deliverMoney = deliverMoney;
    }

    @Override
    public String getDemandMoney() {
        return demandMoney;
    }

    @Override
    public String getDeliverMoney() {
        return deliverMoney;
    }
}
