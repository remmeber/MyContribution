package com.rhg.qf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.rhg.qf.R;
import com.rhg.qf.activity.GoodsDetailActivity;
import com.rhg.qf.apapter.GoodsListAdapter;
import com.rhg.qf.bean.ShopDetailUriBean;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.impl.RcvItemClickListener;
import com.rhg.qf.mvp.presenter.ShopDetailPresenter;
import com.rhg.qf.mvp.presenter.ShopDetailPresenterImpl;
import com.rhg.qf.widget.VerticalTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * desc:店铺详情中的商品类型fm，和{@link VerticalTabLayout}一起使用
 * author：remember
 * time：2016/5/28 16:43
 * email：1013773046@qq.com
 */
public class FoodTypeFragment extends BaseFragment implements RcvItemClickListener<ShopDetailUriBean.ShopDetailBean> {
    //    private int tag;
    List<ShopDetailUriBean.ShopDetailBean> shopDetailBeanList;
    GoodsListAdapter goodsListAdapter;
    ShopDetailPresenter shopDetailPresenter;

    String merchantId;
    @Bind(R.id.common_recycle)
    RecyclerView commonRecycle;
    @Bind(R.id.common_refresh)
    ProgressBar commonRefresh;
    @Bind(R.id.common_swipe)
    SwipeRefreshLayout commonSwipe;


    public FoodTypeFragment() {
        shopDetailPresenter = new ShopDetailPresenterImpl(this);
    }

    @Override
    public void receiveData(Bundle arguments) {
        // TODO: 获取数据
        merchantId = arguments.getString(AppConstants.KEY_MERCHANT_ID);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_swipe_recycle_layout;
    }

    @Override
    public void loadData() {
        commonRefresh.setVisibility(View.VISIBLE);
        shopDetailPresenter.getShopDetail("food", merchantId);
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
        commonRecycle.setHasFixedSize(true);
        commonRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        goodsListAdapter = new GoodsListAdapter(getContext(), shopDetailBeanList);
        goodsListAdapter.setOnGoodsItemClickListener(this);
        commonRecycle.setAdapter(goodsListAdapter);
        commonSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shopDetailPresenter.getShopDetail("food", merchantId);
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
        shopDetailBeanList = (List<ShopDetailUriBean.ShopDetailBean>) o;
        if (goodsListAdapter != null) {
            goodsListAdapter.setShopDetailBeanList(shopDetailBeanList);
            goodsListAdapter.notifyDataSetChanged();
        }
        if (commonSwipe.isRefreshing())
            commonSwipe.setRefreshing(false);
        if (commonRefresh.getVisibility() == View.VISIBLE)
            commonRefresh.setVisibility(View.GONE);
    }

    @Override
    public void onItemClickListener(int position, ShopDetailUriBean.ShopDetailBean item) {
        Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
        /*intent.putExtra(AppConstants.KEY_PRODUCT_ID, "20160518");
        intent.putExtra(AppConstants.KEY_PRODUCT_NAME, "土豆丝");
        intent.putExtra(AppConstants.KEY_PRODUCT_PRICE, "90");*/
        intent.putExtra(AppConstants.KEY_PRODUCT_ID, item.getID());
//        intent.putExtra() //todo 传递参数
        startActivityForResult(intent, 1);
    }

   /* public void setShopDetailBeanList(List<ShopDetailUriBean.ShopDetailBean> shopDetailBeanList) {
        this.shopDetailBeanList = shopDetailBeanList;
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        if (goodsListAdapter != null) {
            goodsListAdapter.setShopDetailBeanList(shopDetailBeanList);
            goodsListAdapter.notifyDataSetChanged();
        }
    }
*/
/*
    public int getFragmentTag() {
        return tag;
    }

    public void setFragmentTag(int tag) {
        this.tag = tag;
    }*/
}
