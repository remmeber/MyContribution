package com.rhg.qf.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.apapter.QFoodShoppingCartExplAdapter;
import com.rhg.qf.bean.ShoppingCartBean;
import com.rhg.qf.utils.ShoppingCartUtil;
import com.rhg.qf.utils.ToastHelper;
import com.rhg.qf.widget.SwipeDeleteExpandListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * desc:购物车fm
 * author：remember
 * time：2016/5/28 16:49
 * email：1013773046@qq.com
 */
public class ShoppingCartFragment extends BaseFragment {
    private static final String TAG = "ShoppingCartFragment";
    List<ShoppingCartBean> shoppingCartBeanList;
    List<ShoppingCartBean.Goods> goodsList;
    QFoodShoppingCartExplAdapter QFoodShoppingCartExplAdapter;

    @Bind(R.id.tb_center_tv)
    TextView tbCenterTV;
    @Bind(R.id.tb_right_tv)
    TextView tbRightTV;
    @Bind(R.id.fl_tab)
    FrameLayout fl_tab;
    @Bind(R.id.rl_shopping_cart_empty)
    RelativeLayout rlShoppingCartEmpty;
    @Bind(R.id.list_shopping_cart)
    SwipeDeleteExpandListView listShoppingCart;
    @Bind(R.id.srl_shopping_cart)
    SwipeRefreshLayout srlShoppingCart;
    @Bind(R.id.tv_count_money)
    TextView tvCountMoney;
    @Bind(R.id.ll_shopping_cart)
    LinearLayout llShoppingCart;
    @Bind(R.id.rl_shopping_cart_pay)
    RelativeLayout rlShoppingCartPay;
    //-----------------根据需求创建相应的presenter----------------------------------------------------

    public ShoppingCartFragment() {
        Log.i(TAG, "ShoppingCartFragment ");
        shoppingCartBeanList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
            shoppingCartBean.setMerchantName("iiiii");
            shoppingCartBean.setMerID("2015051800");
            goodsList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                ShoppingCartBean.Goods goods = new ShoppingCartBean.Goods();
                goods.setGoodsLogoUrl(R.drawable.recommend_default_icon_1);
                goods.setGoodsName("" + j);
                goods.setPrice("" + j * 2);
                goods.setProductID("20160518");
                goods.setNumber("1");
                goodsList.add(goods);
            }
            shoppingCartBean.setGoods(goodsList);
            shoppingCartBeanList.add(shoppingCartBean);
        }
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public int getLayoutResId() {
        return R.layout.shopping_cart_layout;
    }


    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData() {
        fl_tab.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
        tbCenterTV.setText(getResources().getString(R.string.shoppingCart));
        tbRightTV.setText("编辑");
        srlShoppingCart.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("RHG", "refresh is done");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srlShoppingCart.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        QFoodShoppingCartExplAdapter = new QFoodShoppingCartExplAdapter(getContext());
        listShoppingCart.setAdapter(QFoodShoppingCartExplAdapter);
        listShoppingCart.setGroupIndicator(null);
        listShoppingCart.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        QFoodShoppingCartExplAdapter.setDataChangeListener(new QFoodShoppingCartExplAdapter.DataChangeListener() {
            @Override
            public void onDataChange(String CountMoney) {
                if (shoppingCartBeanList.size() == 0) {
                    showEmpty(true);
                } else
                    showEmpty(false);
                String countMoney = String.format(getResources().getString(R.string.countMoney), CountMoney);
                tvCountMoney.setText(countMoney);
            }
        });
        /*View.OnClickListener listener = QFoodShoppingCartExplAdapter.getShortCartListener();
        if (listener != null)
            tvCountGoods.setOnClickListener(listener);*/
        updateListView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden)
            Log.i("RHG", "Shoppingcart:hide");
        else
            Log.i("RHG", "Shoppingcart:show");
    }

    private void updateListView() {
        QFoodShoppingCartExplAdapter.setmData(shoppingCartBeanList);
        QFoodShoppingCartExplAdapter.notifyDataSetChanged();
        expandAll(shoppingCartBeanList.size());
    }

    private void showEmpty(boolean isEmpty) {
        if (isEmpty) {
            rlShoppingCartEmpty.setVisibility(View.VISIBLE);
            listShoppingCart.setVisibility(View.GONE);
            rlShoppingCartPay.setVisibility(View.GONE);
        } else {
            rlShoppingCartEmpty.setVisibility(View.GONE);
            listShoppingCart.setVisibility(View.VISIBLE);
            rlShoppingCartPay.setVisibility(View.VISIBLE);
        }
    }

    private void expandAll(int size) {
        for (int i = 0; i < size; i++) {
            listShoppingCart.expandGroup(i);
        }
    }


    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {

    }

    @OnClick({R.id.iv_shopping_cart_empty, R.id.tv_count_money})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_shopping_cart_empty:
                break;
            case R.id.tv_count_money:
                if (ShoppingCartUtil.hasSelectedGoods(shoppingCartBeanList))
                    ToastHelper.getInstance()._toast("去付款");
                else
                    ToastHelper.getInstance()._toast("亲，请选择商品！");
                break;
        }
    }
}
