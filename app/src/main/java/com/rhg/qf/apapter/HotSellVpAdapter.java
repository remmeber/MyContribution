package com.rhg.qf.apapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * desc:热销单品vp适配器
 * author：remember
 * time：2016/6/19 13:49
 * email：1013773046@qq.com
 */
public class HotSellVpAdapter extends FragmentPagerAdapter {
    String[] mTitles;
    Fragment[] fragments;

    public HotSellVpAdapter(FragmentManager fm, String[] mTitles, Fragment[] fragments) {
        super(fm);
        this.mTitles = mTitles;
        this.fragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.length == 0 ? "" : mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.length == 0 ? null : fragments[position];
    }

    @Override
    public int getCount() {
        return mTitles.length == 0 ? 0 : mTitles.length;
    }
}
