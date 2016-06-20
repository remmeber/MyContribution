package com.rhg.outsourcing.mvp.presenter;

import com.rhg.outsourcing.bean.HomeBean;
import com.rhg.outsourcing.mvp.model.HomeModel;
import com.rhg.outsourcing.mvp.model.HomeModelImpl;
import com.rhg.outsourcing.mvp.view.BaseView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * desc:mvp presenter 测试实现类
 * author：remember
 * time：2016/5/28 17:02
 * email：1013773046@qq.com
 */
public class HomePresenterImpl implements HomePresenter {
    BaseView testView;
    HomeModel homeModel;

    public HomePresenterImpl(BaseView baseView) {
        testView = baseView;
        homeModel = new HomeModelImpl();
    }

    @Override
    public void getHomeData() {
        homeModel.getHomeData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        testView.showData(homeBean);
                    }
                });
    }
}
