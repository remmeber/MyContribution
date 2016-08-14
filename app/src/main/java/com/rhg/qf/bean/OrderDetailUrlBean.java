package com.rhg.qf.bean;

import java.util.List;

/*
 *desc 
 *author rhg
 *time 2016/7/10 17:20
 *email 1013773046@qq.com
 */
public class OrderDetailUrlBean {
    /**
     * result : 0
     * msg : 请求成功
     * total : 1
     * rows : [{"Receiver":"R1","Address":"杭州下沙","Fee":"12","Foods":[{"FName":"微辣黄焖鸡米饭","Price":"288","Num":"2"},{"FName":"重辣黄焖鸡米饭","Price":"128","Num":"1"},{"FName":"不辣绿焖鸡米饭","Price":"20","Num":"2"}]}]
     */

    private int result;
    private String msg;
    private int total;
    /**
     * Receiver : R1
     * Address : 杭州下沙
     * Fee : 12
     * Foods : [{"FName":"微辣黄焖鸡米饭","Price":"288","Num":"2"},{"FName":"重辣黄焖鸡米饭","Price":"128","Num":"1"},{"FName":"不辣绿焖鸡米饭","Price":"20","Num":"2"}]
     */

    private List<OrderDetailBean> rows;

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

    public List<OrderDetailBean> getRows() {
        return rows;
    }

    public void setRows(List<OrderDetailBean> rows) {
        this.rows = rows;
    }

    public static class OrderDetailBean {
        private String Receiver;
        private String Phone;
        private String Address;
        private String Fee;
        /**
         * FName : 微辣黄焖鸡米饭
         * Price : 288
         * Num : 2
         */

        private List<FoodsBean> Foods;

        public String getReceiver() {
            return Receiver;
        }

        public void setReceiver(String Receiver) {
            this.Receiver = Receiver;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            Phone = phone;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getFee() {
            return Fee;
        }

        public void setFee(String Fee) {
            this.Fee = Fee;
        }

        public List<FoodsBean> getFoods() {
            return Foods;
        }

        public void setFoods(List<FoodsBean> Foods) {
            this.Foods = Foods;
        }

        public static class FoodsBean {
            private String FName;
            private String Price;
            private String Num;

            public String getFName() {
                return FName;
            }

            public void setFName(String FName) {
                this.FName = FName;
            }

            public String getPrice() {
                return Price;
            }

            public void setPrice(String Price) {
                this.Price = Price;
            }

            public String getNum() {
                return Num;
            }

            public void setNum(String Num) {
                this.Num = Num;
            }

            @Override
            public String toString() {
                return "FoodsBean{" +
                        "FName='" + FName + '\'' +
                        ", Price='" + Price + '\'' +
                        ", Num='" + Num + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "OrderDetailBean{" +
                    "Receiver='" + Receiver + '\'' +
                    ", Phone='" + Phone + '\'' +
                    ", Address='" + Address + '\'' +
                    ", Fee='" + Fee + '\'' +
                    ", Foods=" + Foods +
                    '}';
        }
    }



 /*   private String ID;
    private String Client;
    private String Receiver;
    private String Address;
    private String Phone;
    private String Price;
    private String Style;
    private String Otime;
    *//**
     * FName : 微辣黄焖鸡米饭
     * Price : 288
     * Pic : http://www.zousitanghulu.com/Pic/1.jpg
     *//*

    private List<Foods> foods;

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

    public List<Foods> getFoods() {
        return foods;
    }

    public void setFoods(List<Foods> foods) {
        this.foods = foods;
    }

    public static class Foods {
        private String FName;
        private String Price;
        private String Pic;

        public String getFName() {
            return FName;
        }

        public void setFName(String FName) {
            this.FName = FName;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
        }
    }*/
}
