package com.example.rhg.outsourcing.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.activity.ShopDetailActivity;
import com.example.rhg.outsourcing.apapter.QFoodMerchantAdapter;
import com.example.rhg.outsourcing.bean.MerchantUrlBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter;
import com.example.rhg.outsourcing.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:所有店铺的按销量
 * author：remember
 * time：2016/5/28 16:42
 * email：1013773046@qq.com
 */
public class BySellNumberFragment extends SuperFragment implements QFoodMerchantAdapter.OnListItemClick {
    private SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    //TODO-------------------------------按销量排序的数据--------------------------------------------
    List<MerchantUrlBean.MerchantBean> dataBySellNumberModels = new ArrayList<>();
    TestPresenter sellertestPresenter;
    LoadingDialog loadingDialog;
    QFoodMerchantAdapter qFoodMerchantAdapter;

    public void setContext(Context context) {
        if (qFoodMerchantAdapter != null)
            qFoodMerchantAdapter.setContext(context);
    }

    public BySellNumberFragment() {
        sellertestPresenter = new TestPresenter(this);
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
    protected void initData() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        qFoodMerchantAdapter = new QFoodMerchantAdapter(getContext(), dataBySellNumberModels);
        qFoodMerchantAdapter.setOnListItemClick(this);
        recyclerView.setAdapter(qFoodMerchantAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sellertestPresenter.getData("restaurants", 0);
            }
        });
    }

    @Override
    public void loadData() {
        super.loadData();
        /*loadingDialog = new LoadingDialog(getContext());
        loadingDialog.show();*/
        sellertestPresenter.getData("restaurants", 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        qFoodMerchantAdapter.setOnListItemClick(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        qFoodMerchantAdapter.setOnListItemClick(null);
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
    public void itemClick(View v, int position) {
//        Toast.makeText(getActivity()," "+position+" is clicked ",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), ShopDetailActivity.class);
        intent.putExtra(AppConstants.KEY_PHONE, "1234567890");
        intent.putExtra(AppConstants.KEY_ADDRESS, "江苏省南京市江宁区东南大学");
        intent.putExtra(AppConstants.KEY_NOTE, "东南大学是一所985高校");
        intent.putExtra(AppConstants.KEY_MERCHANT_ID, "20160517");
        intent.putExtra(AppConstants.KEY_MERCHANT_NAME, "荣哥土菜馆");
        intent.putExtra(AppConstants.KEY_MERCHANT_LOGO, AppConstants.images[1]);
        startActivityForResult(intent, 1);
        /*Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
        intent.putExtra("productId","20160518");
        intent.putExtra("productName","黄焖鸡米饭");
        intent.putExtra("goodsPrice","￥:90");
        startActivity(intent);*/
    }

}
