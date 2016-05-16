package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.ExpandableListViewAdapter;
import com.example.rhg.outsourcing.model.ShoppingCartBean;
import com.example.rhg.outsourcing.utils.ShoppingCartUtil;
import com.example.rhg.outsourcing.widget.MyExpandListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/4/28.
 */
public class ShoppingCartFragment extends SuperFragment {
    private static final String TAG = "ShoppingCartFragment";
    View view;
    List<ShoppingCartBean> shoppingCartBeanList;
    List<ShoppingCartBean.Goods> goodsList;
    MyExpandListView expandableListView;
    ExpandableListViewAdapter expandableListViewAdapter;
    TextView tvCountGoods;
    TextView tvCountMoney;
    //-----------------根据需求创建相应的presenter----------------------------------------------------

    //----------------------------------------------------------------------------------------------
    public ShoppingCartFragment() {
        Log.i(TAG, "ShoppingCartFragment ");
        shoppingCartBeanList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
            shoppingCartBean.setMerchantName("iiiii");
            goodsList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                ShoppingCartBean.Goods goods = new ShoppingCartBean.Goods();
                goods.setGoodsLogoUrl(R.drawable.recommend_default_icon_1);
                goods.setGoodsName("" + j);
                goods.setPrice("" + j * 2);
                goods.setProductID("20160510");
                goods.setNumber("1");
                goodsList.add(goods);
            }
            shoppingCartBean.setGoods(goodsList);
            shoppingCartBeanList.add(shoppingCartBean);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shoppingcartlayout, container, false);
        expandableListView = (MyExpandListView) view.findViewById(R.id.shoppinglist);
        tvCountMoney = (TextView) view.findViewById(R.id.tvCountMoney);
        tvCountGoods = (TextView) view.findViewById(R.id.tvCountGoods);
        expandableListViewAdapter = new ExpandableListViewAdapter(getContext());
        expandableListView.setAdapter(expandableListViewAdapter);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        expandableListViewAdapter.setDataChangeListener(new ExpandableListViewAdapter.DataChangeListener() {
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
        View.OnClickListener listener = expandableListViewAdapter.getShortCartListener();
        if (listener != null)
            tvCountGoods.setOnClickListener(listener);
        updateListView();
        return view;
    }

    private void updateListView() {
        expandableListViewAdapter.setmData(shoppingCartBeanList);
        expandableListViewAdapter.notifyDataSetChanged();
        expandAll(shoppingCartBeanList.size());
    }

    private void showEmpty(boolean isEmpty) {
        if (isEmpty) {
            expandableListView.setVisibility(View.GONE);
        } else {
            expandableListView.setVisibility(View.VISIBLE);
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
