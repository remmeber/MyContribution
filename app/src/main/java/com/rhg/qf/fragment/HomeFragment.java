package com.rhg.qf.fragment;

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

import com.rhg.qf.R;
import com.rhg.qf.activity.DIYOrderActivity;
import com.rhg.qf.activity.HotFoodActivity;
import com.rhg.qf.activity.SearchActivity;
import com.rhg.qf.activity.ShopDetailActivity;
import com.rhg.qf.adapter.QFoodGridViewAdapter;
import com.rhg.qf.adapter.RecycleMultiTypeAdapter;
import com.rhg.qf.application.InitApplication;
import com.rhg.qf.bean.BannerTypeBean;
import com.rhg.qf.bean.BannerTypeUrlBean;
import com.rhg.qf.bean.FavorableFoodUrlBean;
import com.rhg.qf.bean.FavorableTypeModel;
import com.rhg.qf.bean.FooterTypeModel;
import com.rhg.qf.bean.HeaderTypeModel;
import com.rhg.qf.bean.HomeBean;
import com.rhg.qf.bean.MerchantUrlBean;
import com.rhg.qf.bean.RecommendListTypeModel;
import com.rhg.qf.bean.RecommendListUrlBean;
import com.rhg.qf.bean.RecommendTextTypeModel;
import com.rhg.qf.bean.TextTypeBean;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.impl.RcvItemClickListener;
import com.rhg.qf.locationservice.LocationService;
import com.rhg.qf.locationservice.MyLocationListener;
import com.rhg.qf.mvp.presenter.HomePresenter;
import com.rhg.qf.utils.AccountUtil;
import com.rhg.qf.utils.NetUtil;
import com.rhg.qf.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:主页
 * author：remember
 * time：2016/5/28 16:44
 * email：1013773046@qq.com
 */
public class HomeFragment extends BaseFragment implements RecycleMultiTypeAdapter.OnBannerClickListener,
        RecycleMultiTypeAdapter.OnGridItemClickListener,
        RcvItemClickListener<MerchantUrlBean.MerchantBean>,
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

    MyLocationListener myLocationListener;
    ProgressBar progressBar;
    boolean isLocated;

    public HomeFragment() {
        homePresenter = new HomePresenter(this);
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
        homePresenter.getHomeData(AppConstants.HOME_RESTAURANTS);
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
        recycleMultiTypeAdapter.setOnItemClickListener(this);
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
                homePresenter.getHomeData(AppConstants.HOME_RESTAURANTS);
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
        recycleMultiTypeAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
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
//        recommendListTypeModel.setOnItemClick(this);
//        recommendListTypeModel.setHomeRecycleAdapter(new HomeRecycleAdapter(getContext()));
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
                doSearch();
                break;
            case R.id.home_tl_right_ll:
                doFeedback();
                startActivity(new Intent(getContext(), DIYOrderActivity.class));
                break;
        }
    }

    /*调起搜索页面，标志参数为AppConstants.KEY_RESTAURANT_SEARCH*/
    private void doSearch() {
        Intent _intent = new Intent(getActivity(), SearchActivity.class);
        _intent.putExtra(AppConstants.KEY_SEARCH_TAG, AppConstants.KEY_RESTAURANT_SEARCH);
        _intent.putExtra(AppConstants.KEY_SEARCH_INDEX, 0);
        startActivity(_intent);
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

//        startActivity(new Intent(getContext(), HotFoodActivity.class));
    }

    @Override
    public void gridItemClick(View view, FavorableFoodUrlBean.FavorableFoodEntity favorableFoodEntity) {
        Intent intent = new Intent(getContext(), HotFoodActivity.class);
        intent.putExtra(AppConstants.KEY_PRODUCT_NAME, favorableFoodEntity.getTitle());
        startActivity(intent);
    }

    @Override
    public void onItemClickListener(int position, MerchantUrlBean.MerchantBean item) {
        Intent intent = new Intent(getContext(), ShopDetailActivity.class);
        /*todo 传递参数*/
        intent.putExtra(AppConstants.KEY_OR_SP_PHONE, "1234567890");
        intent.putExtra(AppConstants.KEY_ADDRESS, "江苏省南京市江宁区东南大学");
        intent.putExtra(AppConstants.KEY_NOTE, "东南大学是一所985高校");

        intent.putExtra(AppConstants.KEY_MERCHANT_ID, item.getID());
        intent.putExtra(AppConstants.KEY_MERCHANT_NAME, item.getName());
        intent.putExtra(AppConstants.KEY_MERCHANT_LOGO, item.getPic());
        startActivity(intent);
    }
}
