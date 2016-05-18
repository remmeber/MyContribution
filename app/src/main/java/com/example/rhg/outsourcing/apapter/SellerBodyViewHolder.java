package com.example.rhg.outsourcing.apapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.constants.AppConstants;

/**
 * Created by remember on 2016/5/6.
 */
public class SellerBodyViewHolder extends RecyclerView.ViewHolder {
    //这四个在商家页面显示
    private View line;
    private ImageView sellerForward;
    public TextView demandMoney;
    public TextView deliverMoney;
    //以下均在主页中显示
    private ImageView homeFoward;//TODO 在商家页面隐藏
    private ImageView sellerIcon;//TODO 在商家页面隐藏
    public ImageView sellerImage;//指店铺的图片
    public TextView sellerName;
    public TextView foodType;
    public TextView sellerDistance;
    public LinearLayout frameLayout_item;
    //以下均在我的订单中显示
    private LinearLayout ly_totalCount;//合计
    public TextView tv_totalCount;//合计总数
    public TextView tv_orderTime;//订单时间
    public TextView tv_orderNumber;//订单号
    public TextView tv_state;//状态{已完成，待付款}

    public SellerBodyViewHolder(View itemView, int type) {
        super(itemView);
        line = itemView.findViewById(R.id.lineShowInsellNumberItem);
        sellerForward = (ImageView) itemView.findViewById(R.id.imageshowInsellerItem);
        demandMoney = (TextView) itemView.findViewById(R.id.tv_required);
        deliverMoney = (TextView) itemView.findViewById(R.id.tv_money);

        homeFoward = (ImageView) itemView.findViewById(R.id.homeForward);
        sellerIcon = (ImageView) itemView.findViewById(R.id.sellerIcon);
        sellerName = (TextView) itemView.findViewById(R.id.storeName);
        foodType = (TextView) itemView.findViewById(R.id.foodType);
        sellerDistance = (TextView) itemView.findViewById(R.id.tv_distance);
        sellerImage = (ImageView) itemView.findViewById(R.id.sellerImage);
        frameLayout_item = (LinearLayout) itemView.findViewById(R.id.item_layout);

        ly_totalCount = (LinearLayout) itemView.findViewById(R.id.ly_totalCount);
        tv_totalCount = (TextView) itemView.findViewById(R.id.tv_totalCount);
        tv_orderTime = (TextView) itemView.findViewById(R.id.tv_orderTime);
        tv_orderNumber = (TextView) itemView.findViewById(R.id.tv_orderNumber);
        tv_state = (TextView) itemView.findViewById(R.id.tv_state);
        switch (type){
            case AppConstants.TypeHome:
                sellerIcon.setVisibility(View.VISIBLE);
                foodType.setVisibility(View.VISIBLE);
                homeFoward.setVisibility(View.VISIBLE);
                sellerDistance.setVisibility(View.VISIBLE);
                tv_state.setVisibility(View.GONE);
                sellerForward.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                tv_orderTime.setVisibility(View.GONE);
                tv_orderNumber.setVisibility(View.GONE);
                demandMoney.setVisibility(View.GONE);
                deliverMoney.setVisibility(View.GONE);
                ly_totalCount.setVisibility(View.GONE);
                break;
            case AppConstants.TypeSeller:
                foodType.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                sellerForward.setVisibility(View.VISIBLE);
                demandMoney.setVisibility(View.VISIBLE);
                deliverMoney.setVisibility(View.VISIBLE);
                sellerDistance.setVisibility(View.VISIBLE);
                homeFoward.setVisibility(View.GONE);
                sellerIcon.setVisibility(View.GONE);
                ly_totalCount.setVisibility(View.GONE);
                tv_orderTime.setVisibility(View.GONE);
                tv_orderNumber.setVisibility(View.GONE);
                tv_state.setVisibility(View.GONE);
                break;
            case AppConstants.TypeOrder:
                sellerIcon.setVisibility(View.VISIBLE);
                tv_state.setVisibility(View.VISIBLE);
                sellerForward.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                tv_orderTime.setVisibility(View.VISIBLE);
                tv_orderNumber.setVisibility(View.VISIBLE);
                ly_totalCount.setVisibility(View.VISIBLE);
                foodType.setVisibility(View.GONE);
                homeFoward.setVisibility(View.GONE);
                demandMoney.setVisibility(View.GONE);
                deliverMoney.setVisibility(View.GONE);
                sellerDistance.setVisibility(View.GONE);
                break;
        }
    }
}
