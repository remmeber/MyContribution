package com.example.rhg.outsourcing.apapter;

import android.content.Context;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.viewHolder.QFoodGridAdapterViewHolder;
import com.example.rhg.outsourcing.bean.FavorableFoodBean;

import java.util.List;

/**
 * GridView Adapter
 */
public class QFoodGridViewAdapter extends QFoodBaseAdapter<FavorableFoodBean> {


    public QFoodGridViewAdapter(Context context, List<FavorableFoodBean> list, int layoutId) {
        super(context, list, layoutId);
    }

    @Override
    public void convert(QFoodGridAdapterViewHolder holder, FavorableFoodBean model, int position) {
        holder.setImageUrl(R.id.gridview_imageView, model.getImageUrl());
        holder.setText(R.id.gridview_delete, model.getTitle());
        holder.setHeaderColor(R.id.VipTextHeadView, model.getHeadercolor());
    }
}
