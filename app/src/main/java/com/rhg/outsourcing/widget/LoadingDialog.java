package com.rhg.outsourcing.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.application.InitApplication;

/**
 * Created by rhg on 2016/5/24.
 */
public class LoadingDialog extends Dialog {
    Context mContext;


    public LoadingDialog(Context context) {
        super(context, R.style.MyDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window dialogWindow = getWindow();
//        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = InitApplication.getInstance().getResources().getDisplayMetrics();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        setCancelable(true);
        initView();
        initData();
    }

    QFoodLoadingCircle loadCircle;

    private void initView() {
        loadCircle = new QFoodLoadingCircle(InitApplication.getInstance().getApplicationContext());
        InitApplication.getInstance().addObject(loadCircle);
        setContentView(loadCircle);
    }

    private void initData() {
    }

    @Override
    protected void onStop() {
    }

    @Override
    public void dismiss() {
        mContext = null;
        loadCircle.stop();
        InitApplication.getInstance().removeObject(loadCircle);
        super.dismiss();
    }
}
