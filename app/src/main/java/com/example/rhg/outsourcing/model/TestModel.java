package com.example.rhg.outsourcing.model;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by remember on 2016/4/28.
 */
public class TestModel implements BaseModel {

    @Override
    public Observable<Object> GetData() {
        return Observable.just("1").flatMap(new Func1<String, Observable<Object>>() {
            @Override
            public Observable<Object> call(String s) {
                if("1".equals(s))
                    return Observable.create(new Observable.OnSubscribe<Object>() {
                        @Override
                        public void call(final Subscriber<? super Object> subscriber) {
                            new Thread(){
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    subscriber.onNext("success");
                                }
                            }.start();
                        }
                    });
                return null;
            }
        });
    }
}
