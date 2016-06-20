package com.rhg.outsourcing.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.rhg.outsourcing.apapter.QFoodMerchantAdapter;
import com.rhg.outsourcing.bean.MerchantUrlBean;
import com.rhg.outsourcing.R;
import com.rhg.outsourcing.impl.RcvItemClickListener;
import com.rhg.outsourcing.mvp.presenter.MerchantsPresenterImpl;
import com.rhg.outsourcing.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:所有店铺的按距离fm
 * author：remember
 * time：2016/5/28 16:42
 * email：1013773046@qq.com
 */
public class ByDistanceFragment extends SuperFragment implements RcvItemClickListener<MerchantUrlBean.MerchantBean> {

    //TODO-------------------------------按距离排序的数据--------------------------------------------
    List<MerchantUrlBean.MerchantBean> dataByDistanceModels = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    MerchantsPresenterImpl sellertestPresenter;
    QFoodMerchantAdapter qFoodMerchantAdapter;
    Context context = getContext();

    public void setContext(Context context) {
        this.context = context;
        if (qFoodMerchantAdapter != null)
            qFoodMerchantAdapter.setContext(context);
    }

    public ByDistanceFragment() {

        sellertestPresenter = new MerchantsPresenterImpl(this);
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
    LoadingDialog loadingDialog;
    @Override
    public void loadData() {
        super.loadData();
        /*loadingDialog = new LoadingDialog(getContext());
        loadingDialog.show();*/
        sellertestPresenter.getMerchants("restaurants", 0);
    }

    @Override
    protected void initData() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        qFoodMerchantAdapter = new QFoodMerchantAdapter(getContext(), dataByDistanceModels);
        qFoodMerchantAdapter.setOnRcvItemClickListener(this);
        recyclerView.setAdapter(qFoodMerchantAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sellertestPresenter.getMerchants("restaurants", 0);
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
        swipeRefreshLayout.setRefreshing(false);
        qFoodMerchantAdapter.setmData((List<MerchantUrlBean.MerchantBean>) o);
        qFoodMerchantAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClickListener(int position, MerchantUrlBean.MerchantBean item) {
        Toast.makeText(getActivity(), " " + position + " is clicked ", Toast.LENGTH_SHORT).show();

    }
}