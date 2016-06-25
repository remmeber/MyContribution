package com.rhg.qf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.rhg.qf.R;
import com.rhg.qf.activity.OrderDetailActivity;
import com.rhg.qf.apapter.QFoodOrderAdapter;
import com.rhg.qf.bean.OrderUrlBean;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.impl.RcvItemClickListener;
import com.rhg.qf.mvp.presenter.OrderDetailPresenter;
import com.rhg.qf.mvp.presenter.OrderDetailPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * desc:订单抽象FM
 * author：remember
 * time：2016/5/28 16:42
 * email：1013773046@qq.com
 */
public abstract class AbstractOrderFragment extends SuperFragment implements RcvItemClickListener<OrderUrlBean.OrderBean> {
    @Bind(R.id.common_recycle)
    RecyclerView commonRecycle;
    @Bind(R.id.common_refresh)
    ProgressBar commonRefresh;
    @Bind(R.id.common_swipe)
    SwipeRefreshLayout commonSwipe;
    QFoodOrderAdapter qFoodOrderAdapter;
    List<OrderUrlBean.OrderBean> orderBeanList = new ArrayList<>();
    OrderDetailPresenter rderDetailPresenter;
    String userId;
    String style;

    public AbstractOrderFragment() {
        rderDetailPresenter = new OrderDetailPresenterImpl(this);
        userId = "1";/*从数据库中获取*/
        style = getFmTag();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_swipe_recycle_layout;
    }

    @Override
    protected void initView(View view) {
        commonRecycle = (RecyclerView) view.findViewById(R.id.common_recycle);
        commonSwipe = (SwipeRefreshLayout) view.findViewById(R.id.common_swipe);
    }

    @Override
    public void loadData() {
        commonRefresh.setVisibility(View.VISIBLE);
        rderDetailPresenter.getData(AppConstants.TABLE_ORDER, userId, style);
    }

    @Override
    protected void initData() {
        qFoodOrderAdapter = new QFoodOrderAdapter(getContext(), orderBeanList);
        qFoodOrderAdapter.setOnRcvItemClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        commonRecycle.setLayoutManager(linearLayoutManager);
        commonRecycle.setHasFixedSize(true);
        commonRecycle.setAdapter(qFoodOrderAdapter);
        commonSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rderDetailPresenter.getData(AppConstants.TABLE_ORDER, userId, style);
            }

        });
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {
        if (commonSwipe.isRefreshing())
            commonSwipe.setRefreshing(false);
        qFoodOrderAdapter.setmData((List<OrderUrlBean.OrderBean>) o);
        qFoodOrderAdapter.notifyDataSetChanged();
        if (commonRefresh.getVisibility() == View.VISIBLE)
            commonRefresh.setVisibility(View.GONE);
    }

    @Override
    public void onItemClickListener(int position, OrderUrlBean.OrderBean item) {
        Intent intent = new Intent(getContext(), OrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.SP_USER_NAME, item.getReceiver());
        bundle.putString(AppConstants.KEY_ADDRESS, item.getAddress());
        bundle.putString(AppConstants.KEY_OR_SP_PHONE, item.getPhone());
        bundle.putString(AppConstants.KEY_PRODUCT_PRICE, item.getPrice());
        bundle.putString(AppConstants.KEY_ORDER_TAG, style);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected abstract String getFmTag();


    /*@Override
    public void itemClick(View v, int position) {
//        ToastHelper.getInstance()._toast("item " + position + "is click.");
        Intent intent = new Intent(getContext(), PayActivity.class);
        startActivity(intent);
    }*/
}
