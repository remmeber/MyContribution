package com.rhg.qf.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.rhg.qf.R;

/**
 * 仿IOS的弹出框
 */
public class UIAlertView extends BaseDiaglogView {

    private String title;
    private String message;
    private String buttonLeftText;
    private String buttonRightText;

    public UIAlertView(Context context, String title, String message,
                       String buttonLeftText, String buttonRightText) {
        super(context, R.layout.ui_alert_view);
        this.title = title;
        this.message = message;
        this.buttonLeftText = buttonLeftText;
        this.buttonRightText = buttonRightText;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        TextView tvLeft = (TextView) view.findViewById(R.id.tvBtnLeft);
        TextView tvRight = (TextView) view.findViewById(R.id.tvBtnRight);
        TextView tvTitle = (TextView)view.findViewById(R.id.tvTitle);

        if ("".equals(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
        }
        tvMessage.setText(message);
        if ("".equals(buttonLeftText))
            tvLeft.setVisibility(View.GONE);
        else
            tvLeft.setText(buttonLeftText);
        tvRight.setText(buttonRightText);

        tvLeft.setOnClickListener(new clickListener());
        tvRight.setOnClickListener(new clickListener());
    }
}
