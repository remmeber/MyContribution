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
public class HotFoodModel {
    public Observable<List<HotFoodUrlBean.HotFoodBean>> getHotFood(String hotFood, int orderType) {
        return QFoodApiMamager.getInstant().getQFoodApiService().getHotGoods(hotFood, String.valueOf(orderType))
                .flatMap(new Func1<HotFoodUrlBean, Observable<List<HotFoodUrlBean.HotFoodBean>>>() {
                    @Override
                    public Observable<List<HotFoodUrlBean.HotFoodBean>> call(final HotFoodUrlBean hotFoodUrlBean) {
                        return Observable.create(
                                new Observable.OnSubscribe<List<HotFoodUrlBean.HotFoodBean>>() {
                                    @Override
                                    public void call(Subscriber<? super List<HotFoodUrlBean.HotFoodBean>>
                                                             subscriber) {
                                        subscriber.onNext(hotFoodUrlBean.getRows());
                                    }
                                });
                    }
                });
    }
}

