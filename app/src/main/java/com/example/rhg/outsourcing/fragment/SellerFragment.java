package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.QFoodVpAdapter;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.impl.SearchListener;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;


/**
 * desc:所有店铺fm
 * author：remember
 * time：2016/5/28 16:47
 * email：1013773046@qq.com
 */
public class SellerFragment extends SuperFragment implements View.OnClickListener {
    private static final String TAG = "SellerFragment";
    List<Fragment> fragments = new ArrayList<Fragment>();

    FrameLayout fl_tab;
    TextView tbCenterTV;
    LinearLayout tbRightLL;
    ImageView tbRightIV;

    ViewPager viewPager;
    SlidingTabLayout tabLayout;

    SearchListener searchListener;

    public void setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }

    //-----------------根据需求创建相应的presenter----------------------------------------------------

    //----------------------------------------------------------------------------------------------


    public SellerFragment() {
        fragments.add(new BySellNumberFragment());
        fragments.add(new ByDistanceFragment());
        fragments.add(new ByRateFragment());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden)
            Log.i("RHG", "SELLER:hide");
        else
            Log.i("RHG", "SELLER:show");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.seller_viewpager_layout;
    }

    @Override
    protected void initView(View view) {
        fl_tab = (FrameLayout) view.findViewById(R.id.fl_tab);
        tbCenterTV = (TextView) view.findViewById(R.id.tb_center_tv);
        tbRightLL = (LinearLayout) view.findViewById(R.id.tb_right_ll);
        tbRightIV = (ImageView) view.findViewById(R.id.tb_right_iv);
        tabLayout = (SlidingTabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.sellerViewPager);
    }

    @Override
    protected void initData() {
        tbCenterTV.setText(getResources().getString(R.string.allstore));
        fl_tab.setBackgroundColor(getResources().getColor(R.color.colorActiveGreen));
        tbRightLL.setOnClickListener(this);
        QFoodVpAdapter qFoodVpAdapter = new QFoodVpAdapter(getChildFragmentManager(), fragments,
                AppConstants.SELL_TITLES, viewPager, tabLayout);
        qFoodVpAdapter.setOnExtraPageChangeListener(new QFoodVpAdapter.OnExtraPageChangeListener() {
            @Override
            public void onExtraPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onExtraPageSelected(int currentPage, int lastPage) {
                switch (currentPage) {
                    case 0:
                        ((BySellNumberFragment) fragments.get(currentPage)).setContext(getContext());
                        ((ByDistanceFragment) fragments.get(1)).setContext(null);
                        ((ByRateFragment) fragments.get(2)).setContext(null);
                        break;
                    case 1:
                        ((BySellNumberFragment) fragments.get(0)).setContext(null);
                        ((ByDistanceFragment) fragments.get(currentPage)).setContext(getContext());
                        ((ByRateFragment) fragments.get(2)).setContext(null);
                        break;
                    case 2:
                        ((BySellNumberFragment) fragments.get(0)).setContext(null);
                        ((ByDistanceFragment) fragments.get(1)).setContext(null);
                        ((ByRateFragment) fragments.get(currentPage)).setContext(getContext());
                        break;
                }
            }

            @Override
            public void onExtraPageScrollStateChanged(int i) {

            }
        });
        /*viewPager.setAdapter(qFoodVpAdapter);
        tabLayout.setViewPager(viewPager);*/
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null)
            viewPager.setCurrentItem(savedInstanceState.getInt("position"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb_right_ll:
                searchListener.doSearch();
                break;
        }
    }
}
