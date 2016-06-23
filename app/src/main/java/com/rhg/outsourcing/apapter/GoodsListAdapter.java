package com.rhg.outsourcing.apapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rhg.outsourcing.R;
import com.rhg.outsourcing.bean.ShopDetailUriBean;
import com.rhg.outsourcing.impl.RcvItemClickListener;

import java.util.List;

/**
 * desc:商品详情里面的商品适配器
 * author：remember
 * time：2016/6/22 15:08
 * email：1013773046@qq.com
 */
public class GoodsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ShopDetailUriBean.ShopDetailBean> shopDetailBeanList;

    public void setShopDetailBeanList(List<ShopDetailUriBean.ShopDetailBean> shopDetailBeanList) {
        this.shopDetailBeanList = shopDetailBeanList;
    }

    public GoodsListAdapter(Context context, List<ShopDetailUriBean.ShopDetailBean> shopDetailBeanList) {
        this.context = context;
        this.shopDetailBeanList = shopDetailBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View.inflate(context,R.layout.item_goods_layout,parent); TODO 用View.inflate会采用默认的warpcontent布局
        LayoutInflater inflater = LayoutInflater.from(context);
        return new GoodsDetailViewHolder(inflater.inflate(R.layout.item_goods_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoodsDetailViewHolder goodsDetailViewHolder = (GoodsDetailViewHolder) holder;
        ShopDetailUriBean.ShopDetailBean shopDetailBean = shopDetailBeanList.get(position);
        bindData(goodsDetailViewHolder, shopDetailBean);
    }

    private void bindData(final GoodsDetailViewHolder goodsDetailViewHolder,
                          final ShopDetailUriBean.ShopDetailBean shopDetailBean) {
        ImageLoader.getInstance().displayImage(shopDetailBean.getPic(),
                goodsDetailViewHolder.goodsImage);
        goodsDetailViewHolder.goodsName.setText(shopDetailBean.getName());
        goodsDetailViewHolder.goodsPrice.setText(String.format(context.getResources().getString(R.string.countMoney),
                shopDetailBean.getPrice()));
        goodsDetailViewHolder.goodsSellNum.setText(shopDetailBean.getMonthlySales());
        goodsDetailViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onGoodsItemClickListener != null)
                    onGoodsItemClickListener.onItemClickListener(goodsDetailViewHolder.getAdapterPosition(),
                            shopDetailBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopDetailBeanList == null ? 0 : shopDetailBeanList.size();
    }


    private RcvItemClickListener<ShopDetailUriBean.ShopDetailBean> onGoodsItemClickListener;

    public void setOnGoodsItemClickListener(RcvItemClickListener<ShopDetailUriBean.ShopDetailBean>
                                                    onGoodsItemClickListener) {
        this.onGoodsItemClickListener = onGoodsItemClickListener;
    }

    private class GoodsDetailViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView goodsImage;
        private TextView goodsName;
        private TextView goodsPrice;
        private TextView goodsSellNum;

        public GoodsDetailViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.ll_goods_item);
            goodsImage = (ImageView) itemView.findViewById(R.id.iv_goods_detail);
            goodsName = (TextView) itemView.findViewById(R.id.tv_goods_detail_name);
            goodsPrice = (TextView) itemView.findViewById(R.id.tv_goods_detail_price);
            goodsSellNum = (TextView) itemView.findViewById(R.id.tv_goods_detail_sell_num);
        }
    }
}
