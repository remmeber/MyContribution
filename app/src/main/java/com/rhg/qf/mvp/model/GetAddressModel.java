package com.rhg.qf.mvp.model;

import android.util.Log;

import com.rhg.qf.bean.AddressUrlBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;
import com.rhg.qf.utils.AccountUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/*
 *desc
 *author rhg
 *time 2016/7/7 20:13
 *email 1013773046@qq.com
 */
public class GetAddressModel {

    public Observable<List<AddressUrlBean.AddressBean>> getAddress(String Table) {
        String userId = AccountUtil.getInstance().getUserID();
        return QFoodApiMamager.getInstant().getQFoodApiService().getAddress(Table, userId)
                .flatMap(new Func1<AddressUrlBean, Observable<List<AddressUrlBean.AddressBean>>>() {
                    @Override
                    public Observable<List<AddressUrlBean.AddressBean>> call(final AddressUrlBean addressUrlBean) {
                        return Observable.create(new Observable.OnSubscribe<List<AddressUrlBean.AddressBean>>() {
                            @Override
                            public void call(Subscriber<? super List<AddressUrlBean.AddressBean>> subscriber) {
                                Log.i("RHG", "RESULT IS :" + addressUrlBean.getMsg());
                                subscriber.onNext(addressUrlBean.getRows());
                            }
                        });
                    }
                });
    }
}
