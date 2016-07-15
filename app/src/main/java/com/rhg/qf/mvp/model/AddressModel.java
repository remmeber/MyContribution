package com.rhg.qf.mvp.model;

import com.rhg.qf.bean.BaseBean;
import com.rhg.qf.constants.AppConstants;
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
    public Observable<String> addOrUpdateAddress(String addressId, String user,
                                                 String phone, String address,
                                                 String detail, String opt) {
        String userName = "19216801";
        String pwd = "123";
        Observable<BaseBean> _address;
        if (addressId == null)/*添加地址*/
            _address = QFoodApiMamager.getInstant().getQFoodApiService().addAddress(userName, pwd,
                    user, phone, address, detail);
        else if (user == null) {/*删除地址*/
            if ("0".equals(opt))
                _address = QFoodApiMamager.getInstant().getQFoodApiService().changeAddress(AppConstants.DELETE_ADDRESS,
                        addressId);
            else
                _address = QFoodApiMamager.getInstant().getQFoodApiService().changeAddress(AppConstants.DEFAULT_ADDRESS,
                        addressId);
        } else/*修改地址*/
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
