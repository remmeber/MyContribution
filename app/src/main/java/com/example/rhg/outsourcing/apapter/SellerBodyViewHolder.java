package com.example.rhg.outsourcing.apapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rhg.outsourcing.Constants.AppConstants;
import com.example.rhg.outsourcing.R;

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

    public SellerBodyViewHolder(View itemView, int type) {
        super(itemView);
        line = itemView.findViewById(R.id.lineShowInsellNumberItem);
        sellerForward = (ImageView) itemView.findViewById(R.id.imageshowInsellerItem);
        demandMoney = (TextView) itemView.findViewById(R.id.demandmoney);
        deliverMoney = (TextView) itemView.findViewById(R.id.delivermoney);

        homeFoward = (ImageView) itemView.findViewById(R.id.homeForward);
        sellerIcon = (ImageView) itemView.findViewById(R.id.sellerIcon);
        sellerName = (TextView) itemView.findViewById(R.id.storeName);
        foodType = (TextView) itemView.findViewById(R.id.foodType);
        sellerDistance = (TextView) itemView.findViewById(R.id.distanceText);
        sellerImage = (ImageView) itemView.findViewById(R.id.sellerImage);
        frameLayout_item = (LinearLayout) itemView.findViewById(R.id.item_layout);

        if (type == AppConstants.TypeSeller) {
            homeFoward.setVisibility(View.GONE);
            sellerIcon.setVisibility(View.GONE);
            line.setVisibility(View.VISIBLE);
            sellerForward.setVisibility(View.VISIBLE);
            demandMoney.setVisibility(View.VISIBLE);
            deliverMoney.setVisibility(View.VISIBLE);
        } else {
            homeFoward.setVisibility(View.VISIBLE);
            sellerIcon.setVisibility(View.VISIBLE);
            line.setVisibility(View.GONE);
            sellerForward.setVisibility(View.GONE);
            demandMoney.setVisibility(View.GONE);
            deliverMoney.setVisibility(View.GONE);
        }
    }
}
