package com.example.rhg.outsourcing.mvp.model;

import com.example.rhg.outsourcing.locationservice.LocationService;

import rx.Observable;

/**
 *desc:mvp中获取定位的接口
 *author：remember
 *time：2016/5/28 16:56
 *email：1013773046@qq.com
 */
public interface LocModel {
    public void getLocation(LocationService locationService);
}
