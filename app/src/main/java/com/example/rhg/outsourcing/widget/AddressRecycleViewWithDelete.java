package com.example.rhg.outsourcing.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import com.example.rhg.outsourcing.apapter.AddressAdapter;
import com.example.rhg.outsourcing.bean.AddressLocalBean;
import com.example.rhg.outsourcing.utils.ToastHelper;

/**
 * desc:带有侧滑删除功能的地址recycleview
 * author：remember
 * time：2016/6/11 13:54
 * email：1013773046@qq.com
 */
public class AddressRecycleViewWithDelete extends RecyclerView implements GestureDetector.OnGestureListener {

    SlideView slideView;
    int lastPosition = 0;
    int slidePosition = -1;
    ViewHolder viewHolder;
    GestureDetectorCompat gestureDetectorCompat;
    Context context;
    int downX;
    int downY;

    public AddressRecycleViewWithDelete(Context context) {
        super(context);
        init(context);
    }

    public AddressRecycleViewWithDelete(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddressRecycleViewWithDelete(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        gestureDetectorCompat = new GestureDetectorCompat(context, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        gestureDetectorCompat.onTouchEvent(ev);
        return super.onTouchEvent(ev);
        /*int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = findChildViewUnder(x, y);
                if (view == null) {
                    return super.onTouchEvent(ev);
                }
                viewHolder = findContainingViewHolder(view);
                slidePosition = getChildAdapterPosition(view);
                if (slidePosition != lastPosition) {
                    if (viewHolder instanceof AddressAdapter.AddressViewHolder) {
                        slideView = ((AddressAdapter.AddressViewHolder) viewHolder).slideView;
                    } else slideView = null;
                }
                lastPosition = slidePosition;
                break;
        }
        if (slideView == null) {
            return super.onTouchEvent(ev);
        } else
            return slideView.onRequireTouchEvent(ev) || super.onTouchEvent(ev);*/
    }

    /*for gesture*/
    @Override
    public boolean onDown(MotionEvent e) {
        downX = (int) e.getX();
        downY = (int) e.getY();
        Log.i("RHG", "downX: " + downX + ",downY: " + downY);
        View view = findChildViewUnder(downX, downY);
        if (view == null) {
            slideView = null;
            return false;
        }
        viewHolder = findContainingViewHolder(view);
        slidePosition = getChildAdapterPosition(view);
        if (viewHolder instanceof AddressAdapter.AddressViewHolder) {
            slideView = ((AddressAdapter.AddressViewHolder) viewHolder).slideView;
        } else slideView = null;
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        AddressAdapter.AddressViewHolder oldLastViewHolder =
                (AddressAdapter.AddressViewHolder) findViewHolderForAdapterPosition(lastPosition);
        if (slidePosition != lastPosition)
            if (oldLastViewHolder.slideView.getState() == SlideView.OnSlideListener.SLIDE_STATUS_ON) {
                slideView.getmOnSlideListener().onSlide(slideView, slideView.getState());
                return false;
            }
        if (slideView != null) {
            onItemClickListener.onItemClick(slidePosition);
        }
        if (slidePosition != lastPosition) {
            View view = findChildViewUnder(downX, downY);
            viewHolder = findContainingViewHolder(view);
            if (viewHolder instanceof AddressAdapter.AddressViewHolder) {
                slideView = ((AddressAdapter.AddressViewHolder) viewHolder).slideView;
            } else slideView = null;
        }
        lastPosition = slidePosition;
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (Math.abs(distanceY) > Math.abs(distanceX) * 2)
            return false;
        if (slideView != null) {
            Log.i("RHG", "DONE" + ": " + slideView);
            slideView.onRequireTouchEvent(e2, e1);
            lastPosition = slidePosition;
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
    /*for gesture*/

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    private ItemClickListener onItemClickListener;

    public void setOnItemClickListener(ItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public boolean hasItemClickListener() {
        return onItemClickListener != null;
    }
}
