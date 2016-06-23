package com.rhg.outsourcing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.bean.PayContentBean;
import com.rhg.outsourcing.utils.DpUtil;
import com.rhg.outsourcing.utils.ToastHelper;
import com.rhg.outsourcing.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc:付款页面 todo 跳到付款页面的数据都要有一个标志：来自购物车还是待付款页面。如果来自购物车，则要去掉购物车
 * 中的商品，标记为待付款或者完成；如果是来自待付款，则保留待付款或者完成；
 * author：remember
 * time：2016/5/28 16:14
 * email：1013773046@qq.com
 */
public class PayActivity extends BaseActivity {
    List<PayContentBean> payContentBeanList;

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
    @Bind(R.id.rcv_item_pay)
    RecyclerView rcvItemPay;
    @Bind(R.id.iv_wepay_check)
    ImageView ivWxpayCheck;
    @Bind(R.id.iv_alipay_check)
    ImageView ivAlipayCheck;
    @Bind(R.id.iv_cash_check)
    ImageView ivCashCheck;

    private static final String WECHAT = "wechat";
    private static final String ALIPAY = "alipay";
    private static final String CASH = "cash";
    String payMethod = WECHAT;

    public PayActivity() {
        payContentBeanList = new ArrayList<>();

    }

    @Override
    public void dataReceive(Intent intent) {
        if (intent != null) {
             /*TODO: 接收要付款的订单*/
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.pay_layout;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData() {
        flTab.setBackgroundColor(getResources().getColor(R.color.colorActiveGreen));
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        tbCenterTv.setText(getResources().getString(R.string.tvPayTitle));
        tvReceiver.setText("收货人：哈哈");
        tvReceiverPhone.setText("联系方式：00001111");
        tvReceiverAddress.setText("收货地址：江苏省南京市江宁区东南大学");

        rcvItemPay.setLayoutManager(new LinearLayoutManager(this));
        rcvItemPay.setHasFixedSize(true);
        rcvItemPay.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL,
                DpUtil.dip2px(1), getResources().getColor(R.color.colorInActive)));
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }

    @OnClick({R.id.tb_left_iv, R.id.iv_edit_right, R.id.bt_pay_affirmance,
            R.id.iv_wepay_check, R.id.iv_wepay, R.id.iv_alipay_check, R.id.iv_alipay,
            R.id.iv_cash_check, R.id.iv_cash})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
            case R.id.iv_edit_right:
                ToastHelper.getInstance()._toast("编辑");
                break;
            case R.id.bt_pay_affirmance:
                ToastHelper.getInstance()._toast("支付");
                /*payContentBeanList.clear();
                for (int i = 0; i < 3; i++) {
                    PayContentBean payContentBean = new PayContentBean();
                    payContentBean.setGoodsName("哈啊哈" + i);
                    payContentBean.setGoodsDescription("好吃哦");
                    payContentBean.setPayMoney("" + i * 2);
                    payContentBeanList.add(payContentBean);
                }
                PayDialog payDialog = new PayDialog(this, payContentBeanList);
                payDialog.show();*/
                break;
            case R.id.iv_wepay_check:
            case R.id.iv_wepay:
                if (WECHAT.equals(payMethod))
                    return;
                ivWxpayCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_green));
                break;
            case R.id.iv_alipay_check:
            case R.id.iv_alipay:
                ivAlipayCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_green));
                break;
            case R.id.iv_cash_check:
            case R.id.iv_cash:
                ivCashCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_green));
                break;
        }
        if (WECHAT.equals(payMethod))
            ivWxpayCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_uncheck_green));
        if (ALIPAY.equals(payMethod))
            ivAlipayCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_uncheck_green));
        if (CASH.equals(payMethod))
            ivCashCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_uncheck_green));
    }
}
