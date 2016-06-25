package com.rhg.qf.bean;

import java.util.List;

/**
 * desc:订单的网络请求数据模型
 * author：remember
 * time：2016/5/28 16:34
 * email：1013773046@qq.com
 */
public class OrderUrlBean {

    /**
     * total : 1
     * rows : [{"ID":"1","Client":"1","Receiver":"R1","Address":"杭州下沙","Phone":"138383838538","Price":"40","Style":"待接单","Otime":"2016-05-29 17:12:53"}]
     */

    private int total;
    /**
     * ID : 1
     * Client : 1
     * Receiver : R1
     * Address : 杭州下沙
     * Phone : 138383838538
     * Price : 40
     * Style : 待接单
     * Otime : 2016-05-29 17:12:53
     */

    private List<OrderBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrderBean> getRows() {
        return rows;
    }

    public void setRows(List<OrderBean> rows) {
        this.rows = rows;
    }

    public static class OrderBean {
        private String ID;
        private String Client;
        private String Receiver;
        private String Address;
        private String Phone;
        private String Price;
        private String Style;
        private String Otime;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

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

        public String getStyle() {
            return Style;
        }

        public void setStyle(String Style) {
            this.Style = Style;
        }

        public String getOtime() {
            return Otime;
        }

        public void setOtime(String Otime) {
            this.Otime = Otime;
        }
    }
}
