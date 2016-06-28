package com.rhg.qf.bean;

import java.util.List;

/**
 * desc:主页店铺推荐列表模型
 * author：remember
 * time：2016/5/28 16:38
 * email：1013773046@qq.com
 */
public class RecommendListTypeModel {
    List<RecommendListUrlBean.RecommendShopBeanEntity> recommendShopBeanEntity;

    public List<RecommendListUrlBean.RecommendShopBeanEntity> getRecommendShopBeanEntity() {
        return recommendShopBeanEntity;
    }

    public void setRecommendShopBeanEntity(
            List<RecommendListUrlBean.RecommendShopBeanEntity> recommendShopBeanEntity) {
        this.recommendShopBeanEntity = recommendShopBeanEntity;

    }
}
