package com.example.rhg.outsourcing.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by remember on 2016/5/6.
 * 为了屏蔽RecycleView 的滑动效果
 */
public class MyRecycleView extends RecyclerView {


    public MyRecycleView(Context context) {
        super(context);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    int lastX;
    int lastY;
    boolean isVerticalSwipe;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int currentX = (int) e.getRawX();
        int currentY = (int) e.getRawY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(currentY - lastY) / Math.abs(currentX - lastX) > 2) {
                    isVerticalSwipe = true;
                    Log.i("RHG", "拦截");
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        lastX = currentX;
        lastY = currentY;
        return isVerticalSwipe;
    }
}
