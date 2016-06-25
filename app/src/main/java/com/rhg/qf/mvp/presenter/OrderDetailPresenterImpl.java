package com.rhg.qf.mvp.presenter;

import com.rhg.qf.bean.OrderUrlBean;
import com.rhg.qf.mvp.model.OrderDetailModel;
import com.rhg.qf.mvp.model.OrderDetailModelImpl;
import com.rhg.qf.mvp.view.BaseView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * desc:mvp presenter 测试实现类
 * author：remember
 * time：2016/5/28 17:02
 * email：1013773046@qq.com
 */
public class OrderDetailPresenterImpl implements OrderDetailPresenter {
    BaseView testView;
    OrderDetailModel orderDetailModel;

    public OrderDetailPresenterImpl(BaseView baseView) {
        testView = baseView;
        orderDetailModel = new OrderDetailModelImpl();
    }

    @Override
    public void getData(String table, String userId, String style) {
        orderDetailModel.getOrderDetail(table, userId, style).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<OrderUrlBean.OrderBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<OrderUrlBean.OrderBean> orderBeanList) {
                        testView.showData(orderBeanList);
                    }
                });
    }
}