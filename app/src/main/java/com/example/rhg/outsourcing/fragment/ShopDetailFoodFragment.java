package com.example.rhg.outsourcing.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.ui.FragmentController;
import com.example.rhg.outsourcing.widget.VerticalTabLayout;

/**
 * Created by remember on 2016/5/20.
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
        fragmentController = new FragmentController(getChildFragmentManager(),null,fragments,R.id.fl_shop_detail);
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
