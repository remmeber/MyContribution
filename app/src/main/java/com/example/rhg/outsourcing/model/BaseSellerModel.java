package com.example.rhg.outsourcing.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by remember on 2016/5/6.
 */

public class BaseSellerModel implements Parcelable {
    private String merchantName;
    private String merchantId;
    private String goodsName;
    private String productId;
    private int imageRid;//TODO 此处为商品的图片不是商家图片 后期要变为String Url
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

    public BaseSellerModel() {
    }

    public BaseSellerModel(String merchantName, int imageRid) {
        this.merchantName = merchantName;
        this.imageRid = imageRid;
    }

    //TODO-------------------------for Recommend----------------------------------------------------
    public BaseSellerModel(String merchantName, String foodType, String sellerDistance, int imageRid) {
        this.merchantName = merchantName;
        this.foodType = foodType;
        this.sellerDistance = sellerDistance;
        this.imageRid = imageRid;
    }

    //TODO--------------------------for store-------------------------------------------------------
    public BaseSellerModel(String merchantName, String foodType, String sellerDistance, String demandMoney, String deliverMoney, int imageRid) {
        this.merchantName = merchantName;
        this.foodType = foodType;
        this.sellerDistance = sellerDistance;
        this.demandMoney = demandMoney;
        this.deliverMoney = deliverMoney;
        this.imageRid = imageRid;
    }

    public BaseSellerModel(String merchantName, int imageRid
            , String tv_state, String tv_orderTime, String tv_orderNumber, String tv_totalCount) {
        this.merchantName = merchantName;
        this.imageRid = imageRid;
        this.tv_state = tv_state;
        this.tv_orderTime = tv_orderTime;
        this.tv_orderNumber = tv_orderNumber;
        this.tv_totalCount = tv_totalCount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getImageRid() {
        return imageRid;
    }

    public void setImageRid(int imageRid) {
        this.imageRid = imageRid;
    }

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

    public String getDemandMoney() {
        return demandMoney;
    }

    public void setDemandMoney(String demandMoney) {
        this.demandMoney = demandMoney;
    }

    public String getDeliverMoney() {
        return deliverMoney;
    }

    public void setDeliverMoney(String deliverMoney) {
        this.deliverMoney = deliverMoney;
    }

    public String getTv_state() {
        return tv_state;
    }

    public void setTv_state(String tv_state) {
        this.tv_state = tv_state;
    }

    public String getTv_orderTime() {
        return tv_orderTime;
    }

    public void setTv_orderTime(String tv_orderTime) {
        this.tv_orderTime = tv_orderTime;
    }

    public String getTv_orderNumber() {
        return tv_orderNumber;
    }

    public void setTv_orderNumber(String tv_orderNumber) {
        this.tv_orderNumber = tv_orderNumber;
    }

    public String getTv_totalCount() {
        return tv_totalCount;
    }

    public void setTv_totalCount(String tv_totalCount) {
        this.tv_totalCount = tv_totalCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Creator<BaseSellerModel> CREATOR=new Creator<BaseSellerModel>(){

        @Override
        public BaseSellerModel createFromParcel(Parcel source) {
            BaseSellerModel baseSellerModel = new BaseSellerModel();
            baseSellerModel.setMerchantName(source.readString());
            baseSellerModel.setMerchantId(source.readString());
            baseSellerModel.setGoodsName(source.readString());
            baseSellerModel.setProductId(source.readString());
            baseSellerModel.setImageRid(source.readInt());
            return null;
        }

        @Override
        public BaseSellerModel[] newArray(int size) {
            return new BaseSellerModel[size];
        }
    };
}
