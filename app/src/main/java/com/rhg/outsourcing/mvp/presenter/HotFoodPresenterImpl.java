package com.rhg.outsourcing.mvp.presenter;

import com.rhg.outsourcing.bean.HotFoodUrlBean;
import com.rhg.outsourcing.mvp.model.HotFoodModel;
import com.rhg.outsourcing.mvp.model.HotFoodModelImpl;
import com.rhg.outsourcing.mvp.view.BaseView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * desc:mvp中获取热销单品presenter
 * author：remember
 * time：2016/5/28 17:01
 * email：1013773046@qq.com
 */
public class HotFoodPresenterImpl implements HotFoodPresenter {
    BaseView baseView;
    HotFoodModel hotFoodModel;

    public HotFoodPresenterImpl(BaseView baseView) {
        this.baseView = baseView;
        hotFoodModel = new HotFoodModelImpl();
    }

    @Override
    public void getHotFoods(final String hotFood, String orderType) {
        hotFoodModel.getHotFood(hotFood, orderType).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<HotFoodUrlBean.HotGoodsBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<HotFoodUrlBean.HotGoodsBean> hotGoodsBeen) {
                        baseView.showData(hotGoodsBeen);
                    }
                });
    }
}
