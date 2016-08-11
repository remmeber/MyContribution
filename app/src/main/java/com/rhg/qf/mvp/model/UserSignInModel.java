package com.rhg.qf.mvp.model;

import android.util.Log;

import com.rhg.qf.bean.SignInBackBean;
import com.rhg.qf.mvp.api.QFoodApiMamager;
import com.rhg.qf.utils.ToastHelper;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * desc:用户注册model
 * author：remember
 * time：2016/8/8 0:51
 * email：1013773046@qq.com
 */
public class UserSignInModel {
    public Observable<SignInBackBean.UserInfoBean> userSignIn(String client, String userName,
                                                              String pwd) {
        return QFoodApiMamager.getInstant().getQFoodApiService().userSignIn(client, userName, pwd)
                .flatMap(new Func1<SignInBackBean, Observable<SignInBackBean.UserInfoBean>>() {
                    @Override
                    public Observable<SignInBackBean.UserInfoBean> call(final SignInBackBean signInBackBean) {
                        return Observable.create(new Observable.OnSubscribe<SignInBackBean.UserInfoBean>() {
                            @Override
                            public void call(Subscriber<? super SignInBackBean.UserInfoBean> subscriber) {
                                if (signInBackBean.getResult() == 2)
                                    subscriber.onNext(null);
                                else subscriber.onNext(signInBackBean.getRows().get(0));
                            }
                        });
                    }
                });
    }
}
