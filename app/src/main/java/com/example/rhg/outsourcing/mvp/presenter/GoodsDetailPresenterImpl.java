package com.example.rhg.outsourcing.mvp.presenter;

import com.example.rhg.outsourcing.bean.GoodsDetailBean;
import com.example.rhg.outsourcing.mvp.model.GoodsDetailModel;
import com.example.rhg.outsourcing.mvp.model.GoodsDetailModelImpl;
import com.example.rhg.outsourcing.mvp.view.BaseView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *desc:mvp中获取商品详情的presenter实现
 *author：remember
 *time：2016/5/28 17:01
 *email：1013773046@qq.com
 */
public class GoodsDetailPresenterImpl implements GoodsDetailPresenter {

    BaseView baseView;
    GoodsDetailModel goodsDetailModel;

    public GoodsDetailPresenterImpl(BaseView baseView) {
        this.baseView = baseView;
        goodsDetailModel = new GoodsDetailModelImpl();
    }

    @Override
    public void getGoodsInfo() {
        goodsDetailModel.getGoodsDetail().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GoodsDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GoodsDetailBean goodsDetailBean) {
                        baseView.showData(goodsDetailBean);
                    }
                });
    }
}
