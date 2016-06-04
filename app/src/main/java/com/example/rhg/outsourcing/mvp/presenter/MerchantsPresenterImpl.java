package com.example.rhg.outsourcing.mvp.presenter;

import com.example.rhg.outsourcing.bean.MerchantUrlBean;
import com.example.rhg.outsourcing.mvp.model.MerchantsModel;
import com.example.rhg.outsourcing.mvp.view.BaseView;
import com.example.rhg.outsourcing.mvp.model.MerchantsModelImpl;

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
public class MerchantsPresenterImpl implements MerchantsPresenter {
    BaseView testView;
    MerchantsModel merchantsModel;

    public MerchantsPresenterImpl(BaseView baseView) {
        testView = baseView;
        merchantsModel = new MerchantsModelImpl();
    }

    @Override
    public void getMerchants(String table, int page) {
        merchantsModel.getMerchants(table,page).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<MerchantUrlBean.MerchantBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<MerchantUrlBean.MerchantBean> merchantBeanList) {
                        testView.showData(merchantBeanList);
                    }
                });
    }
}
