package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.bean.ShopDetailUriBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.impl.SwipeRefreshListener;
import com.example.rhg.outsourcing.mvp.presenter.ShopDetailPresenter;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter1;
import com.example.rhg.outsourcing.mvp.presenter.ShopDetailPresenterImpl;
import com.example.rhg.outsourcing.ui.FragmentController;
import com.example.rhg.outsourcing.widget.LoadingDialog;
import com.example.rhg.outsourcing.widget.VerticalTabLayout;

import java.util.List;

/**
 * desc:店铺详情的食物类型fm，里面{@link FoodTypeFragment}展示店铺中的商品
 * author：remember
 * time：2016/5/28 16:48
 * email：1013773046@qq.com
 */
public class ShopDetailFoodFragment extends SuperFragment implements SwipeRefreshListener {
    VerticalTabLayout verticalTabLayout;
    FragmentController fragmentController;
    Fragment[] fragments;

    List<ShopDetailUriBean.ShopDetailBean> shopDetailBeanList;
    ShopDetailPresenter shopDetailPresenter;
    LoadingDialog loadingDialog;

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
        verticalTabLayout = (VerticalTabLayout) view.findViewById(R.id.vt_selector);
    }


    @Override
    protected void initData() {
        verticalTabLayout.setTitles(AppConstants.SHOP_TITLES);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.KEY_MERCHANT_ID, merchantId);
        fragments = new Fragment[5];
        for (int i = 0; i < fragments.length; i++) {
            FoodTypeFragment fragment = new FoodTypeFragment();
            fragment.setSwipeRefreshListener(this);
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
    public void loadData() {
        loadingDialog = new LoadingDialog(getContext());
        loadingDialog.show();
        shopDetailPresenter.getShopDetail("food", merchantId);
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
        ((FoodTypeFragment) fragments[verticalTabLayout.getCurrentPosition()])
                .setShopDetailBeanList(shopDetailBeanList);
        loadingDialog.dismiss();
    }

    @Override
    public void startRefresh() {
        loadingDialog.show();
        shopDetailPresenter.getShopDetail("food", merchantId);
    }
}
