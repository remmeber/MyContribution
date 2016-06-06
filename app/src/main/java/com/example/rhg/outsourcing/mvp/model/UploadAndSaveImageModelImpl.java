package com.example.rhg.outsourcing.mvp.model;

import com.example.rhg.outsourcing.bean.TestBean;
import com.example.rhg.outsourcing.mvp.api.QFoodApiMamager;

import java.io.File;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * 作者：rememberon 2016/5/31
 * 邮箱：1013773046@qq.com
 */
public class UploadAndSaveImageModelImpl implements UploadAndSaveImageModel {


    @Override
    public Observable<String> UploadAndSaveImage(File file, String userName, String pwd) {
        return QFoodApiMamager.getInstant().getQFoodApiService().UploadHeadImage(file, userName, pwd)
                .flatMap(new Func1<TestBean, Observable<String>>() {
                    @Override
                    public Observable<String> call(final TestBean testBean) {
                        return Observable.create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                subscriber.onNext(testBean.getMsg());
                            }
                        });
                    }
                });
    }
}
