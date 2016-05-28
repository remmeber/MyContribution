package com.example.rhg.outsourcing.mvp.model;

import android.util.Log;

import com.example.rhg.outsourcing.bean.BannerTypeBean;
import com.example.rhg.outsourcing.bean.BannerTypeUrlBean;
import com.example.rhg.outsourcing.mvp.api.QFoodApiMamager;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 *desc:mvp测试实现
 *author：remember
 *time：2016/5/28 17:00
 *email：1013773046@qq.com
 */
public class TestModel implements BaseModel {
    @Override
    public Observable<BannerTypeBean> getData() {
        return QFoodApiMamager.getInstant().getQFoodApiService().getBannerUrl()
                .flatMap(new Func1<BannerTypeUrlBean, Observable<BannerTypeBean>>() {// TODO: 类型转换
                    @Override
                    public Observable<BannerTypeBean> call(final BannerTypeUrlBean bannerTypeUrlBean) {
                        Log.i("RHG", "" + bannerTypeUrlBean.toString());
                        return Observable.create(new Observable.OnSubscribe<BannerTypeBean>() {
                            @Override
                            public void call(Subscriber<? super BannerTypeBean> subscriber) {
                                BannerTypeBean imageModel = new BannerTypeBean();
                                List<String> imageUrls = new ArrayList<String>();
                                int _count = Integer.valueOf(bannerTypeUrlBean.getTotal());
                                for (int i = 0; i < _count; i++) {
                                    String _imageurl = bannerTypeUrlBean.getRows().get(i).getSrc();
                                    imageUrls.add(_imageurl);
                                    Log.i("RHG", _imageurl);
                                }
                                imageModel.setImageUrls(imageUrls);
                                subscriber.onNext(imageModel);
                            }
                        });
                    }
                });
    }
    /*@Override
    public Observable<Object> GetData() {
        return Observable.just("1").flatMap(new Func1<String, Observable<Object>>() {
            @Override
            public Observable<Object> call(String s) {
                if("1".equals(s))
                    return Observable.create(new Observable.OnSubscribe<Object>() {
                        @Override
                        public void call(final Subscriber<? super Object> subscriber) {
                            try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    subscriber.onNext("success");
                            *//*new Thread(){
                                @Override
                                public void run() {
                                    // TODO: 网络请求

                                }
                            }.start();*//*
                        }
                    });
                return null;
            }
        });
    }*/
}
