package com.example.rhg.outsourcing.utils;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.rhg.outsourcing.fragment.HomeFragment;
import com.example.rhg.outsourcing.fragment.MyFragment;
import com.example.rhg.outsourcing.fragment.SellerFragment;
import com.example.rhg.outsourcing.fragment.ShoppingCarFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/4/28.
 */
public class FragmentUtil {
    private static List<FragmentActivity> fragmentActivities = new ArrayList<FragmentActivity>();
    private static int old_index = 0;

    public static void addFragment(FragmentActivity fragmentActivity){
        fragmentActivities.add(fragmentActivity);
    }

    @Nullable
    private static FragmentActivity getFragmentActivity(int index) {
        return fragmentActivities.size()==0?null:fragmentActivities.get(index);
    }

    public static FragmentActivity getFragmentInstance(int index) {
        FragmentActivity fragment = null;
        if (fragmentActivities.size() == 0) {
            switch (index) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new SellerFragment();
                    break;
                case 2:
                    fragment = new MyFragment();
                    break;
                case 3:
                    fragment = new ShoppingCarFragment();
                    break;
            }
            fragmentActivities.add(fragment);
            return fragment;
        }
        fragment = getFragmentActivity(index);
        if (fragment != null) {
            if (index != old_index)
                old_index = index;
            return fragment;
        }

        return fragment;
    }
}
