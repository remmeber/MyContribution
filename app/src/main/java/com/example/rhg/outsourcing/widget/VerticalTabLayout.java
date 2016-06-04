package com.example.rhg.outsourcing.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.utils.ToastHelper;
import com.flyco.tablayout.SlidingTabLayout;

/**
 * Created by remember on 2016/5/20.
 */
public class VerticalTabLayout extends FrameLayout implements View.OnClickListener {
    private final static int DEFAULT_SELECT_TEXT_COLOR = Color.WHITE;
    private final static int DEFAULT_SELECT_BG_COLOR = Color.GREEN;
    private final static int DEFAULT_UNSELECT_TEXT_COLOR = Color.BLACK;
    private final static int DEFAULT_UNSELECT_BG_COLOR = Color.GRAY;
    Context mContext;
    LinearLayout mLayout;
    int tabCount;
    int lastPosition;
    int itemHeight;
    int itemWidth;
    float textSize;
    int selectTextColor;
    int unSelectTextColor;
    int selectBgColor;
    int unSelectBgColor;
    private String[] titles;

    public VerticalTabLayout(Context context) {

        this(context, null, 0);

    }

    public VerticalTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        ontainAttr(context, attrs);


        mLayout = new LinearLayout(context);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        /*LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(context, attrs);
        layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;*/
        mLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                , LinearLayout.LayoutParams.MATCH_PARENT));

        addView(mLayout);

    }

    private void ontainAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VerticalTabLayout);
        if (a.hasValue(R.styleable.VerticalTabLayout_item_height)) {
            itemHeight = a.getDimensionPixelSize(R.styleable.VerticalTabLayout_item_height, 0);
        }
        if (a.hasValue(R.styleable.VerticalTabLayout_item_width)) {
            itemWidth = a.getDimensionPixelSize(R.styleable.VerticalTabLayout_item_width, 0);
        }
        if (a.hasValue(R.styleable.VerticalTabLayout_item_text_size)) {
            textSize = a.getDimension(R.styleable.VerticalTabLayout_item_text_size, sp2px(14));
        }
        if (a.hasValue(R.styleable.VerticalTabLayout_item_click_text_color)) {
            selectTextColor = a.getColor(R.styleable.VerticalTabLayout_item_click_text_color, DEFAULT_SELECT_TEXT_COLOR);
        }
        if (a.hasValue(R.styleable.VerticalTabLayout_item_unclick_text_color)) {
            unSelectTextColor = a.getColor(R.styleable.VerticalTabLayout_item_unclick_text_color, DEFAULT_UNSELECT_TEXT_COLOR);
        }
        if (a.hasValue(R.styleable.VerticalTabLayout_item_click_bg_color)) {
            selectBgColor = a.getColor(R.styleable.VerticalTabLayout_item_click_bg_color, DEFAULT_SELECT_BG_COLOR);
        }
        if (a.hasValue(R.styleable.VerticalTabLayout_item_unclick_bg_color)) {
            unSelectBgColor = a.getColor(R.styleable.VerticalTabLayout_item_unclick_bg_color, DEFAULT_UNSELECT_BG_COLOR);
        }
    }

    /*public void setVp(ViewPager vp) {
        if (vp == null || vp.getAdapter() == null) {
            throw new IllegalStateException("ViewPager or ViewPager adapter can not be NULL !");
        }
        this.vp = vp;
        this.vp.removeOnPageChangeListener(this);
        this.vp.addOnPageChangeListener(this);
        notifyDataSetChanged();
    }*/

    private void notifyDataSetChanged() {
        mLayout.removeAllViews();
        this.tabCount = titles.length;
        for (int i = 0; i < tabCount; i++) {
            final int position = i;
            TextView textview = new TextView(mContext);
            LinearLayout.LayoutParams _lp = new LinearLayout.LayoutParams(
                    itemWidth == 0 ? LinearLayout.LayoutParams.WRAP_CONTENT : /*dip2px(itemWidth)*/(int) itemWidth,
                    itemHeight == 0 ? LinearLayout.LayoutParams.WRAP_CONTENT : /*dip2px(itemHeight)*/(int) itemHeight
            );
            _lp.setMargins(dip2px(5), dip2px(15), dip2px(5), dip2px(15));
            textview.setLayoutParams(_lp);
            textview.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            CharSequence pageTitle = titles[i];
            textview.setText(pageTitle);
            if (i == 0) {
                textview.setTextColor(selectTextColor);
                textview.setBackgroundColor(selectBgColor);
            } else {
                textview.setTextColor(unSelectTextColor);
                textview.setBackgroundColor(unSelectBgColor);
            }
            textview.setGravity(Gravity.CENTER);
            textview.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastPosition != position) {
                        if (mVerticalTabClickListener != null)
                            mVerticalTabClickListener.onVerticalTabClick(position);
                        changeTabState(position);
                    }
                }
            });
            mLayout.addView(textview, i, _lp);
        }
    }

    public interface VerticalTabClickListener {
        public void onVerticalTabClick(int position);
    }

    private VerticalTabClickListener mVerticalTabClickListener;

    public void setOnVerticalTabClickListener(VerticalTabClickListener mVerticalTabClickListener) {
        this.mVerticalTabClickListener = mVerticalTabClickListener;
    }


    public void changeTabState(int position) {
        if (lastPosition != position) {
            View _view = mLayout.getChildAt(position);
            TextView tv = (TextView) _view;
            tv.setTextColor(selectTextColor);
            tv.setBackgroundColor(selectBgColor);
            _view = mLayout.getChildAt(lastPosition);
            tv = (TextView) _view;
            tv.setTextColor(unSelectTextColor);
            tv.setBackgroundColor(unSelectBgColor);
            lastPosition = position;
        }
    }

    public int getCurrentPosition() {
        return lastPosition;
    }


    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    protected int sp2px(float sp) {
        final float scale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {

    }

    public void setTitles(String[] titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }
}
