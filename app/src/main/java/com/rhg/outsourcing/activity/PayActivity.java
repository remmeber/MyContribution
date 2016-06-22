package com.rhg.outsourcing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.bean.PayContentBean;
import com.rhg.outsourcing.utils.DpUtil;
import com.rhg.outsourcing.utils.ToastHelper;
import com.rhg.outsourcing.widget.PayDescriptionView;
import com.rhg.outsourcing.widget.PayDialog;
import com.rhg.outsourcing.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:付款页面 todo 跳到付款页面的数据都要有一个标志：来自购物车还是待付款页面。如果来自购物车，则要去掉购物车
 * 中的商品，标记为待付款或者完成；如果是来自待付款，则保留待付款或者完成；
 * author：remember
 * time：2016/5/28 16:14
 * email：1013773046@qq.com
 */
public class PayActivity extends BaseActivity implements View.OnClickListener{
    ImageView ivBack;
    TextView tvCenter;
    FrameLayout flTab;

    TextView tvReceiver;
    TextView tvReceiverPhone;
    TextView tvReceiverAddress;
    ImageView ivEdit;

    RecyclerView rcvPayItem;
    Button flPay;

    LinearLayout llPay;
    PayDescriptionView payDescriptionView;
    List<PayContentBean> payContentBeanList;

    public PayActivity() {
        payContentBeanList = new ArrayList<>();

    }

    @Override
    public void dataReceive(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            //// TODO: 接收要付款的订单
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.pay_layout;
    }

    @Override
    protected void initView(View view) {
        ivBack = (ImageView) findViewById(R.id.tb_left_iv);
        tvCenter = (TextView) findViewById(R.id.tb_center_tv);
        flTab = (FrameLayout) findViewById(R.id.fl_tab);

        tvReceiver = (TextView) findViewById(R.id.tv_receiver);
        tvReceiverPhone = (TextView) findViewById(R.id.tv_receiver_phone);
        tvReceiverAddress = (TextView) findViewById(R.id.tv_receiver_address);
        ivEdit = (ImageView) findViewById(R.id.iv_edit_right);

        rcvPayItem = (RecyclerView) findViewById(R.id.rcv_item_pay);
        flPay = (Button) findViewById(R.id.bt_pay_affirmance);
        llPay = (LinearLayout) findViewById(R.id.ll_pay);
        payDescriptionView = (PayDescriptionView) findViewById(R.id.pay_content);
    }

    @Override
    protected void initData() {
        flTab.setBackgroundColor(getResources().getColor(R.color.colorActiveGreen));
        ivBack.setOnClickListener(this);
        ivBack.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        tvCenter.setText(getResources().getString(R.string.tvPayTitle));
        tvReceiver.setText("收货人：哈哈");
        tvReceiverPhone.setText("联系方式：00001111");
        tvReceiverAddress.setText("收货地址：江苏省南京市江宁区东南大学");
        ivEdit.setOnClickListener(this);

        rcvPayItem.setLayoutManager(new LinearLayoutManager(this));
        rcvPayItem.setHasFixedSize(true);
        rcvPayItem.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL,
                DpUtil.dip2px(1), getResources().getColor(R.color.colorInActive)));

        flPay.setOnClickListener(this);

    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
            case R.id.iv_edit_right:
                ToastHelper.getInstance()._toast("编辑");
                break;
            case R.id.bt_pay_affirmance:

                payContentBeanList.clear();
                for (int i = 0; i < 3; i++) {
                    PayContentBean payContentBean = new PayContentBean();
                    payContentBean.setGoodsName("哈啊哈" + i);
                    payContentBean.setGoodsDescription("好吃哦");
                    payContentBean.setPayMoney("" + i * 2);
                    payContentBeanList.add(payContentBean);
                }
                PayDialog payDialog = new PayDialog(this, payContentBeanList);
                payDialog.show();
                break;
        }
    }


}
