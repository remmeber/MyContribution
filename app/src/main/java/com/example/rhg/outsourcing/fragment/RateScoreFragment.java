package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.RecycleSellerAdapter;
import com.example.rhg.outsourcing.model.BaseSellerModel;
import com.example.rhg.outsourcing.presenter.TestPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/5/7.
 */
public class RateScoreFragment extends SuperFragment implements RecycleSellerAdapter.OnListItemClick{
    //TODO-------------------------------按评分排序的数据--------------------------------------------
    List<BaseSellerModel> dataByRateScoreModels = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    TestPresenter distancetestPresenter;

    public RateScoreFragment() {
        for (int i = 0; i < 6; i++) {
            BaseSellerModel baseSellerModel = new BaseSellerModel("哈哈", "中餐", "距离" + 10 + i + "m", R.drawable.recommend_default_icon_1);
            dataByRateScoreModels.add(baseSellerModel);
        }
        distancetestPresenter = new TestPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sellnumber_layout, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.seller_swipe);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.sellNumberRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecycleSellerAdapter recycleSellerAdapter = new RecycleSellerAdapter(getContext(), dataByRateScoreModels, AppConstants.TypeSeller);
        recycleSellerAdapter.setOnListItemClick(this);
        recyclerView.setAdapter(recycleSellerAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                distancetestPresenter.getData();
            }
        });
        return view;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
