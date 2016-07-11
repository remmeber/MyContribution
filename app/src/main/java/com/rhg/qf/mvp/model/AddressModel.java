package com.rhg.qf.mvp.model;

import com.rhg.qf.bean.BaseBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * desc:添加或者修改地址model
 * author：remember
 * time：2016/7/11 23:25
 * email：1013773046@qq.com
 */
public class AddressModel {
    public Observable<String> addOrUpdateAddress(String addressId, String user, String phone, String address) {
        String userName = "19216801";
        String pwd = "123";
        Observable<BaseBean> _address;
        if (addressId == null)
            _address = QFoodApiMamager.getInstant().getQFoodApiService().addAddress(userName, pwd,
                    user, phone, address);
        else if (user == null)
            _address = QFoodApiMamager.getInstant().getQFoodApiService().deleteAddress(addressId);
        else
            _address = QFoodApiMamager.getInstant().getQFoodApiService().updateAddress(userName, addressId,
                    user, phone, address, pwd);
        return _address.flatMap(new Func1<BaseBean, Observable<String>>() {
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
