package com.example.rhg.outsourcing.fragment;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.HotSellItemAdapter;
import com.example.rhg.outsourcing.bean.HotGoodsUrlBean;
import com.example.rhg.outsourcing.impl.RcvItemClickListener;
import com.example.rhg.outsourcing.utils.DpUtil;
import com.example.rhg.outsourcing.utils.ToastHelper;
import com.example.rhg.outsourcing.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:热销单品——综合
 * author：remember
 * time：2016/6/19 16:55
 * email：1013773046@qq.com
 */
public class ComprehensiveHotSellFragment extends SuperFragment implements
        RcvItemClickListener<HotGoodsUrlBean.HotGoodsBean> {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    List<HotGoodsUrlBean.HotGoodsBean> hotGoodsBeanList;
    HotSellItemAdapter hotSellItemAdapter;

    public ComprehensiveHotSellFragment() {
        hotGoodsBeanList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HotGoodsUrlBean.HotGoodsBean hotGoodsBean = new HotGoodsUrlBean.HotGoodsBean();
            hotGoodsBean.setRName("黄焖鸡饭店" + i);
            hotGoodsBean.setFName("黄焖鸡米饭" + i);
            hotGoodsBean.setDelivery("满" + i + "起送");
            hotGoodsBean.setFee("配送费" + i + "元");
            hotGoodsBean.setStars("3." + i);
            hotGoodsBean.setDistance("距离" + i + "00米");
            hotGoodsBean.setPrice(i + "22.00");
            hotGoodsBeanList.add(hotGoodsBean);
        }

    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_swipe_recycle_layout;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.common_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.common_recycle);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.HORIZONTAL, DpUtil.dip2px(1),
                getResources().getColor(R.color.colorInActive)));
        hotSellItemAdapter = new HotSellItemAdapter(getContext(),
                hotGoodsBeanList);
        hotSellItemAdapter.setOnRcvItemClickListener(this);
        recyclerView.setAdapter(hotSellItemAdapter);
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {

    }

    @Override
    public void onItemClickListener(int position, HotGoodsUrlBean.HotGoodsBean item) {
        ToastHelper.getInstance()._toast("点击：" + position);
    }
}
