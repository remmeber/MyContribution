package com.rhg.qf.bean;

import java.util.List;

/**
 *desc:用于新建订单
 *author：remember
 *time：2016/7/11 0:27
 *email：1013773046@qq.com
 */
public class NewOrderBean {

    /**
     * Client : 1
     * Receiver : 张三
     * Address : 杭州下沙
     * Phone : 1383838438
     * Price : 200
     * food : [{"ID1":"1"},{"ID2":"2"}]
     */

    private String Client;
    private String Receiver;
    private String Address;
    private String Phone;
    private String Price;
    /**
     * ID1 : 1
     */

    private List<FoodBean> food;

    public String getClient() {
        return Client;
    }

    public void setClient(String Client) {
        this.Client = Client;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String Receiver) {
        this.Receiver = Receiver;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public List<FoodBean> getFood() {
        return food;
    }

    public void setFood(List<FoodBean> food) {
        this.food = food;
    }

    public static class FoodBean {
        private String foodId;

        public String getFoodId() {
            return foodId;
        }

        public void setFoodId(String foodId) {
            this.foodId = foodId;
        }
    }
}
