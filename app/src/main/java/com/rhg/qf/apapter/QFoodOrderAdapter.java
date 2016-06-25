package com.rhg.qf.apapter;

import android.content.Context;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rhg.qf.R;
import com.rhg.qf.apapter.viewHolder.BodyViewHolder;
import com.rhg.qf.bean.OrderUrlBean;
import com.rhg.qf.constants.AppConstants;

import java.util.List;

/**
 * desc:
 * author：remember
 * time：2016/6/19 18:02
 * email：1013773046@qq.com
 */
public class QFoodOrderAdapter extends RecycleAbstractAdapter<OrderUrlBean.OrderBean> {
    Context context;

    public QFoodOrderAdapter(Context context, List<OrderUrlBean.OrderBean> mData) {
        super(context, mData);
        this.context = context;
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
    protected void bindBodyData(final BodyViewHolder holder, final OrderUrlBean.OrderBean data, int type) {
        holder.sellerName.setText("ddddddd");
        ImageLoader.getInstance().displayImage(AppConstants.images[0], holder.sellerImage);
        holder.tv_state.setText(data.getStyle());
        holder.tv_orderTime.setText(data.getOtime());
        holder.tv_orderTag.setText(data.getID());
        holder.tv_totalMoney.setText(String.format(context.getResources().getString(R.string.countMoney),
                data.getPrice()));
        holder.frameLayout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnRcvItemClickListener() != null)
                    getOnRcvItemClickListener().onItemClickListener(holder.getAdapterPosition(), data);
            }
        });
    }
}
