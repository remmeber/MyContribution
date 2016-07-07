package com.rhg.qf.mvp.model;


import com.rhg.qf.bean.HotFoodSearchUrlBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/*
 *desc
 *author rhg
 *time 2016/7/7 20:31
 *email 1013773046@qq.com
 */
public class HotFoodSearchModel {
    public Observable<List<HotFoodSearchUrlBean.HotFoodSearchBean>> getSearchHotFood(String searchHotFood,
                                                                                     final String searchContent,
                                                                                     int order) {
        return QFoodApiMamager.getInstant().getQFoodApiService().getHotGoodsForSearch(searchHotFood, searchContent, String.valueOf(order))
                .flatMap(new Func1<HotFoodSearchUrlBean, Observable<List<HotFoodSearchUrlBean.HotFoodSearchBean>>>() {
                    @Override
                    public Observable<List<HotFoodSearchUrlBean.HotFoodSearchBean>> call(final HotFoodSearchUrlBean hotFoodSearchUrlBean) {
                        return Observable.create(new Observable.OnSubscribe<List<HotFoodSearchUrlBean.HotFoodSearchBean>>() {
                            @Override
                            public void call(Subscriber<? super List<HotFoodSearchUrlBean.HotFoodSearchBean>> subscriber) {
                                subscriber.onNext(hotFoodSearchUrlBean.getRows());
                            }
                        });
                    }
                });
    }
}
