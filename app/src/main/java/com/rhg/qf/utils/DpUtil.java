package com.rhg.qf.utils;

import com.rhg.qf.application.InitApplication;

/**
 * 作者：rememberon 2016/6/19
 * 邮箱：1013773046@qq.com
 */
public class DpUtil {

    public static int dip2px(float dpValue) {
        final float scale = InitApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
