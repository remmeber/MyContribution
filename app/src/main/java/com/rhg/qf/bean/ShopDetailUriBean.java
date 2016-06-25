package com.rhg.qf.bean;

import java.util.List;

/**
 * 作者：rememberon 2016/6/3
 * 邮箱：1013773046@qq.com
 */
public class ShopDetailUriBean {
    /**
     * result : 0
     * msg : 请求成功
     * total : 2
     * rows : [{"ID":"1","Name":"微辣黄焖鸡米饭","Price":"288","MonthlySales":"16","Style":"有货","Pic":"http://www.zousitanghulu.com/Pic/1.jpg"},{"ID":"2","Name":"重辣黄焖鸡米饭","Price":"128","MonthlySales":"20","Style":"有货","Pic":"http://www.zousitanghulu.com/Pic/1.jpg"}]
     */

    private String result;
    private String msg;
    private int total;
    /**
     * ID : 1
     * Name : 微辣黄焖鸡米饭
     * Price : 288
     * MonthlySales : 16
     * Style : 有货
     * Pic : http://www.zousitanghulu.com/Pic/1.jpg
     */

    private List<ShopDetailBean> rows;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
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
    public List<ShopDetailBean> getRows() {
        return rows;
    }

    public void setRows(List<ShopDetailBean> rows) {
        this.rows = rows;
    }

    public static class ShopDetailBean {
        private String ID;
        private String Name;
        private String Price;
        private String MonthlySales;
        private String Style;
        private String Pic;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

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

        public String getPic() {
            return Pic;
        }

        public void setPic(String pic) {
            Pic = pic;
        }
    }
}
