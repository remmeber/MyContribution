package com.rhg.outsourcing.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.apapter.QFoodMerchantAdapter;
import com.rhg.outsourcing.bean.MerchantUrlBean;
import com.rhg.outsourcing.impl.RcvItemClickListener;
import com.rhg.outsourcing.mvp.presenter.MerchantsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * desc:所有店铺的按评价fm
 * author：remember
 * time：2016/5/28 16:43
 * email：1013773046@qq.com
 */
public class ByRateFragment extends SuperFragment implements RcvItemClickListener<MerchantUrlBean.MerchantBean> {
    //TODO-------------------------------按评分排序的数据--------------------------------------------
    List<MerchantUrlBean.MerchantBean> dataByRateScoreModels = new ArrayList<>();
    @Bind(R.id.common_recycle)
    RecyclerView commonRecycle;
    @Bind(R.id.common_refresh)
    ProgressBar commonRefresh;
    @Bind(R.id.common_swipe)
    SwipeRefreshLayout commonSwipe;

    MerchantsPresenterImpl getMerchantsOrderByRatePresenter;
    Context context;
    QFoodMerchantAdapter qFoodMerchantAdapter;

    public void setContext(Context context) {
        this.context = context;
        if (qFoodMerchantAdapter != null)
            qFoodMerchantAdapter.setContext(context);
    }

    public ByRateFragment() {
        getMerchantsOrderByRatePresenter = new MerchantsPresenterImpl(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_swipe_recycle_layout;
    }


    @Override
    protected void initView(View view) {
    }

    @Override
    public void loadData() {
        commonRefresh.setVisibility(View.VISIBLE);
        getMerchantsOrderByRatePresenter.getMerchants("restaurants", 2);
    }

    @Override
    protected void initData() {
        commonRecycle.setHasFixedSize(true);
        commonRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        qFoodMerchantAdapter = new QFoodMerchantAdapter(getContext(), dataByRateScoreModels);
        qFoodMerchantAdapter.setOnRcvItemClickListener(this);
        commonRecycle.setAdapter(qFoodMerchantAdapter);
        commonSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMerchantsOrderByRatePresenter.getMerchants("restaurants", 2);
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
        qFoodMerchantAdapter.setmData((List<MerchantUrlBean.MerchantBean>) o);
        qFoodMerchantAdapter.notifyDataSetChanged();
        if (commonSwipe.isRefreshing())
            commonSwipe.setRefreshing(false);
        if (commonRefresh.getVisibility() == View.VISIBLE)
            commonRefresh.setVisibility(View.GONE);
    }

    @Override
    public void onItemClickListener(int position, MerchantUrlBean.MerchantBean item) {

        Toast.makeText(getActivity(), " " + position + " is clicked ", Toast.LENGTH_SHORT).show();
    }

}
