package com.rhg.qf.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.rhg.qf.R;
import com.rhg.qf.adapter.QFoodVpAdapter;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.fragment.OrderCompleteFm;
import com.rhg.qf.fragment.OrderDeliveringFm;
import com.rhg.qf.fragment.OrderDrawbackFm;
import com.rhg.qf.fragment.OrderUnPaidFM;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * desc:订单页面
 * author：remember
 * time：2016/5/28 16:14
 * email：1013773046@qq.com
 */
public class OrderListActivity extends BaseFragmentActivity {
    @Bind(R.id.tb_center_tv)
    TextView tbCenterTv;
    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.fl_tab)
    FrameLayout flTab;
    @Bind(R.id.stl_myorder)
    SlidingTabLayout stlMyorder;
    @Bind(R.id.vp_myorder)
    ViewPager vpMyorder;

    @Override
    protected int getLayoutResId() {
        return R.layout.myorder_layout;
    }

    @Override
    protected void initData() {
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        flTab.setBackgroundResource(R.color.colorGreenNormal);
        tbCenterTv.setText(getResources().getString(R.string.myOrder));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new OrderUnPaidFM());
        fragments.add(new OrderDeliveringFm());
        fragments.add(new OrderCompleteFm());
        fragments.add(new OrderDrawbackFm());
        QFoodVpAdapter qFoodVpAdapter = new QFoodVpAdapter(getSupportFragmentManager(), fragments,
                AppConstants.ORDER_TITLES);
        vpMyorder.setAdapter(qFoodVpAdapter);
        vpMyorder.setOffscreenPageLimit(3);
        stlMyorder.setViewPager(vpMyorder);

    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }

    @OnClick(R.id.tb_left_iv)
    public void onClick() {
        finish();
    }
}
