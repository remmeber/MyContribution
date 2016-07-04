package com.rhg.qf.activity;

import android.content.Intent;
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
import com.rhg.qf.utils.DataUtil;
import com.rhg.qf.utils.ToastHelper;

import butterknife.Bind;
import butterknife.OnClick;

/*
 *desc 增加地址或者修改地址页面
 *author rhg
 *time 2016/7/4 20:01
 *email 1013773046@qq.com
 */

public class AddOrNewAddressActivity extends BaseActivity {
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
    private StringBuilder address;


    @Override
    public void dataReceive(Intent intent) {
        if (intent.getStringExtra(AppConstants.KEY_ADDRESS) == null) {/*为空则说明是增加地址，否则是修改地址*/
            resultCode = AppConstants.BACK_WITH_ADD;
            ToastHelper.getInstance()._toast("增加地址");
        } else {
            resultCode = AppConstants.BACK_WITH_UPDATE;
            ToastHelper.getInstance()._toast("修改地址");
        }
    }

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
                AddressUrlBean.AddressBean addressBean = new AddressUrlBean.AddressBean();
                addressBean.setID(DataUtil.getCurrentTime());
                addressBean.setName(addNewAddressContactPersonContent.getText().toString());
                addressBean.setPhone(addNewAddressContactsContent.getText().toString());
                StringBuilder _address = new StringBuilder();
                _address.append(addNewAddressContactAddressContent).append(addNewAddressContentDetail);
                addressBean.setAddress(String.valueOf(_address));
                /*TODO 以下代码需要在提交地址保存至数据库成功后执行*/
                setResult(resultCode, new Intent().putExtra(AppConstants.KEY_ADDRESS, addressBean));
                finish();
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
        setResult(resultCode, null);/*不需要做任何事情*/
        super.onBackPressed();
    }
}
