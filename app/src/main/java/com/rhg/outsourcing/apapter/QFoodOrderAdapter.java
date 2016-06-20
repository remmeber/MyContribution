package com.rhg.outsourcing.apapter;

import android.content.Context;
import android.view.View;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.apapter.viewHolder.BodyViewHolder;
import com.rhg.outsourcing.bean.OrderUrlBean;
import com.rhg.outsourcing.constants.AppConstants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * desc:
 * author：remember
 * time：2016/6/19 18:02
 * email：1013773046@qq.com
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
        return R.layout.item_sell_layout;
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
                if (getOnRcvItemClickListener() != null)
                    getOnRcvItemClickListener().onItemClickListener(holder.getAdapterPosition(), null);
            }
        });
    }
}
