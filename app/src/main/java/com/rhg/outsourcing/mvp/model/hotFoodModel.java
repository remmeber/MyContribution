package com.rhg.outsourcing.mvp.model;

import com.rhg.outsourcing.bean.HotFoodUrlBean;

import java.util.List;

import rx.Observable;

/**
 * desc:mvp中用户获取商品详情接口
 * author：remember
 * time：2016/5/28 16:55
 * email：1013773046@qq.com
 */
public interface HotFoodModel {
    Observable<List<HotFoodUrlBean.HotGoodsBean>> getHotFood(String hotFood, String orderType);
}

