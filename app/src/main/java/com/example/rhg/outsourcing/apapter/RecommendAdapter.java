package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.model.ImageModel;
import com.example.rhg.outsourcing.model.SellerModel;

import java.util.List;

/**
 * Created by remember on 2016/5/6.
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendListViewHolder> {
    private Context context;
    private List<SellerModel> mData;
    private FrameLayout frameLayout_item;

    public RecommendAdapter(Context context, List<SellerModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public RecommendListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recyclerecommenditem,null);
        RecommendListViewHolder recommendListViewHolder = new RecommendListViewHolder(view);
        return recommendListViewHolder;
    }

    @Override
    public void onBindViewHolder(RecommendListViewHolder holder, final int position) {
        holder.sellerName.setText(mData.get(position).getSellerName());
        holder.sellerImage.setImageResource(mData.get(position).getImageRid());
        holder.foodType.setText(mData.get(position).getFoodType());
        holder.sellerDistance.setText(mData.get(position).getSellerDistance());
        holder.frameLayout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onListItemClick!=null)
                    onListItemClick.itemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
    private OnListItemClick onListItemClick;
    public void setOnListItemClick(OnListItemClick onListItemClick){
        this.onListItemClick = onListItemClick;
    }
    public interface OnListItemClick{
        public void itemClick(View v,int position);
    }
}
