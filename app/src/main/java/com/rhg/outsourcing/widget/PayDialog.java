package com.rhg.outsourcing.widget;

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

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.bean.PayContentBean;

import java.util.List;

/**
 * Created by rhg on 2016/5/24.
 */
public class PayDialog extends Dialog implements View.OnClickListener{
    Context mContext;
    PayDescriptionView payDesc;
    Button btPay;
    Button btCancel;
    List<PayContentBean> payContentBeanList;

    public PayDialog(Context context, List<PayContentBean> payContentBeanList) {
        super(context, R.style.MyDialogStyle);
        this.payContentBeanList = payContentBeanList;
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window dialogWindow = getWindow();
        dialogWindow.getDecorView().setPadding(0,0,0,0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics();
        lp.width = (int)(d.widthPixels);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
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
        switch (v.getId()){
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
        mContext=null;
    }
}
