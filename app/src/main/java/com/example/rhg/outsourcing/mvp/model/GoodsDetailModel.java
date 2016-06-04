package com.example.rhg.outsourcing.mvp.model;

import com.example.rhg.outsourcing.bean.GoodsDetailUrlBean;

import rx.Observable;

/**
 *desc:mvp中用户获取商品详情接口
 *author：remember
 *time：2016/5/28 16:55
 *email：1013773046@qq.com
 */
public interface GoodsDetailModel {
    Observable<GoodsDetailUrlBean.GoodsDetailBean> getGoodsDetail(String foodmessage, String foodId);
}

