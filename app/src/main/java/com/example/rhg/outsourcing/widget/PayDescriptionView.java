package com.example.rhg.outsourcing.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.bean.PayContent;

import java.util.List;

/**
 * Created by remember on 2016/5/22.
 */
public class PayDescriptionView extends ScrollView {
    List<PayContent> payContentList;
    LinearLayout mLayout;
    Context mContext;

    public PayDescriptionView(Context context) {
        this(context, null);
    }

    public PayDescriptionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayDescriptionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mLayout = new LinearLayout(context);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mLayout);
    }

    public void setPayContentList(List<PayContent> payContentList) {
        this.payContentList = payContentList;
        invalidateView();
    }

    private void invalidateView() {
        for (int i = 0; i < payContentList.size(); i++) {
            PayContent _temp = payContentList.get(i);
            View view = View.inflate(mContext, R.layout.item_pay_content, null);
            TextView goodsName = (TextView)view.findViewById(R.id.product_subject);
            TextView goodsDesc = (TextView)view.findViewById(R.id.product_desc);
            TextView goodsPrice = (TextView)view.findViewById(R.id.product_price);
            goodsName.setText(_temp.getGoodsName());
            goodsDesc.setText(_temp.getGoodsDescription());
            goodsPrice.setText(_temp.getPayMoney());
            mLayout.addView(view, i, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }
}
