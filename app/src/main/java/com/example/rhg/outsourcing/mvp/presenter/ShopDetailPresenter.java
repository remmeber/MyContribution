package com.example.rhg.outsourcing.mvp.presenter;

/**
 * desc:mvp presenter 测试
 * author：remember
 * time：2016/5/28 17:02
 * email：1013773046@qq.com
 */
public interface ShopDetailPresenter {
    /**
     * desc:table:restaurants;page:0表示按销量;1表示按距离;2表示按评分
     * author：remember
     * time：2016/6/3 13:58
     * email：1013773046@qq.com
     */
    public void getShopDetail(String table, String merchantId);
}
