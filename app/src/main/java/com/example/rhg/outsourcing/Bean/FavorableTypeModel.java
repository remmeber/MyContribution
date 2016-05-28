package com.example.rhg.outsourcing.bean;

import com.example.rhg.outsourcing.apapter.QFoodGridViewAdapter;

import java.util.List;
/**
 *desc:主页推荐食物本地数据模型
 *author：remember
 *time：2016/5/28 16:12
 *email：1013773046@qq.com
 */

public class FavorableTypeModel {
    List<FavorableFoodBean> favorableFoodBeen;
    private QFoodGridViewAdapter dpGridViewAdapter;

    public FavorableTypeModel(List<FavorableFoodBean> favorableFoodBeen, QFoodGridViewAdapter dpGridViewAdapter) {
        this.favorableFoodBeen = favorableFoodBeen;
        this.dpGridViewAdapter = dpGridViewAdapter;
    }

    public List<FavorableFoodBean> getFavorableFoodBeen() {
        return favorableFoodBeen;
    }

    public QFoodGridViewAdapter getDpGridViewAdapter() {
        return dpGridViewAdapter;
    }
}
