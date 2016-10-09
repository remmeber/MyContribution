package com.rhg.qf.mvp.presenter;

import com.rhg.qf.bean.GoodsDetailUrlBean;
import com.rhg.qf.mvp.model.GoodsDetailModel;
import com.rhg.qf.mvp.view.BaseView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * desc:mvp中获取商品详情的presenter实现
 * author：remember
 * time：2016/5/28 17:01
 * email：1013773046@qq.com
 */
public class GoodsDetailPresenter {

    BaseView baseView;
    GoodsDetailModel goodsDetailModel;

    public GoodsDetailPresenter(BaseView baseView) {
        this.baseView = baseView;
        goodsDetailModel = new GoodsDetailModel();
    }

    public void getGoodsInfo(String foodmessage, String foodId) {
        goodsDetailModel.getGoodsDetail(foodmessage, foodId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GoodsDetailUrlBean.GoodsDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GoodsDetailUrlBean.GoodsDetailBean goodsDetailBean) {
                        baseView.showData(goodsDetailBean);
                    }
                });
    }
}
