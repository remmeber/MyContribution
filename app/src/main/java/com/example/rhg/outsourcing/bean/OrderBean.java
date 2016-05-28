package com.example.rhg.outsourcing.bean;

/**
 *desc:订单本地数据模型
 *author：remember
 *time：2016/5/28 16:34
 *email：1013773046@qq.com
 */
public class OrderBean extends BaseSellerBean {
    private String tv_state;
    private String tv_orderTime;
    private String tv_orderTag;
    private String tv_totalMoney;

    public void setTv_state(String tv_state) {
        this.tv_state = tv_state;
    }

    public void setTv_orderTime(String tv_orderTime) {
        this.tv_orderTime = tv_orderTime;
    }

    public void setTv_orderTag(String tv_orderTag) {
        this.tv_orderTag = tv_orderTag;
    }

    public void setTv_totalMoney(String tv_totalMoney) {
        this.tv_totalMoney = tv_totalMoney;
    }

    public String getTv_state() {
        return tv_state;
    }

    public String getTv_orderTime() {
        return tv_orderTime;
    }

    public String getTv_orderTag() {
        return tv_orderTag;
    }

    public String getTv_totalMoney() {
        return tv_totalMoney;
    }
}
