package com.example.rhg.outsourcing.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.bean.PayContentBean;

import java.util.List;

/**
 * Created by rhg on 2016/5/24.
 */
public class LoadingDialog extends Dialog {
    Context mContext;

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public LoadingDialog(Context context) {
        super(context, R.style.MyDialogStyle);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window dialogWindow = getWindow();
//        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        setCancelable(true);
        initView();
        initData();
    }


    private void initView() {
        View view = new QFoodLoadingCircle(mContext);
        setContentView(view);
    }

    private void initData() {
    }

    @Override
    protected void onStop() {
        mContext = null;
    }
}
