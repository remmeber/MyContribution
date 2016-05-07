package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.Constants.AppConstants;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.RecommendAdapter;
import com.example.rhg.outsourcing.model.SellerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/5/7.
 */
public class sellNumberFragment extends SuperFragment {
    private RecyclerView recyclerView;
    List<SellerModel> sellerModels = new ArrayList<>();

    public sellNumberFragment() {
        for (int i = 0; i < 6; i++) {
            SellerModel sellerModel = new SellerModel("哈哈", "中餐", "距离10m", R.drawable.recommend_default_icon_1);
            sellerModels.add(sellerModel);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sellnumberlayout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.sellNumberRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecommendAdapter recommendAdapter = new RecommendAdapter(getContext(), sellerModels, AppConstants.TypeSeller);
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
