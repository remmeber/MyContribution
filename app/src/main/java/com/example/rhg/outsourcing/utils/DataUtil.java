package com.example.rhg.outsourcing.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 作者：rememberon 2016/6/2
 * 邮箱：1013773046@qq.com
 */
public class DataUtil {

    public static String getData() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyyMMdd_HHmmss", Locale.ENGLISH);
        return dateFormat.format(date);
    }
/*
    // 使用系统当前日期加以调整作为照片的名称
    public static String getDataForImageName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss", Locale.ENGLISH);
        return dateFormat.format(date) + ".jpg";
    }*/
}