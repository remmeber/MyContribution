package com.rhg.qf.mvp.model;

import com.rhg.qf.bean.BaseBean;
import com.rhg.qf.bean.NewOrderBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * desc:添加新订单Model
 * author：remember
 * time：2016/7/16 0:13
 * email：1013773046@qq.com
 */
public class NewOrderModel {
    public Observable<String> createNewOrder(NewOrderBean newOrderBean) {
        return QFoodApiMamager.getInstant().getQFoodApiService().createOrder(newOrderBean)
                .flatMap(new Func1<BaseBean, Observable<String>>() {
                    @Override
                    public Observable<String> call(final BaseBean baseBean) {
                        return Observable.create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                if (baseBean.getResult() == 0)
                                    subscriber.onNext(baseBean.getMsg());
                            }
                        });
                    }
                });
    }
}
