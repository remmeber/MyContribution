package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.view.View;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.viewHolder.BodyViewHolder;
import com.example.rhg.outsourcing.bean.RecommendListBean;
import com.example.rhg.outsourcing.bean.RecommendListUrlBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by remember on 2016/5/28.
 */
public class HomeRecycleAdapter extends RecycleAbstractAdapter<RecommendListUrlBean.RecommendShopBeanEntity> {
    List<RecommendListUrlBean.RecommendShopBeanEntity> recommendListBean;


    public HomeRecycleAdapter(Context context) {
        super(context);
    }

    public void setRecommendListBean(List<RecommendListUrlBean.RecommendShopBeanEntity> recommendListBean) {
        this.recommendListBean = recommendListBean;
        setmData(this.recommendListBean);
    }

    public HomeRecycleAdapter(Context context, List<RecommendListUrlBean.RecommendShopBeanEntity> recommendListBean) {
        super(context, recommendListBean);
        this.recommendListBean = recommendListBean;
    }

    @Override
    public int getDisplayType() {
        return AppConstants.TypeHome;
    }

    @Override
    protected int getLayoutResId(int viewtype) {
        return R.layout.sellitem_body;
    }

    @Override
    protected void bindBodyData(final BodyViewHolder holder, RecommendListUrlBean.RecommendShopBeanEntity data, int type) {
        holder.sellerName.setText(data.getName());
        ImageLoader.getInstance().displayImage(data.getSrc(), holder.sellerImage);
        holder.sellerDistance.setText(data.getDistance());
//        holder.foodType.setText(data.getFoodType());
        if (getOnListItemClick() == null)
            holder.frameLayout_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnListItemClick().itemClick(v, holder.getAdapterPosition());
                }
            });
        /*holder.sellerName.setText(data.getMerchantName());
        holder.sellerImage.setImageResource(data.get);
        if (type != AppConstants.TypeOrder) {
            holder.sellerDistance.setText(data.getSellerDistance());
            holder.foodType.setText(data.getFoodType());
        } else {
            holder.tv_state.setText(data.getTv_state());
            holder.tv_orderTime.setText(data.getTv_orderTime());
            holder.tv_orderTag.setText(data.getTv_orderTag());
            holder.tv_totalMoney.setText(data.getTv_totalMoney());
        }
        if (type == AppConstants.TypeSeller) {
            holder.deliverMoney.setText("配送费1元");
            holder.demandMoney.setText("满20元起");*/
//        }

        /*holder.frameLayout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onListItemClick != null)
                    onListItemClick.itemClick(v, holder.getAdapterPosition());
            }
        });*/
    }
}
