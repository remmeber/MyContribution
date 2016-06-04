package com.example.rhg.outsourcing.mvp.presenter;

import com.example.rhg.outsourcing.bean.OrderUrlBean;
import com.example.rhg.outsourcing.bean.ShopDetailUriBean;
import com.example.rhg.outsourcing.mvp.model.ShopDetailModel;
import com.example.rhg.outsourcing.mvp.model.ShopDetailModelImpl;
import com.example.rhg.outsourcing.mvp.model.TestModel1;
import com.example.rhg.outsourcing.mvp.model.TestModelImpl;
import com.example.rhg.outsourcing.mvp.view.BaseView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * desc:mvp presenter 测试实现类
 * author：remember
 * time：2016/5/28 17:02
 * email：1013773046@qq.com
 */
public class TestPresenterImpl implements TestPresenter1 {
    BaseView testView;
    TestModel1 testModel;

    public TestPresenterImpl(BaseView baseView) {
        testView = baseView;
        testModel = new TestModelImpl();
    }

    @Override
    public void getData(String table, String userId, String style) {
        testModel.getData(table, userId , style).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<OrderUrlBean.OrderBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<OrderUrlBean.OrderBean> orderBeanList) {
                        testView.showData(orderBeanList);
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
