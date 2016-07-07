package com.rhg.qf.adapter;

import android.content.Context;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rhg.qf.R;
import com.rhg.qf.adapter.viewHolder.BodyViewHolder;
import com.rhg.qf.bean.MerchantUrlBean;

import java.util.List;
import java.util.Locale;

/*
 *desc 商家搜索适配器
 *author rhg
 *time 2016/7/7 21:50
 *email 1013773046@qq.com
 */
public class SearchMerchantAdapter extends RecycleAbstractAdapter<MerchantUrlBean.MerchantBean> {

    Context context;


    public SearchMerchantAdapter(Context context, List<MerchantUrlBean.MerchantBean> mData) {
        super(context, mData);
        this.context = context;
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_sell_body;
    }

    @Override
    protected void bindBodyData(final BodyViewHolder holder, MerchantUrlBean.MerchantBean data, int type) {
        holder.sellerName.setText(data.getName());
        ImageLoader.getInstance().displayImage(data.getPic(), holder.sellerImage);
        holder.sellerDistance.setText(String.format(Locale.ENGLISH,
                context.getResources().getString(R.string.tvDeliverRequire), data.getDistance()));
        holder.foodType.setText(data.getStyle());
        holder.deliverMoney.setText(String.format(Locale.ENGLISH,
                context.getResources().getString(R.string.tvDeliverFee), data.getFee()));
        holder.demandMoney.setText(String.format(Locale.ENGLISH,
                context.getResources().getString(R.string.tvDistance), data.getDelivery()));
        holder.frameLayout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getOnRcvItemClickListener() != null)
                    getOnRcvItemClickListener().onItemClickListener(holder.getAdapterPosition(), null);
            }
        });

    }
}
