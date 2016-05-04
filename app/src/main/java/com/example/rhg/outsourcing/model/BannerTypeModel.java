package com.example.rhg.outsourcing.model;

import java.util.List;

/**
 * Created by remember on 2016/5/3.
 * 图片轮播数据模型
 */
public class BannerTypeModel {
    private List<String> imageUrls;

    public BannerTypeModel(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

}
