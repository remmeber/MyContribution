package com.example.rhg.outsourcing.bean;

import com.example.rhg.outsourcing.apapter.HomeRecycleAdapter;

import java.util.List;

/**
 *desc:主页店铺推荐列表模型
 *author：remember
 *time：2016/5/28 16:38
 *email：1013773046@qq.com
 */
public class RecommendListTypeModel {
    List<RecommendListBean> homeSellerModels;
    private HomeRecycleAdapter homeRecycleAdapter;
    private HomeRecycleAdapter.OnListItemClick onListItemClick;

    public List<RecommendListBean> getHomeSellerModels() {
        return homeSellerModels;
    }

    public void setHomeSellerModels(List<RecommendListBean> homeSellerModels) {
        this.homeSellerModels = homeSellerModels;
    }

    public HomeRecycleAdapter getHomeRecycleAdapter() {
        return homeRecycleAdapter;
    }

    public void setHomeRecycleAdapter(HomeRecycleAdapter homeRecycleAdapter) {
        this.homeRecycleAdapter = homeRecycleAdapter;
    }

    public HomeRecycleAdapter.OnListItemClick getOnListItemClick() {
        return onListItemClick;
    }

    public void setOnListItemClick(HomeRecycleAdapter.OnListItemClick onListItemClick) {
        this.onListItemClick = onListItemClick;
    }
}
