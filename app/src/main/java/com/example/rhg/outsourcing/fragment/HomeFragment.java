package com.example.rhg.outsourcing.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rhg.outsourcing.activity.GoodsDetailActivity;
import com.example.rhg.outsourcing.activity.ShopDetailActivity;
import com.example.rhg.outsourcing.apapter.HomeRecycleAdapter;
import com.example.rhg.outsourcing.application.InitApplication;
import com.example.rhg.outsourcing.bean.BannerTypeUrlBean;
import com.example.rhg.outsourcing.bean.FavorableFoodUrlBean;
import com.example.rhg.outsourcing.bean.HomeBean;
import com.example.rhg.outsourcing.bean.RecommendListUrlBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.QFoodGridViewAdapter;
import com.example.rhg.outsourcing.apapter.RecycleMultiTypeAdapter;
import com.example.rhg.outsourcing.bean.BannerTypeBean;
import com.example.rhg.outsourcing.bean.FavorableTypeModel;
import com.example.rhg.outsourcing.bean.FooterTypeModel;
import com.example.rhg.outsourcing.bean.HeaderTypeModel;
import com.example.rhg.outsourcing.bean.RecommendListTypeModel;
import com.example.rhg.outsourcing.bean.RecommendTextTypeModel;
import com.example.rhg.outsourcing.bean.TextTypeBean;
import com.example.rhg.outsourcing.impl.SearchListener;
import com.example.rhg.outsourcing.locationservice.LocationService;
import com.example.rhg.outsourcing.locationservice.MyLocationListener;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter;
import com.example.rhg.outsourcing.utils.BannerController;
import com.example.rhg.outsourcing.utils.ImageUtils;
import com.example.rhg.outsourcing.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:主页
 * author：remember
 * time：2016/5/28 16:44
 * email：1013773046@qq.com
 */
