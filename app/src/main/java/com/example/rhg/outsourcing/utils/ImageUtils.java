package com.example.rhg.outsourcing.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

import com.example.rhg.outsourcing.constants.AppConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * desc:图片工具
 * author：remember
 * time：2016/6/2 9:39
 * email：1013773046@qq.com
 */
public class ImageUtils {
    public static void TintFill(ImageView v, Drawable drawable, int tint) {
        Drawable tint_drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(tint_drawable, tint);
        v.setImageDrawable(tint_drawable);
    }

    public static Drawable TintWithoutFill(Drawable drawable, int tint) {
        Drawable tint_drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(tint_drawable, tint);
        return tint_drawable;
    }


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static Uri getImageUri(Bitmap bitmap) {

        File file = new File(AppConstants.f_Path);
        if (!file.exists()) {
            file.mkdir();
        }
        File _file = new File(AppConstants.f_Path, DataUtil.getCurrentTime() + ".png");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(_file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Uri _uri = Uri.fromFile(_file);
        return _uri;
    }
}
