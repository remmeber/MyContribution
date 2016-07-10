package com.rhg.qf.mvp.model;

import com.rhg.qf.bean.ShopDetailUrlBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * desc:mvp测试实现
 * author：remember
 * time：2016/5/28 17:00
 * email：1013773046@qq.com
 */
public class ShopDetailModel {

    public Observable<List<ShopDetailUrlBean.ShopDetailBean>> getShopDetail(String table, String merchantId) {
        return QFoodApiMamager.getInstant().getQFoodApiService().
                getMerchantFoods(table, Integer.valueOf(merchantId))
                .flatMap(new Func1<ShopDetailUrlBean, Observable<List<ShopDetailUrlBean.ShopDetailBean>>>() {

                    @Override
                    public Observable<List<ShopDetailUrlBean.ShopDetailBean>>
                    call(final ShopDetailUrlBean shopDetailUrlBean) {
                        return Observable.create(new Observable.OnSubscribe<List<ShopDetailUrlBean.ShopDetailBean>>() {
                            @Override
                            public void call(Subscriber<? super List<ShopDetailUrlBean.ShopDetailBean>> subscriber) {
                                subscriber.onNext(shopDetailUrlBean.getRows());
                            }
                        });
                    }
                });
    }
}

