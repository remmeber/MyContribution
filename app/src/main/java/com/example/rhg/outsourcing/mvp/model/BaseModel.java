package com.example.rhg.outsourcing.mvp.model;

import com.example.rhg.outsourcing.bean.BannerTypeModel;
import com.example.rhg.outsourcing.bean.TestBean;

import rx.Observable;
import rx.Observer;

/**
 * Created by remember on 2016/4/28.
 */
public interface BaseModel {
    // TODO: 返回目标数据
    Observable<BannerTypeModel> getData();
}
