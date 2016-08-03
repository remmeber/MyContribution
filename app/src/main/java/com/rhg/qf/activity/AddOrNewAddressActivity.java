package com.rhg.qf.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
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

    private int resultCode;
    AddOrUpdateAddressPresenter addOrUpdateAddress;
    private String orderId;


    @Override
    public void dataReceive(Intent intent) {
        if (intent.getParcelableExtra(AppConstants.KEY_ADDRESS) == null) {
            resultCode = AppConstants.BACK_WITH_ADD;
        } else {
            resultCode = AppConstants.BACK_WITH_UPDATE;
            AddressUrlBean.AddressBean _address = intent.getParcelableExtra(AppConstants.KEY_ADDRESS);
            orderId = _address.getID();
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
        flTab.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        if (resultCode == AppConstants.BACK_WITH_ADD)
            tbCenterTv.setText(getResources().getString(R.string.newAddress));
        else {
            tbCenterTv.setText(getResources().getString(R.string.modifyAddress));
        }
    }

    @Override
    protected void showSuccess(Object s) {
//        setResult(resultCode, new Intent().putExtra(AppConstants.KEY_ADDRESS, addressBean));
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
               /* AddressUrlBean.AddressBean addressBean = new AddressUrlBean.AddressBean();
                addressBean.setName(addNewAddressContactPersonContent.getText().toString());
                addressBean.setPhone(addNewAddressContactsContent.getText().toString());
                String _address = addNewAddressContactAddressContent.getText().toString();
                _address = _address.concat(addNewAddressContentDetail.getText().toString());*/
//                StringBuilder _address = new StringBuilder();
               /* _address.append(addNewAddressContactAddressContent.getText().toString())
                        .append(addNewAddressContentDetail.getText().toString());*/
//                addressBean.setAddress(_address);
                /*TODO 以下代码需要在提交地址保存至数据库成功后执行*/
                if (addOrUpdateAddress == null)
                    addOrUpdateAddress = new AddOrUpdateAddressPresenter(this);
                addOrUpdateAddress.addOrUpdateAddress(orderId, addNewAddressContactPersonContent.getText().toString(),
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
//        setResult(resultCode, null);/*不需要做任何事情*/
        super.onBackPressed();
    }
}
