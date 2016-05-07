package com.example.rhg.outsourcing.Constants;

import android.app.Application;
import android.graphics.drawable.Drawable;

import com.example.rhg.outsourcing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/5/4.
 */
public class AppConstants extends Application {
    public static final int[] imageindictors = new int[]{R.drawable.ic_page_indicator,
            R.drawable.ic_page_indicator_focused};
    public static final String[] tabtitles = new String[]{"哈哈", "呵呵", "啊啊"};
    //---------------------------店铺复用------------------------------------------------------------
    public static final int TypeHome = 0;
    public static final int TypeSeller = 1;
    //---------------------------页面类型-----------------------------------------------------------
    public static final int TypeMy = 2;
    public static final int TypeShoppingCar = 3;

}
