package com.example.rhg.outsourcing.bean;

/**
 *desc:商家的基类数据
 *author：remember
 *time：2016/5/28 16:29
 *email：1013773046@qq.com
 */
public class BaseSellerBean {
    private String merchantName;
    private String merchantId;
    private String goodsName;
    private String productId;
    private String imageUrl;//TODO 此处为商品的图片不是商家图片 后期要变为String Url

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
