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
import com.example.rhg.outsourcing.apapter.RecycleSellerAdapter;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.model.GoodsDetailModel;
import com.example.rhg.outsourcing.presenter.TestPresenter;
import com.example.rhg.outsourcing.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/5/20.
 */
public class FoodTypeFragment extends SuperFragment implements GoodsListAdapter.GoodsItemClickListener {

    private String[] images = {
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
    };
    List<GoodsDetailModel> goodsDetailModelList;
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
        return R.layout.rcv_item;
    }

    @Override
    protected void initData() {
        goodsDetailModelList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            GoodsDetailModel goodsDetailModel = new GoodsDetailModel();
            goodsDetailModel.setImageUrls(images);
            goodsDetailModel.setGoodsName("哈哈" + i);
            goodsDetailModel.setGoodSellNum("" + i);
            goodsDetailModel.setGoodsPrice(i + "0");
            goodsDetailModelList.add(goodsDetailModel);
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        GoodsListAdapter goodsListAdapter = new GoodsListAdapter(getContext(), goodsDetailModelList);
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
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview);
        /*RecycleSellerAdapter recycleSellerAdapter = new RecycleSellerAdapter(getContext(), dataBySellNumberModels, AppConstants.TypeSeller);
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
        ToastHelper.getInstance()._toast("" + position);
        Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
        intent.putExtra(AppConstants.KEY_PRODUCT_ID,"20160518");
        intent.putExtra(AppConstants.KEY_PRODUCT_NAME,"土豆丝");
        intent.putExtra(AppConstants.KEY_PRODUCT_PRICE,"90");
//        intent.putExtra() //todo 传递参数
        startActivity(intent);
    }
}
