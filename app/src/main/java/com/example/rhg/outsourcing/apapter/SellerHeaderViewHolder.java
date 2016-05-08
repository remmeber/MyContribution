package com.example.rhg.outsourcing.apapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;

/**
 * Created by remember on 2016/5/8.
 */
public class SellerHeaderViewHolder extends RecyclerView.ViewHolder {
    public ImageView headerstoreimage;
    public TextView headerstorename;
    public TextView headerdemandmoney;
    public TextView headerdelivermoney;
    public TextView headerdistance;
    public LinearLayout headerlayout;

    public SellerHeaderViewHolder(View itemView) {
        super(itemView);
        headerstoreimage = (ImageView)itemView.findViewById(R.id.headerstoreiamge);
        headerstorename = (TextView)itemView.findViewById(R.id.headerstorename);
        headerdemandmoney = (TextView)itemView.findViewById(R.id.headerdemandmoney);
        headerdelivermoney = (TextView)itemView.findViewById(R.id.headerdelivermoney);
        headerdistance = (TextView)itemView.findViewById(R.id.headerdistanceText);
        headerlayout = (LinearLayout)itemView.findViewById(R.id.headerlayout);
    }
}
