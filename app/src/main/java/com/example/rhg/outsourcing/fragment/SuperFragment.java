package com.example.rhg.outsourcing.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.mvp.view.BaseView;

/**
 * Created by remember on 2016/5/3.
 */
public abstract class SuperFragment extends Fragment implements BaseView {
    public SuperFragment() {
    }

    // TODO: 子类重写该方法，获取数据的统一入口
    public void receiveData(Bundle arguments) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        receiveData(getArguments());
        View view = inflater.inflate(getLayoutResId(), container, false);
        initView(view);
        initData();
        return view;
    }

    public int getLayoutResId() {
        return 0;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
        fillData();
    }

    /**
     * 将数据填充
     */
    public void fillData() {
    }

    /**
     * 从网络获取数据，在new的时候只加载一次，后期都需要refresh才能更新
     */
    public void loadData() {
        Log.i("RHG", "LOAD");
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    @Override
    public void showData(Object o) {
        if (o != null) {
            showSuccess(o);
        } else
            showFailed();
    }

    protected abstract void showFailed();


    public abstract void showSuccess(Object o);

}
