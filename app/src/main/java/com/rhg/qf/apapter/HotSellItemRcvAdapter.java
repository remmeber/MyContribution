package com.rhg.qf.apapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.bean.HotFoodUrlBean;
import com.rhg.qf.impl.RcvItemClickListener;
import com.rhg.qf.widget.MyRatingBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：rememberon 2016/6/19
 * 邮箱：1013773046@qq.com
 */
public class HotSellItemRcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HotFoodUrlBean.HotGoodsBean> hotGoodsBeanList;
    RcvItemClickListener<HotFoodUrlBean.HotGoodsBean> onRcvItemClickListener;

    public HotSellItemRcvAdapter(Context context, List<HotFoodUrlBean.HotGoodsBean> hotGoodsBeanList) {
        this.context = context;
        this.hotGoodsBeanList = hotGoodsBeanList;
    }

    public void setHotGoodsBeanList(List<HotFoodUrlBean.HotGoodsBean> hotGoodsBeanList) {
        this.hotGoodsBeanList = hotGoodsBeanList;
        notifyDataSetChanged();
    }

    public void setOnRcvItemClickListener(RcvItemClickListener<HotFoodUrlBean.HotGoodsBean>
                                                  onRcvItemClickListener) {
        this.onRcvItemClickListener = onRcvItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotSellItemViewHolder(View.inflate(context, R.layout.item_hot_sell, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final HotSellItemViewHolder hotSellItemViewHolder = (HotSellItemViewHolder) holder;
        hotSellItemViewHolder.hotSellLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRcvItemClickListener != null)
                    onRcvItemClickListener.onItemClickListener(hotSellItemViewHolder.getAdapterPosition(),
                            null);
            }
        });
        bindData(hotSellItemViewHolder, hotGoodsBeanList.get(position));
    }

    private void bindData(HotSellItemViewHolder hotSellItemViewHolder,
                          HotFoodUrlBean.HotGoodsBean hotGoodsBean) {
        hotSellItemViewHolder.hotSellMerchantName.setText(hotGoodsBean.getRName());
        /*ImageLoader.getInstance().displayImage(R.drawable.recommend_default_icon_1,
                hotSellItemViewHolder.hotSellFoodImage);*/
        hotSellItemViewHolder.hotSellFoodImage.setImageDrawable(
                context.getResources().getDrawable(R.drawable.recommend_default_icon_1));
        hotSellItemViewHolder.hotSellFoodName.setText(hotGoodsBean.getFName());
        hotSellItemViewHolder.hotSellDeliverRequire.setText(hotGoodsBean.getDelivery());
        hotSellItemViewHolder.hotSellDeliverMoney.setText(hotGoodsBean.getFee());
        hotSellItemViewHolder.hotSellRatingBar.setStarRating(Float.parseFloat(hotGoodsBean.getStars()));
        hotSellItemViewHolder.hotSellDeliverDistance.setText(hotGoodsBean.getDistance());
        hotSellItemViewHolder.hotSellTotalMoney.setText(String.format(
                context.getResources().getString(R.string.countMoney), hotGoodsBean.getPrice()
        ));
    }

    @Override
    public int getItemCount() {
        return hotGoodsBeanList.size() == 0 ? 0 : hotGoodsBeanList.size();
    }

    public class HotSellItemViewHolder extends RecyclerView.ViewHolder {
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

        HotSellItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
