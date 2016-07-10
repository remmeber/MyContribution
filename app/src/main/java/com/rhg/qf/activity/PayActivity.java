package com.rhg.qf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.adapter.PayItemAdapter;
import com.rhg.qf.bean.PayBean;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.pay.BasePayActivity;
import com.rhg.qf.pay.model.OrderInfo;
import com.rhg.qf.pay.model.PayType;
import com.rhg.qf.utils.DpUtil;
import com.rhg.qf.utils.ToastHelper;
import com.rhg.qf.widget.RecycleViewDivider;

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
public class PayActivity extends BasePayActivity implements PayItemAdapter.PayItemClickListener {
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

    List<PayBean> payList = new ArrayList<>();
    private PayItemAdapter payItemAdapter;

    @Override
    protected OrderInfo OnOrderCreate() {
        if (PayType.WeixinPay.equals(payType)) {
            return BuildOrderInfo(null, null, null, null, null, null, null);
        }
        if (PayType.AliPay.equals(payType)) {
            return BuildOrderInfo(null, null, null, null, null, null, null);
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_layout);
        ButterKnife.bind(this);
        initData(getIntent());
    }

    protected void initData(Intent intent) {
        flTab.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        tbCenterTv.setText(getResources().getString(R.string.tvPayTitle));
        tvReceiver.setText("收货人：哈哈");
        tvReceiverPhone.setText("联系方式：00001111");
        tvReceiverAddress.setText("收货地址：江苏省南京市江宁区东南大学");

        PayBean _payBean = new PayBean();
        _payBean.setProductName("");
        _payBean.setChecked(true);
        _payBean.setProductId("1");
        _payBean.setProductPic(intent.getStringExtra(AppConstants.KEY_IMAGE));
        _payBean.setProductPrice(intent.getStringExtra(AppConstants.KEY_PRODUCT_PRICE));
        payList.add(_payBean);

        rcvItemPay.setLayoutManager(new LinearLayoutManager(this));
        rcvItemPay.setHasFixedSize(true);
        rcvItemPay.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL,
                DpUtil.dip2px(1), getResources().getColor(R.color.colorInActive)));
        payItemAdapter = new PayItemAdapter(this, payList);
        payItemAdapter.setOnPayItemClick(this);
        rcvItemPay.setAdapter(payItemAdapter);

        RegisterBasePay(null, null, null, null, null, null);
//        BuildOrderInfo()
    }

    @Override
    protected void showSuccess(String s) {
        ToastHelper.getInstance()._toast((String) s);
    }

    @Override
    protected void Warning(String s) {
        ToastHelper.getInstance()._toast(s);

    }

    @Override
    protected void showError(String s) {
        ToastHelper.getInstance()._toast((String) s);

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
                if (getCheckCount(payList) == 0) {
                    ToastHelper.getInstance()._toast("当前未选择商品！");
                    return;
                }
                Pay(v);
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
                if (PayType.WeixinPay.equals(payType))
                    return;
                ivWxpayCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_green));
                if (PayType.AliPay.equals(payType)) {
                    ivAlipayCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_uncheck_green));
                    payType = PayType.WeixinPay;
                    break;
                }
                if (PayType.Cash.equals(payType)) {
                    ivCashCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_uncheck_green));
                    payType = PayType.WeixinPay;
                }
                break;
            case R.id.iv_alipay_check:
            case R.id.iv_alipay:
                if (PayType.AliPay.equals(payType))
                    return;
                ivAlipayCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_green));

                if (PayType.WeixinPay.equals(payType)) {
                    ivWxpayCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_uncheck_green));
                    payType = PayType.AliPay;
                    break;
                }
                if (PayType.Cash.equals(payType)) {
                    ivCashCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_uncheck_green));
                    payType = PayType.AliPay;
                }
                break;
            case R.id.iv_cash_check:
            case R.id.iv_cash:
                if (PayType.Cash.equals(payType))
                    return;
                ivCashCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_green));
                if (PayType.WeixinPay.equals(payType)) {
                    ivWxpayCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_uncheck_green));
                    payType = PayType.Cash;
                    break;
                }
                if (PayType.AliPay.equals(payType)) {
                    ivAlipayCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_uncheck_green));
                    payType = PayType.Cash;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onPayItemClick(int position) {
        if (payList.get(position).isChecked()) {
            payList.get(position).setChecked(false);
        } else
            payList.get(position).setChecked(true);
        payItemAdapter.setPayList(payList);
    }

    private int getCheckCount(List<PayBean> payList) {
        int count = 0;
        for (PayBean _payBean : payList) {
            if (_payBean.isChecked())
                count++;
        }
        return count;
    }

    private List<String> getCheckItemId(List<PayBean> payList) {
        List<String> _list = new ArrayList<>();
        for (PayBean _payBean : payList) {
            if (_payBean.isChecked())
                _list.add(_payBean.getProductId());
        }
        return _list;
    }

    private int getCheckItemTotalMoney(List<PayBean> payList) {
        int count = 0;
        for (PayBean _payBean : payList) {
            if (_payBean.isChecked())
                count += Integer.valueOf(_payBean.getProductPrice());
        }
        return count;
    }
}
