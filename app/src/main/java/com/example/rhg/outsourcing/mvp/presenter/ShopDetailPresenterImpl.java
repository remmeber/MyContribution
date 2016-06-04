package com.example.rhg.outsourcing.mvp.presenter;

import com.example.rhg.outsourcing.bean.ShopDetailUriBean;
import com.example.rhg.outsourcing.mvp.model.ShopDetailModel;
import com.example.rhg.outsourcing.mvp.model.ShopDetailModelImpl;
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
public class ShopDetailPresenterImpl implements ShopDetailPresenter {
    BaseView testView;
    ShopDetailModel testModel;

    public ShopDetailPresenterImpl(BaseView baseView) {
        testView = baseView;
        testModel = new ShopDetailModelImpl();
    }

    @Override
    public void getShopDetail(String table, String merchantId) {
        testModel.getShopDetail(table,merchantId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<ShopDetailUriBean.ShopDetailBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ShopDetailUriBean.ShopDetailBean> shopDetailBeen) {
                        testView.showData(shopDetailBeen);
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
