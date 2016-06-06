package com.example.rhg.outsourcing.mvp.presenter;

import android.util.Log;

import com.example.rhg.outsourcing.mvp.model.UploadAndSaveImageModel;
import com.example.rhg.outsourcing.mvp.model.UploadAndSaveImageModelImpl;
import com.example.rhg.outsourcing.mvp.view.BaseView;

import java.io.File;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：rememberon 2016/5/31
 * 邮箱：1013773046@qq.com
 */
public class UploadAndSaveImagePresenterImpl implements UploadAndSaveImagePresenter {

    BaseView baseView;
    UploadAndSaveImageModel uploadAndSaveImageModel;

    public UploadAndSaveImagePresenterImpl(BaseView baseView) {
        this.baseView = baseView;
        uploadAndSaveImageModel = new UploadAndSaveImageModelImpl();
    }

    @Override
    public void UploadAndSaveImage(File file, String userName, String pwd) {
        uploadAndSaveImageModel.UploadAndSaveImage(file, userName, pwd).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("RHG", e.getMessage());
                    }

                    @Override
                    public void onNext(String message) {
                        Log.i("RHG", message);
                        baseView.showData(message);
                    }
                });
    }

}
