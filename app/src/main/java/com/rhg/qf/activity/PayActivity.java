package com.rhg.qf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.adapter.PayItemAdapter;
import com.rhg.qf.application.InitApplication;
import com.rhg.qf.bean.NewOrderBean;
import com.rhg.qf.bean.PayModel;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.mvp.presenter.ModifyOrderPresenter;
import com.rhg.qf.mvp.presenter.NewOrderPresenter;
import com.rhg.qf.mvp.view.BaseView;
import com.rhg.qf.pay.BasePayActivity;
import com.rhg.qf.pay.model.OrderInfo;
import com.rhg.qf.pay.model.PayType;
import com.rhg.qf.utils.AccountUtil;
import com.rhg.qf.utils.DpUtil;
import com.rhg.qf.utils.NetUtil;
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
public class PayActivity extends BasePayActivity implements PayItemAdapter.PayItemClickListener,
        BaseView {
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

    ArrayList<PayModel.PayBean> payList = new ArrayList<>();
    NewOrderBean newOrderBean;
    private PayItemAdapter payItemAdapter;
    NewOrderPresenter createOrderPresenter;
    ModifyOrderPresenter modifyOrderDeliveringPresenter;/*修改用户订单状态*/
    String ipv4;
    String tradeNumber;
    String style;//用来表示是否需要生成订单
    String orderId;


    @Override
    protected OrderInfo OnOrderCreate() {
        ipv4 = NetUtil.getPsdnIp();
        if (PayType.WeixinPay.equals(payType)) {
            return BuildOrderInfo("微信支付", "30m", "www.baidu.com",
                    getOutTradeNo(),
                    getItemsName(payList),
                    String.valueOf(getCheckItemTotalMoney(payList)),
                    ipv4);
        }
        if (PayType.AliPay.equals(payType)) {
            return BuildOrderInfo("支付宝支付", "30m", "http://notify.msp.hk/notify.htm",
                    getOutTradeNo(),
                    getItemsName(payList),
                    String.valueOf(getCheckItemTotalMoney(payList)),
                    ipv4);
        }
        return null;
    }

    private NewOrderBean generateOrder() {
        NewOrderBean _orderBean = new NewOrderBean();
        _orderBean.setReceiver(tvReceiver.getText().toString());
        _orderBean.setPhone(tvReceiverPhone.getText().toString());
        _orderBean.setAddress(tvReceiverAddress.getText().toString());
        _orderBean.setFood(getCheckedFood(payList));
        _orderBean.setClient(AccountUtil.getInstance().getUserID());
        _orderBean.setPrice(String.valueOf(getCheckItemTotalMoney(payList)));
        return _orderBean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_layout);
        ButterKnife.bind(this);
        initData(getIntent());
    }

    protected void initData(Intent intent) {
        tbCenterTv.setText(getResources().getString(R.string.tvPayTitle));
        flTab.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        PayModel payModel = intent.getParcelableExtra(AppConstants.KEY_PARCELABLE);
        style = intent.getStringExtra(AppConstants.ORDER_STYLE);
        if (payModel != null) {
            tvReceiver.setText(payModel.getReceiver());
            tvReceiverPhone.setText(payModel.getPhone());
            tvReceiverAddress.setText(payModel.getAddress());
            payList.addAll(payModel.getPayBeanList());
        }

        rcvItemPay.setLayoutManager(new LinearLayoutManager(this));
        rcvItemPay.setHasFixedSize(true);
        rcvItemPay.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL,
                DpUtil.dip2px(1), getResources().getColor(R.color.colorInActive)));
        payItemAdapter = new PayItemAdapter(this, payList);
        payItemAdapter.setOnPayItemClick(this);
        rcvItemPay.setAdapter(payItemAdapter);

        RegisterBasePay(/*KeyLibs.ali_partner, KeyLibs.ali_sellerId, KeyLibs.ali_privateKey,*/
                InitApplication.WXID, "10000100", null);
//        BuildOrderInfo()
    }

    /*支付成功的回调*/
    @Override
    protected void showSuccess(String s) {
        ToastHelper.getInstance()._toast(s);
        if (modifyOrderDeliveringPresenter == null) {
            modifyOrderDeliveringPresenter = new ModifyOrderPresenter(this);
        }
        modifyOrderDeliveringPresenter.modifyUserOrDeliverOrderState(getProductIdList(payList),
                AppConstants.ORDER_DELIVERING);//修改为配送中的状态
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
                newOrderBean = generateOrder();
                if (style == null) {
                    if (createOrderPresenter == null)
                        createOrderPresenter = new NewOrderPresenter(this);
                    createOrderPresenter.createNewOrder(newOrderBean);
                } else
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
    public void onPayItemClick(int position) {
        if (payList.get(position).isChecked()) {
            payList.get(position).setChecked(false);
        } else
            payList.get(position).setChecked(true);
        payItemAdapter.setPayList(payList);
    }

    private int getCheckCount(List<PayModel.PayBean> payList) {
        int count = 0;
        for (PayModel.PayBean _payBean : payList) {
            if (_payBean.isChecked())
                count++;
        }
        return count;
    }

    private List<NewOrderBean.FoodBean> getCheckedFood(List<PayModel.PayBean> payList) {
        List<NewOrderBean.FoodBean> _bean = new ArrayList<>();
        for (PayModel.PayBean _payBean : payList) {
            if (_payBean.isChecked()) {
                NewOrderBean.FoodBean foodBean = new NewOrderBean.FoodBean();
                foodBean.setID(_payBean.getProductId());
                foodBean.setNum(_payBean.getProductNumber());
                _bean.add(foodBean);
            }
        }
        return _bean;
    }

    private List<String> getProductIdList(List<PayModel.PayBean> payList) {
        List<String> _bean = new ArrayList<>();
        for (PayModel.PayBean _payBean : payList) {
            _bean.add(_payBean.getProductId());
        }
        return _bean;
    }

    private int getCheckItemTotalMoney(List<PayModel.PayBean> payList) {
        int count = 0;
        for (PayModel.PayBean _payBean : payList) {
            if (_payBean.isChecked())
                count += Integer.valueOf(_payBean.getProductPrice()) * Integer.valueOf(_payBean.getProductNumber());
        }
        return count;
    }

    private String getItemsName(List<PayModel.PayBean> payList) {
        String concatName = "";
        for (PayModel.PayBean _payBean : payList) {
            concatName = concatName.concat(_payBean.getProductName());
        }
        return concatName;
    }

    @Override
    public void showData(Object o) {
        if (o instanceof String)
            if (!"error".equals(o)) {
                tradeNumber = (String) o;
                Log.i("RHG", "tradeNumber is :" + tradeNumber);
                Pay(null);
            } else
                ToastHelper.getInstance()._toast((String) o);

    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private String getOutTradeNo() {
        /*SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;*/
        return tradeNumber;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
