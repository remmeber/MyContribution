package com.rhg.qf.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rhg.qf.R;
import com.rhg.qf.adapter.FoodsDetailAdapter;
import com.rhg.qf.bean.OrderDetailUrlBean;
import com.rhg.qf.bean.PayModel;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.mvp.presenter.ModifyOrderPresenter;
import com.rhg.qf.mvp.presenter.OrderDetailPresenter;
import com.rhg.qf.utils.ToastHelper;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.Bind;
import butterknife.OnClick;

/*
 *desc 订单详情页
 *author rhg
 *time 2016/6/22 20:31
 *email 1013773046@qq.com
 */
public class OrderDetailActivity extends BaseAppcompactActivity {

    @Bind(R.id.tb_center_tv)
    TextView tbCenterTv;
    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.fl_tab)
    FrameLayout flTab;
    @Bind(R.id.tv_receiver)
    TextView tvReceiver;
    @Bind(R.id.tv_receiver_phone)
    TextView tvReceiverPhone;
    @Bind(R.id.tv_receiver_address)
    TextView tvReceiverAddress;
    @Bind(R.id.tvOrderNote)
    TextView tvOrderNote;
    @Bind(R.id.tvMerchantName)
    TextView tvMerchantName;
    @Bind(R.id.btDrawback)
    Button btDrawback;
    @Bind(R.id.rcyPayItem)
    RecyclerView rcyPayItem;

    @Bind(R.id.tv_totalMoney)
    TextView tvTotalMoney;
    @Bind(R.id.ly_totalCount)
    LinearLayout lyTotalCount;
    @Bind(R.id.btPayOrRateOrConform)
    TextView btPayOrRateOrConform;
    @Bind(R.id.iv_edit_right)
    ImageView ivEditRight;
    @Bind(R.id.tv_edit)
    TextView tvEdit;

    OrderDetailPresenter getOrderDetailPresenter;
    ModifyOrderPresenter modifyOrderPresenter;

    String orderId;
    int orderTag;
    String orderPrice;
    String merchantName;
    private FoodsDetailAdapter foodsDetailAdapter;
    OrderDetailUrlBean.OrderDetailBean foodBean = new OrderDetailUrlBean.OrderDetailBean();

    @Override
    public void dataReceive(Intent intent) {
        orderId = intent.getStringExtra(AppConstants.KEY_ORDER_ID);
        merchantName = intent.getStringExtra(AppConstants.KEY_MERCHANT_NAME);
        orderPrice = intent.getStringExtra(AppConstants.KEY_PRODUCT_PRICE);
        orderTag = intent.getIntExtra(AppConstants.KEY_ORDER_TAG, -1);
        /*
        orderReceiver = intent.getStringExtra(AppConstants.SP_USER_NAME);
        orderPhone = intent.getStringExtra(AppConstants.KEY_OR_SP_PHONE);
        orderAddress = intent.getStringExtra(AppConstants.KEY_ADDRESS);*/
    }

    @Override
    public void loadingData() {
        getOrderDetailPresenter = new OrderDetailPresenter(this);
        getOrderDetailPresenter.getOrderDetail(AppConstants.ORDER_DETAIL, orderId);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.order_detail_layout;
    }

    @Override
    protected void initData() {
        flTab.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
        tbCenterTv.setText(getResources().getString(R.string.myOrder));
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        ivEditRight.setVisibility(View.GONE);
        tvEdit.setVisibility(View.GONE);
        if (orderTag != AppConstants.USER_ORDER_DELIVERING)
            btDrawback.setVisibility(View.GONE);
        /*recycleview*/
        rcyPayItem.setLayoutManager(new LinearLayoutManager(this));
        rcyPayItem.setHasFixedSize(true);
        foodsDetailAdapter = new FoodsDetailAdapter(this, foodBean);
        rcyPayItem.setAdapter(foodsDetailAdapter);
        /*recycleview*/
        tvMerchantName.setText(merchantName);
        lyTotalCount.setVisibility(View.VISIBLE);
        tvTotalMoney.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.countMoney),
                orderPrice));
        setText(btPayOrRateOrConform);
    }

    private void setText(TextView btPayOrRateOrConform) {
        if (AppConstants.USER_ORDER_UNPAID == orderTag) {
            btPayOrRateOrConform.setText(getResources().getString(R.string.goPay));
            return;
        }
        if (AppConstants.USER_ORDER_DELIVERING == orderTag) {
            btPayOrRateOrConform.setText(getResources().getString(R.string.conformReceive));
            return;
        }
        if (AppConstants.USER_ORDER_COMPLETE == orderTag
                || AppConstants.ORDER_DRAWBACK == orderTag) {
            btPayOrRateOrConform.setText(getResources().getString(R.string.goEvaluate));
        }
    }

    @Override
    protected void showSuccess(Object s) {
        if (s instanceof OrderDetailUrlBean.OrderDetailBean) {
            foodBean = (OrderDetailUrlBean.OrderDetailBean) s;
            setData((OrderDetailUrlBean.OrderDetailBean) s);
        }
        if (s instanceof String)
            ToastHelper.getInstance()._toast((String) s);
    }

    private void setData(OrderDetailUrlBean.OrderDetailBean orderDetail) {
        tvReceiver.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.tvReceiver),
                orderDetail.getReceiver()));
        tvReceiverPhone.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.tvContactPhone),
                orderDetail.getPhone()));
        tvReceiverAddress.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.tvReceiveAddress),
                orderDetail.getAddress()));
        tvOrderNote.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.tvNote),
                /*TODO 缺少note*/"无"));
        tvMerchantName.setText("");/*接口中订单名字*/
        foodsDetailAdapter.setFoodsBeanList(orderDetail);
    }

    @Override
    protected void showError(Object s) {

    }

    @OnClick({R.id.tb_left_iv, R.id.btDrawback, R.id.btPayOrRateOrConform})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
            case R.id.btDrawback:
                if (modifyOrderPresenter == null)
                    modifyOrderPresenter = new ModifyOrderPresenter(this);
                modifyOrderPresenter.modifyUserOrDeliverOrderState("1"/*订单号*/, "0"/*0:退单，1,：完成*/);
                break;
            case R.id.btPayOrRateOrConform:
                if (orderTag == AppConstants.USER_ORDER_DELIVERING) {
                    Intent intent = new Intent(this, DeliverStateNoneActivity.class);
                    intent.putExtra(AppConstants.KEY_ORDER_ID, orderId);
                    startActivity(intent);
                }
                if (orderTag == AppConstants.USER_ORDER_UNPAID) {
                    if (foodsDetailAdapter.getItemCount() == 0)
                        return;
                    Intent intent = new Intent(this, PayActivity.class);
                    PayModel payModel = new PayModel();
                    payModel.setReceiver(tvReceiver.getText().toString());
                    payModel.setPhone(tvReceiverPhone.getText().toString());
                    payModel.setAddress(tvReceiverAddress.getText().toString());
                    ArrayList<PayModel.PayBean> payBeen = new ArrayList<>();
                    PayModel.PayBean _pay = new PayModel.PayBean();
                    _pay.setProductName("");
                    _pay.setChecked(true);
                    _pay.setProductId("1");
                    int count = 0;
                    _pay.setProductNumber(String.valueOf(count));
                    _pay.setProductPic("");
                    _pay.setProductPrice(String.valueOf(orderPrice) == null ? "0" : String.valueOf(orderPrice));
                    payBeen.add(_pay);
                    payModel.setPayBeanList(payBeen);
                    intent.putExtra(AppConstants.KEY_PARCELABLE, payModel);
                    startActivity(intent);
                }
                break;
        }
    }

}
