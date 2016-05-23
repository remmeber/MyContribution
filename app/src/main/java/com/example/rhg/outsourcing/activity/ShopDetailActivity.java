package com.example.rhg.outsourcing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.MyPagerAdapter;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.fragment.ShopDetailFoodFragment;
import com.example.rhg.outsourcing.fragment.ShopDetailFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/5/20.
 */
public class ShopDetailActivity extends BaseActivity {
    ImageView ivBack;
    ImageView ivShopLogo;
    String shopLogoUrl;
    TextView tvShopName;
    SlidingTabLayout slidingTabLayout;
    ViewPager viewPager;
    String merchantName;
    String merchantId;
    String merchantPhone;
    String merchantAddress;
    String merchantNote;

    @Override
    public void dataReceive(Intent intent) {
        /*此ACTIVITY会接收上一个ACTIVITY的消息*/
        if (intent != null) {
            Log.i("RHG", "IS NOT NULL");
            Bundle bundle = intent.getExtras();
            merchantName = bundle.getString(AppConstants.KEY_MERCHANT_NAME);
            merchantId = bundle.getString(AppConstants.KEY_MERCHANT_ID);
            shopLogoUrl = bundle.getString(AppConstants.KEY_MERCHANT_LOGO);
            merchantPhone = bundle.getString(AppConstants.KEY_PHONE, "无");
            merchantAddress = bundle.getString(AppConstants.KEY_ADDRESS, "无");
            merchantNote = bundle.getString(AppConstants.KEY_NOTE, "无");
        } else {
            Log.i("RHG", "IS NULL");
            merchantName = "东大东食府";
            merchantId = "20160517";
            shopLogoUrl = "http://img2.3lian.com/2014/f2/37/d/40.jpg";
        }
    }

    @Override()
    public int getLayoutResId() {
        return R.layout.shop_detail_layout;
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_shop_detail_back);
        ivShopLogo = (ImageView) findViewById(R.id.iv_shop_detail_logo);
        tvShopName = (TextView) findViewById(R.id.tv_shop_detail_name);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_shop_detail);
        viewPager = (ViewPager) findViewById(R.id.vp_shop_detail);
    }

    @Override
    protected void initData() {
        tvShopName.setText(merchantName);
        ImageLoader.getInstance().displayImage(shopLogoUrl, ivShopLogo);
        Fragment fragment;
        List<Fragment> fragments = new ArrayList<>();
        fragment = new ShopDetailFoodFragment();
        fragments.add(fragment);
        fragment = new ShopDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.KEY_PHONE, merchantPhone);
        bundle.putString(AppConstants.KEY_ADDRESS, merchantAddress);
        bundle.putString(AppConstants.KEY_NOTE, merchantNote);
        fragment.setArguments(bundle);
        fragments.add(fragment);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments,
                AppConstants.SHOP_DETAIL_TITLES);
        viewPager.setAdapter(myPagerAdapter);
        slidingTabLayout.setViewPager(viewPager);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void showData(Object o) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_shop_detail_back:
                setResult(AppConstants.BACK);
                finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==AppConstants.DELETE){
            setResult(resultCode,data);//AppConstants.KEY_SHOPPING_CART
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(AppConstants.BACK);
        super.onBackPressed();
    }
}
