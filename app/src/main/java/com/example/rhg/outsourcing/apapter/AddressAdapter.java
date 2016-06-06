package com.example.rhg.outsourcing.apapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.bean.AddressBean;
import com.example.rhg.outsourcing.bean.AddressLocalBean;

import java.util.List;

/**
 * 作者：rememberon 2016/6/5
 * 邮箱：1013773046@qq.com
 */
public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    Context context;
    List<AddressLocalBean> addressBeanList;
    int lastPosition = 0;
    int currentPosition = 0;

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
        return new AddressViewHolder(layoutInflater.inflate(R.layout.item_address, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AddressViewHolder _holder = (AddressViewHolder) holder;
        AddressLocalBean addressBean = addressBeanList.get(position);
        bindData(_holder, addressBean, position);
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    private void bindData(AddressViewHolder holder, AddressLocalBean addressLocalBean, int position) {
        holder.tvReceiver.setText(addressLocalBean.getName());
        holder.tvPhone.setText(addressLocalBean.getPhone());
        holder.tvAddress.setText(addressLocalBean.getAddress());
        boolean isChecked = addressLocalBean.isChecked();
        holder.rlAddress.setTag(holder.getLayoutPosition());
        setImage(isChecked, holder.ivCheck);
        if (holder.rlAddress.hasOnClickListeners())
            return;
        holder.rlAddress.setOnClickListener(this);
    }

    private void setImage(boolean isChecked, ImageView ivCheck) {
        if (isChecked) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivCheck.setImageDrawable(context.getDrawable(R.mipmap.checked));
            } else
                ivCheck.setImageDrawable(context.getResources().getDrawable(R.mipmap.checked));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivCheck.setImageDrawable(context.getDrawable(R.mipmap.unchecked));
            } else
                ivCheck.setImageDrawable(context.getResources().getDrawable(R.mipmap.unchecked));
        }
    }


    private void selectOne(int position) {
        for (int i = 0; i < addressBeanList.size(); i++) {
            if (position == i)
                addressBeanList.get(i).setChecked(true);
            else addressBeanList.get(i).setChecked(false);
        }
    }


    @Override
    public int getItemCount() {
        return addressBeanList == null ? 0 : addressBeanList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_address:
                int position = (int) v.getTag();
                if (position != lastPosition) {
                    selectOne(position);
                    notifyDataSetChanged();
                    lastPosition = position;
                }
                break;
        }
    }

    private class AddressViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlAddress;
        ImageView ivCheck;
        TextView tvReceiver;
        TextView tvPhone;
        TextView tvAddress;

        public AddressViewHolder(View inflateView) {
            super(inflateView);
            rlAddress = (RelativeLayout) inflateView.findViewById(R.id.rl_address);
            ivCheck = (ImageView) inflateView.findViewById(R.id.checkImage);
            tvReceiver = (TextView) inflateView.findViewById(R.id.tv_address_receiver);
            tvPhone = (TextView) inflateView.findViewById(R.id.tv_address_phone);
            tvAddress = (TextView) inflateView.findViewById(R.id.tv_address_content);
        }
    }

    public interface AddressClickListener {
        public void onAddressItemClick(View v, AddressBean addressBean);
    }

    private AddressClickListener onAddressClickListener;

    public void setOnAddressClickListener(AddressClickListener onAddressClickListener) {
        this.onAddressClickListener = onAddressClickListener;
    }
}
