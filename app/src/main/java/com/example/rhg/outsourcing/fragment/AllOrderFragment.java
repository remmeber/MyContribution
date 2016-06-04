package com.example.rhg.outsourcing.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.activity.PayActivity;
import com.example.rhg.outsourcing.apapter.QFoodOrderAdapter;
import com.example.rhg.outsourcing.bean.OrderUrlBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter1;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenterImpl;
import com.example.rhg.outsourcing.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:所有订单fm
 * author：remember
 * time：2016/5/28 16:42
 * email：1013773046@qq.com
 */
public class AllOrderFragment extends SuperFragment implements QFoodOrderAdapter.OnListItemClick {
    RecyclerView rcv;
    QFoodOrderAdapter qFoodOrderAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    List<OrderUrlBean.OrderBean> orderBeanList = new ArrayList<>();
    LoadingDialog loadingDialog;
    TestPresenter1 testPresenter;
    String userId;
    String style;

    public AllOrderFragment() {
        testPresenter = new TestPresenterImpl(this);
        userId = "1";
        style = "3";
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_swipe_recycle_layout;
    }

    @Override
    protected void initView(View view) {
        rcv = (RecyclerView) view.findViewById(R.id.common_recycle);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.common_swipe);
    }

    @Override
    public void loadData() {
        loadingDialog = new LoadingDialog(getContext());
        loadingDialog.show();
        testPresenter.getData(AppConstants.TABLE_ORDER, userId, style);
    }

    @Override
    protected void initData() {
        qFoodOrderAdapter = new QFoodOrderAdapter(getContext(), orderBeanList);
        qFoodOrderAdapter.setOnListItemClick(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setHasFixedSize(true);
        rcv.setAdapter(qFoodOrderAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                testPresenter.getData(AppConstants.TABLE_ORDER, userId, style);
            }

        });
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        qFoodOrderAdapter.setmData((List<OrderUrlBean.OrderBean>) o);
        qFoodOrderAdapter.notifyDataSetChanged();
        loadingDialog.dismiss();
    }

    @Override
    public void itemClick(View v, int position) {
//        ToastHelper.getInstance()._toast("item " + position + "is click.");
        Intent intent = new Intent(getContext(), PayActivity.class);
        startActivity(intent);
    }
}
