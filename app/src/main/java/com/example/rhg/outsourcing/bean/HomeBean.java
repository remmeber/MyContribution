package com.example.rhg.outsourcing.bean;

/**
 *desc:整个主页的数据模型，网络请求后，需要将所有的数据都整合到该类中
 *author：remember
 *time：2016/5/28 16:33
 *email：1013773046@qq.com
 */
public class HomeBean {
    BannerTypeBean bannerTypeBean;
    TextTypeBean textTypeBean;
    FavorableTypeModel favorableTypeModel;
    RecommendTextTypeModel recommendTextTypeModel;
    RecommendListTypeModel recommendListTypeModel;

    public BannerTypeBean getBannerTypeBean() {
        return bannerTypeBean;
    }

    public void setBannerTypeBean(BannerTypeBean bannerTypeBean) {
        this.bannerTypeBean = bannerTypeBean;
    }

    public TextTypeBean getTextTypeBean() {
        return textTypeBean;
    }

    public void setTextTypeBean(TextTypeBean textTypeBean) {
        this.textTypeBean = textTypeBean;
    }

    public FavorableTypeModel getFavorableTypeModel() {
        return favorableTypeModel;
    }

    public void setFavorableTypeModel(FavorableTypeModel favorableTypeModel) {
        this.favorableTypeModel = favorableTypeModel;
    }

    public RecommendTextTypeModel getRecommendTextTypeModel() {
        return recommendTextTypeModel;
    }

    public void setRecommendTextTypeModel(RecommendTextTypeModel recommendTextTypeModel) {
        this.recommendTextTypeModel = recommendTextTypeModel;
    }

    public RecommendListTypeModel getRecommendListTypeModel() {
        return recommendListTypeModel;
    }

    public void setRecommendListTypeModel(RecommendListTypeModel recommendListTypeModel) {
        this.recommendListTypeModel = recommendListTypeModel;
    }
}
