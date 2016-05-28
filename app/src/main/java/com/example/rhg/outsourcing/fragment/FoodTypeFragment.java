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
import com.example.rhg.outsourcing.bean.GoodsDetailBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 *desc:店铺详情中的商品类型fm，和{@link com.example.rhg.outsourcing.widget.VerticalTabLayout}一起使用
 *author：remember
 *time：2016/5/28 16:43
 *email：1013773046@qq.com
 */
public class FoodTypeFragment extends SuperFragment implements GoodsListAdapter.GoodsItemClickListener {

    private String[] images = {
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
    };
    List<GoodsDetailBean> goodsDetailBeanList;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    TestPresenter testPresenter;

    public FoodTypeFragment() {
        testPresenter = new TestPresenter(this);
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
        goodsDetailBeanList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            GoodsDetailBean goodsDetailBean = new GoodsDetailBean();
            goodsDetailBean.setImageUrls(images);
            goodsDetailBean.setGoodsName("哈哈" + i);
            goodsDetailBean.setGoodSellNum("" + i);
            goodsDetailBean.setGoodsPrice(i + "0");
            goodsDetailBeanList.add(goodsDetailBean);
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        GoodsListAdapter goodsListAdapter = new GoodsListAdapter(getContext(), goodsDetailBeanList);
        goodsListAdapter.setGoodsItemClickListener(this);
        recyclerView.setAdapter(goodsListAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                testPresenter.getData();
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
    public void loadData() {
        super.loadData();
        swipeRefreshLayout.setRefreshing(true);
        testPresenter.getData();
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
        intent.putExtra(AppConstants.KEY_PRODUCT_ID,"20160518");
        intent.putExtra(AppConstants.KEY_PRODUCT_NAME,"土豆丝");
        intent.putExtra(AppConstants.KEY_PRODUCT_PRICE,"90");
//        intent.putExtra() //todo 传递参数
        startActivityForResult(intent,1);
    }

}
