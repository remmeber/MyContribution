package com.example.rhg.outsourcing.bean;

import com.example.rhg.outsourcing.apapter.HomeRecycleAdapter;

import java.util.List;

/**
 * desc:主页店铺推荐列表模型
 * author：remember
 * time：2016/5/28 16:38
 * email：1013773046@qq.com
 */
public class RecommendListTypeModel {
    List<RecommendListUrlBean.RecommendShopBeanEntity> recommendShopBeanEntity;
    private HomeRecycleAdapter homeRecycleAdapter;
    private HomeRecycleAdapter.OnListItemClick onListItemClick;

    public List<RecommendListUrlBean.RecommendShopBeanEntity> getRecommendShopBeanEntity() {
        return recommendShopBeanEntity;
    }

    public void setRecommendShopBeanEntity(List<RecommendListUrlBean.RecommendShopBeanEntity> recommendShopBeanEntity) {
        this.recommendShopBeanEntity = recommendShopBeanEntity;
        if (this.homeRecycleAdapter != null)
            homeRecycleAdapter.setRecommendListBean(this.recommendShopBeanEntity);
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
