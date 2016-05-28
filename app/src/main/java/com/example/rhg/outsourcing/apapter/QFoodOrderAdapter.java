package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.viewHolder.BodyViewHolder;
import com.example.rhg.outsourcing.bean.OrderBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by remember on 2016/5/28.
 */
public class QFoodOrderAdapter extends RecycleAbstractAdapter<OrderBean> {

    public QFoodOrderAdapter(Context context, List<OrderBean> mData) {
        super(context, mData);
    }

    @Override
    public int getDisplayType() {
        return AppConstants.TypeOrder;
    }

    @Override
    protected int getLayoutResId(int viewtype) {
        return R.layout.sellitem_layout;
    }

    @Override
    protected void bindBodyData(final BodyViewHolder holder, OrderBean data, int type) {
        holder.sellerName.setText(data.getMerchantName());
        ImageLoader.getInstance().displayImage(data.getImageUrl(), holder.sellerImage);
        holder.tv_state.setText(data.getTv_state());
        holder.tv_orderTime.setText(data.getTv_orderTime());
        holder.tv_orderTag.setText(data.getTv_orderTag());
        holder.tv_totalMoney.setText(data.getTv_totalMoney());
        holder.frameLayout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnListItemClick() != null)
                    getOnListItemClick().itemClick(v, holder.getAdapterPosition());
            }
        });
    }
}
