package com.rhg.qf.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.rhg.qf.apapter.TimerImageAdapter;
import com.rhg.qf.impl.ImageChangeListener;
import com.rhg.qf.utils.AnimationUtils;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Created by whiskeyfei on 15-7-24.
 */
public class TimerImageView extends ImageView implements ImageChangeListener, View.OnClickListener {

    private static final String TAG = "TimerImageView";

    private Context mContext;
    private TimerImageAdapter mTimerImageAdapter;

    public TimerImageView(Context context) {
        super(context);
        init(context);
    }

    public TimerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setScaleType(ScaleType.FIT_CENTER);
        setOnClickListener(this);
    }


    public void setAdapter(TimerImageAdapter adapter) {
        mTimerImageAdapter = adapter;
        mTimerImageAdapter.setImageChangeListener(this);
    }

    @Override
    public void updateImage(String imageUrl, int position) {
        Toast.makeText(mContext, "updateImage position:" + position, Toast.LENGTH_SHORT).show();
        ImageLoader.getInstance().displayImage(imageUrl, this);
        AnimationUtils.alphaAnimation(this, 0.0f, 1.0f, 500);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTimerImageAdapter != null) mTimerImageAdapter.setImageChangeListener(null);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext, "onClick position:" + mTimerImageAdapter.getCurrentPos(), Toast.LENGTH_SHORT).show();
        if (mTimerImageAdapter != null) mTimerImageAdapter.switchNext();
    }
}
