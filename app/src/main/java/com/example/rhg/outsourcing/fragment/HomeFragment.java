package com.example.rhg.outsourcing.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by remember on 2016/4/28.
 */
public class HomeFragment extends FragmentActivity {
    private static final String TAG = "HomeFragment";
    public HomeFragment() {
        Log.i(TAG,"HomeFragment");
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }
}
