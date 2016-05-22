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
        Drawable tint_drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(tint_drawable,tint);
        v.setImageDrawable(tint_drawable);
    }
    public static Drawable TintWithoutFill(Drawable drawable,int tint){
        Drawable tint_drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(tint_drawable,tint);
        return tint_drawable;
    }
}
