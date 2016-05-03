package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.rhg.outsourcing.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by remember on 2016/5/3.
 */
public class BannerImageHolder implements Holder<String> {
    ImageView imageView;
    String[] imageUrl;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        imageView.setImageResource(R.drawable.recommend_default_icon_1);
        ImageLoader.getInstance().displayImage(data,imageView);
    }
}
