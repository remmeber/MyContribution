package com.rhg.outsourcing.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.activity.ShopDetailActivity;
import com.rhg.outsourcing.apapter.QFoodMerchantAdapter;
import com.rhg.outsourcing.bean.MerchantUrlBean;
import com.rhg.outsourcing.constants.AppConstants;
import com.rhg.outsourcing.impl.RcvItemClickListener;
import com.rhg.outsourcing.mvp.presenter.MerchantsPresenter;
import com.rhg.outsourcing.mvp.presenter.MerchantsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * desc:所有店铺的按销量
 * author：remember
 * time：2016/5/28 16:42
 * email：1013773046@qq.com
 */
public class BySellNumberFragment extends SuperFragment implements RcvItemClickListener<MerchantUrlBean.MerchantBean> {
    @Bind(R.id.common_recycle)
    RecyclerView commonRecycle;
    @Bind(R.id.common_refresh)
    ProgressBar commonRefresh;
    @Bind(R.id.common_swipe)
    SwipeRefreshLayout commonSwipe;
    //TODO-------------------------------按销量排序的数据--------------------------------------------
    List<MerchantUrlBean.MerchantBean> dataBySellNumberModels = new ArrayList<>();
    MerchantsPresenter getMerchantsOrderBySellNumberPresenter;
    QFoodMerchantAdapter qFoodMerchantAdapter;

    public void setContext(Context context) {
        if (qFoodMerchantAdapter != null)
            qFoodMerchantAdapter.setContext(context);
    }

    public BySellNumberFragment() {
        getMerchantsOrderBySellNumberPresenter = new MerchantsPresenterImpl(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_swipe_recycle_layout;
    }


    @Override
    protected void initData() {
        commonRecycle.setHasFixedSize(true);
        commonRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        qFoodMerchantAdapter = new QFoodMerchantAdapter(getContext(), dataBySellNumberModels);
        qFoodMerchantAdapter.setOnRcvItemClickListener(this);
        commonRecycle.setAdapter(qFoodMerchantAdapter);
        commonSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMerchantsOrderBySellNumberPresenter.getMerchants("restaurants", 0);
            }
        });
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    public void loadData() {
        commonRefresh.setVisibility(View.VISIBLE);
        getMerchantsOrderBySellNumberPresenter.getMerchants("restaurants", 0);
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
        dataBySellNumberModels = (List<MerchantUrlBean.MerchantBean>) o;
        qFoodMerchantAdapter.setmData(dataBySellNumberModels);
        qFoodMerchantAdapter.notifyDataSetChanged();
        if (commonSwipe.isRefreshing())
            commonSwipe.setRefreshing(false);
        if (commonRefresh.getVisibility() == View.VISIBLE) {
            commonRefresh.setVisibility(View.GONE);
        }
    }


    @Override
    public void onItemClickListener(int position, MerchantUrlBean.MerchantBean item) {
        Intent intent = new Intent(getContext(), ShopDetailActivity.class);
        MerchantUrlBean.MerchantBean merchantBean = dataBySellNumberModels.get(position);
        /*目前后台还没有加入这是三个字段*/
        intent.putExtra(AppConstants.KEY_OR_SP_PHONE, "1234567890");
        intent.putExtra(AppConstants.KEY_ADDRESS, "江苏省南京市江宁区东南大学");
        intent.putExtra(AppConstants.KEY_NOTE, "东南大学是一所985高校");

        intent.putExtra(AppConstants.KEY_MERCHANT_ID, merchantBean.getID());
        intent.putExtra(AppConstants.KEY_MERCHANT_NAME, merchantBean.getName());
        intent.putExtra(AppConstants.KEY_MERCHANT_LOGO, merchantBean.getPic());
        startActivityForResult(intent, 1);
        /*Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
        intent.putExtra("productId","20160518");
        intent.putExtra("productName","黄焖鸡米饭");
        intent.putExtra("goodsPrice","￥:90");
        startActivity(intent);*/

    }

}
