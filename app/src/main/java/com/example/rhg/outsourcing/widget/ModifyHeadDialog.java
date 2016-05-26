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

/**
 * Created by rhg on 2016/5/24.
 * 修改头像
 */
public class ModifyHeadDialog extends Dialog implements View.OnClickListener {
    Context mContext;
    Button btCamera;
    Button btGallery;

    public ModifyHeadDialog(Context context) {
        super(context, R.style.MyDialogStyle);
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
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        setCancelable(true);
        initView();
        initData();
    }


    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pic_choose_layout, null);

        setContentView(view);
        btCamera = (Button) view.findViewById(R.id.bt_camera);
        btGallery = (Button) view.findViewById(R.id.bt_gallery);

    }

    private void initData() {
        btCamera.setOnClickListener(this);
        btGallery.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_camera:
                /*调起相机*/
                dismiss();

                break;
            case R.id.bt_gallery:
                dismiss();
                break;
        }
    }

    @Override
    protected void onStop() {
        if (mContext != null)
            mContext = null;
    }
}