public class HomeFragment extends SuperFragment implements RecycleMultiTypeAdapter.OnBannerClickListener,
        RecycleMultiTypeAdapter.OnGridItemClickListener, HomeRecycleAdapter.OnListItemClick, View.OnClickListener {
    FavorableTypeModel favorableTypeModel;
    List<FavorableFoodUrlBean.FavorableFoodEntity> favorableFoodBeen = new ArrayList<>();

    BannerTypeBean bannerTypeBean;
    List<BannerTypeUrlBean.BannerEntity> bannerTypeBeanList = new ArrayList<>();

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
    ImageView tlCenterIV;
    LinearLayout tlRightLL;
    ImageView tlRightIV;
    TextView tlRightTV;
    /*toolbar 相关*/

    TestPresenter testPresenter;
    //itme的数据类型集合
    List<Object> mData;

    SearchListener searchListener;
    MyLocationListener myLocationListener;
    boolean isLocated;

    public HomeFragment() {
        testPresenter = new TestPresenter(this);
        myLocationListener = new MyLocationListener(this);
        favorableTypeModel = new FavorableTypeModel();
        bannerTypeBean = new BannerTypeBean();
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
        tlCenterIV = (ImageView) view.findViewById(R.id.home_tl_center_iv);

        tlRightLL = (LinearLayout) view.findViewById(R.id.home_tl_right_ll);
        tlRightIV = (ImageView) view.findViewById(R.id.home_tl_right_iv);

        home_rcv = (RecyclerView) view.findViewById(R.id.home_recycle);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.home_swipe);
    }

    @Override
    protected void initData() {

        ImageUtils.TintFill(tlLeftIV, getResources().getDrawable(R.mipmap.ic_place_white_48dp)
                , getResources().getColor(R.color.colorActiveGreen));
        ImageUtils.TintFill(tlCenterIV, getResources().getDrawable(R.mipmap.ic_search_white)
                , getResources().getColor(R.color.colorActiveGreen));
        ImageUtils.TintFill(tlRightIV, getResources().getDrawable(R.mipmap.ic_search_white)
                , getResources().getColor(R.color.colorActiveGreen));
        tlLeftRL.setOnClickListener(this);
        tlCenterTV.setOnClickListener(this);
        tlCenterIV.setOnClickListener(this);
        tlRightLL.setOnClickListener(this);

        mData = new ArrayList<>();
        recycleMultiTypeAdapter = new RecycleMultiTypeAdapter(getContext(), mData);
        recycleMultiTypeAdapter.setBannerClickListener(this);
        recycleMultiTypeAdapter.setOnGridItemClickListener(this);
        fillItemList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        home_rcv.setLayoutManager(linearLayoutManager);
        home_rcv.setHasFixedSize(false);
        home_rcv.setAdapter(recycleMultiTypeAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                testPresenter.getData();
            }
        });
    }

    @Override
    public LocationService GetMapService() {
        return ((InitApplication) getActivity().getApplication()).locationService;
    }

    @Override
    public void getLocation(LocationService locationService, MyLocationListener mLocationListener) {
        mLocationListener.getLocation(locationService);
    }

    @Override
    public MyLocationListener getLocationListener() {
        return new MyLocationListener(this);
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
        BannerController.getInstance().startBanner(2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        BannerController.getInstance().stopBanner();
    }

    @Override
    protected void showFailed() {
    }

    @Override
    public void showSuccess(Object o) {
        HomeBean _homeBean = (HomeBean) o;
        bannerTypeBean.setBannerEntityList(_homeBean.getBannerEntityList());
        favorableTypeModel.setFavorableFoodBeen(_homeBean.getFavorableFoodEntityList());
        recommendListTypeModel.setRecommendShopBeanEntity(_homeBean.getRecommendShopBeanEntityList());
        recycleMultiTypeAdapter.notifyItemChanged(1);
        recycleMultiTypeAdapter.notifyItemChanged(3);
        recycleMultiTypeAdapter.notifyItemChanged(5);
        swipeRefreshLayout.setRefreshing(false);

    }


    /*定位显示*/
    @Override
    public void showLocSuccess(String s) {
        isLocated = true;
        tlLeftTV.setText(s);
    }

    @Override
    public void showLocFailed(String s) {
        ToastHelper.getInstance()._toast(s);
    }

    private void fillItemList() {
        for (int i = 0; i < 6; i++) {
            FavorableFoodUrlBean.FavorableFoodEntity favorableFoodBean = new FavorableFoodUrlBean.FavorableFoodEntity();
            BannerTypeUrlBean.BannerEntity bannerEntity = new BannerTypeUrlBean.BannerEntity();
            bannerEntity.setID("" + i);
            bannerEntity.setSrc(AppConstants.images[2]);
//            BaseSellerModel sellRecommendModel = new BaseSellerModel("哈哈", "中餐", "距离10m", R.drawable.recommend_default_icon_1);
            RecommendListUrlBean.RecommendShopBeanEntity recommendShopBeanEntity = new RecommendListUrlBean.RecommendShopBeanEntity();
            recommendShopBeanEntity.setName("哈哈");
//            recommendShopBeanEntity.setFoodType("中餐");
            recommendShopBeanEntity.setDistance("距离10m");
            recommendShopBeanEntity.setSrc(AppConstants.images[1]);

            favorableFoodBean.setSrc(AppConstants.images[0]);
            switch (i) {
                case 0:
                case 3:

//                    favorableFoodBean.set(R.color.colorAccent);
                    favorableFoodBean.setTitle("哈哈哈哈");
                    break;
                case 1:
                case 4:
//                    favorableFoodBean.setHeadercolor(R.color.colorActiveGreen);
                    favorableFoodBean.setTitle("呵呵呵呵");
                    break;
                case 2:
                case 5:
//                    favorableFoodBean.setHeadercolor(R.color.colorInActive);
                    favorableFoodBean.setTitle("啊啊啊啊");
                    break;
            }
            recommendShopBeanEntityList.add(recommendShopBeanEntity);
            favorableFoodBeen.add(favorableFoodBean);
            bannerTypeBeanList.add(bannerEntity);
        }

        mData.add(new HeaderTypeModel("Header", R.color.cardview_shadow_start_color));
//        BannerTypeBean bannerTypeBean = new BannerTypeBean();
//        bannerTypeBean.setImageUrls(Arrays.asList(AppConstants.images));

        bannerTypeBean.setBannerEntityList(bannerTypeBeanList);
        mData.add(bannerTypeBean);
        TextTypeBean textTypeBean = new TextTypeBean();
        mData.add(textTypeBean);

        favorableTypeModel.setDpGridViewAdapter(new QFoodGridViewAdapter(getContext(),
                R.layout.recyclegriditem));
        favorableTypeModel.setFavorableFoodBeen(favorableFoodBeen);
        mData.add(favorableTypeModel);

        mData.add(new RecommendTextTypeModel());

        recommendListTypeModel.setOnListItemClick(this);
        recommendListTypeModel.setHomeRecycleAdapter(new HomeRecycleAdapter(getContext()));
        recommendListTypeModel.setRecommendShopBeanEntity(recommendShopBeanEntityList);
        mData.add(recommendListTypeModel);

        mData.add(new FooterTypeModel("FooterType", R.color.colorPrimaryDark));
        recycleMultiTypeAdapter.notifyDataSetChanged();
    }

    //TODO 扫一扫
    private void doSwipe() {
        ToastHelper.getInstance()._toast("扫一扫");
    }


    //--------------------------------点击事件回调---------------------------------------------------

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_tl_left_rl:
                if (InitApplication.isNetworkAvailable)
                    reStartLocation();
                else ToastHelper.getInstance()._toast("请检查网络");
                break;
            case R.id.home_tl_center_tv:
                searchListener.doSearch();
                break;
            case R.id.home_tl_center_iv:
                searchListener.doSearch();
                break;
            case R.id.home_tl_right_ll:
                doSwipe();
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
    }

    @Override
    public void gridItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
        intent.putExtra(AppConstants.KEY_PRODUCT_ID, "20160518");
        intent.putExtra(AppConstants.KEY_PRODUCT_NAME, "土豆丝");
        intent.putExtra(AppConstants.KEY_PRODUCT_PRICE, "90");
        startActivityForResult(intent, AppConstants.START_0);
    }

    @Override
    public void itemClick(View v, int position) {
        Intent intent = new Intent(getContext(), ShopDetailActivity.class);
        /*todo 传递参数*/
        intent.putExtra(AppConstants.KEY_PHONE, "1234567890");
        intent.putExtra(AppConstants.KEY_ADDRESS, "江苏省南京市江宁区东南大学");
        intent.putExtra(AppConstants.KEY_NOTE, "东南大学是一所985高校");
        intent.putExtra(AppConstants.KEY_MERCHANT_ID, "20160517");
        intent.putExtra(AppConstants.KEY_MERCHANT_NAME, "荣哥土菜馆");
        intent.putExtra(AppConstants.KEY_MERCHANT_LOGO, AppConstants.images[1]);
        startActivity(intent);
    }

}
