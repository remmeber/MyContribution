package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.bean.HotGoodsUrlBean;
import com.example.rhg.outsourcing.impl.RcvItemClickListener;
import com.example.rhg.outsourcing.widget.MyRatingBar;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：rememberon 2016/6/19
 * 邮箱：1013773046@qq.com
 */
public class HotSellItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HotGoodsUrlBean.HotGoodsBean> hotGoodsBeanList;
    RcvItemClickListener<HotGoodsUrlBean.HotGoodsBean> onRcvItemClickListener;

    public HotSellItemAdapter(Context context, List<HotGoodsUrlBean.HotGoodsBean> hotGoodsBeanList) {
        this.context = context;
        this.hotGoodsBeanList = hotGoodsBeanList;
    }

    public void setHotGoodsBeanList(List<HotGoodsUrlBean.HotGoodsBean> hotGoodsBeanList) {
        this.hotGoodsBeanList = hotGoodsBeanList;
        notifyDataSetChanged();
    }

    public void setOnRcvItemClickListener(RcvItemClickListener<HotGoodsUrlBean.HotGoodsBean>
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
                          HotGoodsUrlBean.HotGoodsBean hotGoodsBean) {
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
