package com.rhg.qf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rhg.qf.R;
import com.rhg.qf.adapter.QFoodVpAdapter;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.fragment.ShopDetailFoodFragment;
import com.rhg.qf.fragment.ShopDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * desc:店铺详情页面
 * author：remember
 * time：2016/5/28 16:15
 * email：1013773046@qq.com
 */
public class ShopDetailActivity extends BaseFragmentActivity {

    @Bind(R.id.iv_shop_detail_logo)
    ImageView ivShopLogo;
    @Bind(R.id.tv_shop_detail_name)
    TextView tvShopName;
    @Bind(R.id.stl_shop_detail)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.vp_shop_detail)
    ViewPager viewPager;
    @Bind(R.id.tb_center_tv)
    TextView tbCenterTv;
    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.fl_tab)
    FrameLayout flTab;

    String shopLogoUrl;
    String merchantName;
    String merchantId;
    String merchantPhone;
    String merchantAddress;
    String merchantNote;
    Bundle bundle;

    @Override
    public void dataReceive(Intent intent) {
        /*此ACTIVITY会接收上一个ACTIVITY的消息*/
        if (intent != null) {
            bundle = intent.getExtras();
            merchantName = bundle.getString(AppConstants.KEY_MERCHANT_NAME);
            merchantId = bundle.getString(AppConstants.KEY_MERCHANT_ID, "");
            shopLogoUrl = bundle.getString(AppConstants.KEY_MERCHANT_LOGO);

            merchantPhone = bundle.getString(AppConstants.KEY_OR_SP_PHONE, "无");
            merchantAddress = bundle.getString(AppConstants.KEY_ADDRESS, "无");
            merchantNote = bundle.getString(AppConstants.KEY_NOTE, "无");
        } else {
            merchantName = "";
            merchantId = "";
            shopLogoUrl = "http://img2.3lian.com/2014/f2/37/d/40.jpg";
        }
    }

    @Override()
    protected int getLayoutResId() {
        return R.layout.shop_detail_layout;
    }


    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData() {
        tbCenterTv.setText("店铺详情");
        tbLeftIv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.chevron_left_black));
        flTab.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlueNormal));
        tvShopName.setText(merchantName);
        ImageLoader.getInstance().displayImage(shopLogoUrl, ivShopLogo);
        Fragment fragment;
        Bundle bundle = new Bundle();
        List<Fragment> fragments = new ArrayList<>();
        fragment = new ShopDetailFoodFragment();
        bundle.putString(AppConstants.KEY_MERCHANT_ID, merchantId);
        bundle.putString(AppConstants.KEY_MERCHANT_NAME, merchantName);
        fragment.setArguments(bundle);
        fragments.add(fragment);
        fragment = new ShopDetailFragment();
        fragment.setArguments(bundle);
        fragments.add(fragment);
        QFoodVpAdapter qFoodVpAdapter = new QFoodVpAdapter(getSupportFragmentManager(), fragments,
                AppConstants.SHOP_DETAIL_TITLES);
        viewPager.setAdapter(qFoodVpAdapter);
        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }

    @OnClick(R.id.tb_left_iv)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb_left_iv:
                bundle = null;
                setResult(AppConstants.BACK_WITHOUT_DATA);
                finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AppConstants.BACK_WITH_DELETE) {
            setResult(resultCode, data);//AppConstants.KEY_BACK_2_SHOPPING_CART
            finish();
            return;
        }
        if (resultCode == AppConstants.BACK_WITHOUT_DATA) {
            /*setResult(resultCode);
            finish();*/
        }
    }

    @Override
    public void onBackPressed() {
        setResult(AppConstants.BACK_WITHOUT_DATA);
        bundle = null;
        super.onBackPressed();
    }

}
