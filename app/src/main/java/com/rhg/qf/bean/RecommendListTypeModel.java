package com.rhg.qf.bean;

import com.rhg.qf.apapter.HomeRecycleAdapter;
import com.rhg.qf.impl.RcvItemClickListener;

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
    private RcvItemClickListener<RecommendListUrlBean.RecommendShopBeanEntity> onItemClick;

    public List<RecommendListUrlBean.RecommendShopBeanEntity> getRecommendShopBeanEntity() {
        return recommendShopBeanEntity;
    }

    public void setRecommendShopBeanEntity(
            List<RecommendListUrlBean.RecommendShopBeanEntity> recommendShopBeanEntity) {
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

    public RcvItemClickListener<RecommendListUrlBean.RecommendShopBeanEntity> getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(
            RcvItemClickListener<RecommendListUrlBean.RecommendShopBeanEntity> onItemClick) {
        this.onItemClick = onItemClick;
    }
}
