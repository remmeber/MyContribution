package com.rhg.qf.mvp.model;

import com.rhg.qf.bean.BaseBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * desc:跑腿员信息完善Model
 * author：remember
 * time：2016/7/15 0:26
 * email：1013773046@qq.com
 */
public class PerfectDeliverInfoModel {
    public Observable<String> perfectInfo(String name, String person_id, String phone,
                                          String area) {
        String user_id = "19216801";
        String pwd = "123";
        return QFoodApiMamager.getInstant().getQFoodApiService().perfectDeliverInfo(name, person_id, phone,
                pwd, area, user_id).flatMap(new Func1<BaseBean, Observable<String>>() {
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
