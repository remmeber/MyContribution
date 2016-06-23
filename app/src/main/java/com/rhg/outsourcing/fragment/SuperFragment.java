package com.rhg.outsourcing.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rhg.outsourcing.application.InitApplication;
import com.rhg.outsourcing.locationservice.LocationService;
import com.rhg.outsourcing.locationservice.MyLocationListener;
import com.rhg.outsourcing.mvp.view.BaseView;
import com.rhg.outsourcing.utils.NetUtil;
import com.rhg.outsourcing.utils.ToastHelper;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;


/**
 * desc:所有fm的基类
 * author：remember
 * time：2016/5/28 16:45
 * email：1013773046@qq.com
 */
public abstract class SuperFragment extends Fragment implements BaseView {
    //TODO 百度地图
    private LocationService locationService;
    private MyLocationListener mLocationListener;
//    private Unbinder bind;

    public SuperFragment() {
    }

    // TODO: 子类重写该方法，获取数据的统一入口
    public void receiveData(Bundle arguments) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        receiveData(getArguments());
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    public int getLayoutResId() {
        return 0;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!NetUtil.isConnected(getContext())) {
            ToastHelper.getInstance()._toast("网络未连接");
        } else {
            startLoc();
            loadData();
        }
        initData();
        fillData();
    }

    private void startLoc() {
        if ((locationService = GetMapService()) != null) {
            if ((mLocationListener = getLocationListener()) != null) {
                locationService.registerListener(mLocationListener);
                locationService.setLocationOption(locationService.getDefaultLocationClientOption());
//                getLocation(locationService, mLocationListener);
                mLocationListener.getLocation(locationService);
            }

        }
    }

    public void reStartLocation() {
        if (locationService == null) {
            locationService = GetMapService();
        }
        if (mLocationListener == null) {
            locationService.registerListener(mLocationListener = getLocationListener());
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        }
//        getLocation(locationService, mLocationListener);
        mLocationListener.getLocation(locationService);
    }

    public MyLocationListener getLocationListener() {
        return null;
    }

    public void getLocation(LocationService locationService, MyLocationListener mLocationListener) {
    }


    /*默认不定位，如果需要定位，子类需要重写该方法*/
    public LocationService GetMapService() {
        return null;
    }

    /**
     * 将数据填充
     */
    public void fillData() {
    }

    /**
     * 从网络获取数据，在new的时候只加载一次，后期都需要refresh才能更新
     */
    public void loadData() {
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    @Override
    public void showData(Object o) {
        if (o instanceof String) {
            String _str = (String) o;
            if (_str.contains("location")) {
                String[] location_str = _str.split(",");
                if (location_str[1].equals("error"))
                    showLocFailed(location_str[2]);
                else
                    showLocSuccess(location_str[2]);
                if (locationService != null) {
                    locationService.stop();
                }
            } else {
                showSuccess(_str);
            }
            return;
        }
        showSuccess(o);
    }

    public void showLocSuccess(String s) {
    }

    public void showLocFailed(String s) {
    }

    @Override
    public void onStop() {
        super.onStop();
        if (locationService != null) {
            locationService.unregisterListener(mLocationListener); //注销掉监听
            locationService.stop(); //停止定位服务
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        RefWatcher refWatcher = InitApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    protected abstract void showFailed();


    public abstract void showSuccess(Object o);

}
