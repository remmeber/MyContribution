package com.example.rhg.outsourcing.bean;

import java.util.List;

/**
 * Created by remember on 2016/5/25.
 * 用来测试的数据
 */
public class TestBean {
    /*
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
        }*/
    String total;
    List<ResultEntity> rows;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ResultEntity> getRows() {
        return rows;
    }

    public void setRows(List<ResultEntity> rows) {
        this.rows = rows;
    }

    public static class ResultEntity {

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
