package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.bean.GoodsDetailUrlBean;
import com.example.rhg.outsourcing.bean.ShopDetailUriBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by remember on 2016/5/20.
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
//        View.inflate(context,R.layout.item_goods_layout,parent);
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
                          ShopDetailUriBean.ShopDetailBean shopDetailBean) {
        ImageLoader.getInstance().displayImage(AppConstants.images[0],
                goodsDetailViewHolder.goodsImage);
        goodsDetailViewHolder.goodsName.setText(shopDetailBean.getName());
        goodsDetailViewHolder.goodsPrice.setText(shopDetailBean.getPrice());
        goodsDetailViewHolder.goodsSellNum.setText(shopDetailBean.getMonthlySales());
        goodsDetailViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onGoodsItemClickListener != null)
                    onGoodsItemClickListener.onItemClick(v, goodsDetailViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopDetailBeanList == null ? 0 : shopDetailBeanList.size();
    }


    /**
     * todo 开放接口
     */
    public interface GoodsItemClickListener {
        public void onItemClick(View v, int position);
    }

    private GoodsItemClickListener onGoodsItemClickListener;

    public void setGoodsItemClickListener(GoodsItemClickListener onGoodsItemClickListener) {
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
