package com.example.rhg.outsourcing.bean;

/**
 *desc:商品详情页点赞模型
 *author：remember
 *time：2016/5/28 16:33
 *email：1013773046@qq.com
 */
public class LikeBean {
    public static final String KEY_PRODUCT_ID = "productId";
    public static final String KEY_LIKE = "like";

    private String product_id;
    private boolean isLike;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
