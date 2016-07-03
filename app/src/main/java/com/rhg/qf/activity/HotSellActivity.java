package com.rhg.qf.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.rhg.qf.R;
import com.rhg.qf.apapter.QFoodVpAdapter;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.fragment.HotFoodDistanceFm;
import com.rhg.qf.fragment.HotFoodRateFm;
import com.rhg.qf.fragment.HotFoodSellNumberFm;
import com.rhg.qf.fragment.OverallHotFoodFm;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * desc:热销单品
 * author：remember
 * time：2016/6/19 13:08
 * email：1013773046@qq.com
 */
public class HotSellActivity extends BaseActivity {

    @Bind(R.id.tb_center_tv)
    TextView tbTitle;
    @Bind(R.id.tb_left_iv)
    ImageView tbBack;
    @Bind(R.id.tb_right_iv)
    ImageView tbSearch;
    @Bind(R.id.tb_right_tv)
    TextView tbRightTv;
    @Bind(R.id.fl_tab)
    FrameLayout flTab;
    @Bind(R.id.hot_sell_tl)
    SlidingTabLayout tlHolSell;
    @Bind(R.id.hot_sell_vp)
    ViewPager vpHotSell;

    List<Fragment> fragments = new ArrayList<>();

    public HotSellActivity() {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.hot_sell_layout;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        fragments.add(new OverallHotFoodFm());
        fragments.add(new HotFoodDistanceFm());
        fragments.add(new HotFoodSellNumberFm());
        fragments.add(new HotFoodRateFm());
        tbBack.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        flTab.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
        tbTitle.setText("热销单品");
        tbRightTv.setVisibility(View.GONE);
        tbSearch.setImageDrawable(getResources().getDrawable(R.drawable.ic_search_black));
        vpHotSell.setOffscreenPageLimit(3);
        QFoodVpAdapter mAdapter = new QFoodVpAdapter(getSupportFragmentManager(), fragments,
                AppConstants.HOT_SELL_TITLES);
        vpHotSell.setAdapter(mAdapter);
        tlHolSell.setViewPager(vpHotSell);
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }


    @OnClick({R.id.tb_left_iv, R.id.tb_right_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
            case R.id.tb_right_iv:
                doSearch();
                break;
        }
    }

    private void doSearch() {
    }
}
