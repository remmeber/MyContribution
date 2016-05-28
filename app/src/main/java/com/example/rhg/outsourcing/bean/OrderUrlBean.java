package com.example.rhg.outsourcing.bean;

/**
 *desc:订单的网络请求数据模型
 *author：remember
 *time：2016/5/28 16:34
 *email：1013773046@qq.com
 */
public class OrderUrlBean {
    String ID;
    String Purchanser;
    String OTime;
    String Deliver;
    String Oaddress;
    String Style;
    String Price;
    String Food;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPurchanser() {
        return Purchanser;
    }

    public void setPurchanser(String purchanser) {
        Purchanser = purchanser;
    }

    public String getOTime() {
        return OTime;
    }

    public void setOTime(String OTime) {
        this.OTime = OTime;
    }

    public String getDeliver() {
        return Deliver;
    }

    public void setDeliver(String deliver) {
        Deliver = deliver;
    }

    public String getOaddress() {
        return Oaddress;
    }

    public void setOaddress(String oaddress) {
        Oaddress = oaddress;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        Style = style;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getFood() {
        return Food;
    }

    public void setFood(String food) {
        Food = food;
    }
}
