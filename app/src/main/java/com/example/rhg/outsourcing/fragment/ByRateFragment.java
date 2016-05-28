package com.example.rhg.outsourcing.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.rhg.outsourcing.apapter.QFoodMerchantAdapter;
import com.example.rhg.outsourcing.apapter.RecycleAbstractAdapter;
import com.example.rhg.outsourcing.bean.QFoodAllSellerBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 *desc:所有店铺的按评价fm
 *author：remember
 *time：2016/5/28 16:43
 *email：1013773046@qq.com
 */
public class ByRateFragment extends SuperFragment implements RecycleAbstractAdapter.OnListItemClick{
    //TODO-------------------------------按评分排序的数据--------------------------------------------
    List<QFoodAllSellerBean> dataByRateScoreModels = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    TestPresenter distancetestPresenter;
    private RecyclerView recyclerView;

    public ByRateFragment() {
        for (int i = 0; i < 6; i++) {
            QFoodAllSellerBean QFoodAllSellerBean = new QFoodAllSellerBean();
            QFoodAllSellerBean.setMerchantName("哈啊哈");
            QFoodAllSellerBean.setFoodType("中餐");
            QFoodAllSellerBean.setSellerDistance("距离20m");
            QFoodAllSellerBean.setImageUrl(AppConstants.images[2]);
            dataByRateScoreModels.add(QFoodAllSellerBean);
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
        QFoodMerchantAdapter QFoodMerchantAdapter = new QFoodMerchantAdapter(getContext(),dataByRateScoreModels);
        QFoodMerchantAdapter.setOnListItemClick(this);
        recyclerView.setAdapter(QFoodMerchantAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                distancetestPresenter.getData();
            }
        });

    }


    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {
        Toast.makeText(getContext(),o.toString(),Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void itemClick(View v, int position) {
        Toast.makeText(getActivity()," "+position+" is clicked ",Toast.LENGTH_SHORT).show();
    }
}
