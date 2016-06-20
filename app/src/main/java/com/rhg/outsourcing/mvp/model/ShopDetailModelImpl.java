package com.rhg.outsourcing.mvp.model;

import com.rhg.outsourcing.bean.ShopDetailUriBean;
import com.rhg.outsourcing.mvp.api.QFoodApiMamager;

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
public class ShopDetailModelImpl implements ShopDetailModel {

    @Override
    public Observable<List<ShopDetailUriBean.ShopDetailBean>> getShopDetail(String table, String merchantId) {
        return QFoodApiMamager.getInstant().getQFoodApiService().
                getShopDetail(table, Integer.valueOf(merchantId))
                .flatMap(new Func1<ShopDetailUriBean, Observable<List<ShopDetailUriBean.ShopDetailBean>>>() {

                    @Override
                    public Observable<List<ShopDetailUriBean.ShopDetailBean>>
                    call(final ShopDetailUriBean shopDetailUriBean) {
                        return Observable.create(new Observable.OnSubscribe<List<ShopDetailUriBean.ShopDetailBean>>() {
                            @Override
                            public void call(Subscriber<? super List<ShopDetailUriBean.ShopDetailBean>> subscriber) {
                                subscriber.onNext(shopDetailUriBean.getRows());
                            }
                        });
                    }
                });
    }
}

