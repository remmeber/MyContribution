package com.example.rhg.outsourcing.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.AddressAdapter;
import com.example.rhg.outsourcing.bean.AddressLocalBean;
import com.example.rhg.outsourcing.utils.DpUtil;
import com.example.rhg.outsourcing.utils.ToastHelper;
import com.example.rhg.outsourcing.widget.AddressRecycleViewWithDelete;
import com.example.rhg.outsourcing.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：rememberon 2016/6/5
 * 邮箱：1013773046@qq.com
 */
public class AddressActivity extends BaseActivity implements AddressRecycleViewWithDelete.ItemClickListener {
    FrameLayout tb_common;
    LinearLayout tbRight_ll;
    TextView tvCenter;
    ImageView ivLeft;

    SwipeRefreshLayout srlAddress;
    AddressRecycleViewWithDelete rcyAddress;
    AddressAdapter addressAdapter;
    Button btAddAddress;
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
        tb_common = (FrameLayout) view.findViewById(R.id.address_include);
        tbRight_ll = (LinearLayout) view.findViewById(R.id.address_include)
                .findViewById(R.id.tb_right_ll);
        tvCenter = (TextView) view.findViewById(R.id.address_include)
                .findViewById(R.id.tb_center_tv);
        ivLeft = (ImageView) view.findViewById(R.id.address_include)
                .findViewById(R.id.tb_left_iv);

        srlAddress = (SwipeRefreshLayout) findViewById(R.id.srl_address);
        rcyAddress = (AddressRecycleViewWithDelete) findViewById(R.id.rcy_address);
        btAddAddress = (Button) findViewById(R.id.bt_add_new_address);
    }

    @Override
    protected void initData() {
        tb_common.setBackgroundColor(getResources().getColor(R.color.colorActiveGreen));
        tbRight_ll.setVisibility(View.GONE);
        tvCenter.setText(getResources().getString(R.string.address));
        ivLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        ivLeft.setOnClickListener(this);

        rcyAddress.addItemDecoration(new RecycleViewDivider(this,
                        DpUtil.dip2px(8),
                        getResources().getColor(R.color.colorSearchHint)
                )
        );
        rcyAddress.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcyAddress.setLayoutManager(linearLayoutManager);
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
        btAddAddress.setText(getResources().getString(R.string.newAddress));
        btAddAddress.setOnClickListener(this);
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_new_address:
                ToastHelper.getInstance()._toast("增加地址");
                break;
            case R.id.tb_left_iv:
                finish();
                break;
        }
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

}
