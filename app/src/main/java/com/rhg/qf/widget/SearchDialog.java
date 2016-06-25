package com.rhg.qf.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.rhg.qf.R;
import com.rhg.qf.bean.PayContentBean;

import java.util.List;

/**
 * desc:搜索框
 * author：remember
 * time：2016/6/18 12:56
 * email：1013773046@qq.com
 */
public class SearchDialog extends Dialog implements View.OnClickListener {
    Context mContext;
    PayDescriptionView payDesc;
    Button btPay;
    Button btCancel;
    List<PayContentBean> payContentBeanList;

    public SearchDialog(Context context, List<PayContentBean> payContentBeanList) {
        super(context, R.style.MyDialogStyle);
        this.payContentBeanList = payContentBeanList;
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window dialogWindow = getWindow();
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics();
        lp.width = (int) (d.widthPixels);
        lp.height = (int) (d.heightPixels);
        dialogWindow.setAttributes(lp);
        setCancelable(false);
        initView();
        initData();
    }


    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pay_dialog_layout, null);
        setContentView(view);
        payDesc = (PayDescriptionView) view.findViewById(R.id.pay_content);
        btPay = (Button) view.findViewById(R.id.bt_pay);
        btCancel = (Button) view.findViewById(R.id.bt_cancel);

    }

    private void initData() {
        payDesc.setPayContentBeanList(payContentBeanList);
        btPay.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_pay:
                /*调起支付页面*/
                dismiss();

                break;
            case R.id.bt_cancel:
                dismiss();
                break;
        }
    }

    @Override
    protected void onStop() {
        mContext = null;
    }
}
