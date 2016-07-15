package com.rhg.qf.bean;

import java.util.List;

/**
 * desc:轮播图片的网络访问数据模型，网络请求后，需要将该类转换为{@link BannerTypeBean}
 * author：remember
 * time：2016/5/28 16:23
 * email：1013773046@qq.com
 */
public class BannerTypeUrlBean {


    /**
     * result : 0
     * msg : 请求成功
     */

    private int result;

    private String msg;

    String total;

    List<BannerEntity> rows;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<BannerEntity> getRows() {
        return rows;
    }

    public void setRows(List<BannerEntity> rows) {
        this.rows = rows;
    }

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

    public static class BannerEntity {

        String ID;
        String Title;
        String Src;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getSrc() {
            return Src;
        }

        public void setSrc(String src) {
            Src = src;
        }

        @Override
        public String toString() {
            return "ID: " + ID + " Title: " + Title + " Src: " + Src;
        }
    }
}
