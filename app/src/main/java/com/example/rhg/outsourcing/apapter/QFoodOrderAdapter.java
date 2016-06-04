package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.viewHolder.BodyViewHolder;
import com.example.rhg.outsourcing.bean.OrderUrlBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by remember on 2016/5/28.
 */
public class QFoodOrderAdapter extends RecycleAbstractAdapter<OrderUrlBean.OrderBean> {

    public QFoodOrderAdapter(Context context, List<OrderUrlBean.OrderBean> mData) {
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
    protected void bindBodyData(final BodyViewHolder holder, OrderUrlBean.OrderBean data, int type) {
        holder.sellerName.setText("ddddddd");
        ImageLoader.getInstance().displayImage(AppConstants.images[0], holder.sellerImage);
        holder.tv_state.setText(data.getStyle());
        holder.tv_orderTime.setText(data.getOtime());
        holder.tv_orderTag.setText(data.getID());
        holder.tv_totalMoney.setText(data.getPrice());
        holder.frameLayout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnListItemClick() != null)
                    getOnListItemClick().itemClick(v, holder.getAdapterPosition());
            }
        });
    }
}
