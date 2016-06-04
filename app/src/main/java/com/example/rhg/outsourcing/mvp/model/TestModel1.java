package com.example.rhg.outsourcing.mvp.model;

import com.example.rhg.outsourcing.bean.OrderUrlBean;
import com.example.rhg.outsourcing.bean.ShopDetailUriBean;

import java.util.List;

import rx.Observable;

/**
 *desc:mvp模型中的测试接口
 *author：remember
 *time：2016/5/28 16:54
 *email：1013773046@qq.com
 */
public interface TestModel1 {
    // TODO: 返回目标数据
    Observable<List<OrderUrlBean.OrderBean>>
    getData(String table, String userId, String style);
}
