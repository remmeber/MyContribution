package com.example.rhg.outsourcing.apapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.rhg.outsourcing.apapter.viewHolder.BannerImageHolder;
import com.example.rhg.outsourcing.bean.BannerTypeUrlBean;
import com.example.rhg.outsourcing.bean.FavorableFoodUrlBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.bean.BannerTypeBean;
import com.example.rhg.outsourcing.bean.FavorableTypeModel;
import com.example.rhg.outsourcing.bean.FooterTypeModel;
import com.example.rhg.outsourcing.bean.HeaderTypeModel;
import com.example.rhg.outsourcing.bean.RecommendListTypeModel;
import com.example.rhg.outsourcing.bean.RecommendTextTypeModel;
import com.example.rhg.outsourcing.bean.TextTypeBean;
import com.example.rhg.outsourcing.utils.BannerController;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:复合类型recycleview 适配器
 * author：remember
 * time：2016/5/28 16:21
 * email：1013773046@qq.com
 */
public class RecycleMultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //--------------------------------Define Item Type----------------------------------------------
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_TEXT = 2;
    public static final int TYPE_FAVORABLE = 3;
    public static final int TYPE_RECOMMEND_TEXT = 4;
    public static final int TYPE_RECOMMEND_LIST = 5;
    public static final int TYPE_FOOTER = 6;
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
        if (object instanceof BannerTypeBean) return TYPE_BANNER;
        if (object instanceof TextTypeBean) return TYPE_TEXT;
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
                return new BannerTypeViewHolder(layoutInflater.inflate(R.layout.item_banner, parent, false));
            case TYPE_TEXT:
                return new TextTypeViewHolder(layoutInflater.inflate(R.layout.text_rcv, parent, false));
            case TYPE_FAVORABLE:
                return new FavorableTypeViewHolder(layoutInflater.inflate(R.layout.grid_type_layout, parent, false), viewType);
            case TYPE_RECOMMEND_TEXT:
                return new RecommendTextTypeViewHolder(layoutInflater.inflate(R.layout.recommend_text_rcv, parent, false));
            case TYPE_RECOMMEND_LIST:
                return new RecommendListTypeViewHolder(layoutInflater.inflate(R.layout.recommend_list_rcv, parent, false), viewType);
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
                bindViewHolderBanner((BannerTypeViewHolder) holder, (BannerTypeBean) data);
                break;
            case TYPE_TEXT:
                bindViewHolderText((TextTypeViewHolder) holder, (TextTypeBean) data);
                break;
            case TYPE_FAVORABLE:
                bindViewHolderFavorable((FavorableTypeViewHolder) holder, (FavorableTypeModel) data);
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
        holder.button.setText(data.getText());
        holder.button.setBackgroundColor(context.getResources().getColor(data.getColor()));
    }

    private void bindViewHolderBanner(BannerTypeViewHolder holder, final BannerTypeBean data) {
        List<String> images = new ArrayList<>();
        List<BannerTypeUrlBean.BannerEntity> _bannerEntity = data.getBannerEntityList();
        int _count = _bannerEntity == null ? 0 : _bannerEntity.size();
        for (int i = 0; i < _count; i++) {
            images.add(_bannerEntity.get(i).getSrc());
        }
        convenientBanner.setPages(new CBViewHolderCreator<BannerImageHolder>() {
            @Override
            public BannerImageHolder createHolder() {
                return new BannerImageHolder();
            }
        }, images)
                .setPageIndicator(AppConstants.IMAGE_INDICTORS);
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                onBannerClickListener.bannerClick(position, data.getBannerEntityList().get(position));
            }
        });
    }

    private void bindViewHolderText(TextTypeViewHolder holder, TextTypeBean data) {
        holder.textView.setText(data.getTitle());
    }

    @SuppressWarnings("NewApi")
    private void bindViewHolderFavorable(FavorableTypeViewHolder holder,
                                         final FavorableTypeModel favorableFoodEntity) {
        holder.dpGridViewAdapter.notifyDataSetChanged();
        if (onGridItemClickListener != null) {
            if (!holder.gridView.hasOnClickListeners()) {
                holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        onGridItemClickListener.gridItemClick(view,favorableFoodEntity.getFavorableFoodBeen().get(position));
                    }
                });
            }
        }

    }

    private void bindViewHolderRecommendText(RecommendTextTypeViewHolder holder, RecommendTextTypeModel data, int position) {
    }

    private void bindViewHolderRecommendList(RecommendListTypeViewHolder holder, RecommendListTypeModel data) {
        holder.homeRecycleAdapter.notifyDataSetChanged();
        holder.homeRecycleAdapter.setOnRcvItemClickListener(data.getOnItemClick());
    }

    private void bindViewHolderFooter(FooterTypeViewHolder holder, FooterTypeModel data, int position) {
        holder.button.setText(data.getText());
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

    private ConvenientBanner<String> convenientBanner;
    BannerController bannerController = new BannerController();

    public void stopBanner() {
        bannerController.stopBanner();
        bannerController.setConvenientBanner(null);
    }

    public void startBanner() {
        bannerController.startBanner(2000);
        bannerController.setConvenientBanner(convenientBanner);
    }

    private class BannerTypeViewHolder extends RecyclerView.ViewHolder {

        public BannerTypeViewHolder(View itemView) {
            super(itemView);
            convenientBanner = (ConvenientBanner) itemView.findViewById(R.id.iv_banner);
            convenientBanner.startTurning(2000);
            bannerController.setConvenientBanner(convenientBanner);
        }
    }

    private class TextTypeViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public TextTypeViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.VipText);
        }
    }

    private class FavorableTypeViewHolder extends RecyclerView.ViewHolder {
        private final GridView gridView;
        private QFoodGridViewAdapter dpGridViewAdapter;

        public FavorableTypeViewHolder(View itemView, int position) {
            super(itemView);
            gridView = (GridView) itemView.findViewById(R.id.gridview);
            gridView.setNumColumns(3);
            dpGridViewAdapter = ((FavorableTypeModel) mData.get(position)).getDpGridViewAdapter();
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
        private HomeRecycleAdapter homeRecycleAdapter;

        public RecommendListTypeViewHolder(View itemView, int viewType) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recommend_list);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            homeRecycleAdapter = ((RecommendListTypeModel) mData.get(viewType)).getHomeRecycleAdapter();
            recyclerView.setAdapter(homeRecycleAdapter);
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

    public void setBannerClickListener(OnBannerClickListener onBannerClickListener) {
        this.onBannerClickListener = onBannerClickListener;
    }

    public interface OnBannerClickListener {
        public void bannerClick(int position, BannerTypeUrlBean.BannerEntity bannerEntity);
    }
    //----------------------------------------------------------------------------------------------

    private OnGridItemClickListener onGridItemClickListener;

    public void setOnGridItemClickListener(OnGridItemClickListener onGridItemClickListener) {
        this.onGridItemClickListener = onGridItemClickListener;
    }

    public interface OnGridItemClickListener {
        public void gridItemClick(View view, FavorableFoodUrlBean.FavorableFoodEntity favorableFoodEntity);
    }


}
