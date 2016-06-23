package com.rhg.outsourcing.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.application.InitApplication;
import com.rhg.outsourcing.bean.AddressLocalBean;
import com.rhg.outsourcing.locationservice.LocationService;
import com.rhg.outsourcing.locationservice.MyLocationListener;
import com.rhg.outsourcing.utils.AddressUtil;
import com.rhg.outsourcing.utils.DataUtil;
import com.rhg.outsourcing.utils.ToastHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：rememberon 2016/6/16
 * 邮箱：1013773046@qq.com
 */
public class NewAddressActivity extends BaseActivity {
    @Bind(R.id.tb_center_tv)
    TextView tbCenterTv;
    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.fl_tab)
    FrameLayout flTab;
    @Bind(R.id.add_new_address_contact_person_content)
    EditText addNewAddressContactPersonContent;
    @Bind(R.id.add_new_address_contacts_content)
    EditText addNewAddressContactsContent;
    @Bind(R.id.add_new_address_contact_address_content)
    TextView addNewAddressContactAddressContent;

    @Override
    protected int getLayoutResId() {
        return R.layout.new_address_layout;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected boolean isNeedFirstLoc() {
        return false;
    }

    @Override
    public LocationService GetMapService() {
        return InitApplication.getInstance().locationService;
    }

    @Override
    public MyLocationListener getLocationListener() {
        return new MyLocationListener(this);
    }

    @Override
    protected void initData() {
        flTab.setBackgroundColor(getResources().getColor(R.color.colorActiveGreen));
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        tbCenterTv.setText(getResources().getString(R.string.newAddress));
    }

    @Override
    protected void showSuccess(Object s) {
    }

    @Override
    protected void showError(Object s) {
    }

    @Override
    public void showLocSuccess(String s) {
        addNewAddressContactAddressContent.setText(s);
    }

    @Override
    public void showLocFailed(String s) {
        ToastHelper.getInstance()._toast(s);
    }

    @OnClick({R.id.tb_left_iv, R.id.add_new_address_bt,
            R.id.add_new_address_location, R.id.add_new_address_contact_address_content})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
            case R.id.add_new_address_bt:
                if (TextUtils.isEmpty(addNewAddressContactPersonContent.getText())) {
                    ToastHelper.getInstance()._toast(getResources().getString(R.string.contactUser_Null));
                    break;
                }
                if (TextUtils.isEmpty(addNewAddressContactsContent.getText())) {
                    ToastHelper.getInstance()._toast(getResources().getString(R.string.contacts_Null));
                    break;
                }
                if (TextUtils.isEmpty(addNewAddressContactAddressContent.getHint())) {
                    ToastHelper.getInstance()._toast(getResources().getString(R.string.contactAddress_Null));
                    break;
                }
                AddressLocalBean addressLocalBean = new AddressLocalBean();
                addressLocalBean.setCreateTime(DataUtil.getCurrentTime());
                addressLocalBean.setName(addNewAddressContactPersonContent.getText().toString());
                addressLocalBean.setPhone(addNewAddressContactsContent.getText().toString());
                addressLocalBean.setAddress(addNewAddressContactAddressContent.getText().toString());
                AddressUtil.insertAddress(addressLocalBean);
                break;
            case R.id.add_new_address_location:
            case R.id.add_new_address_contact_address_content:
                reStartLocation();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
