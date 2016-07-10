package com.rhg.qf.mvp.model;

import com.rhg.qf.bean.BaseBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * desc:
 * author：remember
 * time：2016/7/10 23:25
 * email：1013773046@qq.com
 */
public class ModifyUserOrderModel {
    public Observable<String> modifyUserOrder(String orderId, String style) {
        return QFoodApiMamager.getInstant().getQFoodApiService().modifyUserOrderState(orderId, style)
                .flatMap(new Func1<BaseBean, Observable<String>>() {
                    @Override
                    public Observable<String> call(final BaseBean baseBean) {
                        return Observable.create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                if (baseBean.getResult() == 0)
                                    subscriber.onNext("success");
                            }
                        });
                    }
                });
    }
}
