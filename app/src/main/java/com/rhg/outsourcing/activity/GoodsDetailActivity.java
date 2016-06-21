package com.rhg.outsourcing.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.rhg.outsourcing.R;
import com.rhg.outsourcing.apapter.viewHolder.BannerImageHolder;
import com.rhg.outsourcing.application.InitApplication;
import com.rhg.outsourcing.bean.GoodsDetailUrlBean;
import com.rhg.outsourcing.bean.ShareModel;
import com.rhg.outsourcing.constants.AppConstants;
import com.rhg.outsourcing.datebase.AccountDao;
import com.rhg.outsourcing.impl.ShareListener;
import com.rhg.outsourcing.locationservice.LocationService;
import com.rhg.outsourcing.locationservice.MyLocationListener;
import com.rhg.outsourcing.mvp.presenter.GoodsDetailPresenter;
import com.rhg.outsourcing.mvp.presenter.GoodsDetailPresenterImpl;
import com.rhg.outsourcing.third.UmengUtil;
import com.rhg.outsourcing.utils.AccountUtil;
import com.rhg.outsourcing.utils.ShoppingCartUtil;
import com.rhg.outsourcing.utils.ToastHelper;
import com.rhg.outsourcing.widget.LoadingDialog;
import com.rhg.outsourcing.widget.ShoppingCartWithNumber;
import com.rhg.outsourcing.widget.UIAlertView;
import com.umeng.socialize.media.UMImage;

import java.util.List;

/**
 * desc:商品详情页面
 * author：remember
 * time：2016/5/28 16:14
 * email：1013773046@qq.com
 */
public class GoodsDetailActivity extends BaseActivity {

    Bundle bundle;
    Drawable drawable_like;
    Drawable drawable_not_like;
    FrameLayout tb_common;
    TextView tvCenter;
    TextView tvRight;
    ImageView ivRight;
    ImageView ivLeft;
    LinearLayout llTabRight;
    ConvenientBanner<String> convenientBanner;
    ImageView ivLike;
    ImageView ivShare;
    TextView tvGoodsName;
    TextView tvSellNumber;
    TextView tvDescriptionContent;
    TextView tvPriceNumber;
    ImageView ivReduce;
    ImageView ivAdd;
    TextView tvNum;
    ShoppingCartWithNumber shoppingCartWithNumber;
    Button btBuy;
    LoadingDialog loadingDialog;
    GoodsDetailPresenter goodsDetailPresenter;
    String foodId;
    String foodName;
    String foodPrice;
    String foodSale;
    String foodStyle;
    String foodMessage;
    List<String> foodSrcs;
    //    private boolean isLike;
    private boolean isNeedLoc;
    private String location;
    private MyLocationListener myLocationListener;
    private String temp_productId;


    public GoodsDetailActivity() {
        goodsDetailPresenter = new GoodsDetailPresenterImpl(this);
        myLocationListener = new MyLocationListener(this);
        /*TODO 页面销毁需要置空，否则会出现内存泄漏*/
        location = AccountUtil.getInstance().getLocation();
        if (TextUtils.isEmpty(location)) {
            isNeedLoc = true;
        }

        /*//TODO 测试数据
        LikeDao likeDao = LikeDao.getInstance();
        likeDao.saveGoodsLikeInfo("20160518", 1).saveGoodsLikeInfo("20160519", 1)
                .saveGoodsLikeInfo("20160520", 1);
        if (likeDao.isExistLike("20160518")) {
            int _num = likeDao.getNumByProductID("20160518");
            if (_num == 1)
                isLike = true;
        }
        *//*goodsDetailBean = new GoodsDetailBean("20160518", images, isLike,
                "黄焖鸡米饭", "销量:90", "很好吃", "￥:16");*//*
        goodsDetailBean.setLike(isLike);*/
        //todo 模拟购物车数量数据库
        ShoppingCartUtil.addGoodToCart("20160518", "3");
        ShoppingCartUtil.addGoodToCart("20160519", "3");
        ShoppingCartUtil.addGoodToCart("20160522", "3");
    }

    @Override
    protected boolean isNeedFirstLoc() {
        return true;
    }

