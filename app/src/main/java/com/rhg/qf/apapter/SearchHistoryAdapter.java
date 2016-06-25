package com.rhg.qf.apapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.impl.RcvItemClickListener;

import java.util.List;

/**
 * desc:
 * author：remember
 * time：2016/6/18 16:00
 * email：1013773046@qq.com
 */
public class SearchHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<String> searchHistory;
    private RcvItemClickListener onSearchItemClick;

    public SearchHistoryAdapter(Context context, List<String> searchHistory) {
        this.context = context;
        this.searchHistory = searchHistory;
    }

    public void setSearchHistory(List<String> searchHistory) {
        this.searchHistory = searchHistory;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHistoryViewHolder(View.inflate(context, R.layout.item_search_history, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchHistoryViewHolder searchHistoryViewHolder = (SearchHistoryViewHolder) holder;
        final int clickPosition = holder.getAdapterPosition();
        searchHistoryViewHolder.tvSearchHistory.setText(searchHistory.get(clickPosition));
        searchHistoryViewHolder.tvSearchHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSearchItemClick != null)
                    onSearchItemClick.onItemClickListener(clickPosition, searchHistory.get(clickPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchHistory == null ? 0 : searchHistory.size();
    }

    public void setOnSearchHistoryClickListener(RcvItemClickListener onSearchItemClick) {
        this.onSearchItemClick = onSearchItemClick;
    }

    private class SearchHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvSearchHistory;

        public SearchHistoryViewHolder(View view) {
            super(view);
            tvSearchHistory = (TextView) view.findViewById(R.id.tv_search_history);
        }
    }
}