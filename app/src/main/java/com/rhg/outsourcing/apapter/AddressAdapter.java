package com.rhg.outsourcing.apapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.bean.AddressLocalBean;
import com.rhg.outsourcing.widget.SlideView;

import java.util.List;

/**
 * 作者：rememberon 2016/6/5
 * 邮箱：1013773046@qq.com
 */
public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        SlideView.OnSlideListener {
    Context context;
    List<AddressLocalBean> addressBeanList;
    int currentPosition = 0;
    SlideView lastSlideView;
    GestureDetectorCompat gestureDetector;

    public AddressAdapter(Context content, List<AddressLocalBean> addressBeanList) {
        this.context = content;
        this.addressBeanList = addressBeanList;
    }

    public AddressAdapter(List<AddressLocalBean> addressBeanList) {
        this.addressBeanList = addressBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_address_content, parent, false);
        SlideView slideView = new SlideView(context);
        slideView.setContentView(view);
        Log.i("RHG", "adapter:" + slideView);
        return new AddressViewHolder(slideView);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AddressViewHolder _holder = (AddressViewHolder) holder;
        AddressLocalBean addressBean = addressBeanList.get(position);
        bindData(_holder, addressBean, position);
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    private void bindData(AddressViewHolder holder, AddressLocalBean addressLocalBean, int position) {
        holder.slideView.setOnSlideListener(this);
        holder.slideView.shrink();
        holder.slideView.setTag(holder.getAdapterPosition());
        holder.tvReceiver.setText(addressLocalBean.getName());
        holder.tvPhone.setText(addressLocalBean.getPhone());
        holder.tvAddress.setText(addressLocalBean.getAddress());
        boolean isChecked = addressLocalBean.isChecked();
        holder.rlAddress.setTag(holder.getAdapterPosition());
        setImage(isChecked, holder.ivCheck);
        /*if (holder.rlAddress.hasOnClickListeners())
            return;
        holder.rlAddress.setOnClickListener(this);*/
    }

    private void setImage(boolean isChecked, ImageView ivCheck) {
        if (isChecked) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivCheck.setImageDrawable(context.getDrawable(R.drawable.ic_check_green));
            } else
                ivCheck.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check_green));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivCheck.setImageDrawable(context.getDrawable(R.drawable.ic_uncheck_green));
            } else
                ivCheck.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_uncheck_green));
        }
    }


    @Override
    public int getItemCount() {
        return addressBeanList == null ? 0 : addressBeanList.size();
    }

    @Override
    public void onSlide(View view, int status) {
        /*if (status == SLIDE_STATE_UPDATE) {
            lastSlideView = (SlideView) view;
            if (lastSlideView.getState() == SlideView.OnSlideListener.SLIDE_STATUS_ON)
                lastSlideView.shrink();
            return;
        }*/
        if (lastSlideView != null && lastSlideView != view) {
            if (lastSlideView.getState() == SlideView.OnSlideListener.SLIDE_STATUS_ON)
                lastSlideView.shrink();
        }
        if (status == SLIDE_STATUS_ON) {
            lastSlideView = (SlideView) view;
        }

    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        public SlideView slideView;
        RelativeLayout rlAddress;
        ImageView ivCheck;
        TextView tvReceiver;
        TextView tvPhone;
        TextView tvAddress;

        public AddressViewHolder(SlideView inflateView) {
            super(inflateView);
            slideView = inflateView;
            rlAddress = (RelativeLayout) inflateView.findViewById(R.id.rl_address);
            ivCheck = (ImageView) inflateView.findViewById(R.id.checkImage);
            tvReceiver = (TextView) inflateView.findViewById(R.id.tv_address_receiver);
            tvPhone = (TextView) inflateView.findViewById(R.id.tv_address_phone);
            tvAddress = (TextView) inflateView.findViewById(R.id.tv_address_content);
        }

    }
}
