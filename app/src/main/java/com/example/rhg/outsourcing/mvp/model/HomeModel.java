package com.example.rhg.outsourcing.mvp.model;

import com.example.rhg.outsourcing.bean.HomeBean;

import rx.Observable;

/**
 *desc:mvp模型中的测试接口
 *author：remember
 *time：2016/5/28 16:54
 *email：1013773046@qq.com
 */
public interface HomeModel {
    // TODO: 返回目标数据
//    Observable<BannerTypeBean> getData();
    Observable<HomeBean> getHomeData();
}
