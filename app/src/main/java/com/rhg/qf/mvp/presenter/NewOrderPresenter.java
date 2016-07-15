package com.rhg.qf.mvp.presenter;

import com.rhg.qf.bean.NewOrderBean;
import com.rhg.qf.mvp.model.NewOrderModel;
import com.rhg.qf.mvp.view.BaseView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * desc:添加新订单Presenter
 * author：remember
 * time：2016/7/16 0:15
 * email：1013773046@qq.com
 */
public class NewOrderPresenter {
    BaseView createNewOrderView;
    NewOrderModel createNewOrderModel;

    public NewOrderPresenter(BaseView createNewOrderView) {
        this.createNewOrderView = createNewOrderView;
        createNewOrderModel = new NewOrderModel();
    }

    public void createNewOrder(NewOrderBean newOrderBean) {
        createNewOrderModel.createNewOrder(newOrderBean).observeOn(AndroidSchedulers.mainThread())
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
                        createNewOrderView.showData(s);
                    }
                });
    }
}
