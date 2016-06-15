package com.example.rhg.outsourcing.widget;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.rhg.outsourcing.bean.ShoppingCartBean;
import com.example.rhg.outsourcing.utils.ToastHelper;

/**
 * desc:
 * author：remember
 * time：2016/6/7 15:58
 * email：1013773046@qq.com
 */
public class SwipeDeleteExpandListView extends ExpandableListView implements GestureDetector.OnGestureListener {
    SlideView slideView;
    int downX;
    int downY;
    int mLastPosition = -1;
    int pointPosition;
    Position position;
    GestureDetectorCompat gestureDetectorCompat;

    public SwipeDeleteExpandListView(Context context) {
        super(context);
        init(context);
    }


    public SwipeDeleteExpandListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwipeDeleteExpandListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        gestureDetectorCompat = new GestureDetectorCompat(context, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        gestureDetectorCompat.onTouchEvent(ev);
        return super.onTouchEvent(ev);
    }

    /* @Override
    public boolean onTouchEvent(MotionEvent ev) {
        ExpandableListAdapter
                adapter = getExpandableListAdapter();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        int pointPosition = pointToPosition(x, y);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Position position = getPosition(adapter, pointPosition);
                if (pointPosition != mLastPosition)
                    if (position.getChild() != -1) {
                        ShoppingCartBean.Goods Item = (ShoppingCartBean.Goods) adapter.getChild(position.getGroup(), position.getChild());
                        slideView = Item.slideView;
                    } else slideView = null;
                mLastPosition = pointPosition;
            default:
                break;
        }
        *//*boolean isConsume = false;
        if (slideView != null) {
            isConsume = slideView.onRequireTouchEvent(ev);
        }
        if (isConsume)
            return true;
        else super.onTouchEvent(ev);*//*
        *//*if (slideView.onRequireTouchEvent(ev)) {
            return true;
        } else
            return super.onTouchEvent(ev);*//*
        if (slideView == null)
            return super.onTouchEvent(ev);
        else
            return slideView.onRequireTouchEvent(ev) || super.onTouchEvent(ev);
    }*/

    /**
     * 获取点击的item position
     *
     * @param adapter
     * @param clickPosition
     * @return
     */
    private Position getPosition(ExpandableListAdapter adapter, int clickPosition) {
        Position position = new Position();
        int totalCount = 0;
        if (clickPosition <= 0) {
            position.setGroup(-1);
            position.setChild(-1);
            return position;
        }
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            totalCount += adapter.getChildrenCount(i) + 1;
            if (clickPosition + 1 <= totalCount) {
                position.setGroup(i);
                position.setChild(clickPosition - (i == 0 ? 0 : totalCount - adapter.getChildrenCount(i) - 1) - 1);
                break;
            }
        }
        return position;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        ExpandableListAdapter
                adapter = getExpandableListAdapter();
        downX = (int) e.getX();
        downY = (int) e.getY();
        pointPosition = pointToPosition(downX, downY);
        Position position = getPosition(adapter, pointPosition);
        if (position.getChild() != -1) {
            ShoppingCartBean.Goods Item = (ShoppingCartBean.Goods) adapter.getChild(position.getGroup(), position.getChild());
            slideView = Item.slideView;/*
                if (slideView.getState() == SlideView.OnSlideListener.SLIDE_STATUS_ON)
                    slideView.getmOnSlideListener().onSlide(slideView, slideView.getState());*/
        } else slideView = null;
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (pointPosition == mLastPosition)
            return false;
        if (slideView != null) {
            slideView.getmOnSlideListener().onSlide(slideView, slideView.getState());
            ToastHelper.getInstance()._toast("单击");
        }
        mLastPosition = pointPosition;
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (Math.abs(e2.getX() - e1.getX()) > 20 && Math.abs(e2.getY() - e1.getY()) < 15) {
            if (slideView != null)
                slideView.onRequireTouchEvent(e2, e1);
            mLastPosition = pointPosition;
            return true;
        }
        if (slideView != null)
            if (slideView.getState() == SlideView.OnSlideListener.SLIDE_STATUS_ON)
                slideView.shrink();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public class Position {
        private int group = -1;
        private int child = -1;

        public int getGroup() {
            return group;
        }

        public void setGroup(int group) {
            this.group = group;
        }

        public int getChild() {
            return child;
        }

        public void setChild(int child) {
            this.child = child;
        }

        @Override
        public String toString() {
            return "Group: " + group + " Child: " + child;
        }
    }

}
