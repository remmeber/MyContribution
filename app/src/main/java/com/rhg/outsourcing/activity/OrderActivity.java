package com.rhg.outsourcing.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.apapter.QFoodVpAdapter;
import com.rhg.outsourcing.constants.AppConstants;
import com.rhg.outsourcing.fragment.AllOrderFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 *desc:订单页面
 *author：remember
 *time：2016/5/28 16:14
 *email：1013773046@qq.com
 */
public class OrderActivity extends BaseActivity{
    FrameLayout tb_common;
    ImageView ivBack;
    TextView tvOrder;
    SlidingTabLayout stlOrder;
    ViewPager vpOrder;

    @Override
    protected int getLayoutResId() {
        return R.layout.myorder_layout;
    }

    @Override
    protected void initData() {
        ivBack.setOnClickListener(this);
        tb_common.setBackgroundResource(R.color.colorActiveGreen);
        tvOrder.setText("我的订单");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new AllOrderFragment());
        fragments.add(new AllOrderFragment());
        fragments.add(new AllOrderFragment());
        fragments.add(new AllOrderFragment());
        QFoodVpAdapter QFoodVpAdapter = new QFoodVpAdapter(getSupportFragmentManager(),fragments,
                AppConstants.ORDER_TITLES,
                vpOrder,stlOrder);
        /*vpOrder.setAdapter(QFoodVpAdapter);
        stlOrder.setViewPager(vpOrder);*/
    }
    @Override
    protected void initView(View view) {
        tb_common =(FrameLayout)findViewById(R.id.fl_tab);
        ivBack = (ImageView)findViewById(R.id.tb_left_iv);
        tvOrder = (TextView)findViewById(R.id.tb_center_tv);
        stlOrder = (SlidingTabLayout)findViewById(R.id.stl_myorder);
        vpOrder = (ViewPager)findViewById(R.id.vp_myorder);
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tb_left_iv:
                //TODO do back
                finish();
                break;
            default:
                break;
        }
    }
}
