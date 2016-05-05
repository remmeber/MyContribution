package com.example.rhg.outsourcing.model;

import java.util.List;

/**
 * Created by whiskeyfei on 15-7-24.
 */
public class ImageModel {
    private int imageId;
    private int headercolor;
    private String content;


    public int getHeadercolor() {
        return headercolor;
    }

    public void setHeadercolor(int headercolor) {
        this.headercolor = headercolor;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "imageId=" + imageId +
                ", content='" + content + '\'' +
                '}';
    }
}
