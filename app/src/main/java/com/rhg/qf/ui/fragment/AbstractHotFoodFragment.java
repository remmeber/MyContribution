package com.rhg.qf.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.rhg.qf.R;
import com.rhg.qf.ui.activity.GoodsDetailActivity;
import com.rhg.qf.adapter.HotFoodAdapter;
import com.rhg.qf.bean.HotFoodUrlBean;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.impl.RcvItemClickListener;
import com.rhg.qf.mvp.presenter.HotFoodPresenter;
import com.rhg.qf.utils.SizeUtil;
import com.rhg.qf.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * desc:热销单品——综合
 * author：remember
 * time：2016/6/19 16:55
 * email：1013773046@qq.com
 */
public abstract class AbstractHotFoodFragment extends BaseFragment implements RcvItemClickListener<HotFoodUrlBean.HotFoodBean> {
    @Bind(R.id.common_recycle)
    RecyclerView commonRecycle;
    @Bind(R.id.common_refresh)
    ProgressBar commonRefresh;
    @Bind(R.id.common_swipe)
    SwipeRefreshLayout commonSwipe;

    List<HotFoodUrlBean.HotFoodBean> hotFoodBeanList = new ArrayList<>();
    HotFoodAdapter hotFoodAdapter;
    HotFoodPresenter hotFoodPresenter;
    int hotFoodType;
    String foodName;

    public AbstractHotFoodFragment() {
        hotFoodPresenter = new HotFoodPresenter(this);
        hotFoodType = getHotFoodType();
    }

    protected abstract int getHotFoodType();

    @Override
    public void receiveData(Bundle arguments) {
        foodName = arguments.getString(AppConstants.KEY_PRODUCT_NAME, "");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_swipe_recycle_layout;
    }

    @Override
    public void loadData() {
        commonRefresh.setVisibility(View.VISIBLE);
        hotFoodPresenter.getHotFoods(AppConstants.HOT_FOOD, hotFoodType, foodName);
    }

    @Override
    protected void initData() {
        commonRecycle.setHasFixedSize(true);
        commonRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        commonRecycle.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.HORIZONTAL, SizeUtil.dip2px(2),
                ContextCompat.getColor(getContext(), R.color.colorInActive)));
        hotFoodAdapter = new HotFoodAdapter(getContext(), hotFoodBeanList);
        hotFoodAdapter.setOnRcvItemClickListener(this);
        commonRecycle.setAdapter(hotFoodAdapter);
        commonSwipe.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getContext(), R.color.colorBlueNormal));
        commonSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                hotFoodPresenter.getHotFoods(AppConstants.HOT_FOOD, hotFoodType, foodName);
            }
        });
    }


    @Override
    protected void initView(View view) {
    }

    @Override
    protected void showFailed() {
    }

    @Override
    public void showSuccess(Object o) {
        hotFoodBeanList = (List<HotFoodUrlBean.HotFoodBean>) o;
        hotFoodAdapter.setHotFoodBeanList(hotFoodBeanList);
        if (commonRefresh.getVisibility() == View.VISIBLE)
            commonRefresh.setVisibility(View.GONE);
        if (commonSwipe.isRefreshing())
            commonSwipe.setRefreshing(false);
    }

    @Override
    public void onItemClickListener(int position, HotFoodUrlBean.HotFoodBean item) {
        Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
        intent.putExtra(AppConstants.KEY_PRODUCT_ID, item.getID());
        startActivity(intent);
    }

}