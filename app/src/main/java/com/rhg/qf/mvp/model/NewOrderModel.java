package com.rhg.qf.mvp.model;

import com.google.gson.Gson;
import com.rhg.qf.bean.BaseBean;
import com.rhg.qf.bean.NewOrderBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * desc:添加新订单Model
 * author：remember
 * time：2016/7/16 0:13
 * email：1013773046@qq.com
 */
public class NewOrderModel {
    public Observable<String> createNewOrder(NewOrderBean newOrderBean) {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (NewOrderBean.FoodBean foodBean : newOrderBean.getFood()) {
            Map<String, String> foodMap = new HashMap<>();
            foodMap.put("ID", foodBean.getID());
            foodMap.put("Num", foodBean.getNum());
            mapList.add(foodMap);
        }
        String food = new Gson().toJson(mapList);
        return QFoodApiMamager.getInstant().getQFoodApiService().createOrder(newOrderBean.getAddress(),
                newOrderBean.getClient(), newOrderBean.getReceiver(), newOrderBean.getPhone(),
                newOrderBean.getPrice(), food)
                .flatMap(new Func1<BaseBean, Observable<String>>() {
                    @Override
                    public Observable<String> call(final BaseBean baseBean) {
                        return Observable.create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                if (baseBean.getResult() == 0)
                                    subscriber.onNext(baseBean.getMsg());
                                else subscriber.onNext("error");
                            }
                        });
                    }
                });
    }
}
