package com.rhg.qf.ui.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.application.InitApplication;
import com.rhg.qf.bean.AddressUrlBean;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.locationservice.LocationService;
import com.rhg.qf.locationservice.MyLocationListener;
import com.rhg.qf.mvp.presenter.AddOrUpdateAddressPresenter;
import com.rhg.qf.utils.ToastHelper;

import butterknife.Bind;
import butterknife.OnClick;

/*
 *desc 增加地址或者修改地址页面
 *author rhg
 *time 2016/7/4 20:01
 *email 1013773046@qq.com
 */

public class AddOrNewAddressActivity extends BaseAppcompactActivity {
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
    @Bind(R.id.add_new_address_content_detail)
    EditText addNewAddressContentDetail;
    AddOrUpdateAddressPresenter addOrUpdateAddress;
    boolean isBackWithoutOption = true;
    private int resultCode = 0;
    private String addressId = null;

    @Override
    public void dataReceive(Intent intent) {
        if (intent.getParcelableExtra(AppConstants.KEY_ADDRESS) == null) {
            resultCode = AppConstants.BACK_WITH_ADD;
        } else {
            resultCode = AppConstants.BACK_WITH_UPDATE;
            AddressUrlBean.AddressBean _address = intent.getParcelableExtra(AppConstants.KEY_ADDRESS);
            addressId = _address.getID();
            addNewAddressContactPersonContent.setText(_address.getName());
            addNewAddressContactsContent.setText(_address.getPhone());
            addNewAddressContactAddressContent.setText(_address.getAddress());
            if (_address.getDetail() != null)
                addNewAddressContentDetail.setText(_address.getDetail());

        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.new_address_layout;
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
        flTab.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlueNormal));
        tbLeftIv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_chevron_left_black));
        if (resultCode == AppConstants.BACK_WITH_ADD)
            tbCenterTv.setText(getResources().getString(R.string.newAddress));
        else {
            tbCenterTv.setText(getResources().getString(R.string.modifyAddress));
        }
    }

    @Override
    protected void showSuccess(Object s) {
//        setResult(resultCode, new Intent().putExtra(AppConstants.KEY_ADDRESS, addressBean));
        Intent intent = new Intent();
        intent.putExtra("return", new AddressUrlBean.AddressBean(
                addNewAddressContactPersonContent.getText().toString(),
                addNewAddressContactsContent.getText().toString(),
                addNewAddressContactAddressContent.getText().toString(),
                addNewAddressContentDetail.getText().toString()));
        setResult(0, intent);/*不需要做任何事情*/
        finish();
    }

    @Override
    protected void showError(Object s) {
    }

    @Override
    public void showLocSuccess(String s) {
        String[] _str = s.split(",");
        addNewAddressContactAddressContent.setText(_str[0].concat(_str[1]).concat(_str[2]));
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
                isBackWithoutOption = true;
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
                if (TextUtils.isEmpty(addNewAddressContactAddressContent.getText())) {
                    ToastHelper.getInstance()._toast(getResources().getString(R.string.contactAddress_Null));
                    break;
                }
                if (TextUtils.isEmpty(addNewAddressContentDetail.getText())) {
                    ToastHelper.getInstance()._toast(getResources().getString(R.string.contactAddress_Null));
                    break;
                }
               /* AddressUrlBean.AddressBean addressBean = new AddressUrlBean.AddressBean();
                addressBean.setName(addNewAddressContactPersonContent.getText().toString());
                addressBean.setPhone(addNewAddressContactsContent.getText().toString());
                String _address = addNewAddressContactAddressContent.getText().toString();
                _address = _address.concat(addNewAddressContentDetail.getText().toString());*/
//                StringBuilder _address = new StringBuilder();
               /* _address.append(addNewAddressContactAddressContent.getText().toString())
                        .append(addNewAddressContentDetail.getText().toString());*/
//                addressBean.setAddress(_address);
                if (addOrUpdateAddress == null)
                    addOrUpdateAddress = new AddOrUpdateAddressPresenter(this);
                addOrUpdateAddress.addOrUpdateAddress(addressId, addNewAddressContactPersonContent.getText().toString(),
                        addNewAddressContactsContent.getText().toString(),
                        addNewAddressContactAddressContent.getText().toString(),
                        addNewAddressContentDetail.getText().toString(),
                        null);
                /*AddressUtil.insertAddress(addressBean);*/
                break;
            case R.id.add_new_address_location:
            case R.id.add_new_address_contact_address_content:
                reStartLocation();
                break;
        }
    }


    @Override
    public void onBackPressed() {
        setResult(resultCode, null);
        super.onBackPressed();
    }

}
