package com.rhg.qf.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.rhg.qf.R;
import com.rhg.qf.apapter.HotSellItemRcvAdapter;
import com.rhg.qf.bean.HotFoodUrlBean;
import com.rhg.qf.impl.RcvItemClickListener;
import com.rhg.qf.mvp.presenter.HotFoodPresenter;
import com.rhg.qf.mvp.presenter.HotFoodPresenterImpl;
import com.rhg.qf.utils.DpUtil;
import com.rhg.qf.utils.ToastHelper;
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
public abstract class AbstractHotFoodFragment extends BaseFragment implements
        RcvItemClickListener<HotFoodUrlBean.HotGoodsBean> {
    List<HotFoodUrlBean.HotGoodsBean> hotGoodsBeanList;
    HotSellItemRcvAdapter hotSellItemRcvAdapter;
    HotFoodPresenter hotFoodPresenter;

    @Bind(R.id.common_recycle)
    RecyclerView commonRecycle;
    @Bind(R.id.common_refresh)
    ProgressBar commonRefresh;
    @Bind(R.id.common_swipe)
    SwipeRefreshLayout commonSwipe;

    String hotFoodType;

    public AbstractHotFoodFragment() {
        hotGoodsBeanList = new ArrayList<>();
        hotFoodPresenter = new HotFoodPresenterImpl(this);
        hotFoodType = getHotFoodType();
    }

    protected abstract String getHotFoodType();


    @Override
    public int getLayoutResId() {
        return R.layout.common_swipe_recycle_layout;
    }

    @Override
    public void loadData() {
        commonRefresh.setVisibility(View.VISIBLE);
        hotFoodPresenter.getHotFoods("hotFood", hotFoodType);
    }

    @Override
    protected void initData() {
        commonRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        commonRecycle.setHasFixedSize(false);
        commonRecycle.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.HORIZONTAL, DpUtil.dip2px(1),
                getResources().getColor(R.color.colorInActive)));
        hotSellItemRcvAdapter = new HotSellItemRcvAdapter(getContext(),
                hotGoodsBeanList);
        hotSellItemRcvAdapter.setOnRcvItemClickListener(this);
        commonRecycle.setAdapter(hotSellItemRcvAdapter);
        commonSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hotFoodPresenter.getHotFoods("hotfood", hotFoodType);
                    }
                }, 2000);
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
        hotGoodsBeanList = (List<HotFoodUrlBean.HotGoodsBean>) o;
        hotSellItemRcvAdapter.setHotGoodsBeanList(hotGoodsBeanList);
        commonRefresh.setVisibility(View.GONE);
    }

    @Override
    public void onItemClickListener(int position, HotFoodUrlBean.HotGoodsBean item) {
        ToastHelper.getInstance()._toast("点击：" + position);
    }
}
