package com.example.rhg.outsourcing.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.SlidesImageAdapter;
import com.example.rhg.outsourcing.impl.SlidesViewChangeListener;
import com.example.rhg.outsourcing.utils.AnimationUtils;


/**
 * Created by whiskeyfei on 15-7-28.
 */
    public class SlidesImageView extends RelativeLayout implements SlidesViewChangeListener {

    private Context mContext;
    private View mConvertView;
    private ImageView mImageView;
    private TextView mTextView;
    private SlidesImageAdapter mSlidesImageAdapter;

    public SlidesImageView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public SlidesImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public SlidesImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public void init() {
//        mConvertView = LayoutInflater.from(mContext).inflate(R.layout.slides_item, null);
//        mImageView = (ImageView) mConvertView.findViewById(R.id.timerimageview);
//        mTextView = (TextView) mConvertView.findViewById(R.id.timer_title);
        addView(mConvertView);
    }

    public void setAdapter(SlidesImageAdapter adapter){
        mSlidesImageAdapter = adapter;
        mSlidesImageAdapter.setImageChangeListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mSlidesImageAdapter!= null) mSlidesImageAdapter.setImageChangeListener(null);
    }

    @Override
    public void update(int id, String title, int position) {
        mImageView.setImageResource(id);
        AnimationUtils.alphaAnimation(mImageView, 0.0f, 1.0f, 500);
        mTextView.setText(title);
    }
}
