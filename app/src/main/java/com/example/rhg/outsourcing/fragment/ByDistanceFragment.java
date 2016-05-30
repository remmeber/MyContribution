package com.example.rhg.outsourcing.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rhg.outsourcing.apapter.QFoodMerchantAdapter;
import com.example.rhg.outsourcing.bean.QFoodAllSellerBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:所有店铺的按距离fm
 * author：remember
 * time：2016/5/28 16:42
 * email：1013773046@qq.com
 */
public class ByDistanceFragment extends SuperFragment implements QFoodMerchantAdapter.OnListItemClick {

    //TODO-------------------------------按距离排序的数据--------------------------------------------
    List<QFoodAllSellerBean> dataByDistanceModels = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    TestPresenter distancetestPresenter;
    QFoodMerchantAdapter qFoodMerchantAdapter;
    Context context = getContext();

    public void setContext(Context context) {
        this.context = context;
        if (qFoodMerchantAdapter != null)
            qFoodMerchantAdapter.setSuperContext(context);
    }

    public ByDistanceFragment() {
        for (int i = 0; i < 6; i++) {
//            BaseSellerModel baseSellerModel = new BaseSellerModel("哈哈", "中餐", "距离"+10+i+"m", R.drawable.recommend_default_icon_1);
            QFoodAllSellerBean QFoodAllSellerBean = new QFoodAllSellerBean();
            QFoodAllSellerBean.setMerchantName("哈啊哈");
            QFoodAllSellerBean.setFoodType("中餐");
            QFoodAllSellerBean.setSellerDistance("距离10" + i + "m");
            QFoodAllSellerBean.setImageUrl(AppConstants.images[3]);
            QFoodAllSellerBean.setDemandMoney("满10元起送");
            QFoodAllSellerBean.setDeliverMoney("配送费10元");
            dataByDistanceModels.add(QFoodAllSellerBean);
        }
        distancetestPresenter = new TestPresenter(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_swipe_recycle_layout;
    }

    @Override
    protected void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.common_swipe);
        recyclerView = (RecyclerView) view.findViewById(R.id.common_recycle);

    }

    @Override
    protected void initData() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        qFoodMerchantAdapter = new QFoodMerchantAdapter(getContext(), dataByDistanceModels);
        qFoodMerchantAdapter.setOnListItemClick(this);
        recyclerView.setAdapter(qFoodMerchantAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                distancetestPresenter.getData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        qFoodMerchantAdapter.setOnListItemClick(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        qFoodMerchantAdapter.setOnListItemClick(null);
    }

    @Override
    protected void showFailed() {
    }

    @Override
    public void showSuccess(Object o) {
        Toast.makeText(getContext(), o.toString(), Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void itemClick(View v, int position) {
        Toast.makeText(getActivity(), " " + position + " is clicked ", Toast.LENGTH_SHORT).show();

    }
}