    @Override
    public LocationService GetMapService() {
        loadingDialog.show();
        if (isNeedLoc) {
            return InitApplication.getInstance().locationService;
        }
        return super.GetMapService();
    }

    @Override
    public void loadingData() {
        goodsDetailPresenter.getGoodsInfo("foodmessage", foodId);
    }

    @Override
    public MyLocationListener getLocationListener() {
        if (isNeedLoc)
            return new MyLocationListener(this);
        return super.getLocationListener();
    }


    //todo Intent 传递接收的数据
    @Override
    public void dataReceive(Intent intent) {
        loadingDialog = new LoadingDialog(this);
        if (intent != null) {
            bundle = intent.getExtras();
            foodId = bundle.getString(AppConstants.KEY_PRODUCT_ID, "");
//            merchantName = bundle.getString(AppConstants.KEY_PRODUCT_NAME, null);
//            merchantSrc = bundle.getString(AppConstants.KEY_PRODUCT_PRICE, null);
        }
    }


    @Override
    public int getLayoutResId() {
        return R.layout.goods_detail_layout;
    }

    @Override
    protected void initView(View view) {
        tb_common = (FrameLayout) findViewById(R.id.fl_tab);
        tvCenter = (TextView) findViewById(R.id.tb_center_tv);
        tvRight = (TextView) findViewById(R.id.tb_right_tv);
        ivRight = (ImageView) findViewById(R.id.tb_right_iv);
        ivLeft = (ImageView) findViewById(R.id.tb_left_iv);
        llTabRight = (LinearLayout) findViewById(R.id.tb_right_ll);
        convenientBanner = (ConvenientBanner) findViewById(R.id.iv_banner);
//        ivLike = (ImageView) findViewById(R.id.iv_like);
        ivShare = (ImageView) findViewById(R.id.ivShare);
        tvGoodsName = (TextView) findViewById(R.id.tv_goods_name);
        tvSellNumber = (TextView) findViewById(R.id.tv_sell_number);
        tvDescriptionContent = (TextView) findViewById(R.id.tv_description_content);
        tvPriceNumber = (TextView) findViewById(R.id.tv_price_number);
        ivReduce = (ImageView) findViewById(R.id.ivReduce);
        ivAdd = (ImageView) findViewById(R.id.ivAdd);
        tvNum = (TextView) findViewById(R.id.tvNum);
        shoppingCartWithNumber = (ShoppingCartWithNumber) findViewById(R.id.ivAddToShoppingCart);
        btBuy = (Button) findViewById(R.id.bt_buy);
    }


    //todo 对本地数据进行绑定
    @Override
    protected void initData() {
//        goodsDetailPresenter.getGoodsInfo();
        ivRight.setImageDrawable(getResources().getDrawable(R.drawable.ic_location_green));
        ivLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        tvRight.setText(location);//TODO 根据定位来决定
        tvCenter.setText(getResources().getString(R.string.goodsDetail));
        // 获取本地数据库的购物车数量
        AccountDao accountDao = AccountDao.getInstance();
        String _productId = String.valueOf(foodId);
        if (accountDao.isExistGood(_productId)) {
            String _num = accountDao.getNumByProductID(_productId);
            tvNum.setText(_num);
            shoppingCartWithNumber.setNum(_num);
        } else {
            tvNum.setText("0");
            shoppingCartWithNumber.setNum("0");
        }


        ivLeft.setOnClickListener(this);
        llTabRight.setOnClickListener(this);
//        ivLike.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        ivReduce.setOnClickListener(this);
        shoppingCartWithNumber.setDrawable(R.drawable.ic_shopping_cart_green);
        shoppingCartWithNumber.setOnClickListener(this);
        btBuy.setOnClickListener(this);
        convenientBanner.setOnClickListener(this);
        convenientBanner.startTurning(2000);
        convenientBanner
                .setPageIndicator(AppConstants.IMAGE_INDICTORS);
    }

    @Override
    public void showLocSuccess(String s) {
        tvRight.setText(s);
        AccountUtil.getInstance().setLocation(s);
    }

    @Override
    public void showLocFailed(String s) {

    }

