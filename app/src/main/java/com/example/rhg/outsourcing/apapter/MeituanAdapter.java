package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.media.Image;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.model.ImageModel;

import java.util.List;

/**
 * Created by whiskeyfei on 15-7-30.
 */
public class MeituanAdapter extends DPBasePageAdapter<ImageModel> {
    public MeituanAdapter(Context context, List<ImageModel> list, int layoutId, boolean isInfiniteLoop) {
        super(context, list, layoutId, isInfiniteLoop);
    }

    public MeituanAdapter(Context context, List<ImageModel> list, int layoutId) {
        super(context, list, layoutId);
    }

    @Override
    public void convert(DPAdapterViewHolder holder, ImageModel imageModel) {
        holder.setImageResource(R.id.meituan_imageview,imageModel.getImageId());
        holder.setText(R.id.meituan_title,imageModel.getContent());
    }

    public MeituanAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        mIsInfiniteLoop = isInfiniteLoop;
        return this;
    }

    public boolean getInfiniteLoop(){
        return mIsInfiniteLoop;
    }
}
