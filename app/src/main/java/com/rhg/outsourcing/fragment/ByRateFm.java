package com.rhg.outsourcing.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.apapter.QFoodMerchantAdapter;
import com.rhg.outsourcing.bean.MerchantUrlBean;
import com.rhg.outsourcing.constants.AppConstants;
import com.rhg.outsourcing.impl.RcvItemClickListener;
import com.rhg.outsourcing.mvp.presenter.MerchantsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * desc:所有店铺的按评价fm
 * author：remember
 * time：2016/5/28 16:43
 * email：1013773046@qq.com
 */
public class ByRateFm extends AbstractMerchantsFragment {
    @Override
    protected int getMerchantsFmType() {
        return AppConstants.MERCHANT_RATE;
    }
}
