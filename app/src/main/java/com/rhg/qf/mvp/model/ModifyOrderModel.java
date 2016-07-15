package com.rhg.qf.mvp.model;

import android.util.Log;

import com.rhg.qf.bean.BaseBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;
import com.rhg.qf.mvp.api.QFoodApiService;

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
    public Observable<String> modifyOrder(String orderId, String styleOrTable) {
        QFoodApiService _service = QFoodApiMamager.getInstant().getQFoodApiService();
        if ("1".equals(styleOrTable) || "0".equals(styleOrTable))
            return _service.modifyOrderState(orderId, styleOrTable)
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
        else return _service.modifyDeliverOrderState(styleOrTable, orderId)
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
