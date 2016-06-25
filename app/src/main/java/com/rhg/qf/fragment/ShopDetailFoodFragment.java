package com.rhg.qf.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.rhg.qf.R;
import com.rhg.qf.bean.ShopDetailUriBean;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.mvp.presenter.ShopDetailPresenter;
import com.rhg.qf.mvp.presenter.ShopDetailPresenterImpl;
import com.rhg.qf.ui.FragmentController;
import com.rhg.qf.widget.VerticalTabLayout;

import java.util.List;

import butterknife.Bind;

/**
 * desc:店铺详情的食物类型fm，里面{@link FoodTypeFragment}展示店铺中的商品
 * author：remember
 * time：2016/5/28 16:48
 * email：1013773046@qq.com
 */
public class ShopDetailFoodFragment extends SuperFragment {
    @Bind(R.id.vt_selector)
    VerticalTabLayout verticalTabLayout;

    FragmentController fragmentController;
    Fragment[] fragments;

    List<ShopDetailUriBean.ShopDetailBean> shopDetailBeanList;
    ShopDetailPresenter shopDetailPresenter;

    String merchantId;

    public ShopDetailFoodFragment() {
        shopDetailPresenter = new ShopDetailPresenterImpl(this);
    }


    @Override
    public int getLayoutResId() {
        return R.layout.shop_detail_fm1_content;
    }

    @Override
    protected void initView(View view) {
    }


    @Override
    protected void initData() {
        verticalTabLayout.setTitles(AppConstants.SHOP_TITLES);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.KEY_MERCHANT_ID, merchantId);
        fragments = new Fragment[5];
        for (int i = 0; i < fragments.length; i++) {
            FoodTypeFragment fragment = new FoodTypeFragment();
            fragment.setArguments(bundle);
            fragments[i] = fragment;
        }
        fragmentController = new FragmentController(getChildFragmentManager(), fragments, R.id.fl_shop_detail);
        verticalTabLayout.setOnVerticalTabClickListener(new VerticalTabLayout.VerticalTabClickListener() {
            @Override
            public void onVerticalTabClick(int position) {
                fragmentController.showFragment(position);
            }
        });
    }


    @Override
    public void receiveData(Bundle arguments) {
        merchantId = arguments.getString(AppConstants.KEY_MERCHANT_ID);
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {
        shopDetailBeanList = (List<ShopDetailUriBean.ShopDetailBean>) o;
        /*((FoodTypeFragment) fragments[verticalTabLayout.getCurrentPosition()])
                .setShopDetailBeanList(shopDetailBeanList);*/
    }

}
