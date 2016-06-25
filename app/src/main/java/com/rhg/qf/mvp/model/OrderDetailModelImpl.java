package com.rhg.qf.mvp.model;

import com.rhg.qf.bean.OrderUrlBean;
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
public class OrderDetailModelImpl implements OrderDetailModel {

    @Override
    public Observable<List<OrderUrlBean.OrderBean>> getOrderDetail(String table, String userId, String style) {
        return QFoodApiMamager.getInstant().getQFoodApiService().getOrderDetail(table, userId, style)
                .flatMap(new Func1<OrderUrlBean, Observable<List<OrderUrlBean.OrderBean>>>() {
                    @Override
                    public Observable<List<OrderUrlBean.OrderBean>> call(final OrderUrlBean orderUrlBean) {
                        return Observable.create(new Observable.OnSubscribe<List<OrderUrlBean.OrderBean>>() {
                            @Override
                            public void call(Subscriber<? super List<OrderUrlBean.OrderBean>> subscriber) {
                                subscriber.onNext(orderUrlBean.getRows());
                            }
                        });
                    }
                });
    }
}
