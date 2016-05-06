package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.rhg.outsourcing.Constants.AppConstants;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.model.BannerTypeModel;
import com.example.rhg.outsourcing.model.FavorableTypeModel;
import com.example.rhg.outsourcing.model.FooterTypeModel;
import com.example.rhg.outsourcing.model.HeaderTypeModel;
import com.example.rhg.outsourcing.model.RecommendListTypeModel;
import com.example.rhg.outsourcing.model.RecommendTextTypeModel;
import com.example.rhg.outsourcing.model.TextTypeModel;
import com.example.rhg.outsourcing.utils.BannerController;

import java.util.List;

/**
 * Created by remember on 2016/5/3.
 */
public class RecycleMultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //--------------------------------Define Item Type----------------------------------------------
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_BANNER = 1;
    private static final int TYPE_TEXT = 2;
    private static final int TYPE_FAVORABLE = 3;
    private static final int TYPE_RECOMMEND_TEXT = 4;
    private static final int TYPE_RECOMMEND_LIST = 5;
    private static final int TYPE_FOOTER = 6;
    //----------------------------------------------------------------------------------------------
    //
    private Context context;
    private List<Object> mData;

    public RecycleMultiTypeAdapter(Context context, List<Object> mData) {
        this.context = context;
        this.mData = mData;
    }

    //--------------------------------定义类型-------------------------------------------------------
    @Override
    public int getItemViewType(int position) {
        Object object = mData.get(position);
        if (object instanceof HeaderTypeModel) return TYPE_HEADER;
        if (object instanceof BannerTypeModel) return TYPE_BANNER;
        if (object instanceof TextTypeModel) return TYPE_TEXT;
        if (object instanceof FavorableTypeModel) return TYPE_FAVORABLE;
        if (object instanceof RecommendTextTypeModel) return TYPE_RECOMMEND_TEXT;
        if (object instanceof RecommendListTypeModel) return TYPE_RECOMMEND_LIST;
        if (object instanceof FooterTypeModel) return TYPE_FOOTER;
        return -1;
    }
    //----------------------------------------------------------------------------------------------

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_HEADER:
                return new HeaderTypeViewHolder(layoutInflater.inflate(R.layout.recycleheader, parent, false));
            case TYPE_BANNER:
                BannerTypeViewHolder bannerTypeViewHolder = new BannerTypeViewHolder(layoutInflater.inflate(R.layout.recyclebanner, parent, false));
                return bannerTypeViewHolder;
            case TYPE_TEXT:
                return new TextTypeViewHolder(layoutInflater.inflate(R.layout.recycletext, parent, false));
            case TYPE_FAVORABLE:
                return new FavorableTypeViewHolder(layoutInflater.inflate(R.layout.recyclegridtype, parent, false),viewType);
            case TYPE_RECOMMEND_TEXT:
                return new RecommendTextTypeViewHolder(layoutInflater.inflate(R.layout.recyclerecommendtext, parent, false));
            case TYPE_RECOMMEND_LIST:
                return new RecommendListTypeViewHolder(layoutInflater.inflate(R.layout.recyclerecommendlist, parent, false),viewType);
            case TYPE_FOOTER:
                return new FooterTypeViewHolder(layoutInflater.inflate(R.layout.recyclefooter, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object data = mData.get(position);
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                bindViewHolderHeader((HeaderTypeViewHolder) holder, (HeaderTypeModel) data, position);
                break;
            case TYPE_BANNER:
                bindViewHolderBanner((BannerTypeViewHolder) holder, (BannerTypeModel) data);
                break;
            case TYPE_TEXT:
                bindViewHolderText((TextTypeViewHolder) holder, (TextTypeModel) data);
                break;
            case TYPE_FAVORABLE:
                bindViewHolderFavorable((FavorableTypeViewHolder) holder);
                break;
            case TYPE_RECOMMEND_TEXT:
                bindViewHolderRecommendText((RecommendTextTypeViewHolder) holder, (RecommendTextTypeModel) data, position);
                break;
            case TYPE_RECOMMEND_LIST:
                bindViewHolderRecommendList((RecommendListTypeViewHolder) holder, (RecommendListTypeModel) data);
                break;
            case TYPE_FOOTER:
                bindViewHolderFooter((FooterTypeViewHolder) holder, (FooterTypeModel) data, position);
                break;
        }
    }

    private void bindViewHolderHeader(HeaderTypeViewHolder holder, HeaderTypeModel data, int position) {
        holder.button.setText(data.getText() + position);
        holder.button.setBackgroundColor(context.getResources().getColor(data.getColor()));
    }

    private void bindViewHolderBanner(BannerTypeViewHolder holder, BannerTypeModel data) {
        holder.convenientBanner.setPages(new CBViewHolderCreator<BannerImageHolder>() {
            @Override
            public BannerImageHolder createHolder() {
                return new BannerImageHolder();
            }
        }, data.getImageUrls())
                .setPageIndicator(AppConstants.imageindictors);
    }

    private void bindViewHolderText(TextTypeViewHolder holder, TextTypeModel data) {
        holder.textView.setText(data.getText());
    }

    private void bindViewHolderFavorable(FavorableTypeViewHolder holder) {
        holder.dpGridViewAdapter.notifyDataSetChanged();
    }

    private void bindViewHolderRecommendText(RecommendTextTypeViewHolder holder, RecommendTextTypeModel data, int position) {
    }

    private void bindViewHolderRecommendList(RecommendListTypeViewHolder holder, RecommendListTypeModel data) {
        holder.recommendAdapter.notifyDataSetChanged();
        holder.recommendAdapter.setOnListItemClick(data.getOnListItemClick());
    }

    private void bindViewHolderFooter(FooterTypeViewHolder holder, FooterTypeModel data, int position) {
        holder.button.setText(data.getText() + position);
        Log.i("RHG", "Color is: " + data.getColor());
        holder.button.setBackgroundColor(context.getResources().getColor(data.getColor()));
    }


    @Override
    public int getItemCount() {
        return (mData == null || mData.isEmpty()) ? 0 : mData.size();
    }

    private class HeaderTypeViewHolder extends RecyclerView.ViewHolder {
        private final Button button;

        public HeaderTypeViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.headerButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "header is click", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private class BannerTypeViewHolder extends RecyclerView.ViewHolder {
        private ConvenientBanner convenientBanner;

        public BannerTypeViewHolder(View itemView) {
            super(itemView);
            convenientBanner = (ConvenientBanner) itemView.findViewById(R.id.home_banner);
            convenientBanner.startTurning(2000);
            convenientBanner.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    onBannerClickListener.bannerClick(position);
                }
            });
            BannerController.getInstance().setConvenientBanner(convenientBanner);
        }
    }

    private class TextTypeViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public TextTypeViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.VipText);
        }
    }

    private class FavorableTypeViewHolder extends RecyclerView.ViewHolder {
        private final GridView gridView;
        private DPGridViewAdapter dpGridViewAdapter;
        public FavorableTypeViewHolder(View itemView,int position) {
            super(itemView);
            gridView = (GridView) itemView.findViewById(R.id.gridview);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(onGridItemClickListener!=null)
                        onGridItemClickListener.gridItemClick(view,position);
                }
            });
            gridView.setNumColumns(3);
            dpGridViewAdapter = ((FavorableTypeModel)mData.get(position)).getDpGridViewAdapter();
            gridView.setAdapter(dpGridViewAdapter);
        }
    }

    private class RecommendTextTypeViewHolder extends RecyclerView.ViewHolder {

        public RecommendTextTypeViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class RecommendListTypeViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private RecommendAdapter recommendAdapter;
        public RecommendListTypeViewHolder(View itemView,int viewType) {
            super(itemView);
            recyclerView = (RecyclerView)itemView.findViewById(R.id.recommendlist);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            recommendAdapter = ((RecommendListTypeModel)mData.get(viewType)).getRecommendAdapter();
            recyclerView.setAdapter(recommendAdapter);
        }
    }

    private class FooterTypeViewHolder extends RecyclerView.ViewHolder {
        private final Button button;

        public FooterTypeViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.footerButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "footer is click", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    //-------------------------------for banner click callback--------------------------------------
    private OnBannerClickListener onBannerClickListener;
    public void setBannerClickListener(OnBannerClickListener onBannerClickListener){
        this.onBannerClickListener = onBannerClickListener;
    }
    public interface OnBannerClickListener{
        public void bannerClick(int position);
    }
    //----------------------------------------------------------------------------------------------

    private OnGridItemClickListener onGridItemClickListener;
    public  void setOnGridItemClickListener(OnGridItemClickListener onGridItemClickListener){
        this.onGridItemClickListener = onGridItemClickListener;
    }
    public  interface OnGridItemClickListener{
        public  void gridItemClick(View view,int position);
    }
}
