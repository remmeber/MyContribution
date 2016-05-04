package com.example.rhg.outsourcing.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.rhg.outsourcing.MainActivity;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.RecycleMultiTypeAdapter;
import com.example.rhg.outsourcing.model.BannerTypeModel;
import com.example.rhg.outsourcing.model.FavorableTypeModel;
import com.example.rhg.outsourcing.model.FooterTypeModel;
import com.example.rhg.outsourcing.model.HeaderTypeModel;
import com.example.rhg.outsourcing.model.RecommendListTypeModel;
import com.example.rhg.outsourcing.model.RecommendTextTypeModel;
import com.example.rhg.outsourcing.model.TextTypeModel;
import com.example.rhg.outsourcing.presenter.TestPresenter;
import com.example.rhg.outsourcing.utils.BannerController;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by remember on 2016/5/3.
 */
public class HomeFragmentTest extends SuperFragment implements RecycleMultiTypeAdapter.OnBannerClickListener {
    private String[] images = {
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
    };
    View view;
    RecyclerView rcv;
    SwipeRefreshLayout swipeRefreshLayout;
    RecycleMultiTypeAdapter recycleMultiTypeAdapter;

    TestPresenter testPresenter;
    //itme的数据类型集合
    List<Object> mData;

    public HomeFragmentTest() {
        testPresenter = new TestPresenter(this);
        Log.i("RHG", "HomeFragmentTest has created");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_layout, container, false);
        rcv = (RecyclerView) view.findViewById(R.id.recycleview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("RHG","onActivityCreated");
        initImageLoader();
        mData = new ArrayList<>();
        fillItemList();
        recycleMultiTypeAdapter = new RecycleMultiTypeAdapter(getContext(), mData);
        recycleMultiTypeAdapter.setBannerClickListener(this);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setHasFixedSize(true);
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
        Log.i("RHG","onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("RHG","onStop");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("RHG","onResume");
        BannerController.getInstance().startBanner(2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("RHG","onPause");
        BannerController.getInstance().stopBanner();
    }

    @Override
    protected void showFailed() {
        Log.i("RHG", "HomeFragment failed");

    }

    @Override
    public void showSuccess() {
        Log.i("RHG", "HomeFragment success");
        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);

    }

    private void fillItemList() {
        mData.add(new HeaderTypeModel("Header", R.color.cardview_shadow_start_color));
        mData.add(new BannerTypeModel(Arrays.asList(images)));//TODO 传入URL集合
        mData.add(new TextTypeModel("Text", R.color.colorAccent));
        mData.add(new FavorableTypeModel("Favorable", R.color.colorActiveYellow));
        mData.add(new RecommendTextTypeModel("RecommendText", R.color.light));
        mData.add(new RecommendListTypeModel("RecommendList", R.color.colorPrimary));
        mData.add(new FooterTypeModel("FooterType", R.color.colorPrimaryDark));

    }
    //------网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.recommend_default_icon_2)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getActivity()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void bannerClick(int position) {
        Toast.makeText(getActivity(),"banner "+position+" is clicked",Toast.LENGTH_SHORT).show();
    }
}
