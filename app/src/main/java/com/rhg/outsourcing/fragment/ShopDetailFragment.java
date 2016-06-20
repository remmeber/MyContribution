package com.rhg.outsourcing.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.constants.AppConstants;

/**
 *desc:店铺详情fm
 *author：remember
 *time：2016/5/28 16:49
 *email：1013773046@qq.com
 */
public class ShopDetailFragment extends SuperFragment {
    TextView tvPhoneNumber;
    String phoneNumber;
    TextView tvAddress;
    String Address;
    TextView tvMerchantNote;
    String merchantNote;

    @Override
    public void receiveData(Bundle arguments) {
        String _address = getResources().getString(R.string.addrss);
        String _note = getResources().getString(R.string.note);
        if (arguments != null) {
            phoneNumber = arguments.getString(AppConstants.KEY_OR_SP_PHONE);
            String _temp = arguments.getString(AppConstants.KEY_ADDRESS);
            if (_temp == null || "".equals(_temp))
                Address = _address + "无";
            else
                Address = _address + _temp;
            _temp = arguments.getString(AppConstants.KEY_NOTE);
            if (_temp == null || "".equals(_temp))
                merchantNote = _note + "无";
            else
                merchantNote = _note + _temp;
        } else {
            phoneNumber = "0246813579";
            Address = _address + "江苏省南京市江宁区秣周东路无线谷";
            merchantNote = _note + "无线谷是一个非常神奇的地方";
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.shop_detail_fm2_content;
    }

    @Override
    protected void initData() {
        tvPhoneNumber.setText(phoneNumber);
        tvAddress.setText(Address);
        tvMerchantNote.setText(merchantNote);
    }

    @Override
    protected void initView(View view) {
        tvPhoneNumber = (TextView) view.findViewById(R.id.tv_shop_phone_num);
        tvAddress = (TextView) view.findViewById(R.id.tv_shop_address);
        tvMerchantNote = (TextView) view.findViewById(R.id.tv_seller_note);
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {

    }
}