package com.example.rhg.outsourcing.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.R;

/**
 * Created by remember on 2016/4/28.
 */
public class SellerFragment extends SuperFragment {
    private static final String TAG = "SellerFragment";
    View view;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    //-----------------根据需求创建相应的presenter----------------------------------------------------

    //----------------------------------------------------------------------------------------------
    public SellerFragment() {
        Log.i(TAG,"SellerFragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.seller_layout,container,false);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.seller_swipe);
        recyclerView = (RecyclerView)view.findViewById(R.id.swiperefresh);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess() {

    }
}
