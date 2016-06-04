package com.example.rhg.outsourcing.mvp.presenter;

import com.example.rhg.outsourcing.bean.ShopDetailUriBean;
import com.example.rhg.outsourcing.mvp.model.ShopDetailModel;
import com.example.rhg.outsourcing.mvp.model.ShopDetailModelImpl;
import com.example.rhg.outsourcing.mvp.view.BaseView;

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
public class ShopDetailPresenterImpl implements ShopDetailPresenter {
    BaseView testView;
    ShopDetailModel shopDetailModel;

    public ShopDetailPresenterImpl(BaseView baseView) {
        testView = baseView;
        shopDetailModel = new ShopDetailModelImpl();
    }

    @Override
    public void getShopDetail(String table, String merchantId) {
        shopDetailModel.getShopDetail(table,merchantId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<ShopDetailUriBean.ShopDetailBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ShopDetailUriBean.ShopDetailBean> shopDetailBeen) {
                        testView.showData(shopDetailBeen);
                    }
                });
    }
}