    //todo 从服务器获得的数据
    @Override
    protected void showSuccess(Object s) {
        GoodsDetailUrlBean.GoodsDetailBean _bean = (GoodsDetailUrlBean.GoodsDetailBean) s;
        foodName = _bean.getName();
        foodPrice = _bean.getPrice();
        foodMessage = _bean.getMessage();
        foodSale = _bean.getMonthlySales();
        foodStyle = _bean.getStyle();
        foodSrcs = _bean.getPics();
        bindData();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //execute the task
                loadingDialog.dismiss();
            }
        }, 500);
    }

    @Override
    protected void showError(Object s) {

    }

    private void bindData() {
        convenientBanner.setPages(new CBViewHolderCreator<BannerImageHolder>() {

            @Override
            public BannerImageHolder createHolder() {
                return new BannerImageHolder();
            }
        }, foodSrcs);
        tvGoodsName.setText(foodName);
        String _temp = getResources().getString(R.string.sellNumber) + foodSale;
        tvSellNumber.setText(_temp);
        tvDescriptionContent.setText(foodMessage);
        tvPriceNumber.setText(foodPrice);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb_left_iv:
                bundle = null;
                setResult(AppConstants.BACK_WITHOUT_DATA);
                finish();
                break;
            case R.id.tb_right_ll:
                ToastHelper.getInstance()._toast("修改定位");
                break;
            case R.id.ivReduce:
                int t = Integer.valueOf(tvNum.getText().toString());
                if (t != 0) {
                    tvNum.setText(String.valueOf(t - 1));
                    shoppingCartWithNumber.setNum(String.valueOf(t - 1));
                    //TODO 需要更新购物车数据库的数据
                }
                break;
            case R.id.ivAdd:
                String text = tvNum.getText().toString();
                int num = Integer.valueOf(text);
                tvNum.setText(String.valueOf(num + 1));
                shoppingCartWithNumber.setNum(String.valueOf(num + 1));
                //TODO 需要更新购物车数据库的数据
                break;
         /*   case R.id.ivAddToShoppingCart:
                *//*LikeDao likeDao = LikeDao.getInstance();
                if (goodsDetailBean.isLike()) {
                    ivLike.setImageDrawable(drawable_not_like);
                    goodsDetailBean.setLike(false);
                    likeDao.updateLike(goodsDetailBean.getProductId(), 0);
                } else {
                    ivLike.setImageDrawable(drawable_like);
                    goodsDetailBean.setLike(true);
                    likeDao.updateLike(goodsDetailBean.getProductId(), 1);
                }*//*
                break;*/
            case R.id.ivShare:
                UmengUtil umengUtil = new UmengUtil(this);
                UMImage imageMedia = new UMImage(this, AccountUtil.getInstance().getHeadImageUrl());
                ShareModel shareModel = new ShareModel("QFood", "好吃", imageMedia);
                umengUtil.Share(shareModel, new ShareListener() {
                    @Override
                    public void shareSuccess(String message) {
                        ToastHelper.getInstance()._toast(message);
                    }

                    @Override
                    public void shareFailed(String message, String content) {
                        ToastHelper.getInstance()._toast(message);
                    }

                    @Override
                    public void shareCancel(String message) {
                        ToastHelper.getInstance()._toast(message);
                    }
                });
                break;
            case R.id.ivAddToShoppingCart:
                dialogShow();
                break;
            case R.id.bt_buy:
                Intent intent = new Intent(GoodsDetailActivity.this,
                        PayActivity.class);
                                           /*todo 带上参数*/
                startActivity(intent);
                break;
        }
    }

    private void dialogShow() {
        final UIAlertView delDialog = new UIAlertView(this, "温馨提示", "加入购物车成功!",
                "继续购买", "前往购物车");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           setResult(AppConstants.BACK_WITH_DELETE, new Intent().putExtra(
                                                   AppConstants.KEY_DELETE,
                                                   AppConstants.KEY_SHOPPING_CART
                                           ));
                                           finish();
                                           delDialog.dismiss();
                                       }
                                   }
        );
    }

    @Override
    public void onBackPressed() {
        bundle = null;
        setResult(AppConstants.BACK_WITHOUT_DATA);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        location = null;
        super.onDestroy();
    }
}
