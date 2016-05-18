package com.example.rhg.outsourcing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.view.BaseView;

/**
 * Created by remember on 2016/5/17.
 */
public class DeliverSignUp extends AppCompatActivity implements BaseView {
    TextView tvCenter;
    TextView tvRight;
    ImageView ivLeft;
    TextInputLayout et_name_wrap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deliver_signup_activity);
        initView();
        initData();
    }


    private void initView() {
        tvCenter = (TextView)findViewById(R.id.tv_center);
        tvRight = (TextView)findViewById(R.id.tv_tab_right);
        ivLeft = (ImageView)findViewById(R.id.iv_tab_left);
        et_name_wrap = (TextInputLayout)findViewById(R.id.et_id_wrap);
    }
    private void initData() {
        tvRight.setText("编辑");
    }

    @Override
    public void showData(Object o) {

    }
}
