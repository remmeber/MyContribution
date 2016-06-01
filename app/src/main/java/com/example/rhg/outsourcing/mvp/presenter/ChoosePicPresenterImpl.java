package com.example.rhg.outsourcing.mvp.presenter;

import com.example.rhg.outsourcing.mvp.model.ChoosePicModel;
import com.example.rhg.outsourcing.mvp.model.ChoosePicModelImpl;
import com.example.rhg.outsourcing.mvp.view.BaseView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：rememberon 2016/5/31
 * 邮箱：1013773046@qq.com
 */
public class ChoosePicPresenterImpl implements ChoosePicPresenter {

    BaseView baseView;
    ChoosePicModel choosePicModel;

    public ChoosePicPresenterImpl(BaseView baseView) {
        this.baseView = baseView;
        choosePicModel = new ChoosePicModelImpl();
    }

    @Override
    public void getPicFromGallery() {
        choosePicModel.getPicFromGallery().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> strings) {

                    }
                });
    }

    @Override
    public void getPicFromCamera() {

    }
}
