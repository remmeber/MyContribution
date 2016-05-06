package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.MySwipeLayout;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.RecommendAdapter;
import com.example.rhg.outsourcing.model.SellerModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by remember on 2016/4/28.
 */
public class SellerFragment extends SuperFragment {
    private static final String TAG = "SellerFragment";
    List<SellerModel> sellerModels = new ArrayList<SellerModel>();
    View view;
    MySwipeLayout mySwipeLayout;
    RecyclerView recyclerView;
    //-----------------根据需求创建相应的presenter----------------------------------------------------

    //----------------------------------------------------------------------------------------------
    public SellerFragment() {
        Log.i(TAG,"SellerFragment");
        for (int i =0;i<6;i++){
            sellerModels.add(new SellerModel("哈哈","中餐","距离10m",R.drawable.recommend_default_icon_1));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.seller_layout,container,false);
        mySwipeLayout = (MySwipeLayout) view.findViewById(R.id.seller_swipe);
        recyclerView = (RecyclerView)view.findViewById(R.id.seller_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        RecommendAdapter recommendAdapter = new RecommendAdapter(getContext(),sellerModels);
        recyclerView.setAdapter(recommendAdapter);
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
    public void showSuccess(Object o) {

    }
}
