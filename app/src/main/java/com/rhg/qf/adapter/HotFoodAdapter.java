package com.rhg.qf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rhg.qf.R;
import com.rhg.qf.adapter.viewHolder.HotFoodViewHolder;
import com.rhg.qf.bean.HotFoodUrlBean;
import com.rhg.qf.impl.RcvItemClickListener;

import java.util.List;

/*
 *desc 热销单品页面以及热销搜索适配器
 *author rhg
 *time 2016/7/7 22:16
 *email 1013773046@qq.com
 */
public class HotFoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HotFoodUrlBean.HotFoodBean> hotFoodBeanList;
    RcvItemClickListener<HotFoodUrlBean.HotFoodBean> onRcvItemClickListener;

    public HotFoodAdapter(Context context, List<HotFoodUrlBean.HotFoodBean> hotFoodBeanList) {
        this.context = context;
        this.hotFoodBeanList = hotFoodBeanList;
    }

    public void setHotFoodBeanList(List<HotFoodUrlBean.HotFoodBean> hotFoodBeanList) {
        this.hotFoodBeanList = hotFoodBeanList;
        notifyDataSetChanged();
    }

    public void setOnRcvItemClickListener(RcvItemClickListener<HotFoodUrlBean.HotFoodBean>
                                                  onRcvItemClickListener) {
        this.onRcvItemClickListener = onRcvItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotFoodViewHolder(LayoutInflater.from(context).inflate(R.layout.item_hot_sell,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final HotFoodViewHolder hotFoodViewHolder = (HotFoodViewHolder) holder;
        hotFoodViewHolder.hotSellLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRcvItemClickListener != null)
                    onRcvItemClickListener.onItemClickListener(hotFoodViewHolder.getAdapterPosition(),
                            hotFoodBeanList.get(hotFoodViewHolder.getAdapterPosition()));
            }
        });
        bindData(hotFoodViewHolder, hotFoodBeanList.get(position));
    }

    private void bindData(HotFoodViewHolder hotFoodViewHolder,
                          HotFoodUrlBean.HotFoodBean hotFoodBean) {
        hotFoodViewHolder.hotSellMerchantName.setText(hotFoodBean.getRName());
        ImageLoader.getInstance().displayImage(hotFoodBean.getPic(),
                hotFoodViewHolder.hotSellFoodImage);
        /*hotFoodViewHolder.hotSellFoodImage.setImageDrawable(
                context.getResources().getDrawable(R.drawable.recommend_default_icon_1));*/
        hotFoodViewHolder.hotSellFoodName.setText(hotFoodBean.getFName());
        hotFoodViewHolder.hotSellDeliverRequire.setText(hotFoodBean.getDelivery());
        hotFoodViewHolder.hotSellDeliverMoney.setText(hotFoodBean.getFee());
        hotFoodViewHolder.hotSellRatingBar.setStarRating(Float.parseFloat(hotFoodBean.getStars()));
//        hotFoodViewHolder.hotSellDeliverDistance.setText(hotFoodBean.getDistance());
        hotFoodViewHolder.hotSellTotalMoney.setText(String.format(
                context.getResources().getString(R.string.countMoney), hotFoodBean.getPrice()
        ));
    }

    @Override
    public int getItemCount() {
        return hotFoodBeanList == null ? 0 : hotFoodBeanList.size();
    }

    /*public class HotFoodViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.hot_sell_merchant_name)
        TextView hotSellMerchantName;
        @Bind(R.id.hot_sell_food_image)
        ImageView hotSellFoodImage;
        @Bind(R.id.hot_sell_food_name)
        TextView hotSellFoodName;
        @Bind(R.id.hot_sell_deliver_require)
        TextView hotSellDeliverRequire;
        @Bind(R.id.hot_sell_deliver_money)
        TextView hotSellDeliverMoney;
        @Bind(R.id.hot_sell_rating_bar)
        MyRatingBar hotSellRatingBar;
        @Bind(R.id.hot_sell_deliver_distance)
        TextView hotSellDeliverDistance;
        @Bind(R.id.hot_sell_total_money)
        TextView hotSellTotalMoney;
        @Bind(R.id.hot_sell_ll)
        LinearLayout hotSellLl;

        HotFoodViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }*/
}
