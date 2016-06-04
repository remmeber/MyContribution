package com.example.rhg.outsourcing.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.activity.GoodsDetailActivity;
import com.example.rhg.outsourcing.apapter.GoodsListAdapter;
import com.example.rhg.outsourcing.bean.ShopDetailUriBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.impl.SwipeRefreshListener;
import com.example.rhg.outsourcing.mvp.presenter.ShopDetailPresenter;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter1;
import com.example.rhg.outsourcing.mvp.presenter.ShopDetailPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:店铺详情中的商品类型fm，和{@link com.example.rhg.outsourcing.widget.VerticalTabLayout}一起使用
 * author：remember
 * time：2016/5/28 16:43
 * email：1013773046@qq.com
 */
public class FoodTypeFragment extends SuperFragment implements GoodsListAdapter.GoodsItemClickListener {
//    private int tag;
    List<ShopDetailUriBean.ShopDetailBean> shopDetailBeanList;
    GoodsListAdapter goodsListAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    SwipeRefreshListener swipeRefreshListener;
    RecyclerView recyclerView;
    ShopDetailPresenter shopDetailPresenter;


    public FoodTypeFragment() {
        shopDetailPresenter = new ShopDetailPresenterImpl(this);
    }

    @Override
    public void receiveData(Bundle arguments) {
        // TODO: 获取数据
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_swipe_recycle_layout;
    }

    @Override
    protected void initData() {
        if (shopDetailBeanList == null)
            shopDetailBeanList = new ArrayList<>();
        /*for (int i = 0; i < 4; i++) {
            GoodsDetailBean goodsDetailBean = new GoodsDetailBean();
            goodsDetailBean.setImageUrls(images);
            goodsDetailBean.setGoodsName("哈哈" + i);
            goodsDetailBean.setGoodSellNum("" + i);
            goodsDetailBean.setGoodsPrice(i + "0");
            goodsDetailBeanList.add(goodsDetailBean);
        }*/
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        goodsListAdapter = new GoodsListAdapter(getContext(), shopDetailBeanList);
        goodsListAdapter.setGoodsItemClickListener(this);
        recyclerView.setAdapter(goodsListAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshListener != null)
                    swipeRefreshListener.startRefresh();
            }

        });
    }

    @Override
    protected void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.common_swipe);
        recyclerView = (RecyclerView) view.findViewById(R.id.common_recycle);
        /*RecycleAbstractAdapter recycleSellerAdapter = new RecycleAbstractAdapter(getContext(), dataBySellNumberModels, AppConstants.TypeSeller);
        recycleSellerAdapter.setOnListItemClick(this);
        recyclerView.setAdapter(recycleSellerAdapter)*/
        ;
    }


    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {
        swipeRefreshLayout.setRefreshing(false);
        shopDetailBeanList = (List<ShopDetailUriBean.ShopDetailBean>) o;
        if (goodsListAdapter != null) {
            goodsListAdapter.setShopDetailBeanList(shopDetailBeanList);
            goodsListAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
        /*intent.putExtra(AppConstants.KEY_PRODUCT_ID, "20160518");
        intent.putExtra(AppConstants.KEY_PRODUCT_NAME, "土豆丝");
        intent.putExtra(AppConstants.KEY_PRODUCT_PRICE, "90");*/
        intent.putExtra(AppConstants.KEY_PRODUCT_ID, shopDetailBeanList.get(position).getID());
//        intent.putExtra() //todo 传递参数
        startActivityForResult(intent, 1);
    }

    public void setShopDetailBeanList(List<ShopDetailUriBean.ShopDetailBean> shopDetailBeanList) {
        this.shopDetailBeanList = shopDetailBeanList;
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        if (goodsListAdapter != null) {
            goodsListAdapter.setShopDetailBeanList(shopDetailBeanList);
            goodsListAdapter.notifyDataSetChanged();
        }
    }

    public void setSwipeRefreshListener(SwipeRefreshListener swipeRefreshListener) {
        this.swipeRefreshListener = swipeRefreshListener;
    }
/*
    public int getFragmentTag() {
        return tag;
    }

    public void setFragmentTag(int tag) {
        this.tag = tag;
    }*/
}
