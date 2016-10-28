package com.rhg.qf.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.bean.AddressUrlBean;
import com.rhg.qf.widget.SlideView;

import java.util.List;

/**
 * desc:
 * author：remember
 * time：2016/6/29 15:39
 * email：1013773046@qq.com
 */
public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        SlideView.OnSlideListener {
    Context context;
    List<AddressUrlBean.AddressBean> addressBeanList;
    int currentPosition = 0;
    SlideView lastSlideView;
    GestureDetectorCompat gestureDetector;
    private deleteListener mDeleteListener;

    public AddressAdapter(Context content, List<AddressUrlBean.AddressBean> addressBeanList) {
        this.context = content;
        this.addressBeanList = addressBeanList;
    }

    public void setAddressBeanList(List<AddressUrlBean.AddressBean> addressBeanList) {
        this.addressBeanList = addressBeanList;
        notifyDataSetChanged();
    }

    public void insertAddressBeanList(List<AddressUrlBean.AddressBean> addressBeanList, int position) {
        this.addressBeanList = addressBeanList;
        notifyItemInserted(position);
    }

    public void updateAddressBeanList(List<AddressUrlBean.AddressBean> addressBeanList, int position) {
        this.addressBeanList = addressBeanList;
        notifyItemChanged(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_address_content, parent, false);
        SlideView slideView = new SlideView(context);
        slideView.setContentView(view);
        return new AddressViewHolder(slideView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AddressViewHolder _holder = (AddressViewHolder) holder;
        AddressUrlBean.AddressBean addressBean = addressBeanList.get(position);
        bindData(_holder, addressBean, position);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    private void bindData(final AddressViewHolder holder, AddressUrlBean.AddressBean addressBean,
                          int position) {
        holder.slideView.setOnSlideListener(this);
        holder.slideView.shrink();
        holder.slideView.setTag(holder.getAdapterPosition());
        holder.tvReceiver.setText(addressBean.getName());
        holder.tvPhone.setText(addressBean.getPhone());
        String _str = addressBean.getAddress().concat(addressBean.getDetail());
        holder.tvAddress.setText(_str);
        String defaultAddress = addressBean.getDefault();
        holder.rlAddress.setTag(holder.getAdapterPosition());
        setImage(defaultAddress, holder.ivCheck);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDeleteListener != null) {
                    mDeleteListener.onDelete(holder.getAdapterPosition());
                }
            }
        });
        /*if (holder.rlAddress.hasOnClickListeners())
            return;
        holder.rlAddress.setOnClickListener(this);*/
    }

    private void setImage(String isChecked, ImageView ivCheck) {
        if ("1".equals(isChecked)) {
            ivCheck.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_check_green));
        } else
            ivCheck.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_uncheck_green));

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

    public void setmDeleteListener(deleteListener mDeleteListener) {
        this.mDeleteListener = mDeleteListener;
    }

    public interface deleteListener {
        void onDelete(int position);
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        public SlideView slideView;
        public RelativeLayout rlAddress;
        public ImageView ivCheck;
        public TextView tvReceiver;
        public TextView tvPhone;
        public TextView tvAddress;
        public RelativeLayout delete;

        public AddressViewHolder(SlideView inflateView) {
            super(inflateView);
            slideView = inflateView;
            rlAddress = (RelativeLayout) inflateView.findViewById(R.id.rl_address);
            ivCheck = (ImageView) inflateView.findViewById(R.id.checkImage);
            tvReceiver = (TextView) inflateView.findViewById(R.id.tv_address_receiver);
            tvPhone = (TextView) inflateView.findViewById(R.id.tv_address_phone);
            tvAddress = (TextView) inflateView.findViewById(R.id.tv_address_content);
            delete = (RelativeLayout) inflateView.findViewById(R.id.holder);
        }

    }
}
