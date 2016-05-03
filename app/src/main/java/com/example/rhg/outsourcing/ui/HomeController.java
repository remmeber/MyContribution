package com.example.rhg.outsourcing.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.fragment.HomeFragment;
import com.example.rhg.outsourcing.fragment.HomeFragmentTest;
import com.example.rhg.outsourcing.fragment.MyFragment;
import com.example.rhg.outsourcing.fragment.SellerFragment;
import com.example.rhg.outsourcing.fragment.ShoppingCarFragment;
import com.example.rhg.outsourcing.presenter.TestPresenter;

import junit.framework.Test;

/**
 * Created by remember on 2016/5/2.
 */
public class HomeController {
    private static final String TAG = "HomeController";
    private Fragment[] fragments;
    private FragmentActivity fragmentActivity;
    private TestPresenter testPresenter;
    private int showMark = 0;

    public HomeController(FragmentActivity fragmentActivity,TestPresenter testPresenter) {
        this.fragmentActivity = fragmentActivity;
        this.testPresenter = testPresenter;
        initFragment();
    }

    //---------------------初始化fragment--------------------------------------------------------
    private void initFragment() {
        fragments = new Fragment[4];
//        fragments[0] = new HomeFragment();
        fragments[0] = new HomeFragmentTest();
        fragments[1] = new SellerFragment();
        fragments[2] = new MyFragment();
        fragments[3] = new ShoppingCarFragment();
        FragmentManager fm = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content_fragment,fragments[0],fragments[0].getClass().getName());
        ft.add(R.id.content_fragment,fragments[1],fragments[1].getClass().getName());
        ft.add(R.id.content_fragment,fragments[2],fragments[2].getClass().getName());
        ft.add(R.id.content_fragment,fragments[3],fragments[3].getClass().getName());
        ft.show(fragments[0]);
        ft.hide(fragments[1]);
        ft.hide(fragments[2]);
        ft.hide(fragments[3]);
        ft.commitAllowingStateLoss();
    }

    public void showFragment(int position) {
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        transaction.show(fragments[position]);
        Log.i(TAG,"fragment: "+position + " is show");
        transaction.hide(fragments[showMark]);
        showMark = position;
    }
}
