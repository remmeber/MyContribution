package com.rhg.qf.fragment;

import com.rhg.qf.constants.AppConstants;

/*
 *desc 已退款FM
 *author rhg
 *time 2016/6/22 21:23
 *email 1013773046@qq.com
 */
public class OrderDrawbackFm extends AbstractOrderFragment {
    @Override
    protected String getFmTag() {
        return AppConstants.ORDER_DRAWBACK;
    }
}