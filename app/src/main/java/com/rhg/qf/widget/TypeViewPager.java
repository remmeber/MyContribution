package com.rhg.qf.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/4/8.
 */
public class TypeViewPager extends ViewPager {

    public TypeViewPager(Context context) {
        super(context);
    }

    public TypeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

}
