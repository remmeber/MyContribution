package com.example.rhg.outsourcing;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.rhg.outsourcing.apapter.MyPagerAdapter;
import com.example.rhg.outsourcing.fragment.TypeFragment;
import com.example.rhg.outsourcing.widget.PointView;

import java.util.ArrayList;
import java.util.List;


public class MeiTuanActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private int[] ICON_MAP_COMMON = {

            R.drawable.recommend_default_icon_1, R.drawable.recommend_default_icon_2,
            R.drawable.recommend_default_icon_1, R.drawable.recommend_default_icon_2,
            R.drawable.recommend_default_icon_1, R.drawable.recommend_default_icon_2,
            R.drawable.recommend_default_icon_1, R.drawable.recommend_default_icon_2,
            R.drawable.recommend_default_icon_1, R.drawable.recommend_default_icon_2};
    private int ICON_SIZE = ICON_MAP_COMMON.length;
    private String[] TITLE = {"1", "2", "1", "2", "1", "2", "1", "2", "1", "2"};
    public static final String KEY_START = "key_start";
    public static final String KEY_COUNT = "key_count";
    public static final String KEY_IMAGE = "key_image";
    public static final String KEY_TITLE = "key_title";

    private Context mContext;
    private ViewPager mViewPager;
    private PointView mPonitView;
    private View mView;
    List<Fragment> mFragmentList;

    public MeiTuanActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meituan);
        mContext = this;
        mFragmentList = new ArrayList<Fragment>();
        TypeFragment fragment = new TypeFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray(KEY_IMAGE, ICON_MAP_COMMON);
        bundle.putStringArray(KEY_TITLE, TITLE);
        for (int i = 0; i < ICON_SIZE; i++) {
            if ((i % 10 == 0) && i >= 1) {
                if (i == ICON_SIZE) {
                    break;
                }
                bundle.putInt(KEY_START, i / 10-1);
                bundle.putInt(KEY_COUNT, 10);
                fragment.setArguments(bundle);
                mFragmentList.add(fragment);
                fragment = new TypeFragment();
                bundle = new Bundle();
                bundle.putIntArray(KEY_IMAGE, ICON_MAP_COMMON);
                bundle.putStringArray(KEY_TITLE, TITLE);
            }
        }
        bundle.putInt(KEY_START, ICON_SIZE / 10 - 1);
        bundle.putInt(KEY_COUNT, ICON_SIZE % 10 == 0 ? 10 : ICON_SIZE % 10);
        fragment.setArguments(bundle);
        mFragmentList.add(fragment);
        initView();
    }

    private void initView() {
        /*mViewPager = (ViewPager) findViewById(R.id.viewpager_meituan);
        mViewPager.setOnPageChangeListener(this);
        mPonitView = (PointView) findViewById(R.id.pointview_meituan);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragmentList));
        mPonitView.setParams(mViewPager);*/
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        Toast.makeText(getActivity(), "index" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
