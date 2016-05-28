package com.example.rhg.outsourcing.bean;

/**
 *desc:所有店铺的数据模型，继承自{@link RecommendListBean},因为其有共同的数据类型
 *author：remember
 *time：2016/5/28 16:36
 *email：1013773046@qq.com
 */
public class QFoodAllSellerBean extends RecommendListBean {
    private String demandMoney;
    private String deliverMoney;

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
}
