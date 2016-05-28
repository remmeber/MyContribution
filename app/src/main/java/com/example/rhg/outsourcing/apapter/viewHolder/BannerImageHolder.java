package com.example.rhg.outsourcing.apapter.viewHolder;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.rhg.outsourcing.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;
/**
 *desc:图片轮播页面
 *author：remember
 *time：2016/5/28 16:15
 *email：1013773046@qq.com
 */
public class BannerImageHolder implements Holder<String> {
    ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        imageView.setImageResource(R.drawable.recommend_default_icon_2);
        ImageLoader.getInstance().displayImage(data,imageView);
    }
}
