package com.rhg.outsourcing.bean;

import java.util.List;

/**
 * 作者：rememberon 2016/6/3
 * 邮箱：1013773046@qq.com
 */
public class GoodsDetailUrlBean {

    /**
     * total : 1
     * rows : [{"Name":"微辣黄焖鸡米饭","Price":"288","MonthlySales":"16","Style":"有货","Message":"微辣的黄焖鸡米饭","Pic":"http://www.zousitanghulu.com/Pic/1.jpg"}]
     */

    private int total;
    /**
     * Name : 微辣黄焖鸡米饭
     * Price : 288
     * MonthlySales : 16
     * Style : 有货
     * Message : 微辣的黄焖鸡米饭
     * Pic : http://www.zousitanghulu.com/Pic/1.jpg
     */

    private List<GoodsDetailBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<GoodsDetailBean> getRows() {
        return rows;
    }

    public void setRows(List<GoodsDetailBean> rows) {
        this.rows = rows;
    }

    public static class GoodsDetailBean {
        private String Name;
        private String Price;
        private String MonthlySales;
        private String Style;
        private String Message;
        private List<String> Pics;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getMonthlySales() {
            return MonthlySales;
        }

        public void setMonthlySales(String MonthlySales) {
            this.MonthlySales = MonthlySales;
        }

        public String getStyle() {
            return Style;
        }

        public void setStyle(String Style) {
            this.Style = Style;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }

        public List<String> getPics() {
            return Pics;
        }

        public void setPics(List<String> Pics) {
            this.Pics = Pics;
        }
    }
}
