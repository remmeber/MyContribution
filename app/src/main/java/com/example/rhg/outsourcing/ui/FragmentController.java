package com.example.rhg.outsourcing.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.rhg.outsourcing.mvp.presenter.TestPresenter;

/**
 * Created by remember on 2016/5/2.
 */
public class FragmentController {
    private static final String TAG = "FragmentController";
    public  FragmentManager fm;
    private Fragment[] fragments;
    private TestPresenter testPresenter;
    private int showMark = 0;

    public FragmentController(FragmentManager fm, TestPresenter testPresenter ,Fragment[] fragments,int id) {
        this.fm = fm;
        this.testPresenter = testPresenter;
        this.fragments = fragments;
        initFragment(id);
    }

    public  FragmentManager getFm() {
        return fm;
    }

    //TODO ---------------------初始化fragment------------------------------------------------------
    private void initFragment(int id) {
        FragmentTransaction ft = fm.beginTransaction();
        for (int i=0;i<fragments.length;i++){
            ft.add(id,fragments[i],fragments[i].getClass().getName());
            if(i==0)
                ft.show(fragments[i]);
            else ft.hide(fragments[i]);
        }
        /*ft.add(id, fragments[0], fragments[0].getClass().getName());
        ft.add(id, fragments[1], fragments[1].getClass().getName());
        ft.add(id, fragments[2], fragments[2].getClass().getName());
        ft.add(id, fragments[3], fragments[3].getClass().getName());
        ft.show(fragments[0]);
        ft.hide(fragments[1]);
        ft.hide(fragments[2]);
        ft.hide(fragments[3]);*/
        ft.commitAllowingStateLoss();
    }

    public void showFragment(int position) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.show(fragments[position]);
        transaction.hide(fragments[showMark]);
        //TODO ---------------------------切换Fragment的时候停止，换回Fragment时启动-----------------
        /*switch (showMark) {
            case 0:
                BannerController.getInstance().stopBanner();
                break;
        }
        switch (position) {
            case 0:
                BannerController.getInstance().startBanner(2000);
                break;
        }*/
        //TODO -------------------------------------------------------------------------------------
        showMark = position;
        transaction.commitAllowingStateLoss();
    }
}
