package com.rhg.qf.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.rhg.qf.R;
import com.rhg.qf.utils.ToastHelper;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * desc:自主点餐
 * author：remember
 * time：2016/6/22 14:41
 * email：1013773046@qq.com
 */
public class PersonalOrderActivity extends BaseFragmentActivity {

    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.fl_tab)
    FrameLayout flTab;
    @Bind(R.id.et_user_input)
    EditText etUserInput;

    @Override
    protected int getLayoutResId() {
        return R.layout.personal_order;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        flTab.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }


    @OnClick({R.id.tb_left_iv, R.id.bt_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
            case R.id.bt_submit:
                if (TextUtils.isEmpty(etUserInput.getText().toString().trim())) {
                    ToastHelper.getInstance()._toast("请输入您想要的菜品");
                    break;
                }
                finish();
        }
    }
}
