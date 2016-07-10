package com.rhg.qf.mvp.presenter;

import com.rhg.qf.mvp.model.ModifyUserOrderModel;
import com.rhg.qf.mvp.view.BaseView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * desc:
 * author：remember
 * time：2016/7/10 23:29
 * email：1013773046@qq.com
 */
public class ModifyUserOrderPresenter {
    BaseView modifyUserOrderView;
    ModifyUserOrderModel modifyUserOrderModel;

    public ModifyUserOrderPresenter(BaseView modifyUserOrderView) {
        this.modifyUserOrderView = modifyUserOrderView;
        modifyUserOrderModel = new ModifyUserOrderModel();
    }

    public void modifyUserOrderState(String orderId, String style) {
        modifyUserOrderModel.modifyUserOrder(orderId, style).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        modifyUserOrderView.showData(s);
                    }
                });
    }
}
