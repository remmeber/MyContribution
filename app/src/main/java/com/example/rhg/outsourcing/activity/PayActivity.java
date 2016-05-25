package com.example.rhg.outsourcing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.ExpandableListViewAdapter;
import com.example.rhg.outsourcing.model.PayContent;
import com.example.rhg.outsourcing.utils.ToastHelper;
import com.example.rhg.outsourcing.view.PayDialog;
import com.example.rhg.outsourcing.widget.PayDescriptionView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/5/22.
 * 付款页面 todo 跳到付款页面的数据都要有一个标志：来自购物车还是待付款页面。如果来自购物车，则要去掉购物车
 * 中的商品，标记为待付款或者完成；如果是来自待付款，则保留待付款或者完成；
 */
public class PayActivity extends BaseActivity {
    ImageView ivBack;
    TextView tvCenter;
    FrameLayout flTab;

    TextView tvReceiver;
    TextView tvReceiverPhone;
    TextView tvReceiverAddress;
    ImageView ivEdit;

    ExpandableListViewAdapter expandableListViewAdapter;
    ExpandableListView expandableListView;
    FrameLayout flPay;

    LinearLayout llPay;
    PayDescriptionView payDescriptionView;

    List<PayContent> payContentList;

    public PayActivity() {
        expandableListViewAdapter = new ExpandableListViewAdapter(this);
        payContentList = new ArrayList<>();

    }

    @Override
    public void dataReceive(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            //// TODO: 接收要付款的订单
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.pay_layout;
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_tab_left);
        tvCenter = (TextView) findViewById(R.id.tv_tab_center);
        flTab = (FrameLayout) findViewById(R.id.fl_tab);

        tvReceiver = (TextView) findViewById(R.id.tv_receiver);
        tvReceiverPhone = (TextView) findViewById(R.id.tv_receiver_phone);
        tvReceiverAddress = (TextView) findViewById(R.id.tv_receiver_address);
        ivEdit = (ImageView) findViewById(R.id.iv_edit_right);

        expandableListView = (ExpandableListView) findViewById(R.id.elv_pay);
        flPay = (FrameLayout) findViewById(R.id.fl_pay);
        llPay = (LinearLayout) findViewById(R.id.ll_pay);
        payDescriptionView = (PayDescriptionView) findViewById(R.id.pay_content);
    }

    @Override
    protected void initData() {
        flTab.setBackgroundColor(getResources().getColor(R.color.colorActiveGreen));
        ivBack.setOnClickListener(this);
        tvCenter.setText(getResources().getString(R.string.tvPayTitle));
        tvReceiver.setText("收货人：哈哈");
        tvReceiverPhone.setText("联系方式：00001111");
        tvReceiverAddress.setText("收货地址：江苏省南京市江宁区东南大学");
        ivEdit.setOnClickListener(this);
        expandableListView.setGroupIndicator(null);
        expandableListView.setAdapter(expandableListViewAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO: 记录父id和子Id,临时保存选中的商品
                return false;
            }
        });
        flPay.setOnClickListener(this);

    }

    @Override
    public void showData(Object o) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_tab_left:
                finish();
                break;
            case R.id.iv_edit_right:
                ToastHelper.getInstance()._toast("编辑");
                break;
            case R.id.fl_pay:

                payContentList.clear();
                for (int i = 0; i < 3; i++) {
                    PayContent payContent = new PayContent();
                    payContent.setGoodsName("哈啊哈" + i);
                    payContent.setGoodsDescription("好吃哦");
                    payContent.setPayMoney("" + i * 2);
                    payContentList.add(payContent);
                }
                PayDialog payDialog = new PayDialog(this,payContentList);
                payDialog.show();
                break;
        }
    }
}
