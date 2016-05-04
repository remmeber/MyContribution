package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.rhg.outsourcing.MySwipeLayout;
import com.example.rhg.outsourcing.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


/**
 * Created by remember on 2016/4/28.
 */
public class SellerFragment extends SuperFragment {
    private static final String TAG = "SellerFragment";
    View view;
    MySwipeLayout mySwipeLayout;
    RecyclerView recyclerView;
    //-----------------根据需求创建相应的presenter----------------------------------------------------

    //----------------------------------------------------------------------------------------------
    public SellerFragment() {
        Log.i(TAG,"SellerFragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.seller_layout,container,false);
        mySwipeLayout = (MySwipeLayout) view.findViewById(R.id.seller_swipe);
        recyclerView = (RecyclerView)view.findViewById(R.id.seller_recycle);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess() {

    }
}
