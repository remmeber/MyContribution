package com.example.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.RecycleMultiTypeAdapter;
import com.example.rhg.outsourcing.model.BannerTypeModel;
import com.example.rhg.outsourcing.model.FavorableTypeModel;
import com.example.rhg.outsourcing.model.FooterTypeModel;
import com.example.rhg.outsourcing.model.HeaderTypeModel;
import com.example.rhg.outsourcing.model.RecommendListTypeModel;
import com.example.rhg.outsourcing.model.RecommendTextTypeModel;
import com.example.rhg.outsourcing.model.TextTypeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remember on 2016/5/3.
 */
public class HomeFragmentTest extends Fragment {
    View view;
    RecycleMultiTypeAdapter recycleMultiTypeAdapter;
    List<Object> mData;

    public HomeFragmentTest() {
        Log.i("RHG","HomeFragmentTest has created");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_layout,container,false);
        RecyclerView rcv = (RecyclerView)view.findViewById(R.id.recycleview);
        mData = new ArrayList<>();
        recycleMultiTypeAdapter = new RecycleMultiTypeAdapter(this.getContext(),mData);
        fillItemList();
        recycleMultiTypeAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                Log.i("RHG","DATA has changed");
            }
        });
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setHasFixedSize(true);
        rcv.setAdapter(recycleMultiTypeAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void fillItemList() {
        mData.add(new HeaderTypeModel("Button",R.color.cardview_shadow_start_color));
        mData.add(new BannerTypeModel("Button",R.color.white));
        mData.add(new TextTypeModel("Button",R.color.colorAccent));
        mData.add(new FavorableTypeModel("Button",R.color.colorActiveYellow));
        mData.add(new RecommendTextTypeModel("Button",R.color.light));
        mData.add(new RecommendListTypeModel("Button",R.color.colorPrimary));
        mData.add(new FooterTypeModel("Button",R.color.colorPrimaryDark));
        recycleMultiTypeAdapter.notifyDataSetChanged();
    }
}
