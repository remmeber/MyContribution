package com.rhg.qf.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.apapter.AddressAdapter;
import com.rhg.qf.bean.AddressLocalBean;
import com.rhg.qf.utils.DpUtil;
import com.rhg.qf.utils.ToastHelper;
import com.rhg.qf.widget.AddressRecycleViewWithDelete;
import com.rhg.qf.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：rememberon 2016/6/5
 * 邮箱：1013773046@qq.com
 */
public class AddressActivity extends BaseActivity implements AddressRecycleViewWithDelete.ItemClickListener {

    @Bind(R.id.tb_center_tv)
    TextView tbCenterTv;
    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.fl_tab)
    FrameLayout flTab;
    @Bind(R.id.rcy_address)
    AddressRecycleViewWithDelete rcyAddress;
    @Bind(R.id.srl_address)
    SwipeRefreshLayout srlAddress;


    AddressAdapter addressAdapter;
    int lastPosition = -1;
    List<AddressLocalBean> addressBeanList;

    public AddressActivity() {
        addressBeanList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            AddressLocalBean addressBean = new AddressLocalBean();
            if (i == 0)
                addressBean.setChecked(true);
            else addressBean.setChecked(false);
            addressBean.setName("哈哈");
            addressBean.setAddress("东南大学");
            addressBean.setPhone("1" + i);
            addressBeanList.add(addressBean);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.address_layout;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        flTab.setBackgroundColor(getResources().getColor(R.color.colorActiveGreen));
        tbCenterTv.setText(getResources().getString(R.string.address));
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcyAddress.setLayoutManager(linearLayoutManager);
        rcyAddress.setHasFixedSize(true);
        RecycleViewDivider divider = new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL,
                DpUtil.dip2px(8), getResources().getColor(R.color.colorBackground));
        rcyAddress.addItemDecoration(divider);
        addressAdapter = new AddressAdapter(this, addressBeanList);
        rcyAddress.setAdapter(addressAdapter);
        rcyAddress.setOnItemClickListener(this);
        /*rcyAddress.setRemoveListener(new SwipeDeleteRecycle.RemoveListener() {
            @Override
            public void removeItem(SwipeDeleteRecycle.RemoveDirection direction, int position) {
                ToastHelper.getInstance()._toast("删除：" + position);
            }
        });*/
        srlAddress.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srlAddress.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }


    @Override
    public void onItemClick(int position) {
        if (position != lastPosition) {
            selectOne(position);
            addressAdapter.notifyDataSetChanged();
            lastPosition = position;
        }
    }

    private void selectOne(int position) {
        for (int i = 0; i < addressBeanList.size(); i++) {
            if (position == i)
                addressBeanList.get(i).setChecked(true);
            else addressBeanList.get(i).setChecked(false);
        }
    }


    @OnClick({R.id.tb_left_iv, R.id.bt_add_new_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add_new_address:
                ToastHelper.getInstance()._toast("增加地址");
                break;
            case R.id.tb_left_iv:
                finish();
                break;
        }
    }
}
