package com.example.rhg.outsourcing.apapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rhg.outsourcing.constants.AppConstants;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private String[] titles;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }


}