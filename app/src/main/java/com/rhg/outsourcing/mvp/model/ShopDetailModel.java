package com.rhg.outsourcing.mvp.model;

import com.rhg.outsourcing.bean.ShopDetailUriBean;

import java.util.List;

import rx.Observable;

/**
 *desc:mvp模型中的测试接口
 *author：remember
 *time：2016/5/28 16:54
 *email：1013773046@qq.com
 */
public interface ShopDetailModel {
    // TODO: 返回目标数据
    Observable<List<ShopDetailUriBean.ShopDetailBean>> getShopDetail(String table, String merchantId);
}
