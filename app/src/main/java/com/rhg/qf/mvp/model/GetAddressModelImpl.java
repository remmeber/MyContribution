package com.rhg.qf.mvp.model;

import android.util.Log;

import com.rhg.qf.bean.AddressUrlBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * 作者：rememberon 2016/6/29
 * 邮箱：1013773046@qq.com
 */
public class GetAddressModelImpl implements GetAddressModel {

    @Override
    public Observable<List<AddressUrlBean.AddressBean>> getAddress(String Table, String userId) {
        return QFoodApiMamager.getInstant().getQFoodApiService().getAddress(Table, userId)
                .flatMap(new Func1<AddressUrlBean, Observable<List<AddressUrlBean.AddressBean>>>() {
                    @Override
                    public Observable<List<AddressUrlBean.AddressBean>> call(final AddressUrlBean addressUrlBean) {
                        return Observable.create(new Observable.OnSubscribe<List<AddressUrlBean.AddressBean>>() {
                            @Override
                            public void call(Subscriber<? super List<AddressUrlBean.AddressBean>> subscriber) {
                                subscriber.onNext(addressUrlBean.getRows());
                            }
                        });
                    }
                });
    }
}
