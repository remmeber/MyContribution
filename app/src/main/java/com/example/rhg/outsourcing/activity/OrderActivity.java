package com.example.rhg.outsourcing.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.MyPagerAdapter;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.fragment.AllOrderFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/5/16.
 */
public class OrderActivity extends BaseActivity{
    FrameLayout tb_common;
    ImageView ivBack;
    TextView tvOrder;
    SlidingTabLayout stlOrder;
    ViewPager vpOrder;

    @Override
    public int getLayoutResId() {
        return R.layout.myorder_layout;
    }

    @Override
    protected void initData() {
        tb_common.setBackgroundResource(R.color.colorActiveGreen);
        tvOrder.setText("我的订单");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new AllOrderFragment());
        fragments.add(new AllOrderFragment());
        fragments.add(new AllOrderFragment());
        fragments.add(new AllOrderFragment());
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),fragments, AppConstants.ORDER_TITLES);
        vpOrder.setAdapter(myPagerAdapter);
        stlOrder.setViewPager(vpOrder);
    }
    @Override
    protected void initView() {
        tb_common =(FrameLayout)findViewById(R.id.fl_tab);
        ivBack = (ImageView)findViewById(R.id.iv_tab_left);
        tvOrder = (TextView)findViewById(R.id.tv_tab_center);
        stlOrder = (SlidingTabLayout)findViewById(R.id.stl_myorder);
        vpOrder = (ViewPager)findViewById(R.id.vp_myorder);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void showData(Object o) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_tab_left:
                //TODO do back
                finish();
                break;
            default:
                break;
        }
    }
}
