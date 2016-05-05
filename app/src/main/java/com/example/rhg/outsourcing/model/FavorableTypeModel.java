package com.example.rhg.outsourcing.model;

import java.util.List;

/**
 * Created by remember on 2016/5/3.
 */
public class FavorableTypeModel {
    List<ImageModel> imageModels;

    public FavorableTypeModel(List<ImageModel> imageModels) {
        this.imageModels = imageModels;
    }

    public List<ImageModel> getImageModels() {
        return imageModels;
    }

}
