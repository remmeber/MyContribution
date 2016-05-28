package com.example.rhg.outsourcing.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.rhg.outsourcing.bean.ShoppingCartBean;

/**
 * Created by remember on 2016/5/15.
 */
public class MyExpandListView extends ExpandableListView {
    SlideView slideView;
    int mLastX;
    int mLastY;
    int mLastPosition=-1;

    public MyExpandListView(Context context) {
        super(context);
    }

    public MyExpandListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyExpandListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        ExpandableListAdapter adapter = getExpandableListAdapter();
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
        if (slideView != null) {
            slideView.onRequireTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

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
