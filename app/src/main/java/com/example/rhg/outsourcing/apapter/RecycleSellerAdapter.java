package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.model.SellerModel;

import java.util.List;

/**
 * Created by remember on 2016/5/6.
 */
public class RecycleSellerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SellerModel> mData;
    private int type;

    public RecycleSellerAdapter(Context context, List<SellerModel> mData, int type) {
        this.context = context;
        this.mData = mData;
        this.type = type;
    }

    @Override
    public int getItemViewType(int position) {
        if (type == AppConstants.TypeSeller) {
            if (position == 0)
                return AppConstants.TypeHeader;
            else
                return AppConstants.TypeBody;
        }
        return super.getItemViewType(position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (type == AppConstants.TypeHome) {
            return new SellerBodyViewHolder(View.inflate(context, R.layout.recyclesellerbodylayout, null), type);
        }
        else {
            if (viewType == AppConstants.TypeBody) {
                //带卡片效果的布局
                return new SellerBodyViewHolder(View.inflate(context, R.layout.sellerstoreitem, null), type);
            }
            else {
                //带卡片效果的布局
                return new SellerHeaderViewHolder(View.inflate(context, R.layout.sellerstoreheaderitem, null));
            }
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case AppConstants.TypeHome:
                bindBodyitemViewHolder((SellerBodyViewHolder)holder,position,type);
                break;
            case AppConstants.TypeBody:
                bindBodyitemViewHolder((SellerBodyViewHolder)holder,position,type);
                break;
            case AppConstants.TypeHeader:
                bindHeaderitemViewHolder((SellerHeaderViewHolder)holder,position);
                break;
        }

    }

    private void bindHeaderitemViewHolder(final SellerHeaderViewHolder holder, int position) {
        SellerModel data = mData.get(position);
        holder.headerstoreimage.setImageResource(data.getImageRid());
        holder.headerstorename.setText(data.getStoreName());
        holder.headerdemandmoney.setText("满20元起");
        holder.headerdelivermoney.setText("配送费1元");
        holder.headerdistance.setText(data.getSellerDistance());
        holder.headerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onListItemClick != null)
                    onListItemClick.itemClick(v, holder.getAdapterPosition());
            }
        });
    }


    private void bindBodyitemViewHolder(final SellerBodyViewHolder holder,int position,int type) {
        SellerModel data = mData.get(position);
        holder.sellerName.setText(data.getStoreName());
        holder.sellerImage.setImageResource(data.getImageRid());
        holder.foodType.setText(data.getFoodType());
        holder.sellerDistance.setText(data.getSellerDistance());
        if(type == AppConstants.TypeSeller){
            holder.deliverMoney.setText("配送费1元");
            holder.demandMoney.setText("满20元起");
        }

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
