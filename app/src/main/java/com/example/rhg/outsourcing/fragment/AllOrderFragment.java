package com.example.rhg.outsourcing.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.activity.PayActivity;
import com.example.rhg.outsourcing.apapter.QFoodOrderAdapter;
import com.example.rhg.outsourcing.bean.OrderBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter;
import com.example.rhg.outsourcing.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 *desc:所有订单fm
 *author：remember
 *time：2016/5/28 16:42
 *email：1013773046@qq.com
 */
public class AllOrderFragment extends SuperFragment implements QFoodOrderAdapter.OnListItemClick {
    RecyclerView rcv;
    SwipeRefreshLayout swipeRefreshLayout;
    List<OrderBean> mData = new ArrayList<>();
    TestPresenter testPresenter;

    public AllOrderFragment() {
        for (int i = 0; i < 6; i++) {
            /*BaseSellerModel baseSellerModel = new BaseSellerModel("哈哈", R.drawable.recommend_default_icon_1
                    , "待付款", "下单时间:20160517", "订单号:0000", "30");*/
            OrderBean orderBean = new OrderBean();
            orderBean.setMerchantName("哈哈");
            orderBean.setImageUrl(AppConstants.images[1]);
            orderBean.setTv_orderTime("下单时间：20160517");
            orderBean.setTv_orderTag("订单号：000");
            orderBean.setTv_state("待付款");
            orderBean.setTv_totalMoney("30");
            mData.add(orderBean);
        }
        testPresenter = new TestPresenter(this);
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
    protected void initData() {
        QFoodOrderAdapter qFoodOrderAdapter = new QFoodOrderAdapter(getContext(), mData);
        qFoodOrderAdapter.setOnListItemClick(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setHasFixedSize(true);
        rcv.setAdapter(qFoodOrderAdapter);
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
//        ToastHelper.getInstance()._toast("item " + position + "is click.");
        Intent intent = new Intent(getContext(), PayActivity.class);
        startActivity(intent);
    }
}
