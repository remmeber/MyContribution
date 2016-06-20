package com.rhg.outsourcing.mvp.model;

import com.rhg.outsourcing.bean.BannerTypeUrlBean;
import com.rhg.outsourcing.bean.FavorableFoodUrlBean;
import com.rhg.outsourcing.bean.HomeBean;
import com.rhg.outsourcing.bean.RecommendListUrlBean;
import com.rhg.outsourcing.bean.TextTypeBean;
import com.rhg.outsourcing.mvp.api.QFoodApiMamager;
import com.rhg.outsourcing.mvp.api.QFoodApiService;

import rx.Observable;
import rx.functions.Func4;

/**
 * desc:mvp测试实现
 * author：remember
 * time：2016/5/28 17:00
 * email：1013773046@qq.com
 */
public class HomeModelImpl implements HomeModel {

    @Override
    public Observable<HomeBean> getHomeData() {
        QFoodApiService qFoodApiService = QFoodApiMamager.getInstant().getQFoodApiService();
        return Observable.zip(qFoodApiService.getBannerUrl(),
                qFoodApiService.getFavorableFood(),
                qFoodApiService.getRecommendList(),
                qFoodApiService.getMessage(),
                new Func4<BannerTypeUrlBean, FavorableFoodUrlBean,
                        RecommendListUrlBean,TextTypeBean, HomeBean>() {
                    @Override
                    public HomeBean call(BannerTypeUrlBean bannerTypeUrlBean,
                                         FavorableFoodUrlBean favorableFoodUrlBean,
                                         RecommendListUrlBean recommendListUrlBean,
                                         TextTypeBean textTypeBean
                    ) {
                        HomeBean _homeBean = new HomeBean();
                        _homeBean.setBannerEntityList(bannerTypeUrlBean.getRows());
                        _homeBean.setFavorableFoodEntityList(favorableFoodUrlBean.getRows());
                        _homeBean.setRecommendShopBeanEntityList(recommendListUrlBean.getRows());
                        _homeBean.setTextTypeBean(textTypeBean);
                        return _homeBean;
                    }
                });
    }
}