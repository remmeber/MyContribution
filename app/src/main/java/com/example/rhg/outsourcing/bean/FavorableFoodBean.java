package com.example.rhg.outsourcing.bean;

/**
 *desc:主页推荐食物网络请求数据模型
 *author：remember
 *time：2016/5/28 16:12
 *email：1013773046@qq.com
 */
public class FavorableFoodBean {
    private String ID;
    private String Title;
    private String imageUrl;
    private int headercolor;


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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getHeadercolor() {
        return headercolor;
    }

    public void setHeadercolor(int headercolor) {
        this.headercolor = headercolor;
    }

    @Override
    public String toString() {
        return "FavorableFoodBean{ID=" + ID +
                "imageUrl=" + imageUrl +
                ", Title='" + Title + '\'' +
                '}';
    }
}
