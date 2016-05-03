package com.example.rhg.outsourcing.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.View.BaseView;
import com.example.rhg.outsourcing.presenter.TestPresenter;

/**
 * Created by remember on 2016/5/3.
 */
public abstract class SuperFragment extends Fragment implements BaseView {
    public SuperFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showData(Object o) {
        if (o.toString().equals("success")) {
            Log.i("SuperFragment","SuperFragment success");
            showSuccess();
        }
        else
            showFailed();
    }

    protected abstract void showFailed();


    public abstract void showSuccess();
}
