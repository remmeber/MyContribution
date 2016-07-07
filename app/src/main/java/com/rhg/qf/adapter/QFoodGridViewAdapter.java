package com.rhg.qf.adapter;

import android.content.Context;

import com.rhg.qf.R;
import com.rhg.qf.adapter.viewHolder.QFoodGridAdapterViewHolder;
import com.rhg.qf.bean.FavorableFoodUrlBean;

import java.util.List;

/**
 * GridView Adapter
 */
public class QFoodGridViewAdapter extends QFoodBaseAdapter<FavorableFoodUrlBean.FavorableFoodEntity> {
    List<FavorableFoodUrlBean.FavorableFoodEntity> list;

    public QFoodGridViewAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    public void setList(List<FavorableFoodUrlBean.FavorableFoodEntity> list) {
        this.list = list;
        if (this.list != null)
            setmDataList(this.list);
    }

    @Override
    public void convert(QFoodGridAdapterViewHolder holder, FavorableFoodUrlBean.FavorableFoodEntity model, int position) {
        holder.setImageUrl(R.id.gridview_imageView, model.getSrc());
        holder.setText(R.id.gridview_delete, model.getTitle());
//        holder.setHeaderColor(R.id.VipTextHeadView, mContext.getResources().getColor(R.color.black));
    }
}
