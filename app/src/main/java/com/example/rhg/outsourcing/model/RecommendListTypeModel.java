package com.example.rhg.outsourcing.model;

import com.example.rhg.outsourcing.apapter.RecycleSellerAdapter;

import java.util.List;

/**
 * Created by remember on 2016/5/3.
 */
public class RecommendListTypeModel {
    List<BaseSellerModel> baseSellerModels;
    private RecycleSellerAdapter recycleSellerAdapter;
    private RecycleSellerAdapter.OnListItemClick onListItemClick;

    public List<BaseSellerModel> getBaseSellerModels() {
        return baseSellerModels;
    }

    public RecycleSellerAdapter getRecycleSellerAdapter() {
        return recycleSellerAdapter;
    }

    public RecycleSellerAdapter.OnListItemClick getOnListItemClick() {
        return onListItemClick;
    }

    public RecommendListTypeModel(List<BaseSellerModel> baseSellerModels, RecycleSellerAdapter recycleSellerAdapter, RecycleSellerAdapter.OnListItemClick onListItemClick) {
        this.baseSellerModels = baseSellerModels;
        this.recycleSellerAdapter = recycleSellerAdapter;
        this.onListItemClick = onListItemClick;
    }
}
