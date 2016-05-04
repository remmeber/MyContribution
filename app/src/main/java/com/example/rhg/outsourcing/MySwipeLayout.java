package com.example.rhg.outsourcing;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/3/2.
 */
public class MySwipeLayout extends SwipeRefreshLayout {

    public MySwipeLayout(Context context) {
        super(context);
    }

    public MySwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        if (super.onTouchEvent(ev))
            Log.i("RHG", "处理情况： "+super.onTouchEvent(ev));
        return false;
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        if (super.onInterceptHoverEvent(event))
            Log.i("RHG", "在SwipeLayout处被拦截");
        return false;
    }
}
