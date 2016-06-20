package com.example.rhg.outsourcing.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.rhg.outsourcing.apapter.QFoodMerchantAdapter;
import com.example.rhg.outsourcing.bean.MerchantUrlBean;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.impl.RcvItemClickListener;
import com.example.rhg.outsourcing.mvp.presenter.MerchantsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:所有店铺的按评价fm
 * author：remember
 * time：2016/5/28 16:43
 * email：1013773046@qq.com
 */
public class ByRateFragment extends SuperFragment implements RcvItemClickListener<MerchantUrlBean.MerchantBean> {
    //TODO-------------------------------按评分排序的数据--------------------------------------------
    List<MerchantUrlBean.MerchantBean> dataByRateScoreModels = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    MerchantsPresenterImpl distanceTestPresenter;
    private RecyclerView recyclerView;
    Context context;
    QFoodMerchantAdapter qFoodMerchantAdapter;

    public void setContext(Context context) {
        this.context = context;
        if (qFoodMerchantAdapter != null)
            qFoodMerchantAdapter.setContext(context);
    }

    public ByRateFragment() {
        distanceTestPresenter = new MerchantsPresenterImpl(this);
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
    public void loadData() {
        super.loadData();
        distanceTestPresenter.getMerchants("restaurants", 2);
    }

    @Override
    protected void initData() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        qFoodMerchantAdapter = new QFoodMerchantAdapter(getContext(), dataByRateScoreModels);
        qFoodMerchantAdapter.setOnRcvItemClickListener(this);
        recyclerView.setAdapter(qFoodMerchantAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                distanceTestPresenter.getMerchants("restaurants", 2);
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        qFoodMerchantAdapter.setOnRcvItemClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        qFoodMerchantAdapter.setOnRcvItemClickListener(null);
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {
        swipeRefreshLayout.setRefreshing(false);
        qFoodMerchantAdapter.setmData((List<MerchantUrlBean.MerchantBean>) o);
        qFoodMerchantAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickListener(int position, MerchantUrlBean.MerchantBean item) {

        Toast.makeText(getActivity(), " " + position + " is clicked ", Toast.LENGTH_SHORT).show();
    }
}
