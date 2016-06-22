package com.rhg.outsourcing.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.activity.GoodsDetailActivity;
import com.rhg.outsourcing.activity.HotSellActivity;
import com.rhg.outsourcing.activity.PersonalOrderActivity;
import com.rhg.outsourcing.activity.ShopDetailActivity;
import com.rhg.outsourcing.apapter.HomeRecycleAdapter;
import com.rhg.outsourcing.apapter.QFoodGridViewAdapter;
import com.rhg.outsourcing.apapter.RecycleMultiTypeAdapter;
import com.rhg.outsourcing.application.InitApplication;
import com.rhg.outsourcing.bean.BannerTypeBean;
import com.rhg.outsourcing.bean.BannerTypeUrlBean;
import com.rhg.outsourcing.bean.FavorableFoodUrlBean;
import com.rhg.outsourcing.bean.FavorableTypeModel;
import com.rhg.outsourcing.bean.FooterTypeModel;
import com.rhg.outsourcing.bean.HeaderTypeModel;
import com.rhg.outsourcing.bean.HomeBean;
import com.rhg.outsourcing.bean.RecommendListTypeModel;
import com.rhg.outsourcing.bean.RecommendListUrlBean;
import com.rhg.outsourcing.bean.RecommendTextTypeModel;
import com.rhg.outsourcing.bean.TextTypeBean;
import com.rhg.outsourcing.constants.AppConstants;
import com.rhg.outsourcing.impl.RcvItemClickListener;
import com.rhg.outsourcing.impl.SearchListener;
import com.rhg.outsourcing.locationservice.LocationService;
import com.rhg.outsourcing.locationservice.MyLocationListener;
import com.rhg.outsourcing.mvp.presenter.HomePresenter;
import com.rhg.outsourcing.mvp.presenter.HomePresenterImpl;
import com.rhg.outsourcing.utils.AccountUtil;
import com.rhg.outsourcing.utils.NetUtil;
import com.rhg.outsourcing.utils.ToastHelper;
import com.rhg.outsourcing.widget.LoadingDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * desc:主页
 * author：remember
 * time：2016/5/28 16:44
 * email：1013773046@qq.com
 */
