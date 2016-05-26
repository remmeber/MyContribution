package com.example.rhg.outsourcing.bean;

/**
<<<<<<< HEAD
 * Created by remember on 2016/5/24.
=======
 * Created by rhg on 2016/5/23.
>>>>>>> db94743a40f21798fbfd111c9c8cfebaa0ca6b2d
 */
public class PayContent {
    String goodsName;
    String goodsDescription;
    String payMoney;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    @Override
    public String toString() {
        return "goodsName: "+goodsName+" goodsDescription: "+" payMoney: ";
    }
}
