package com.rhg.qf.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easemob.easeui.EaseConstant;
import com.rhg.qf.R;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.utils.AccountUtil;
import com.rhg.qf.utils.ToastHelper;

import butterknife.Bind;
import butterknife.OnClick;

/*
 *desc 
 *author rhg
 *time 2016/9/25 20:51
 *email 1013773046@qq.com
 */
public class PersonalOrderActivity extends BaseAppcompactActivity {
    @Bind(R.id.tb_center_tv)
    TextView tbCenterTv;
    @Bind(R.id.tb_right_ll)
    LinearLayout tbRightLl;
    @Bind(R.id.etNum)
    EditText etNum;
    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.fl_tab)
    FrameLayout flTab;

    @Override
    protected void initData() {
        tbCenterTv.setText(R.string.personalOrder);
        tbRightLl.setVisibility(View.GONE);
        tbLeftIv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_chevron_left_black));
        flTab.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlueNormal));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.personal_order_layout;
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }

    @Override
    public void keyBoardHide() {
        if (TextUtils.isEmpty(etNum.getText())) {
            etNum.setText("0");
        }
    }

    @OnClick({R.id.tb_left_iv, R.id.ivPersonalBackground, R.id.ivReduce, R.id.ivAdd,
            R.id.btPersonalOrderPay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
            case R.id.ivPersonalBackground:
                if (!AccountUtil.getInstance().hasAccount()) {
                    ToastHelper.getInstance().displayToastWithQuickClose("请登录");
                    break;
                }
                startActivity(new Intent(this, ChatActivity.class)
                        .putExtra(EaseConstant.EXTRA_USER_ID, AppConstants.CUSTOMER_SERVER));

                break;
            case R.id.ivReduce:
                int num = Integer.valueOf(etNum.getText().toString());
                if (num == 0)
                    return;
                etNum.setText(String.valueOf(num - 1));
                break;
            case R.id.ivAdd:
                etNum.setText(String.valueOf(Integer.valueOf(etNum.getText().toString()) + 1));
                break;
            case R.id.btPersonalOrderPay:
                break;
        }
    }
}
