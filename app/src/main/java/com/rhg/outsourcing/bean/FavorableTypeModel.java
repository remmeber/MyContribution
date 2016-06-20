package com.rhg.outsourcing.bean;

import com.rhg.outsourcing.apapter.QFoodGridViewAdapter;

import java.util.List;

/**
 * desc:主页推荐食物本地数据模型
 * author：remember
 * time：2016/5/28 16:12
 * email：1013773046@qq.com
 */

public class FavorableTypeModel {
    List<FavorableFoodUrlBean.FavorableFoodEntity> favorableFoodBeen;
    private QFoodGridViewAdapter dpGridViewAdapter;

    public void setFavorableFoodBeen(List<FavorableFoodUrlBean.FavorableFoodEntity> favorableFoodBeen) {
        this.favorableFoodBeen = favorableFoodBeen;
        if (dpGridViewAdapter != null)
            dpGridViewAdapter.setList(this.favorableFoodBeen);
    }

    public void setDpGridViewAdapter(QFoodGridViewAdapter dpGridViewAdapter) {
        this.dpGridViewAdapter = dpGridViewAdapter;
    }

    public List<FavorableFoodUrlBean.FavorableFoodEntity> getFavorableFoodBeen() {
        return favorableFoodBeen;
    }

    public QFoodGridViewAdapter getDpGridViewAdapter() {
        return dpGridViewAdapter;
    }
}
