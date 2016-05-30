package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.viewHolder.BodyViewHolder;
import com.example.rhg.outsourcing.apapter.viewHolder.HeaderViewHolder;
import com.example.rhg.outsourcing.bean.QFoodAllSellerBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by remember on 2016/5/28.
 */
public class QFoodMerchantAdapter extends RecycleAbstractAdapter<QFoodAllSellerBean> {



    public QFoodMerchantAdapter(Context context, List<QFoodAllSellerBean> mData) {
        super(context, mData);
    }

    public void setSuperContext(Context context){
        setContext(context);

    }

    @Override
    public boolean getHasHead() {
        return true;
    }

    @Override
    public int getDisplayType() {
        return AppConstants.TypeSeller;
    }

    @Override
    protected int getLayoutResId(int viewtype) {
        if (viewtype == AppConstants.TypeHeader)
            return R.layout.all_store_header_layout;
        return R.layout.sellitem_layout;
    }

    @Override
    public void bindHeadData(final HeaderViewHolder holder, QFoodAllSellerBean data, int type) {
        ImageLoader.getInstance().displayImage(data.getImageUrl(), holder.headerstoreimage);
        holder.headerstorename.setText(data.getMerchantName());
        holder.headerdemandmoney.setText("满20元起");
        holder.headerdelivermoney.setText("配送费1元");
        holder.headerdistance.setText(data.getSellerDistance());
        holder.headerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnListItemClick() != null)
                    getOnListItemClick().itemClick(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    protected void bindBodyData(final BodyViewHolder holder, QFoodAllSellerBean data, int type) {
        holder.sellerName.setText(data.getMerchantName());
        ImageLoader.getInstance().displayImage(data.getImageUrl(), holder.sellerImage);
        holder.sellerDistance.setText(data.getSellerDistance());
        holder.foodType.setText(data.getFoodType());
        holder.deliverMoney.setText("配送费1元");
        holder.demandMoney.setText("满20元起");
        holder.frameLayout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnListItemClick() != null)
                    getOnListItemClick().itemClick(v, holder.getAdapterPosition());
            }
        });

    }
}
