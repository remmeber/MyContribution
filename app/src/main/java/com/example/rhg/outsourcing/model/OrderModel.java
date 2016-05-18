package com.example.rhg.outsourcing.model;

/**
 * Created by remember on 2016/5/17.
 */
public class OrderModel extends BaseSellerModel {
    private String tv_state;
    private String tv_orderTime;
    private String tv_orderNumber;
    private String tv_totalCount;

    public OrderModel(String storeName, int imageRid, String tv_state, String tv_orderTime
            , String tv_orderNumber, String tv_totalCount) {
        super(storeName, imageRid);
        this.tv_state = tv_state;
        this.tv_orderTime = tv_orderTime;
        this.tv_orderNumber = tv_orderNumber;
        this.tv_totalCount = tv_totalCount;
    }

    public String getTv_state() {
        return tv_state;
    }

    public String getTv_orderTime() {
        return tv_orderTime;
    }

    public String getTv_orderNumber() {
        return tv_orderNumber;
    }

    public String getTv_totalCount() {
        return tv_totalCount;
    }
}
