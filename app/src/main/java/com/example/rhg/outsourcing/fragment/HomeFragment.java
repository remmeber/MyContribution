package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.BannerImageHolder;
import com.example.rhg.outsourcing.presenter.TestPresenter;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by remember on 2016/4/28.
 */
public class HomeFragment extends SuperFragment {
    private static final String TAG = "HomeFragment";
    private MaterialRefreshLayout materialRefreshLayout;
    private ConvenientBanner convenientbanner;
    private TestPresenter testPresenter;
    private List<String> networkImages;
    private String[] images = {
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
    };

    View view;

    public HomeFragment() {
        testPresenter = new TestPresenter(this);
        networkImages = Arrays.asList(images);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_main, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initImageLoader();
        initView();
        initData();
    }

    private void initData() {
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(false);
    }


    private void initView() {
        convenientbanner = (ConvenientBanner) view.findViewById(R.id.pictureBanner);
        convenientbanner.setPages(new CBViewHolderCreator<BannerImageHolder>() {
            @Override
            public BannerImageHolder createHolder() {
                return new BannerImageHolder();
            }
        }, networkImages)
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Log.i(TAG, "picture: " + position + " is click");
                    }
                });

        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                Log.i(TAG, "onRefresh");
                testPresenter.getData();
            }

            @Override
            public void onfinish() {
                Log.i(TAG, "finish");
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
            }
        });
    }

    //------网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.recommend_default_icon_2)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this.getActivity()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onResume() {
        super.onResume();
        convenientbanner.startTurning(2000);
        Log.i(TAG, "convenientbanner is started");
    }

    @Override
    public void onPause() {
        super.onPause();
        convenientbanner.stopTurning();
        Log.i(TAG, "convenientbanner is stopped");
    }

    @Override
    public void showSuccess(Object o) {
        Log.i(TAG, "HomeFragment success");
        materialRefreshLayout.finishRefresh();
    }

    @Override
    protected void showFailed() {
        Log.i(TAG, "HomeFragment failed");
    }
}
