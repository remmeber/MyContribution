package com.example.rhg.outsourcing.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rhg.outsourcing.activity.GoodsDetailActivity;
import com.example.rhg.outsourcing.activity.ShopDetailActivity;
import com.example.rhg.outsourcing.bean.TestBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.DPGridViewAdapter;
import com.example.rhg.outsourcing.apapter.RecycleSellerAdapter;
import com.example.rhg.outsourcing.apapter.RecycleMultiTypeAdapter;
import com.example.rhg.outsourcing.bean.BannerTypeModel;
import com.example.rhg.outsourcing.bean.FavorableTypeModel;
import com.example.rhg.outsourcing.bean.FooterTypeModel;
import com.example.rhg.outsourcing.bean.HeaderTypeModel;
import com.example.rhg.outsourcing.bean.ImageModel;
import com.example.rhg.outsourcing.bean.RecommendListTypeModel;
import com.example.rhg.outsourcing.bean.RecommendTextTypeModel;
import com.example.rhg.outsourcing.bean.BaseSellerModel;
import com.example.rhg.outsourcing.bean.TextTypeModel;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter;
import com.example.rhg.outsourcing.utils.BannerController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by remember on 2016/5/3.
 */
public class HomeFragment extends SuperFragment implements RecycleMultiTypeAdapter.OnBannerClickListener,
        RecycleMultiTypeAdapter.OnGridItemClickListener, RecycleSellerAdapter.OnListItemClick {
    List<ImageModel> imageModels = new ArrayList<ImageModel>();
    private String[] images = {
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
    };
    List<BaseSellerModel> sellRecommendModels = new ArrayList<BaseSellerModel>();
    View view;
    RecyclerView rcv;
    SwipeRefreshLayout swipeRefreshLayout;
    RecycleMultiTypeAdapter recycleMultiTypeAdapter;

    TestPresenter testPresenter;
    //itme的数据类型集合
    List<Object> mData;

    public HomeFragment() {
        testPresenter = new TestPresenter(this);
        Log.i("RHG", "HomeFragment has created");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.rcv_item;
    }


    @Override
    protected void initView(View view) {
        rcv = (RecyclerView) view.findViewById(R.id.recycleview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        recycleMultiTypeAdapter = new RecycleMultiTypeAdapter(getContext(), mData);
        recycleMultiTypeAdapter.setBannerClickListener(this);
        recycleMultiTypeAdapter.setOnGridItemClickListener(this);
        fillItemList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setHasFixedSize(false);
        rcv.setAdapter(recycleMultiTypeAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("RHG", "OnRefresh");
                testPresenter.getData();
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i("RHG", "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("RHG", "onStop");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("RHG", "onResume");
        BannerController.getInstance().startBanner(2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("RHG", "onPause");
        BannerController.getInstance().stopBanner();
    }

    @Override
    protected void showFailed() {
        Log.i("RHG", "HomeFragment failed");

    }

    @Override
    public void showSuccess(Object o) {
        mData.remove(1);
        mData.add(1,(BannerTypeModel)o);
        recycleMultiTypeAdapter.notifyItemChanged(1);

        swipeRefreshLayout.setRefreshing(false);

    }

    private void fillItemList() {
        for (int i = 0; i < 6; i++) {
            ImageModel imageModel = new ImageModel();
            BaseSellerModel sellRecommendModel = new BaseSellerModel("哈哈", "中餐", "距离10m", R.drawable.recommend_default_icon_1);

            imageModel.setImageId(R.drawable.recommend_default_icon_1);
            switch (i) {
                case 0:
                case 3:

                    imageModel.setHeadercolor(R.color.colorAccent);
                    imageModel.setContent("哈哈哈哈");
                    break;
                case 1:
                case 4:
                    imageModel.setHeadercolor(R.color.colorActiveGreen);
                    imageModel.setContent("呵呵呵呵");
                    break;
                case 2:
                case 5:
                    imageModel.setHeadercolor(R.color.colorInActive);
                    imageModel.setContent("啊啊啊啊");
                    break;
            }

            sellRecommendModels.add(sellRecommendModel);
            imageModels.add(imageModel);
        }
        mData.add(new HeaderTypeModel("Header", R.color.cardview_shadow_start_color));
        BannerTypeModel bannerTypeModel = new BannerTypeModel();
        bannerTypeModel.setImageUrls(Arrays.asList(images));
        mData.add(bannerTypeModel);//TODO 传入URL集合
        mData.add(new TextTypeModel("aaaaa"));
        mData.add(new FavorableTypeModel(imageModels,new DPGridViewAdapter(getContext(),imageModels,R.layout.recyclegriditem)));
        mData.add(new RecommendTextTypeModel());
        mData.add(new RecommendListTypeModel(sellRecommendModels, new RecycleSellerAdapter(getContext(), sellRecommendModels, AppConstants.TypeHome), this));
        mData.add(new FooterTypeModel("FooterType", R.color.colorPrimaryDark));
        recycleMultiTypeAdapter.notifyDataSetChanged();
    }



    //--------------------------------点击事件回调---------------------------------------------------
    @Override
    public void bannerClick(int position) {
        Intent intent = new Intent(getContext(), ShopDetailActivity.class);
        /*todo 传递参数*/
        intent.putExtra(AppConstants.KEY_PHONE,"1234567890");
        intent.putExtra(AppConstants.KEY_ADDRESS,"江苏省南京市江宁区东南大学");
        intent.putExtra(AppConstants.KEY_NOTE,"东南大学是一所985高校");
        intent.putExtra(AppConstants.KEY_MERCHANT_ID,"20160517");
        intent.putExtra(AppConstants.KEY_MERCHANT_NAME,"荣哥土菜馆");
        intent.putExtra(AppConstants.KEY_MERCHANT_LOGO,AppConstants.images[3]);
        startActivity(intent);
    }

    @Override
    public void gridItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
        intent.putExtra(AppConstants.KEY_PRODUCT_ID,"20160518");
        intent.putExtra(AppConstants.KEY_PRODUCT_NAME,"土豆丝");
        intent.putExtra(AppConstants.KEY_PRODUCT_PRICE,"90");
        startActivityForResult(intent,AppConstants.START_0);
    }

    @Override
    public void itemClick(View v, int position) {
        Intent intent = new Intent(getContext(), ShopDetailActivity.class);
        /*todo 传递参数*/
        intent.putExtra(AppConstants.KEY_PHONE,"1234567890");
        intent.putExtra(AppConstants.KEY_ADDRESS,"江苏省南京市江宁区东南大学");
        intent.putExtra(AppConstants.KEY_NOTE,"东南大学是一所985高校");
        intent.putExtra(AppConstants.KEY_MERCHANT_ID,"20160517");
        intent.putExtra(AppConstants.KEY_MERCHANT_NAME,"荣哥土菜馆");
        intent.putExtra(AppConstants.KEY_MERCHANT_LOGO,AppConstants.images[1]);
        startActivity(intent);
    }
}
