package com.rhg.qf.ui.fragment;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.ui.activity.PayActivity;
import com.rhg.qf.adapter.QFoodShoppingCartExplAdapter;
import com.rhg.qf.bean.AddressUrlBean;
import com.rhg.qf.bean.OrderUrlBean;
import com.rhg.qf.bean.PayModel;
import com.rhg.qf.bean.ShoppingCartBean;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.mvp.presenter.GetAddressPresenter;
import com.rhg.qf.mvp.presenter.OrdersPresenter;
import com.rhg.qf.utils.AccountUtil;
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
    List<ShoppingCartBean> shoppingCartBeanList;
    List<ShoppingCartBean.Goods> goodsList;
    QFoodShoppingCartExplAdapter QFoodShoppingCartExplAdapter;
    OrdersPresenter getOrdersPresenter;

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
    private GetAddressPresenter getAddressPresenter;
    private String userId;
    //-----------------根据需求创建相应的presenter----------------------------------------------------

    public ShoppingCartFragment() {
        shoppingCartBeanList = new ArrayList<>();
        /*for (int i = 0; i < 6; i++) {
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
        }*/
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public int getLayoutResId() {
        return R.layout.shopping_cart_layout;
    }


    @Override
    public void loadData() {
        getOrdersPresenter = new OrdersPresenter(this);
        if (AccountUtil.getInstance().hasAccount()) {
            userId = AccountUtil.getInstance().getUserID();
            getOrdersPresenter.getOrders(AppConstants.TABLE_ORDER, userId, AppConstants.USER_ORDER_UNPAID);
        } else {
            ToastHelper.getInstance()._toast("当前用户未登录");
            shoppingCartBeanList.clear();
            updateListView();
        }
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData() {
        fl_tab.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorBlueNormal));
        tbCenterTV.setText(getResources().getString(R.string.shoppingCart));
        tbRightTV.setText(getResources().getString(R.string.tvEdit));
        srlShoppingCart.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getContext(), R.color.colorBlueNormal));
        srlShoppingCart.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (AccountUtil.getInstance().hasAccount()) {
                    getOrdersPresenter.getOrders(AppConstants.TABLE_ORDER, userId, AppConstants.USER_ORDER_UNPAID);
                } else {
                    ToastHelper.getInstance()._toast("当前用户未登录");
                }
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
        updateListView();
    }

    private void updateListView() {
        QFoodShoppingCartExplAdapter.setmData(shoppingCartBeanList);
        QFoodShoppingCartExplAdapter.notifyDataSetChanged();
        expandAll(shoppingCartBeanList.size());
        srlShoppingCart.setRefreshing(false);
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
        if (o instanceof AddressUrlBean.AddressBean) {
            AddressUrlBean.AddressBean addressBean = (AddressUrlBean.AddressBean) o;
            createOrderAndToPay(addressBean);
            return;
        }
        if (o instanceof String && "error".equals(o)) {
            ToastHelper.getInstance()._toast(o.toString());
            return;
        }
        ShoppingCartUtil.delAllGoods();
        shoppingCartBeanList.clear();
        for (OrderUrlBean.OrderBean orderBean : (List<OrderUrlBean.OrderBean>) o) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
            List<ShoppingCartBean.Goods> goodsList = new ArrayList<>();
            ShoppingCartBean.Goods goods = new ShoppingCartBean.Goods();
            goods.setGoodsName(orderBean.getRName());
            goods.setGoodsLogoUrl(orderBean.getPic());
            goods.setPrice(orderBean.getPrice());
            goods.setGoodsID(orderBean.getID());
            goods.setNumber("1");
            goodsList.add(goods);
            shoppingCartBean.setGoods(goodsList);
            shoppingCartBeanList.add(shoppingCartBean);
            ShoppingCartUtil.addGoodToCart(orderBean.getID(), orderBean.getRName());
        }
        updateListView();
    }

    private void createOrderAndToPay(AddressUrlBean.AddressBean addressBean) {
        Intent intent = new Intent(getActivity(),
                PayActivity.class);
        PayModel payModel = new PayModel();
        payModel.setReceiver(addressBean.getName());
        payModel.setPhone(addressBean.getPhone());
        payModel.setAddress(addressBean.getAddress().concat(addressBean.getDetail()));
        ArrayList<PayModel.PayBean> payBeen = new ArrayList<>();
        List<ShoppingCartBean.Goods> goodsList = ShoppingCartUtil.getSelectGoods(shoppingCartBeanList);
        for (ShoppingCartBean.Goods _goods : goodsList) {
            PayModel.PayBean _pay = new PayModel.PayBean();
            _pay.setMerchantName(_goods.getGoodsName());
            _pay.setProductName(_goods.getGoodsName());
            _pay.setChecked(true);
            _pay.setProductId(_goods.getGoodsID());
            _pay.setProductNumber(_goods.getNumber());
            _pay.setProductPic(_goods.getGoodsLogoUrl());
            _pay.setProductPrice(_goods.getPrice());
            payBeen.add(_pay);
        }
        payModel.setPayBeanList(payBeen);
        intent.putExtra(AppConstants.KEY_PARCELABLE, payModel);
        intent.putExtra(AppConstants.ORDER_STYLE, AppConstants.USER_ORDER_UNPAID);
        startActivity(intent);
    }

    @OnClick({R.id.iv_shopping_cart_empty, R.id.tv_count})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_shopping_cart_empty:
                break;
            case R.id.tv_count:
                if (ShoppingCartUtil.hasSelectedGoods(shoppingCartBeanList)) {
                    if (getAddressPresenter == null)
                        getAddressPresenter = new GetAddressPresenter(this);
                    getAddressPresenter.getAddress(AppConstants.TABLE_DEFAULT_ADDRESS);
                } else
                    ToastHelper.getInstance().displayToastWithQuickClose("亲，请选择商品！");
                break;
        }
    }
}