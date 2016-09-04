package com.rhg.qf.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.rhg.qf.R;

public class SlideView extends LinearLayout {

    int state = OnSlideListener.SLIDE_STATUS_OFF;
    boolean slide = false;
    int downX;
    int downY;
    private Context mContext;
    private LinearLayout mViewContent;
    private Scroller mScroller;
    private OnSlideListener mOnSlideListener;
    private int mHolderWidth = 80;

    public SlideView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        mScroller = new Scroller(mContext);

        setOrientation(LinearLayout.HORIZONTAL);
        View.inflate(mContext, R.layout.swipe_delete_view, this);
        mViewContent = (LinearLayout) findViewById(R.id.view_content);
        mHolderWidth = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mHolderWidth, getResources()
                        .getDisplayMetrics()));
    }

    /**
     * desc:设置滑动的宽度
     * author：remember
     * time：2016/6/11 14:12
     * email：1013773046@qq.com
     */
    public void setmHolderWidth(int mHolderWidth) {
        this.mHolderWidth = mHolderWidth;
    }

    public void setButtonText(CharSequence text) {
        ((TextView) findViewById(R.id.delete)).setText(text);
    }

    public void setContentView(View view) {
        mViewContent.addView(view);
    }

    public void setOnSlideListener(OnSlideListener onSlideListener) {
        mOnSlideListener = onSlideListener;
    }

    public OnSlideListener getmOnSlideListener() {
        return mOnSlideListener;
    }

    public int getState() {
        return state;
    }

    public void shrink() {
        if (getScrollX() != 0) {
            this.smoothScrollTo(0, 0);
            state = OnSlideListener.SLIDE_STATUS_OFF;
            slide = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return false;
    }

    public void onRequireTouchEvent(MotionEvent event, MotionEvent event1) {
        int x = (int) event.getX();
        downX = (int) event1.getX();
        downY = (int) event1.getY();
        int scrollX = getScrollX();


        int deltaX = x - downX;

        int newScrollX = scrollX - deltaX;
        if (deltaX != 0) {
            if (newScrollX < 0) {
                newScrollX = 0;
            } else if (newScrollX > mHolderWidth) {
                newScrollX = mHolderWidth;
            }
            this.scrollTo(newScrollX, 0);
        }
        if (scrollX - mHolderWidth * 0.4 > 0) {
            newScrollX = mHolderWidth;
        } else newScrollX = 0;
        this.smoothScrollTo(newScrollX, 0);
        if (mOnSlideListener != null) {
            mOnSlideListener.onSlide(this,
                    state = newScrollX == 0 ? OnSlideListener.SLIDE_STATUS_OFF
                            : OnSlideListener.SLIDE_STATUS_ON);
        }
    }

    private void smoothScrollTo(int destX, int destY) {
        // 缓慢滚动到指定位置
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX, 0, delta, 0, Math.abs(delta) * 3);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    public interface OnSlideListener {
        int SLIDE_STATUS_OFF = 0;
        int SLIDE_STATUS_ON = 2;

        /**
         * @param view   current SlideView
         * @param status SLIDE_STATUS_ON or SLIDE_STATUS_OFF
         */
        public void onSlide(View view, int status);
    }
}
