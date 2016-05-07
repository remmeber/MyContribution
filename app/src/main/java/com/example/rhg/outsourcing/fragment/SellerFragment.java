package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
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
import com.example.rhg.outsourcing.ui.HomeController;

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
    TabLayout tabLayout;
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
        view = inflater.inflate(R.layout.sellerviewpagerlayout, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.sellerViewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(HomeController.getFm(), fragments);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

//        recyclerView = (RecyclerView)view.findViewById(R.id.seller_recycle);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
//        RecommendAdapter recommendAdapter = new RecommendAdapter(getContext(),sellerModels);
//        recyclerView.setAdapter(recommendAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
        outState.putInt("positon", tabLayout.getSelectedTabPosition());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null)
            viewPager.setCurrentItem(savedInstanceState.getInt("position"));
    }
}
