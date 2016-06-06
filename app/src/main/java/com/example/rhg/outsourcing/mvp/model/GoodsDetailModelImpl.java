package com.example.rhg.outsourcing.mvp.model;

import android.util.Log;

import com.example.rhg.outsourcing.bean.GoodsDetailUrlBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.mvp.api.QFoodApiMamager;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * desc:Mvp中获取数据详情的实现类
 * author：remember
 * time：2016/5/28 16:56
 * email：1013773046@qq.com
 */
public class GoodsDetailModelImpl implements GoodsDetailModel {
    @Override
    public Observable<GoodsDetailUrlBean.GoodsDetailBean> getGoodsDetail(String foodmessage, String foodId) {
        return QFoodApiMamager.getInstant()
                .getQFoodApiService()
                .getGoodsDetail(foodmessage, Integer.valueOf(foodId))
                .flatMap(new Func1<GoodsDetailUrlBean, Observable<GoodsDetailUrlBean.GoodsDetailBean>>() {
                    @Override
                    public Observable<GoodsDetailUrlBean.GoodsDetailBean> call(
                            final GoodsDetailUrlBean goodsDetailUrlBean) {
                        return Observable.create(new Observable.OnSubscribe<GoodsDetailUrlBean.GoodsDetailBean>() {
                            @Override
                            public void call(Subscriber<? super GoodsDetailUrlBean.GoodsDetailBean> subscriber) {
                                subscriber.onNext(goodsDetailUrlBean.getRows().get(0));
                            }
                        });
                    }
                });
    }
}
