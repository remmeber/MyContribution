package com.example.rhg.outsourcing.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.QFoodShoppingCartExplAdapter;
import com.example.rhg.outsourcing.bean.ShoppingCartBean;
import com.example.rhg.outsourcing.widget.MyExpandListView;

import java.util.ArrayList;
import java.util.List;

/**
 *desc:购物车fm
 *author：remember
 *time：2016/5/28 16:49
 *email：1013773046@qq.com
 */
public class ShoppingCartFragment extends SuperFragment {
    private static final String TAG = "ShoppingCartFragment";
    List<ShoppingCartBean> shoppingCartBeanList;
    List<ShoppingCartBean.Goods> goodsList;
    MyExpandListView expandableListView;
    RelativeLayout rlShoppingCartEmpty;
    RelativeLayout rlShoppingCartPay;
    QFoodShoppingCartExplAdapter QFoodShoppingCartExplAdapter;
    TextView tvCountGoods;
    TextView tvCountMoney;


    FrameLayout fl_tab;
    TextView tbCenterTV;
    LinearLayout tbRightLL;
    TextView tbRightTV;
    //-----------------根据需求创建相应的presenter----------------------------------------------------

    //----------------------------------------------------------------------------------------------
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

    @Override
    public int getLayoutResId() {
        return R.layout.shopping_cart_layout;
    }


    @Override
    protected void initView(View view) {
        fl_tab = (FrameLayout)view.findViewById(R.id.fl_tab);
        tbCenterTV = (TextView)view.findViewById(R.id.tb_center_tv);
        tbRightLL = (LinearLayout)view.findViewById(R.id.tb_right_ll);
        tbRightTV = (TextView)view.findViewById(R.id.tb_right_tv);

        expandableListView = (MyExpandListView) view.findViewById(R.id.list_shopping_cart);
        tvCountMoney = (TextView) view.findViewById(R.id.tv_count_money);
        tvCountGoods = (TextView) view.findViewById(R.id.tv_count);
        rlShoppingCartEmpty = (RelativeLayout) view.findViewById(R.id.rl_shopping_cart_empty);
        rlShoppingCartPay = (RelativeLayout) view.findViewById(R.id.rl_shopping_cart_pay);
    }

    @Override
    protected void initData() {
        fl_tab.setBackgroundColor(getResources().getColor(R.color.colorActiveGreen));
        tbCenterTV.setText(getResources().getString(R.string.shoppingcar));
        tbRightTV.setText("编辑");

        QFoodShoppingCartExplAdapter = new QFoodShoppingCartExplAdapter(getContext());
        expandableListView.setAdapter(QFoodShoppingCartExplAdapter);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
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
        View.OnClickListener listener = QFoodShoppingCartExplAdapter.getShortCartListener();
        if (listener != null)
            tvCountGoods.setOnClickListener(listener);
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
            expandableListView.setVisibility(View.GONE);
            rlShoppingCartPay.setVisibility(View.GONE);
        } else {
            rlShoppingCartEmpty.setVisibility(View.GONE);
            expandableListView.setVisibility(View.VISIBLE);
            rlShoppingCartPay.setVisibility(View.VISIBLE);
        }
    }

    private void expandAll(int size) {
        for (int i = 0; i < size; i++) {
            expandableListView.expandGroup(i);
        }
    }


    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {

    }

}
