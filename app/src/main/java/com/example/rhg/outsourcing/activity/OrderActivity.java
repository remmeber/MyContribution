package com.example.rhg.outsourcing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.MyPagerAdapter;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.fragment.AllOrderFragment;
import com.example.rhg.outsourcing.view.BaseView;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/5/16.
 */
public class OrderActivity extends AppCompatActivity implements BaseView, View.OnClickListener{
    ImageView ivBack;
    TextView tvOrder;
    SlidingTabLayout stlOrder;
    ViewPager vpOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorder_layout);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData() {
        tvOrder.setText("我的订单");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new AllOrderFragment());
        fragments.add(new AllOrderFragment());
        fragments.add(new AllOrderFragment());
        fragments.add(new AllOrderFragment());
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),fragments, AppConstants.ORDERTITLES);
        vpOrder.setAdapter(myPagerAdapter);
        stlOrder.setViewPager(vpOrder);
    }

    private void initView() {
        ivBack = (ImageView)findViewById(R.id.iv_tab_left);
        tvOrder = (TextView)findViewById(R.id.tv_center);
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
