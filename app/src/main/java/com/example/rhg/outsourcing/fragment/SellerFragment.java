package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.MySwipeLayout;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.MyPagerAdapter;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by remember on 2016/4/28.
 */
public class SellerFragment extends SuperFragment {
    private static final String TAG = "SellerFragment";
    List<Fragment> fragments = new ArrayList<Fragment>();
    View view;
    ViewPager viewPager;
    SlidingTabLayout tabLayout;
    MySwipeLayout mySwipeLayout;
    RecyclerView recyclerView;
    //-----------------根据需求创建相应的presenter----------------------------------------------------

    //----------------------------------------------------------------------------------------------


    public SellerFragment() {
        Log.i(TAG, "SellerFragment");
        fragments.add(new sellNumberFragment());
        fragments.add(new DistanceFragment());
        fragments.add(new RateScoreFragment());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.seller_viewpager_layout, container, false);
        tabLayout = (SlidingTabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.sellerViewPager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), fragments, AppConstants.SELL_TITLES);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setViewPager(viewPager);
//        recyclerView = (RecyclerView)view.findViewById(R.id.seller_recycle);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
//        RecycleSellerAdapter recommendAdapter = new RecycleSellerAdapter(getContext(),dataBySellNumberModels);
//        recyclerView.setAdapter(recommendAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

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

}