public class HomeFragment extends SuperFragment implements RecycleMultiTypeAdapter.OnBannerClickListener,
        RecycleMultiTypeAdapter.OnGridItemClickListener, RcvItemClickListener<RecommendListUrlBean.RecommendShopBeanEntity>,
        View.OnClickListener {
    FavorableTypeModel favorableTypeModel;
    List<FavorableFoodUrlBean.FavorableFoodEntity> favorableFoodBeen = new ArrayList<>();

    BannerTypeBean bannerTypeBean;
    List<BannerTypeUrlBean.BannerEntity> bannerTypeBeanList = new ArrayList<>();

    TextTypeBean textTypeBean;

    RecommendListTypeModel recommendListTypeModel;
    List<RecommendListUrlBean.RecommendShopBeanEntity> recommendShopBeanEntityList = new ArrayList<>();

    SwipeRefreshLayout swipeRefreshLayout;
    RecycleMultiTypeAdapter recycleMultiTypeAdapter;

    View view;
    RecyclerView home_rcv;
    /*toolbar 相关*/
    RelativeLayout tlLeftRL;
    ImageView tlLeftIV;
    TextView tlLeftTV;
    TextView tlCenterTV;
    LinearLayout tlRightLL;
    ImageView tlRightIV;
    TextView tlRightTV;
    /*toolbar 相关*/

    HomePresenter homePresenter;
    //itme的数据类型集合
    List<Object> mData;

    SearchListener searchListener;
    MyLocationListener myLocationListener;
    WeakReference<LoadingDialog> loadingDialog;
    ProgressBar progressBar;
    boolean isLocated;

    public HomeFragment() {
        homePresenter = new HomePresenterImpl(this);
        myLocationListener = new MyLocationListener(this);
        favorableTypeModel = new FavorableTypeModel();
        bannerTypeBean = new BannerTypeBean();
        textTypeBean = new TextTypeBean();
        recommendListTypeModel = new RecommendListTypeModel();
    }


    @Override
    public int getLayoutResId() {
        return R.layout.home_fm_layout;
    }

    public void setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }

    @Override
    protected void initView(View view) {
        tlLeftRL = (RelativeLayout) view.findViewById(R.id.home_tl_left_rl);
        tlLeftIV = (ImageView) view.findViewById(R.id.home_tl_left_bt);
        tlLeftTV = (TextView) view.findViewById(R.id.home_tl_left_tv);

        tlCenterTV = (TextView) view.findViewById(R.id.home_tl_center_tv);

        tlRightLL = (LinearLayout) view.findViewById(R.id.home_tl_right_ll);
        tlRightIV = (ImageView) view.findViewById(R.id.home_tl_right_iv);

        home_rcv = (RecyclerView) view.findViewById(R.id.home_recycle);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.home_swipe);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
    }

    @Override
    public void loadData() {
        loadingDialog = new WeakReference<>(new LoadingDialog(getActivity()));
        loadingDialog.get().show();
        homePresenter.getHomeData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden)
            Log.i("RHG", "Home:hide");
        else
            Log.i("RHG", "Home:show");
    }

    @Override
    protected void initData() {
        tlLeftIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_location_green));
        tlLeftRL.setOnClickListener(this);
        tlCenterTV.setOnClickListener(this);
        tlRightLL.setOnClickListener(this);

        mData = new ArrayList<>();
        recycleMultiTypeAdapter = new RecycleMultiTypeAdapter(getContext(), mData);
        recycleMultiTypeAdapter.setBannerClickListener(this);
        recycleMultiTypeAdapter.setOnGridItemClickListener(this);
        initList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        home_rcv.setLayoutManager(linearLayoutManager);
        home_rcv.setHasFixedSize(false);
        home_rcv.setAdapter(recycleMultiTypeAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (TextUtils.isEmpty(tlLeftTV.getText()) || "null".equals(tlLeftTV.getText())) {
                    reStartLocation();
                }
                homePresenter.getHomeData();
            }
        });

    }

    @Override
    public LocationService GetMapService() {
        progressBar.setVisibility(View.VISIBLE);
        return InitApplication.getInstance().locationService;
    }

    @Override
    public void getLocation(LocationService locationService, MyLocationListener mLocationListener) {
    }

    @Override
    public MyLocationListener getLocationListener() {
        return myLocationListener;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        recycleMultiTypeAdapter.startBanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        recycleMultiTypeAdapter.stopBanner();
    }

    @Override
    protected void showFailed() {
    }

    @Override
    public void showSuccess(Object o) {
        HomeBean _homeBean = (HomeBean) o;
        /*set null */

        bannerTypeBean.setBannerEntityList(_homeBean.getBannerEntityList());
        favorableTypeModel.setFavorableFoodBeen(_homeBean.getFavorableFoodEntityList());
        recommendListTypeModel.setRecommendShopBeanEntity(_homeBean.getRecommendShopBeanEntityList());
        textTypeBean.setTitle(_homeBean.getTextTypeBean().getTitle());
        /*recycleMultiTypeAdapter.notifyItemChanged(1);
        recycleMultiTypeAdapter.notifyItemChanged(2);
        recycleMultiTypeAdapter.notifyItemChanged(3);
        recycleMultiTypeAdapter.notifyItemChanged(5);*/
        recycleMultiTypeAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        if (loadingDialog != null) {
            loadingDialog.get().dismiss();
        }
    }


    /*定位显示*/
    @Override
    public void showLocSuccess(String s) {
        isLocated = true;
        tlLeftTV.setText(s);
        AccountUtil.getInstance().setLocation(s);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLocFailed(String s) {
        ToastHelper.getInstance()._toast(s);
        progressBar.setVisibility(View.GONE);
    }

    private void initList() {
        mData.add(new HeaderTypeModel("Header", R.color.cardview_shadow_start_color));
        mData.add(bannerTypeBean);
        mData.add(textTypeBean);
        favorableTypeModel.setDpGridViewAdapter(new QFoodGridViewAdapter(getContext(),
                R.layout.item_grid_rcv));
        mData.add(favorableTypeModel);
        mData.add(new RecommendTextTypeModel());
        recommendListTypeModel.setOnItemClick(this);
        recommendListTypeModel.setHomeRecycleAdapter(new HomeRecycleAdapter(getContext()));
        mData.add(recommendListTypeModel);
        mData.add(new FooterTypeModel("FooterType", R.color.colorPrimaryDark));
        recycleMultiTypeAdapter.notifyDataSetChanged();
    }

    //TODO 扫一扫
    private void doFeedback() {
        ToastHelper.getInstance()._toast("反馈");
    }


    //--------------------------------点击事件回调---------------------------------------------------

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_tl_left_rl:
                if (NetUtil.isConnected(getContext()))
                    reStartLocation();
                else ToastHelper.getInstance()._toast("请检查网络");
                break;
            case R.id.home_tl_center_tv:
                searchListener.doSearch();
                break;
            case R.id.home_tl_right_ll:
                doFeedback();
                startActivity(new Intent(getContext(), PersonalOrderActivity.class));
                break;
        }
    }

    @Override
    public void bannerClick(int position, BannerTypeUrlBean.BannerEntity bannerEntity) {
        Log.i("RHG", bannerEntity.getID() + ", " + bannerEntity.getSrc());
        /*Intent intent = new Intent(getContext(), ShopDetailActivity.class);
        *//*todo 传递参数*//*
        intent.putExtra(AppConstants.KEY_PHONE, "1234567890");
        intent.putExtra(AppConstants.KEY_ADDRESS, "江苏省南京市江宁区东南大学");
        intent.putExtra(AppConstants.KEY_NOTE, "东南大学是一所985高校");
        intent.putExtra(AppConstants.KEY_MERCHANT_ID, "20160517");
        intent.putExtra(AppConstants.KEY_MERCHANT_NAME, "荣哥土菜馆");
        intent.putExtra(AppConstants.KEY_MERCHANT_LOGO, AppConstants.images[3]);
        startActivity(intent);*/

        startActivity(new Intent(getContext(), HotSellActivity.class));
    }

    @Override
    public void gridItemClick(View view, FavorableFoodUrlBean.FavorableFoodEntity favorableFoodEntity) {
        Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
        intent.putExtra(AppConstants.KEY_PRODUCT_ID, "1");
        /*intent.putExtra(AppConstants.KEY_PRODUCT_NAME, "土豆丝");
        intent.putExtra(AppConstants.KEY_PRODUCT_PRICE, "90");*/
        startActivityForResult(intent, AppConstants.START_0);
    }

    @Override
    public void onItemClickListener(int position, RecommendListUrlBean.RecommendShopBeanEntity item) {
        Intent intent = new Intent(getContext(), ShopDetailActivity.class);
        /*todo 传递参数*/
        intent.putExtra(AppConstants.KEY_OR_SP_PHONE, "1234567890");
        intent.putExtra(AppConstants.KEY_ADDRESS, "江苏省南京市江宁区东南大学");
        intent.putExtra(AppConstants.KEY_NOTE, "东南大学是一所985高校");
        intent.putExtra(AppConstants.KEY_MERCHANT_ID, "20160517");
        intent.putExtra(AppConstants.KEY_MERCHANT_NAME, "荣哥土菜馆");
        intent.putExtra(AppConstants.KEY_MERCHANT_LOGO, AppConstants.images[1]);
        startActivity(intent);
    }
}
