package com.example.rhg.outsourcing.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.R;

/**
 * Created by remember on 2016/4/28.
 */
public class ShoppingCarFragment extends SuperFragment {
    private static final String TAG = "ShoppingCarFragment";
    View view;
    //-----------------根据需求创建相应的presenter----------------------------------------------------

    //----------------------------------------------------------------------------------------------
    public ShoppingCarFragment() {
        Log.i(TAG,"ShoppingCarFragment ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shoppingcar_layout,container,false);
        return view;
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess() {

    }
}
