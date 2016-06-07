package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.viewHolder.BodyViewHolder;
import com.example.rhg.outsourcing.apapter.viewHolder.HeaderViewHolder;
import com.example.rhg.outsourcing.bean.MerchantUrlBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 *desc:
 *author：remember
 *time：2016/6/6 14:14
 *email：1013773046@qq.com
 */
public class QFoodMerchantAdapter extends RecycleAbstractAdapter<MerchantUrlBean.MerchantBean> {



    public QFoodMerchantAdapter(Context context, List<MerchantUrlBean.MerchantBean> mData) {
        super(context, mData);
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
    public void bindHeadData(final HeaderViewHolder holder, MerchantUrlBean.MerchantBean data, int type) {
        ImageLoader.getInstance().displayImage(data.getPic(), holder.headerstoreimage);
        holder.headerstorename.setText(data.getName());
        holder.headerdemandmoney.setText(data.getDelivery());
        holder.headerdelivermoney.setText(data.getFee());
        holder.headerdistance.setText(data.getDistance());
        holder.headerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnListItemClick() != null)
                    getOnListItemClick().itemClick(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    protected void bindBodyData(final BodyViewHolder holder, MerchantUrlBean.MerchantBean data, int type) {
        holder.sellerName.setText(data.getName());
        ImageLoader.getInstance().displayImage(data.getPic(), holder.sellerImage);
        holder.sellerDistance.setText(data.getDistance());
        holder.foodType.setText(data.getStyle());
        holder.deliverMoney.setText(data.getFee());
        holder.demandMoney.setText(data.getDelivery());
        holder.frameLayout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnListItemClick() != null)
                    getOnListItemClick().itemClick(v, holder.getAdapterPosition());
            }
        });

    }
}
