package com.example.rhg.outsourcing.model;

import java.util.List;

/**
 * Created by remember on 2016/5/19.
 * TODO 商品详情数据模型
 */
public class GoodsDetailModel {
    //轮播图片url集合
    private String productId;
    /*todo 在商品列表中只需要取imageUrls[0]即可*/
    private String[] imageUrls;
    private boolean isLike;
    private String goodsName;
    private String goodSellNum;
    private String goodsDescription;
    private String goodsPrice;

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setImageUrls(String[] imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodSellNum(String goodSellNum) {
        this.goodSellNum = goodSellNum;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getProductId() {
        return productId;
    }

    public String[] getImageUrls() {
        return imageUrls;
    }

    public boolean isLike() {
        return isLike;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodSellNum() {
        return goodSellNum;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }
}
