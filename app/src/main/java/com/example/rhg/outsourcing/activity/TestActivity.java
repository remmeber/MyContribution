package com.example.rhg.outsourcing.activity;

import android.view.View;

import butterknife.ButterKnife;
import retrofit.Retrofit;
import rx.subscriptions.CompositeSubscription;

/**
 *desc:测试页面
 *author：remember
 *time：2016/5/28 16:15
 *email：1013773046@qq.com
 */
public class TestActivity extends BaseActivity {
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
