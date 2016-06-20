package com.rhg.outsourcing.bean;

import java.util.List;

/**
 *desc:轮播图片的网络访问数据模型，网络请求后，需要将该类转换为{@link BannerTypeBean}
 *author：remember
 *time：2016/5/28 16:23
 *email：1013773046@qq.com
 */
public class BannerTypeUrlBean {
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

    public static class BannerEntity {

        String ID;
        String Title;
        String Src;
        String Jump;

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

        public String getJump() {
            return Jump;
        }

        public void setJump(String jump) {
            Jump = jump;
        }

        @Override
        public String toString() {
            return "ID: " + ID + " Title: " + Title + " Src: " + Src + " Jump: " + Jump;
        }
    }

    @Override
    public String toString() {
        return "total: " + total + " result: " + rows;
    }
}
