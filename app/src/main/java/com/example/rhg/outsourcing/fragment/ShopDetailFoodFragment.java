package com.example.rhg.outsourcing.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.ui.FragmentController;
import com.example.rhg.outsourcing.widget.VerticalTabLayout;

/**
 *desc:店铺详情的食物类型fm，里面{@link FoodTypeFragment}展示店铺中的商品
 *author：remember
 *time：2016/5/28 16:48
 *email：1013773046@qq.com
 */
public class ShopDetailFoodFragment extends SuperFragment {
    VerticalTabLayout verticalTabLayout;
    FragmentController fragmentController;

    public ShopDetailFoodFragment() {
    }


    @Override
    public int getLayoutResId() {
        return R.layout.shop_detail_fm1_content;
    }

    @Override
    protected void initView(View view) {
        verticalTabLayout = (VerticalTabLayout)view.findViewById(R.id.vt_selector);
    }

    @Override
    protected void initData() {
        verticalTabLayout.setTitles(AppConstants.SHOP_TITLES);
        Fragment[] fragments = new Fragment[5];
        fragments[0] = new FoodTypeFragment();
        fragments[1] = new FoodTypeFragment();
        fragments[2] = new FoodTypeFragment();
        fragments[3] = new FoodTypeFragment();
        fragments[4] = new FoodTypeFragment();
        fragmentController = new FragmentController(getChildFragmentManager(),fragments,R.id.fl_shop_detail);
        verticalTabLayout.setOnVerticalTabClickListener(new VerticalTabLayout.VerticalTabClickListener() {
            @Override
            public void onVerticalTabClick(int position) {
                fragmentController.showFragment(position);
            }
        });
    }

    @Override
    public void loadData() {
        Log.i("RHG","加载数据....");
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {

    }
}
