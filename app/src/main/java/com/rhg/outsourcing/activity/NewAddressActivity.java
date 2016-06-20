package com.rhg.outsourcing.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.application.InitApplication;
import com.rhg.outsourcing.bean.AddressLocalBean;
import com.rhg.outsourcing.locationservice.LocationService;
import com.rhg.outsourcing.locationservice.MyLocationListener;
import com.rhg.outsourcing.utils.AddressUtil;
import com.rhg.outsourcing.utils.DataUtil;
import com.rhg.outsourcing.utils.ToastHelper;

import butterknife.OnClick;

/**
 * 作者：rememberon 2016/6/16
 * 邮箱：1013773046@qq.com
 */
public class NewAddressActivity extends BaseActivity {

    TextView tbCenterTv;
    ImageView tbLeftIv;
    LinearLayout tbRightLl;
    FrameLayout flTab;
    EditText addNewAddressContactPersonContent;
    EditText addNewAddressContactsContent;
    ImageView addNewAddressLocation;
    EditText addNewAddressContentDetail;
    Button addNewAddressBt;
    TextView addNewAddressContactAddressContent;

    @Override
    protected int getLayoutResId() {
        return R.layout.new_address_layout;
    }

    @Override
    protected void initView(View view) {
        flTab = (FrameLayout) view.findViewById(R.id.new_address_include);
        tbCenterTv = (TextView) view.findViewById(R.id.new_address_include).findViewById(R.id.tb_center_tv);
        tbLeftIv = (ImageView) view.findViewById(R.id.new_address_include).findViewById(R.id.tb_left_iv);
        tbRightLl = (LinearLayout) view.findViewById(R.id.new_address_include).findViewById(R.id.tb_right_ll);

        addNewAddressContactPersonContent = (EditText) findViewById(R.id.add_new_address_contact_person_content);
        addNewAddressContactsContent = (EditText) findViewById(R.id.add_new_address_contacts_content);
        addNewAddressContentDetail = (EditText) findViewById(R.id.add_new_address_content_detail);
        addNewAddressLocation = (ImageView) findViewById(R.id.add_new_address_location);
        addNewAddressBt = (Button) findViewById(R.id.add_new_address_bt);
        addNewAddressContactAddressContent = (TextView) findViewById(R.id.add_new_address_contact_address_content);


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
        tbRightLl.setVisibility(View.GONE);

        tbLeftIv.setOnClickListener(this);
        addNewAddressBt.setOnClickListener(this);
        addNewAddressContactAddressContent.setOnClickListener(this);
        addNewAddressLocation.setOnClickListener(this);
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

}
