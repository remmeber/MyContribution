package com.example.rhg.outsourcing.activity;

import android.view.View;

import butterknife.ButterKnife;
import retrofit.Retrofit;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by remember on 2016/5/25.
 */
public class TextActivity extends BaseActivity {
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
    }

    @Override
    public void showData(Object o) {

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
