package com.example.rhg.outsourcing.mvp.model;

import com.example.rhg.outsourcing.bean.GoodsDetailBean;
import com.example.rhg.outsourcing.constants.AppConstants;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 *desc:Mvp中获取数据详情的实现类
 *author：remember
 *time：2016/5/28 16:56
 *email：1013773046@qq.com
 */
public class GoodsDetailModelImpl implements GoodsDetailModel {
    @Override
    public Observable<GoodsDetailBean> getGoodsDetail() {
        return Observable.just("1").flatMap(new Func1<String, Observable<GoodsDetailBean>>() {
            @Override
            public Observable<GoodsDetailBean> call(String s) {
                return Observable.create(new Observable.OnSubscribe<GoodsDetailBean>() {
                    @Override
                    public void call(Subscriber<? super GoodsDetailBean> subscriber) {
                        GoodsDetailBean goodsDetailBean = new GoodsDetailBean();
                        goodsDetailBean.setGoodsDescription("很好吃");
                        goodsDetailBean.setGoodSellNum("99");
                        goodsDetailBean.setImageUrls(AppConstants.images);
                        subscriber.onNext(goodsDetailBean);
                    }
                });
            }
        });
    }
}
