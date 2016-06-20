package com.rhg.outsourcing.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.utils.ImageUtils;

/*
 *desc
 *author rhg
 *time 2016/6/20 16:06
 *email 1013773046@qq.com
 */
public class ShoppingCartWithNumber extends FrameLayout {
    private static final int DEFAULT_BACK_COLOR = Color.GREEN;
    TextView tv_goodsNum;
    ImageView iv_goodsCart;
    private int backColor;

    public ShoppingCartWithNumber(Context context) {
        this(context, null);
//        this(context, null);
    }

    public ShoppingCartWithNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShoppingCartWithNumber);
        if (a.hasValue(R.styleable.ShoppingCartWithNumber_tint_color)) {
            backColor = a.getColor(R.styleable.ShoppingCartWithNumber_tint_color, DEFAULT_BACK_COLOR);

        }
        a.recycle();
    }

    public ShoppingCartWithNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.shopping_cart_with_number, this, true);
        tv_goodsNum = (TextView) view.findViewById(R.id.tv_goods_num);
        iv_goodsCart = (ImageView) view.findViewById(R.id.iv_shopping_cart);
//        ImageUtils.TintFill(iv_goodsCart, iv_goodsCart.getDrawable(), backColor);
    }

    public void setNum(String num) {
        if (tv_goodsNum != null) {
            if (Integer.valueOf(num) == 0)
                setNumVisible(false);
            else {
                setNumVisible(true);
                tv_goodsNum.setText(num);
            }
        }
    }
    public int getNum(){
        if(tv_goodsNum.getVisibility()==GONE)
            return 0;
        String _string_num = tv_goodsNum.getText().toString();
        return Integer.valueOf(_string_num);
    }

    public void setDrawable(int resID) {
//        iv_goodsCart.setImageDrawable(getResources().getDrawable(resID));
        ImageUtils.TintFill(iv_goodsCart,getResources().getDrawable(resID),backColor);
    }

    private void setNumVisible(boolean visible) {
        if (tv_goodsNum != null) {
            if (visible)
                tv_goodsNum.setVisibility(VISIBLE);
            else tv_goodsNum.setVisibility(GONE);
        }
    }

}
