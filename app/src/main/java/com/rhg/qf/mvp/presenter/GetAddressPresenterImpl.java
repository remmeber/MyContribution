package com.rhg.qf.mvp.presenter;

import com.rhg.qf.bean.AddressUrlBean;
import com.rhg.qf.mvp.model.GetAddressModel;
import com.rhg.qf.mvp.model.GetAddressModelImpl;
import com.rhg.qf.mvp.view.BaseView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * desc:
 * author：remember
 * time：2016/6/29 16:01
 * email：1013773046@qq.com
 */
public class GetAddressPresenterImpl implements GetAddressPresenter {
    BaseView baseView;
    GetAddressModel getAddressModel;

    public GetAddressPresenterImpl(BaseView baseView) {
        this.baseView = baseView;
        getAddressModel = new GetAddressModelImpl();
    }

    @Override
    public void getAddress(String Table, String userId) {
        getAddressModel.getAddress(Table, userId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<AddressUrlBean.AddressBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<AddressUrlBean.AddressBean> addressBeen) {
                        baseView.showData(addressBeen);
                    }
                });
    }
}
