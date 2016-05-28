package com.example.rhg.outsourcing.bean;

import java.util.List;

/**
 *desc:店铺模型  保存该店铺所有的商品信息 todo 还未使用
 *author：remember
 *time：2016/5/28 16:33
 *email：1013773046@qq.com
 */
public class MerchantModel {
    String MerchantId;
    String MerchantName;
    String MerchantLogoUrl;
    String MerchantTelephone;
    String MerchantAddress;
    List<GoodsDetailBean> goodsDetailBeanList;

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

    public List<GoodsDetailBean> getGoodsDetailBeanList() {
        return goodsDetailBeanList;
    }

    public void setGoodsDetailBeanList(List<GoodsDetailBean> goodsDetailBeanList) {
        this.goodsDetailBeanList = goodsDetailBeanList;
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
