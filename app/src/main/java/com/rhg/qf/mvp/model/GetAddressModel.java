package com.rhg.qf.mvp.model;

import com.rhg.qf.bean.AddressUrlBean;

import java.util.List;

import rx.Observable;

/**
 * 作者：rememberon 2016/6/29
 * 邮箱：1013773046@qq.com
 */
public interface GetAddressModel {
    Observable<List<AddressUrlBean.AddressBean>> getAddress(String Table, String userId);
}
