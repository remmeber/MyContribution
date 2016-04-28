package com.example.rhg.outsourcing.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.rhg.outsourcing.R;

/**
 * Created by remember on 2016/4/28.
 */
public class ImageUtils {
    public static void TintFill(ImageView v, Drawable drawable,int tint){
        Drawable tintdrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(tintdrawable,tint);
        v.setImageDrawable(tintdrawable);
    }
}
