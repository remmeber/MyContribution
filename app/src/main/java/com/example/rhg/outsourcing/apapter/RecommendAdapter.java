package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.rhg.outsourcing.Constants.AppConstants;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.model.SellerModel;

import java.util.List;

/**
 * Created by remember on 2016/5/6.
 */
public class RecommendAdapter extends RecyclerView.Adapter<SellerViewHolder> {
    private Context context;
    private List<SellerModel> mData;
    private FrameLayout frameLayout_item;
    private int type;

    public RecommendAdapter(Context context, List<SellerModel> mData, int type) {
        this.context = context;
        this.mData = mData;
        this.type = type;
    }

    @Override
    public SellerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (type == AppConstants.TypeHome)
            view = View.inflate(context, R.layout.recycleselleritem, null);
        else
            view = View.inflate(context, R.layout.sellnumberitem, null);
        return new SellerViewHolder(view, type);
    }

    @Override
    public void onBindViewHolder(final SellerViewHolder holder, int position) {
        holder.sellerName.setText(mData.get(position).getSellerName());
        holder.sellerImage.setImageResource(mData.get(position).getImageRid());
        holder.foodType.setText(mData.get(position).getFoodType());
        holder.sellerDistance.setText(mData.get(position).getSellerDistance());
        holder.frameLayout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onListItemClick != null)
                    onListItemClick.itemClick(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    private OnListItemClick onListItemClick;

    public void setOnListItemClick(OnListItemClick onListItemClick) {
        this.onListItemClick = onListItemClick;
    }

    public interface OnListItemClick {
        public void itemClick(View v, int position);
    }
}
