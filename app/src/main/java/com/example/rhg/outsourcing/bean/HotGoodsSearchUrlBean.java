package com.example.rhg.outsourcing.bean;

import java.util.List;

/**
 * 作者：rememberon 2016/6/4
 * 邮箱：1013773046@qq.com
 */
public class HotGoodsSearchUrlBean {

    /**
     * result : 0
     * msg : 请求成功
     * total : 5
     * rows : [{"RName":"黄焖鸡米饭","FName":"重辣黄焖鸡米饭","Stars":"4","Delivery":"100","Fee":"5","Distance":"100","Price":"128"},{"RName":"绿焖鸡米饭","FName":"重辣绿焖鸡米饭","Stars":"5","Delivery":"200","Fee":"4","Distance":"200","Price":"100"},{"RName":"绿焖鸡米饭","FName":"不辣绿焖鸡米饭","Stars":"5","Delivery":"200","Fee":"4","Distance":"200","Price":"20"},{"RName":"黄焖鸡米饭","FName":"微辣黄焖鸡米饭","Stars":"4","Delivery":"100","Fee":"5","Distance":"100","Price":"288"},{"RName":"Pizza","FName":"鸡蛋培根披萨","Stars":"5","Delivery":"10","Fee":"2","Distance":"300","Price":"210"}]
     */

    private int result;
    private String msg;
    private int total;
    /**
     * RName : 黄焖鸡米饭
     * FName : 重辣黄焖鸡米饭
     * Stars : 4
     * Delivery : 100
     * Fee : 5
     * Distance : 100
     * Price : 128
     */

    private List<HotGoodsSearchBean> rows;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<HotGoodsSearchBean> getRows() {
        return rows;
    }

    public void setRows(List<HotGoodsSearchBean> rows) {
        this.rows = rows;
    }

    public static class HotGoodsSearchBean {
        private String RName;
        private String FName;
        private String Stars;
        private String Delivery;
        private String Fee;
        private String Distance;
        private String Price;

        public String getRName() {
            return RName;
        }

        public void setRName(String RName) {
            this.RName = RName;
        }

        public String getFName() {
            return FName;
        }

        public void setFName(String FName) {
            this.FName = FName;
        }

        public String getStars() {
            return Stars;
        }

        public void setStars(String Stars) {
            this.Stars = Stars;
        }

        public String getDelivery() {
            return Delivery;
        }

        public void setDelivery(String Delivery) {
            this.Delivery = Delivery;
        }

        public String getFee() {
            return Fee;
        }

        public void setFee(String Fee) {
            this.Fee = Fee;
        }

        public String getDistance() {
            return Distance;
        }

        public void setDistance(String Distance) {
            this.Distance = Distance;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }
    }
}
