package com.example.rhg.outsourcing.apapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;

/**
 * Created by remember on 2016/5/6.
 */
public class RecommendListViewHolder extends RecyclerView.ViewHolder{
    public TextView sellerName;
    public TextView foodType;
    public TextView sellerDistance;
    public ImageView sellerImage;
    public FrameLayout frameLayout_item;
    public RecommendListViewHolder(View itemView) {
        super(itemView);
        sellerName = (TextView)itemView.findViewById(R.id.sellerName);
        foodType = (TextView)itemView.findViewById(R.id.foodType);
        sellerDistance = (TextView)itemView.findViewById(R.id.distanceText);
        sellerImage = (ImageView)itemView.findViewById(R.id.sellerImage);
        frameLayout_item = (FrameLayout)itemView.findViewById(R.id.item_layout);
    }
}
