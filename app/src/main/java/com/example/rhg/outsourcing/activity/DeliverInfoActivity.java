package com.example.rhg.outsourcing.activity;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.widget.ModifyHeadDialog;
import com.example.rhg.outsourcing.widget.CircleImageView;

/**
 * Created by remember on 2016/5/17.
 */
public class DeliverInfoActivity extends BaseActivity{
    FrameLayout tb_common;
    TextView tvRight;
    ImageView ivLeft;

    CircleImageView headView;
    TextInputLayout et_name_wrap;
    TextInputLayout et_id_wrap;
    TextInputLayout et_phone_wrap;
    TextInputLayout et_place_wrap;
    Button bt_save;
    Button bt_exit;

    @Override
    public int getLayoutResId() {
        return R.layout.deliver_info_activity;
    }

    @Override
    protected void initView() {
        tb_common = (FrameLayout)findViewById(R.id.fl_tab);
        tvRight = (TextView) findViewById(R.id.tv_tab_right);//TODO 有待考虑
        ivLeft = (ImageView) findViewById(R.id.iv_tab_left);

        headView = (CircleImageView)findViewById(R.id.ci_head);
        et_name_wrap = (TextInputLayout) findViewById(R.id.et_name_wrap);
        et_id_wrap = (TextInputLayout)findViewById(R.id.et_id_wrap);
        et_phone_wrap = (TextInputLayout)findViewById(R.id.et_phone_wrap);
        et_place_wrap = (TextInputLayout)findViewById(R.id.et_place_wrap);
        bt_save = (Button)findViewById(R.id.bt_save);
        bt_exit = (Button)findViewById(R.id.bt_exit);
    }

    protected void initData() {
        tb_common.setBackgroundResource(R.color.colorActiveGreen);
        tvRight.setText("编辑");
        ivLeft.setOnClickListener(this);

        headView.setOnClickListener(this);
        et_name_wrap.setError("");
        bt_save.setOnClickListener(this);
        bt_exit.setOnClickListener(this);
    }

    @Override
    public void showData(Object o) {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_tab_left:
                finish();
                break;
            case R.id.bt_save:
                break;
            case R.id.bt_exit:
                finish();
                break;
            case R.id.ci_head:
                ModifyHeadDialog modifyHeadDialog = new ModifyHeadDialog(this);
                modifyHeadDialog.show();
                break;
        }
    }
}
