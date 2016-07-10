package com.rhg.qf.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.adapter.DeliverOrderItemAdapter;
import com.rhg.qf.bean.DeliverOrderUrlBean;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.impl.RcvItemClickListener;
import com.rhg.qf.mvp.presenter.DeliverOrderPresenter;
import com.rhg.qf.utils.DpUtil;
import com.rhg.qf.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * desc:跑腿员订单查看页面
 * author：remember
 * time：2016/7/9 0:14
 * email：1013773046@qq.com
 */
public class DeliverOrderActivity extends BaseAppcompactActivity implements DeliverOrderItemAdapter.OrderStyleListener,
        RcvItemClickListener<DeliverOrderUrlBean.DeliverOrderBean> {

    @Bind(R.id.tb_center_tv)
    TextView tbCenterTv;
    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.bt_order_snatch)
    TextView btOrderSnatch;
    @Bind(R.id.bt_order_progress)
    TextView btOrderProgress;
    @Bind(R.id.common_recycle)
    RecyclerView commonRecycle;
    @Bind(R.id.common_refresh)
    ProgressBar commonRefresh;
    @Bind(R.id.common_swipe)
    SwipeRefreshLayout commonSwipe;
    private List<DeliverOrderUrlBean.DeliverOrderBean> deliverOrderBeanList = new ArrayList<>();
    private DeliverOrderItemAdapter deliverOrderItemAdapter;
    DeliverOrderPresenter getDeliverOrder;

    @Override
    public void loadingData() {
        commonRefresh.setVisibility(View.VISIBLE);
        getDeliverOrder = new DeliverOrderPresenter(this);
        getDeliverOrder.getDeliverOrder(AppConstants.DELIVER_ORDER, "1");
    }

    @Override
    protected void initData() {
        tbCenterTv.setText(getResources().getString(R.string.myOrder));
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        commonRecycle.setLayoutManager(new LinearLayoutManager(this));
        commonRecycle.setHasFixedSize(false);
        RecycleViewDivider _divider = new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL,
                DpUtil.dip2px(16), getResources().getColor(R.color.white));
        commonRecycle.addItemDecoration(_divider);
        deliverOrderItemAdapter = new DeliverOrderItemAdapter(this, deliverOrderBeanList);
        deliverOrderItemAdapter.setOnRcvItemClick(this);
        deliverOrderItemAdapter.setOnStyleChange(this);
        commonRecycle.setAdapter(deliverOrderItemAdapter);

        commonSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*TODO refresh*/
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.order_manage_layout;
    }

    @Override
    protected void showSuccess(Object s) {
        deliverOrderBeanList = (List<DeliverOrderUrlBean.DeliverOrderBean>) s;
        deliverOrderItemAdapter.setDeliverOrderBeanList(deliverOrderBeanList);
        if (commonRefresh.getVisibility() == View.VISIBLE)
            commonRefresh.setVisibility(View.GONE);
        if (commonSwipe.isRefreshing())
            commonSwipe.setRefreshing(false);
    }

    @Override
    protected void showError(Object s) {

    }


    @OnClick({R.id.tb_left_iv, R.id.bt_order_snatch, R.id.bt_order_progress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
            case R.id.bt_order_snatch:
                btOrderSnatch.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
                btOrderProgress.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.bt_order_progress:
                btOrderProgress.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
                btOrderSnatch.setBackgroundColor(getResources().getColor(R.color.white));
                break;
        }
    }

    @Override
    public void onStyleChange(String style, int position) {
        switch (style) {
            case AppConstants.ORDER_START:
                deliverOrderBeanList.get(position).setStyle(AppConstants.ORDER_DELIVER_COMPLETE);
                deliverOrderItemAdapter.updateCertainPosition(deliverOrderBeanList, position);
                break;
            case AppConstants.ORDER_DELIVER_COMPLETE:
                deliverOrderBeanList.get(position).setStyle(AppConstants.ORDER_DELIVER_FINISH);
                deliverOrderItemAdapter.updateCertainPosition(deliverOrderBeanList, position);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClickListener(int position, DeliverOrderUrlBean.DeliverOrderBean item) {

    }
}
