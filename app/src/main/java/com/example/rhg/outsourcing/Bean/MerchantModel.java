package com.example.rhg.outsourcing.bean;

import java.util.List;

/**
 * Created by remember on 2016/5/21.
 * 店铺模型  保存该店铺所有的商品信息
 */
public class MerchantModel {
    String MerchantId;
    String MerchantName;
    String MerchantLogoUrl;
    String MerchantTelephone;
    String MerchantAddress;
    List<GoodsDetailModel> goodsDetailModelList;

    public String getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(String merchantId) {
        MerchantId = merchantId;
    }

    public String getMerchantName() {
        return MerchantName;
    }

    public void setMerchantName(String merchantName) {
        MerchantName = merchantName;
    }

    public String getMerchantLogoUrl() {
        return MerchantLogoUrl;
    }

    public void setMerchantLogoUrl(String merchantLogoUrl) {
        MerchantLogoUrl = merchantLogoUrl;
    }

    public List<GoodsDetailModel> getGoodsDetailModelList() {
        return goodsDetailModelList;
    }

    public void setGoodsDetailModelList(List<GoodsDetailModel> goodsDetailModelList) {
        this.goodsDetailModelList = goodsDetailModelList;
    }

    public String getMerchantTelephone() {
        return MerchantTelephone;
    }

    public void setMerchantTelephone(String merchantTelephone) {
        MerchantTelephone = merchantTelephone;
    }

    public String getMerchantAddress() {
        return MerchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        MerchantAddress = merchantAddress;
    }
}
