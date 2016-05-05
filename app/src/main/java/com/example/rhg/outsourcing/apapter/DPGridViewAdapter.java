package com.example.rhg.outsourcing.apapter;

import android.content.Context;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.model.ImageModel;

import java.util.List;

/**
 * GridView Adapter
 */
public class DPGridViewAdapter extends DPBaseAdapter<ImageModel>{
	
	
	public DPGridViewAdapter(Context context, List<ImageModel> list, int layoutId) {
		super(context, list, layoutId);
	}

	@Override
	public void convert(DPAdapterViewHolder holder, ImageModel model,int position) {
		holder.setImageResource(R.id.gridview_imageView, model.getImageId());
		holder.setText(R.id.gridview_delete, model.getContent());
		holder.setHeaderColor(R.id.VipTextHeadView,model.getHeadercolor());
	}
}
