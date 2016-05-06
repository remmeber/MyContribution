package com.example.rhg.outsourcing.model;

import com.example.rhg.outsourcing.apapter.RecommendAdapter;

import java.util.List;

/**
 * Created by remember on 2016/5/3.
 */
public class RecommendListTypeModel {
    List<SellerModel> sellerModels;
    private RecommendAdapter recommendAdapter;
    private RecommendAdapter.OnListItemClick onListItemClick;

    public List<SellerModel> getSellerModels() {
        return sellerModels;
    }

    public RecommendAdapter getRecommendAdapter() {
        return recommendAdapter;
    }

    public RecommendAdapter.OnListItemClick getOnListItemClick() {
        return onListItemClick;
    }

    public RecommendListTypeModel(List<SellerModel> sellerModels, RecommendAdapter recommendAdapter, RecommendAdapter.OnListItemClick onListItemClick) {
        this.sellerModels = sellerModels;
        this.recommendAdapter = recommendAdapter;
        this.onListItemClick = onListItemClick;
    }
}
