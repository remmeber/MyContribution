package com.rhg.outsourcing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rhg.outsourcing.R;
import com.rhg.outsourcing.apapter.QFoodVpAdapter;
import com.rhg.outsourcing.constants.AppConstants;
import com.rhg.outsourcing.fragment.ShopDetailFoodFragment;
import com.rhg.outsourcing.fragment.ShopDetailFragment;

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
public class ShopDetailActivity extends BaseActivity {

    @Bind(R.id.iv_shop_detail_logo)
    ImageView ivShopLogo;
    @Bind(R.id.iv_shop_detail_back)
    ImageView ivBack;
    @Bind(R.id.tv_shop_detail_name)
    TextView tvShopName;
    @Bind(R.id.stl_shop_detail)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.vp_shop_detail)
    ViewPager viewPager;

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
            merchantName = "东大东食府";
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
        Bundle bundle = new Bundle();
        List<Fragment> fragments = new ArrayList<>();
        fragment = new ShopDetailFoodFragment();
        bundle.putString(AppConstants.KEY_MERCHANT_ID, merchantId);
        fragment.setArguments(bundle);
        fragments.add(fragment);
        fragment = new ShopDetailFragment();
        bundle = new Bundle();
        bundle.putString(AppConstants.KEY_OR_SP_PHONE, merchantPhone);
        bundle.putString(AppConstants.KEY_ADDRESS, merchantAddress);
        bundle.putString(AppConstants.KEY_NOTE, merchantNote);
        fragment.setArguments(bundle);
        fragments.add(fragment);
        QFoodVpAdapter QFoodVpAdapter = new QFoodVpAdapter(getSupportFragmentManager(), fragments,
                AppConstants.SHOP_DETAIL_TITLES, viewPager, slidingTabLayout);
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }

    @OnClick(R.id.iv_shop_detail_back)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_shop_detail_back:
                bundle = null;
                setResult(AppConstants.BACK_WITHOUT_DATA);
                finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AppConstants.BACK_WITH_DELETE) {
            setResult(resultCode, data);//AppConstants.KEY_SHOPPING_CART
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
