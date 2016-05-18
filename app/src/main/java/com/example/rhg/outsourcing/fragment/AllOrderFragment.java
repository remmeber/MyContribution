package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.MyPagerAdapter;
import com.example.rhg.outsourcing.apapter.RecycleMultiTypeAdapter;
import com.example.rhg.outsourcing.apapter.RecycleSellerAdapter;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.model.BaseSellerModel;
import com.example.rhg.outsourcing.model.OrderModel;
import com.example.rhg.outsourcing.presenter.TestPresenter;
import com.example.rhg.outsourcing.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/5/17.
 */
public class AllOrderFragment extends SuperFragment implements RecycleSellerAdapter.OnListItemClick {
    RecyclerView rcv;
    SwipeRefreshLayout swipeRefreshLayout;
    List<BaseSellerModel> mData = new ArrayList<>();
    TestPresenter testPresenter;

    public AllOrderFragment() {
        for (int i = 0; i < 6; i++) {
            BaseSellerModel baseSellerModel = new BaseSellerModel("哈哈", R.drawable.recommend_default_icon_1
                    , "待付款", "下单时间:20160517", "订单号:0000", "30");
            mData.add(baseSellerModel);
        }
        testPresenter = new TestPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rcv_item, container, false);
        rcv = (RecyclerView) view.findViewById(R.id.recycleview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("RHG", "onActivityCreated");
        RecycleSellerAdapter recycleSellerAdapter = new RecycleSellerAdapter(getContext()
                , mData, AppConstants.TypeOrder);
        recycleSellerAdapter.setOnListItemClick(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setHasFixedSize(true);
        rcv.setAdapter(recycleSellerAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("RHG", "OnRefresh");
                testPresenter.getData();
            }

        });

    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {
        ToastHelper.getInstance()._toast(o.toString());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void itemClick(View v, int position) {
        ToastHelper.getInstance()._toast("item " + position + "is click.");
    }
}
