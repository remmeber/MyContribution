package com.rhg.qf.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.constants.AppConstants;

import butterknife.Bind;

/**
 * desc:店铺详情fm
 * author：remember
 * time：2016/5/28 16:49
 * email：1013773046@qq.com
 */
public class ShopDetailFragment extends SuperFragment {
    @Bind(R.id.tv_shop_phone_num)
    TextView tvPhoneNumber;
    @Bind(R.id.tv_shop_address)
    TextView tvShopAddress;
    @Bind(R.id.tv_seller_note)
    TextView tvSellerNote;

    String phoneNumber;
    String Address;
    String sellerNote;

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
                sellerNote = _note + "无";
            else
                sellerNote = _note + _temp;
        } else {
            phoneNumber = "0246813579";
            Address = _address + "江苏省南京市江宁区秣周东路无线谷";
            sellerNote = _note + "无线谷是一个非常神奇的地方";
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.shop_detail_fm2_content;
    }

    @Override
    protected void initData() {
        tvPhoneNumber.setText(phoneNumber);
        tvShopAddress.setText(Address);
        tvSellerNote.setText(sellerNote);
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {

    }
}
