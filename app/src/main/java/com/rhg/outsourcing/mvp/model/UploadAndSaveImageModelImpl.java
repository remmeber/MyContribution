package com.rhg.outsourcing.mvp.model;

import android.util.Log;

import com.rhg.outsourcing.bean.TestBean;
import com.rhg.outsourcing.mvp.api.QFoodApiMamager;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        RequestBody desc_userName =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), userName);
        RequestBody desc_pwd =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), pwd);
        MultipartBody.Part part = MultipartBody.Part.createFormData("Pic", file.getName(), requestBody);
        return QFoodApiMamager.getInstant().getQFoodApiService().UploadHeadImage(part, desc_userName, desc_pwd)
                .flatMap(new Func1<TestBean, Observable<String>>() {
                    @Override
                    public Observable<String> call(final TestBean testBean) {
                        return Observable.create(new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                Log.i("RHG", "DONE");
                                subscriber.onNext("" + testBean.getMsg());
                            }
                        });
                    }
                });
    }
}
