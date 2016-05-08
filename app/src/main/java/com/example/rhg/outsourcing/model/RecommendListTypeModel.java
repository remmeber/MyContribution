package com.example.rhg.outsourcing.model;

import com.example.rhg.outsourcing.apapter.RecycleSellerAdapter;

import java.util.List;

/**
 * Created by remember on 2016/5/3.
 */
public class RecommendListTypeModel {
    List<SellerModel> sellerModels;
    private RecycleSellerAdapter recycleSellerAdapter;
    private RecycleSellerAdapter.OnListItemClick onListItemClick;

    public List<SellerModel> getSellerModels() {
        return sellerModels;
    }

    public RecycleSellerAdapter getRecycleSellerAdapter() {
        return recycleSellerAdapter;
    }

    public RecycleSellerAdapter.OnListItemClick getOnListItemClick() {
        return onListItemClick;
    }

    public RecommendListTypeModel(List<SellerModel> sellerModels, RecycleSellerAdapter recycleSellerAdapter, RecycleSellerAdapter.OnListItemClick onListItemClick) {
        this.sellerModels = sellerModels;
        this.recycleSellerAdapter = recycleSellerAdapter;
        this.onListItemClick = onListItemClick;
    }
}
