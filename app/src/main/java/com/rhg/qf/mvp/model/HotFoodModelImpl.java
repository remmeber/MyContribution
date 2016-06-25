package com.rhg.qf.mvp.model;

import com.rhg.qf.bean.HotFoodUrlBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * desc:mvp中获取热销单品
 * author：remember
 * time：2016/5/28 16:55
 * email：1013773046@qq.com
 */
public class HotFoodModelImpl implements HotFoodModel {
    @Override
    public Observable<List<HotFoodUrlBean.HotGoodsBean>> getHotFood(String hotFood, String orderType) {
        return QFoodApiMamager.getInstant().getQFoodApiService().getHotGoods(hotFood, orderType)
                .flatMap(new Func1<HotFoodUrlBean, Observable<List<HotFoodUrlBean.HotGoodsBean>>>() {
                    @Override
                    public Observable<List<HotFoodUrlBean.HotGoodsBean>> call(final HotFoodUrlBean hotFoodUrlBean) {
                        return Observable.create(
                                new Observable.OnSubscribe<List<HotFoodUrlBean.HotGoodsBean>>() {
                                    @Override
                                    public void call(Subscriber<? super List<HotFoodUrlBean.HotGoodsBean>>
                                                             subscriber) {
                                        subscriber.onNext(hotFoodUrlBean.getRows());
                                    }
                                });
                    }
                });
    }
}

