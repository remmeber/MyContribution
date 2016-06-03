package com.example.rhg.outsourcing.mvp.presenter;

import com.example.rhg.outsourcing.bean.HomeBean;
import com.example.rhg.outsourcing.mvp.model.BaseModel;
import com.example.rhg.outsourcing.mvp.model.HomeModel;
import com.example.rhg.outsourcing.mvp.model.HomeModelImpl;
import com.example.rhg.outsourcing.mvp.model.TestModel;
import com.example.rhg.outsourcing.mvp.view.BaseView;

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
    HomeModel testModel;

    public HomePresenterImpl(BaseView baseView) {
        testView = baseView;
        testModel = new HomeModelImpl();
    }

    @Override
    public void getHomeData() {
        testModel.getHomeData().observeOn(AndroidSchedulers.mainThread())
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

        /*testModel.getData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())//使用subscribeOn()指定观察者代码运行的线程；非ui线程
                .subscribe(new Observer<BannerTypeBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (AppConstants.DEBUG)
                            Log.i("RHG", e.toString());
                    }

                    @Override
                    public void onNext(BannerTypeBean s) {
                        if (s != null) {
                            testView.showData(s);
                        }
                    }
                });*/
    }
}
