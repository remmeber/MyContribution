package com.rhg.qf.mvp.presenter;

import com.rhg.qf.bean.DeliverOrderUrlBean;
import com.rhg.qf.mvp.model.GetDeliverOrderModel;
import com.rhg.qf.mvp.view.BaseView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
 *desc 获取跑腿员订单presenter
 *author rhg
 *time 2016/7/9 12:29
 *email 1013773046@qq.com
 */
public class GetDeliverOrderPresenter {

    BaseView getDeliverOrderView;
    GetDeliverOrderModel getDeliverOrderModel;

    public GetDeliverOrderPresenter(BaseView getDeliverOrderView) {
        this.getDeliverOrderView = getDeliverOrderView;
        getDeliverOrderModel = new GetDeliverOrderModel();
    }

    public void getDeliverOrder(final String deliverOrder, final String deliverId) {
        getDeliverOrderModel.getDeliverOrder(deliverOrder, deliverId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<DeliverOrderUrlBean.DeliverOrderBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<DeliverOrderUrlBean.DeliverOrderBean> deliverOrderBeen) {
                        getDeliverOrderView.showData(deliverOrderBeen);
                    }
                });
    }
}
