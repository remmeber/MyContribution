package com.rhg.qf.mvp.model;

import android.util.Log;

import com.rhg.qf.bean.BaseBean;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.mvp.api.QFoodApiMamager;
import com.rhg.qf.mvp.api.QFoodApiService;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * desc:
 * author：remember
 * time：2016/7/10 23:25
 * email：1013773046@qq.com
 */
public class ModifyOrderModel {
    /**
     * desc:styleOrTable 表示订单类型或者查询的表
     * author：remember
     * time：2016/7/15 22:29
     * email：1013773046@qq.com
     */
    public Observable<String> modifyOrder(List<String> orderId, String styleOrTable) {
        final QFoodApiService _service = QFoodApiMamager.getInstant().getQFoodApiService();
        if (AppConstants.ORDER_WITHDRAW.equals(styleOrTable) || AppConstants.ORDER_FINISH.equals(styleOrTable))
            return _service.modifyOrderState(orderId.get(0), styleOrTable)
                    .flatMap(new Func1<BaseBean, Observable<String>>() {
                        @Override
                        public Observable<String> call(final BaseBean baseBean) {
                            return Observable.create(new Observable.OnSubscribe<String>() {
                                @Override
                                public void call(Subscriber<? super String> subscriber) {
                                    Log.i("RHG", "MODIFY:" + baseBean.getResult());
                                    if (baseBean.getResult() == 0)
                                        subscriber.onNext(baseBean.getMsg());
                                }
                            });
                        }
                    });
        if (AppConstants.ORDER_DELIVERING.equals(styleOrTable))
            return Observable.from(orderId).flatMap(new Func1<String, Observable<BaseBean>>() {
                @Override
                public Observable<BaseBean> call(String s) {
                    return _service.orderDelivering(s);
                }
            }).flatMap(new Func1<BaseBean, Observable<String>>() {
                @Override
                public Observable<String> call(final BaseBean baseBean) {
                    return Observable.create(new Observable.OnSubscribe<String>() {
                        @Override
                        public void call(Subscriber<? super String> subscriber) {
                            subscriber.onNext(baseBean.getMsg());
                        }
                    });
                }
            });
                    /*.flatMap(new Func1<BaseBean, Observable<String>>() {
                        @Override
                        public Observable<String> call(final BaseBean baseBean) {
                            return Observable.create(new Observable.OnSubscribe<String>() {
                                @Override
                                public void call(Subscriber<? super String> subscriber) {
                                    if (baseBean.getResult() == 0)
                                        subscriber.onNext(baseBean.getMsg());
                                    else subscriber.onNext("error");
                                }
                            });
                        }
                    });*/
        else return _service.modifyDeliverOrderState(styleOrTable, orderId.get(0))
                .flatMap(new Func1<BaseBean, Observable<String>>() {
                    @Override
                    public Observable<String> call(final BaseBean baseBean) {
                        return Observable.create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                Log.i("RHG", "MODIFY:" + baseBean.getResult());
                                if (baseBean.getResult() == 0)
                                    subscriber.onNext(baseBean.getMsg());
                            }
                        });
                    }
                });
    }
}
