package com.example.rhg.outsourcing.presenter;

import com.example.rhg.outsourcing.view.BaseView;
import com.example.rhg.outsourcing.model.BaseModel;
import com.example.rhg.outsourcing.model.TestModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by remember on 2016/4/28.
 */
public class TestPresenter implements Presenter {
    BaseView testView;
    BaseModel testModel;
    public TestPresenter(BaseView baseView){
        testView = baseView;
        testModel = new TestModel();
    }

    @Override
    public void getData() {
        testModel.GetData().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object s) {
                        if("success".equals(s.toString()))
                            testView.showData(s);
                    }
                });
    }
}
