package com.rhg.qf.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/*
 *desc
 *author rhg
 *time 2016/11/15 13:16
 *email 1013773046@qq.com
 */

public class SwipeDeleteRecycleView extends RecyclerView {

    SwipeDeleteLayout expandedSwipeLayout;

    public SwipeDeleteRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean canScrollVertically(int direction) {
        if (hasExpandState()) {
            expandedSwipeLayout.shrink();
            return false;
        }
        return super.canScrollVertically(direction);
    }


    boolean hasExpandState() {
        return expandedSwipeLayout != null;
    }

    public void setExpandedSwipeLayout(SwipeDeleteLayout expandedSwipeLayout) {
        this.expandedSwipeLayout = expandedSwipeLayout;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (expandedSwipeLayout != null)
            expandedSwipeLayout = null;
    }
}
