package com.example.rhg.outsourcing.mvp.model;

import android.util.Log;

import com.example.rhg.outsourcing.bean.BannerTypeModel;
import com.example.rhg.outsourcing.bean.TestBean;
import com.example.rhg.outsourcing.mvp.api.TestApiMamager;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by remember on 2016/4/28.
 */
public class TestModel implements BaseModel {
    @Override
    public Observable<BannerTypeModel> getData() {
        return TestApiMamager.getInstant().getTestApiService().getResult()
                .flatMap(new Func1<TestBean, Observable<BannerTypeModel>>() {// TODO: 类型转换
                    @Override
                    public Observable<BannerTypeModel> call(final TestBean testBean) {
                        Log.i("RHG", "" + testBean.toString());
                        return Observable.create(new Observable.OnSubscribe<BannerTypeModel>() {
                            @Override
                            public void call(Subscriber<? super BannerTypeModel> subscriber) {
                                BannerTypeModel imageModel = new BannerTypeModel();
                                List<String> imageUrls = new ArrayList<String>();
                                int _count = Integer.valueOf(testBean.getTotal());
                                for (int i = 0; i < _count; i++) {
                                    String _imageurl = testBean.getRows().get(i).getSrc();
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
