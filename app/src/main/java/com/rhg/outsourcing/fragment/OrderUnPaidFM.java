package com.rhg.outsourcing.fragment;

import com.rhg.outsourcing.constants.AppConstants;

/*
 *desc 待付款FM
 *author rhg
 *time 2016/6/22 21:17
 *email 1013773046@qq.com
 */
public class OrderUnPaidFM extends AbstractOrderFragment {
    @Override
    protected String getFmTag() {
        return AppConstants.ORDER_UNPAID;
    }
}
