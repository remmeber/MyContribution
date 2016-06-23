package com.rhg.outsourcing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.rhg.outsourcing.widget.ShoppingCartWithNumber;
import com.rhg.outsourcing.widget.UIAlertView;
import com.umeng.socialize.media.UMImage;

import java.util.Locale;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * desc:商品详情页面
 * author：remember
 * time：2016/5/28 16:14
 * email：1013773046@qq.com
 */
public class GoodsDetailActivity extends BaseActivity {

    @Bind(R.id.tb_center_tv)
    TextView tbCenterTv;
    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.tb_right_iv)
    ImageView tbRightIv;
    @Bind(R.id.tb_right_tv)
    TextView tbRightTv;
    @Bind(R.id.common_refresh)
    ProgressBar commonRefresh;
    @Bind(R.id.iv_banner)
    ConvenientBanner ivBanner;
    @Bind(R.id.tv_sell_number)
    TextView tvSellNumber;
    @Bind(R.id.tv_goods_name)
    TextView tvGoodsName;
    @Bind(R.id.tv_description_content)
    TextView tvDescriptionContent;
    @Bind(R.id.tv_price_number)
    TextView tvPriceNumber;
    @Bind(R.id.tvNum)
    TextView tvNum;
    @Bind(R.id.ivAddToShoppingCart)
    ShoppingCartWithNumber ivAddToShoppingCart;
    //    private boolean isLike;

    Bundle bundle;

    GoodsDetailPresenter goodsDetailPresenter;
    String foodId;
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
        if (intent != null) {
            foodId = intent.getStringExtra(AppConstants.KEY_PRODUCT_ID);
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

    }


    //todo 对本地数据进行绑定
    @Override
    protected void initData() {
//        goodsDetailPresenter.getGoodsInfo();
        tbRightIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_location_green));
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        tbRightTv.setText(location);//TODO 根据定位来决定
        tbCenterTv.setText(getResources().getString(R.string.goodsDetail));
        // 获取本地数据库的购物车数量
        AccountDao accountDao = AccountDao.getInstance();
        String _productId = String.valueOf(foodId);
        if (accountDao.isExistGood(_productId)) {
            String _num = accountDao.getNumByProductID(_productId);
            tvNum.setText(_num);
            ivAddToShoppingCart.setNum(_num);
        } else {
            tvNum.setText("0");
            ivAddToShoppingCart.setNum("0");
        }
        ivAddToShoppingCart.setDrawable(R.drawable.ic_shopping_cart_green);
        ivBanner.startTurning(2000);
        ivBanner.setPageIndicator(AppConstants.IMAGE_INDICTORS);
    }

    @Override
    public void showLocSuccess(String s) {
        tbRightTv.setText(s);
        AccountUtil.getInstance().setLocation(s);
    }

    @Override
    public void showLocFailed(String s) {

    }

    //todo 从服务器获得的数据
    @Override
    protected void showSuccess(Object s) {
        GoodsDetailUrlBean.GoodsDetailBean _bean = (GoodsDetailUrlBean.GoodsDetailBean) s;
        bindData(_bean);
        if (commonRefresh.getVisibility() == View.VISIBLE)
            commonRefresh.setVisibility(View.GONE);
    }

    @Override
    protected void showError(Object s) {

    }

    private void bindData(GoodsDetailUrlBean.GoodsDetailBean _bean) {
        ivBanner.setPages(new CBViewHolderCreator<BannerImageHolder>() {

            @Override
            public BannerImageHolder createHolder() {
                return new BannerImageHolder();
            }
        }, _bean.getPicsrc());
        tvGoodsName.setText(_bean.getName());
        tvSellNumber.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.sellNumber),
                _bean.getMonthlySales()));
        tvDescriptionContent.setText(_bean.getMessage());
        tvPriceNumber.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.countMoney),
                _bean.getPrice()));
    }

    @OnClick({R.id.tb_left_iv, R.id.tb_right_ll, R.id.ivAddToShoppingCart, R.id.ivShare,
            R.id.ivReduce, R.id.ivAdd, R.id.bt_buy})
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
                    ivAddToShoppingCart.setNum(String.valueOf(t - 1));
                    //TODO 需要更新购物车数据库的数据
                }
                break;
            case R.id.ivAdd:
                String text = tvNum.getText().toString();
                int num = Integer.valueOf(text);
                tvNum.setText(String.valueOf(num + 1));
                ivAddToShoppingCart.setNum(String.valueOf(num + 1));
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
