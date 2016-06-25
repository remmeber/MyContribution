package com.rhg.qf.mvp.model;

import com.rhg.qf.bean.MerchantUrlBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;
import com.rhg.qf.utils.AccountUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * desc:mvp测试实现
 * author：remember
 * time：2016/5/28 17:00
 * email：1013773046@qq.com
 */
public class MerchantsModelImpl implements MerchantsModel {

    @Override
    public Observable<List<MerchantUrlBean.MerchantBean>> getMerchants(String table, int page) {
        return QFoodApiMamager.getInstant().getQFoodApiService().getAllShop(table, page,
                AccountUtil.getInstance().getLongitude(),
                AccountUtil.getInstance().getLatitude())
                .flatMap(new Func1<MerchantUrlBean, Observable<List<MerchantUrlBean.MerchantBean>>>() {
                    @Override
                    public Observable<List<MerchantUrlBean.MerchantBean>> call(final MerchantUrlBean merchantUrlBean) {
                        return Observable.create(new Observable.OnSubscribe<List<MerchantUrlBean.MerchantBean>>() {
                            @Override
                            public void call(Subscriber<? super List<MerchantUrlBean.MerchantBean>> subscriber) {
                                subscriber.onNext(merchantUrlBean.getRows());
                            }
                        });
                    }
                });
    }
}