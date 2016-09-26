package com.rhg.qf.mvp.presenter;

import com.rhg.qf.mvp.model.ModifyOrderModel;
import com.rhg.qf.mvp.view.BaseView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * desc:
 * author：remember
 * time：2016/7/10 23:29
 * email：1013773046@qq.com
 */
public class ModifyOrderPresenter {
    BaseView modifyUserOrderView;
    ModifyOrderModel modifyOrderModel;

    public ModifyOrderPresenter(BaseView modifyUserOrderView) {
        this.modifyUserOrderView = modifyUserOrderView;
        modifyOrderModel = new ModifyOrderModel();
    }

    public void modifyUserOrDeliverOrderState(List<String> orderId, String style) {
        modifyOrderModel.modifyOrder(orderId, style).observeOn(AndroidSchedulers.mainThread())
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
