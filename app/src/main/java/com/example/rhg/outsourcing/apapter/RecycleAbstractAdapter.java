package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.apapter.viewHolder.BodyViewHolder;
import com.example.rhg.outsourcing.apapter.viewHolder.HeaderViewHolder;
import com.example.rhg.outsourcing.constants.AppConstants;

import java.util.List;

/**
 * desc:适配器扩展数据抽象类
 * author：remember
 * time：2016/5/28 16:21
 * email：1013773046@qq.com
 */
public abstract class RecycleAbstractAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<T> mData;
    private int type = -1;
    private boolean hasHead;
    private boolean hasFooter;

    public void setContext(Context context) {
        this.context = context;
    }

    public RecycleAbstractAdapter(Context context) {
        this.context = context;

        this.type = getDisplayType();
        this.hasHead = getHasHead();
        this.hasFooter = getHasFooter();
    }

    public void setmData(List<T> mData) {
        this.mData = mData;
    }

    public RecycleAbstractAdapter(Context context, List<T> mData) {
        this.context = context;
        this.mData = mData;
        this.type = getDisplayType();
        this.hasHead = getHasHead();
        this.hasFooter = getHasFooter();
    }

    public boolean getHasFooter() {
        return false;
    }

    public boolean getHasHead() {
        return false;
    }

    public int getDisplayType() {
        return -1;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasHead)
            if (position == 0)
                return AppConstants.TypeHeader;
        if (hasFooter)
            if (position == getItemCount() - 1)
                return -1;
        return AppConstants.TypeBody;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AppConstants.TypeHeader)
            return new HeaderViewHolder(View.inflate(context, getLayoutResId(viewType), null), type);
        return new BodyViewHolder(View.inflate(context, getLayoutResId(viewType), null), type);
    }

    protected abstract int getLayoutResId(int viewType);


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (type == AppConstants.TypeHome || type == AppConstants.TypeOrder) {
            bindBodyItemViewHolder((BodyViewHolder) holder, position, type);
            return;
        }
        //TODO 刷商家页数据
        switch (getItemViewType(position)) {
            case AppConstants.TypeBody:
                bindBodyItemViewHolder((BodyViewHolder) holder, position, type);
                break;
            case AppConstants.TypeHeader:
                bindHeaderItemViewHolder((HeaderViewHolder) holder, position, type);
                break;
        }

    }

    private void bindHeaderItemViewHolder(HeaderViewHolder holder, int position, int type) {
        T data = mData.get(position);
        bindHeadData(holder, data, type);
    }


    private void bindBodyItemViewHolder(BodyViewHolder holder, int position, int type) {
        T data = mData.get(position);
        bindBodyData(holder, data, type);


    }

    public void bindHeadData(HeaderViewHolder holder, T data, int type) {
    }

    protected abstract void bindBodyData(BodyViewHolder holder, T data, int type);

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    private OnListItemClick onListItemClick;

    public void setOnListItemClick(OnListItemClick onListItemClick) {
        this.onListItemClick = onListItemClick;
    }

    public OnListItemClick getOnListItemClick() {
        return onListItemClick;
    }

    public interface OnListItemClick {
        void itemClick(View v, int position);
    }
}
