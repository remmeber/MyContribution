package com.rhg.qf.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.constants.AppConstants;

import java.util.Locale;

import butterknife.Bind;
import butterknife.OnClick;

/*
 *desc 订单详情页
 *author rhg
 *time 2016/6/22 20:31
 *email 1013773046@qq.com
 */
public class OrderDetailActivity extends BaseActivity {

    @Bind(R.id.tb_center_tv)
    TextView tbCenterTv;
    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.fl_tab)
    FrameLayout flTab;
    @Bind(R.id.tv_receiver)
    TextView tvReceiver;
    @Bind(R.id.tv_receiver_phone)
    TextView tvReceiverPhone;
    @Bind(R.id.tv_receiver_address)
    TextView tvReceiverAddress;
    @Bind(R.id.tvOrderNote)
    TextView tvOrderNote;
    @Bind(R.id.tvMerchantName)
    TextView tvMerchantName;
    @Bind(R.id.btDrawback)
    Button btDrawback;
    @Bind(R.id.rcyPayItem)
    RecyclerView rcyPayItem;

    @Bind(R.id.tv_totalMoney)
    TextView tvTotalMoney;
    @Bind(R.id.ly_totalCount)
    LinearLayout lyTotalCount;
    @Bind(R.id.btPayOrRateOrConform)
    TextView btPayOrRateOrConform;
    @Bind(R.id.iv_edit_right)
    ImageView ivEditRight;
    @Bind(R.id.tv_edit)
    TextView tvEdit;

    String orderTag;
    String orderReceiver;
    String orderPhone;
    String orderAddress;
    String orderPrice;

    @Override
    public void dataReceive(Intent intent) {
        orderTag = intent.getStringExtra(AppConstants.KEY_ORDER_TAG);
        orderReceiver = intent.getStringExtra(AppConstants.SP_USER_NAME);
        orderPhone = intent.getStringExtra(AppConstants.KEY_OR_SP_PHONE);
        orderAddress = intent.getStringExtra(AppConstants.KEY_ADDRESS);
        orderPrice = intent.getStringExtra(AppConstants.KEY_PRODUCT_PRICE);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.order_detail_layout;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        flTab.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
        tbCenterTv.setText(getResources().getString(R.string.myOrder));
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        tvReceiver.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.tvReceiver),
                orderReceiver));
        tvReceiverPhone.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.tvContactPhone),
                orderPhone));
        tvReceiverAddress.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.tvReceiveAddress),
                orderAddress));
        ivEditRight.setVisibility(View.GONE);
        tvEdit.setVisibility(View.GONE);
        tvOrderNote.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.tvNote),
                "无"));
        tvMerchantName.setText("黄焖鸡");/*接口中订单名字*/
        btDrawback.setVisibility(View.GONE);
        /*recycleview*/
        rcyPayItem.setLayoutManager(new LinearLayoutManager(this));
        rcyPayItem.setHasFixedSize(false);
        /*recycleview*/

        lyTotalCount.setVisibility(View.VISIBLE);
        tvTotalMoney.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.countMoney),
                orderPrice));
        setText(btPayOrRateOrConform);
    }

    private void setText(TextView btPayOrRateOrConform) {
        if (AppConstants.ORDER_UNPAID.equals(orderTag)) {
            btPayOrRateOrConform.setText(getResources().getString(R.string.goPay));
            return;
        }
        if (AppConstants.ORDER_DELIVERING.equals(orderTag)) {
            btPayOrRateOrConform.setText(getResources().getString(R.string.conformReceive));
            return;
        }
        if (AppConstants.ORDER_COMPLETE.equals(orderTag)
                || AppConstants.ORDER_DRAWBACK.equals(orderTag)) {
            btPayOrRateOrConform.setText(getResources().getString(R.string.goEvaluate));
        }
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }

    @OnClick({R.id.tb_left_iv, R.id.btDrawback, R.id.btPayOrRateOrConform})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
            case R.id.btDrawback:
                break;
            case R.id.btPayOrRateOrConform:
                break;
        }
    }

}
