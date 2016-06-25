package com.rhg.qf.mvp.model;

import com.rhg.qf.locationservice.LocationService;

/**
 * desc:mvp中获取定位的接口
 * author：remember
 * time：2016/5/28 16:56
 * email：1013773046@qq.com
 */
public interface LocModel {
    public void getLocation(LocationService locationService);
}
