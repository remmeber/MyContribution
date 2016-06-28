package com.rhg.qf.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.rhg.qf.R;
import com.rhg.qf.apapter.QFoodVpAdapter;
import com.rhg.qf.constants.AppConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * desc:所有店铺fm
 * author：remember
 * time：2016/5/28 16:47
 * email：1013773046@qq.com
 */
public class SellerFragment extends BaseFragment {
    private static final String TAG = "SellerFragment";
    List<Fragment> fragments = new ArrayList<Fragment>();

    @Bind(R.id.tb_center_tv)
    TextView tbCenterTv;
    @Bind(R.id.tb_right_iv)
    ImageView tbRightIv;
    @Bind(R.id.fl_tab)
    FrameLayout flTab;
    @Bind(R.id.all_seller_tl)
    SlidingTabLayout allSellerTl;
    @Bind(R.id.sellerViewPager)
    ViewPager sellerViewPager;


    //-----------------根据需求创建相应的presenter----------------------------------------------------

    //----------------------------------------------------------------------------------------------

    public SellerFragment() {

        fragments.add(new BySellNumberFm());
        fragments.add(new ByDistanceFm());
        fragments.add(new ByRateFm());
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
        return R.layout.all_shop_home_layout;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData() {
        tbCenterTv.setText(getResources().getString(R.string.allStore));
        flTab.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
        tbRightIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_search_black));
        QFoodVpAdapter qFoodVpAdapter = new QFoodVpAdapter(getChildFragmentManager(), fragments,
                AppConstants.SELL_TITLES);

        sellerViewPager.setAdapter(qFoodVpAdapter);
        sellerViewPager.setOffscreenPageLimit(2);
        allSellerTl.setViewPager(sellerViewPager);
        /*qFoodVpAdapter.setOnExtraPageChangeListener(new QFoodVpAdapter.OnExtraPageChangeListener() {
            @Override
            public void onExtraPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onExtraPageSelected(int currentPage, int lastPage) {
                switch (currentPage) {
                    case 0:
                        ((BySellNumberFm) fragments.get(currentPage)).setContext(getContext());
                        ((ByDistanceFm) fragments.get(1)).setContext(null);
                        ((ByRateFm) fragments.get(2)).setContext(null);
                        break;
                    case 1:
                        ((BySellNumberFm) fragments.get(0)).setContext(null);
                        ((ByDistanceFm) fragments.get(currentPage)).setContext(getContext());
                        ((ByRateFm) fragments.get(2)).setContext(null);
                        break;
                    case 2:
                        ((BySellNumberFm) fragments.get(0)).setContext(null);
                        ((ByDistanceFm) fragments.get(1)).setContext(null);
                        ((ByRateFm) fragments.get(currentPage)).setContext(getContext());
                        break;
                }
            }

            @Override
            public void onExtraPageScrollStateChanged(int i) {

            }
        });
*/
        sellerViewPager.setAdapter(qFoodVpAdapter);
        sellerViewPager.setOffscreenPageLimit(3);
        allSellerTl.setViewPager(sellerViewPager);
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
            sellerViewPager.setCurrentItem(savedInstanceState.getInt("position"));
    }

    @OnClick(R.id.tb_right_iv)
    public void onClick() {
        doSearch();
    }

    private void doSearch() {
    }
}
