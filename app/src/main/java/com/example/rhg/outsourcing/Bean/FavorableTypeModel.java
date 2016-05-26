package com.example.rhg.outsourcing.bean;

import com.example.rhg.outsourcing.apapter.DPGridViewAdapter;

import java.util.List;

/**
 * Created by remember on 2016/5/3.
 */
public class FavorableTypeModel {
    List<ImageModel> imageModels;
    private DPGridViewAdapter dpGridViewAdapter;

    public FavorableTypeModel(List<ImageModel> imageModels, DPGridViewAdapter dpGridViewAdapter) {
        this.imageModels = imageModels;
        this.dpGridViewAdapter = dpGridViewAdapter;
    }

    public List<ImageModel> getImageModels() {
        return imageModels;
    }

    public DPGridViewAdapter getDpGridViewAdapter() {
        return dpGridViewAdapter;
    }
}
