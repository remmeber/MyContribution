package com.rhg.outsourcing.apapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

/**
 * desc:ViewPager统一适配器
 * author：remember
 * time：2016/5/28 16:20
 * email：1013773046@qq.com
 */
public class QFoodVpAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private List<Fragment> fragmentList;
    private String[] titles;
    private OnExtraPageChangeListener onExtraPageChangeListener; // ViewPager切换页面时的额外功能添加
    private int currentPage = 0;

    public void setOnExtraPageChangeListener(OnExtraPageChangeListener onExtraPageChangeListener) {
        this.onExtraPageChangeListener = onExtraPageChangeListener;
    }

    public QFoodVpAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles, ViewPager viewPager, SlidingTabLayout slidingTabLayout) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(this);
        viewPager.setOffscreenPageLimit(3);
        slidingTabLayout.setViewPager(viewPager);
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

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        /*if (null != onExtraPageChangeListener) { // 如果设置了额外功能接口
            onExtraPageChangeListener.onExtraPageScrolled(position, positionOffset, positionOffsetPixels);
        }*/
    }

    @Override
    public void onPageSelected(int position) {
        /*fragmentList.get(currentPage).onPause();
        if(fragmentList.get(position).isAdded())
            fragmentList.get(position).onResume();
        currentPage = position;
        Log.i("RHG","onPageSelected: "+position);*/
        if (null != onExtraPageChangeListener) { // 如果设置了额外功能接口
            onExtraPageChangeListener.onExtraPageSelected(position, currentPage);
        }
        currentPage = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        /*if (null != onExtraPageChangeListener) { // 如果设置了额外功能接口
            onExtraPageChangeListener.onExtraPageScrollStateChanged(state);
        }*/
    }

    public interface OnExtraPageChangeListener {
        public void onExtraPageScrolled(int i, float v, int i2);

        public void onExtraPageSelected(int currentPage, int lastPage);

        public void onExtraPageScrollStateChanged(int i);
    }
}