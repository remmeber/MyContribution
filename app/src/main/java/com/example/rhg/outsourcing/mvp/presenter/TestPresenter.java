package com.example.rhg.outsourcing.mvp.presenter;

import android.util.Log;

import com.example.rhg.outsourcing.bean.BannerTypeModel;
import com.example.rhg.outsourcing.bean.ImageModel;
import com.example.rhg.outsourcing.bean.TestBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.mvp.view.BaseView;
import com.example.rhg.outsourcing.mvp.model.BaseModel;
import com.example.rhg.outsourcing.mvp.model.TestModel;

import java.util.ArrayList;
import java.util.List;

import retrofit.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by remember on 2016/4/28.
 */
public class TestPresenter implements Presenter {
    BaseView testView;
    BaseModel testModel;

    public TestPresenter(BaseView baseView) {
        testView = baseView;
        testModel = new TestModel();
    }

    @Override
    public void getData() {
        testModel.getData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())//使用subscribeOn()指定观察者代码运行的线程；非ui线程
                .subscribe(new Observer<BannerTypeModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (AppConstants.DEBUG)
                            Log.i("RHG", e.toString());
                    }

                    @Override
                    public void onNext(BannerTypeModel s) {
                        if (s != null) {
                            testView.showData(s);
                        }
                    }
                });
    }
}
