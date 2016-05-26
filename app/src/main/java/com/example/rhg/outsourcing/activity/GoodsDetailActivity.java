package com.example.rhg.outsourcing.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.apapter.BannerImageHolder;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.dao.LikeDao;
import com.example.rhg.outsourcing.dao.ShoppingCartDao;
import com.example.rhg.outsourcing.bean.GoodsDetailModel;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter;
import com.example.rhg.outsourcing.utils.ImageUtils;
import com.example.rhg.outsourcing.utils.ShoppingCartUtil;
import com.example.rhg.outsourcing.utils.ToastHelper;
import com.example.rhg.outsourcing.widget.UIAlertView;
import com.example.rhg.outsourcing.widget.ShoppingCartWithNumber;

import java.util.Arrays;

/**
 * Created by remember on 2016/5/18.
 */
public class GoodsDetailActivity extends BaseActivity {
    private String[] images = {
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
    };

    private boolean isLike;
    private String temp_productId;

    Drawable drawable_like;
    Drawable drawable_not_like;
    FrameLayout tb_common;
    TextView tvCenter;
    TextView tvRight;
    ImageView ivRight;
    ImageView ivLeft;
    LinearLayout llTabRight;

    ConvenientBanner convenientBanner;
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

    TestPresenter testPresenter;
    GoodsDetailModel goodsDetailModel;

    public GoodsDetailActivity() {
        testPresenter = new TestPresenter(this);
        goodsDetailModel = new GoodsDetailModel();
        //TODO 测试数据
        LikeDao likeDao = LikeDao.getInstance();
        likeDao.saveGoodsLikeInfo("20160518", 1).saveGoodsLikeInfo("20160519", 1)
                .saveGoodsLikeInfo("20160520", 1);
        if (likeDao.isExistLike("20160518")) {
            int _num = likeDao.getNumByProductID("20160518");
            if (_num == 1)
                isLike = true;
        }
        /*goodsDetailModel = new GoodsDetailModel("20160518", images, isLike,
                "黄焖鸡米饭", "销量:90", "很好吃", "￥:16");*/
        goodsDetailModel.setLike(isLike);
        //todo 模拟购物车数量数据库
        ShoppingCartUtil.addGoodToCart("20160518", "3");
        ShoppingCartUtil.addGoodToCart("20160519", "3");
        ShoppingCartUtil.addGoodToCart("20160522", "3");
    }


    //todo Intent 传递接收的数据
    @Override
    public void dataReceive(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            String _temp = bundle.getString(AppConstants.KEY_PRODUCT_ID);
            goodsDetailModel.setProductId(_temp);
            _temp = bundle.getString(AppConstants.KEY_PRODUCT_NAME);
            goodsDetailModel.setGoodsName(_temp);
            _temp = "￥:";
            _temp += bundle.getString(AppConstants.KEY_PRODUCT_PRICE);
            goodsDetailModel.setGoodsPrice(_temp);
        }
    }


    @Override
    public int getLayoutResId() {
        return R.layout.goods_detail_layout;
    }

    @Override
    protected void initView() {
        tb_common = (FrameLayout) findViewById(R.id.fl_tab);
        tvCenter = (TextView) findViewById(R.id.tv_tab_center);
        tvRight = (TextView) findViewById(R.id.tv_tab_right);
        ivRight = (ImageView) findViewById(R.id.iv_tab_right);
        ivLeft = (ImageView) findViewById(R.id.iv_tab_left);
        llTabRight = (LinearLayout) findViewById(R.id.ll_tab_right);

        convenientBanner = (ConvenientBanner) findViewById(R.id.iv_banner);
        ivLike = (ImageView) findViewById(R.id.iv_like);
        ivShare = (ImageView) findViewById(R.id.iv_share);
        tvGoodsName = (TextView) findViewById(R.id.tv_goods_name);
        tvSellNumber = (TextView) findViewById(R.id.tv_sell_number);
        tvDescriptionContent = (TextView) findViewById(R.id.tv_description_content);
        tvPriceNumber = (TextView) findViewById(R.id.tv_price_number);
        ivReduce = (ImageView) findViewById(R.id.ivReduce);
        ivAdd = (ImageView) findViewById(R.id.ivAdd);
        tvNum = (TextView) findViewById(R.id.tvNum);
        shoppingCartWithNumber = (ShoppingCartWithNumber) findViewById(R.id.shopping_cart_with_number);
        btBuy = (Button) findViewById(R.id.bt_buy);
    }


    //todo 对本地数据进行绑定
    @Override
    protected void initData() {
        ImageUtils.TintFill(ivRight, getResources().getDrawable(R.mipmap.ic_place_white_48dp),
                getResources().getColor(R.color.colorActiveGreen));
        ImageUtils.TintFill(ivShare, getResources().getDrawable(R.mipmap.ic_social_share),
                getResources().getColor(R.color.colorActiveGreen));
        drawable_like = ImageUtils.TintWithoutFill(getResources().getDrawable(R.mipmap.ic_like),
                getResources().getColor(R.color.colorActiveGreen));
        drawable_not_like = ImageUtils.TintWithoutFill(getResources().getDrawable(R.mipmap.ic_not_like),
                getResources().getColor(R.color.colorActiveGreen));
        tvRight.setText("杭州");//TODO 根据定位来决定
        tvCenter.setText(getResources().getString(R.string.goodsDetail));
        // 获取本地数据库的购物车数量
        ShoppingCartDao shoppingCartDao = ShoppingCartDao.getInstance();
        String _productId = goodsDetailModel.getProductId();
        if (shoppingCartDao.isExistGood(_productId)) {
            String _num = shoppingCartDao.getNumByProductID(_productId);
            tvNum.setText(_num);
            shoppingCartWithNumber.setNum(_num);
        } else {
            tvNum.setText("0");
            shoppingCartWithNumber.setNum("0");
        }
        if (goodsDetailModel.isLike())
            ivLike.setImageDrawable(drawable_like);
        else
            ivLike.setImageDrawable(drawable_not_like);

        ivLeft.setOnClickListener(this);
        llTabRight.setOnClickListener(this);
        ivLike.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        ivReduce.setOnClickListener(this);
        shoppingCartWithNumber.setDrawable(R.mipmap.ic_shopping_cart_black_48dp);
        shoppingCartWithNumber.setOnClickListener(this);
        btBuy.setOnClickListener(this);
        convenientBanner.setOnClickListener(this);
        convenientBanner.startTurning(2000);
        convenientBanner
                .setPageIndicator(AppConstants.imageindictors);
        testPresenter.getData();
    }

    //todo 从服务器获得的数据
    @Override
    public void showData(Object o) {
        ToastHelper.getInstance()._toast(o.toString());
        goodsDetailModel.setGoodsDescription("很好吃");
        goodsDetailModel.setGoodSellNum("99");
        goodsDetailModel.setImageUrls(images);

        bindData(goodsDetailModel);
    }

    private void bindData(GoodsDetailModel goodsDetailModel) {
        convenientBanner.setPages(new CBViewHolderCreator<BannerImageHolder>() {

            @Override
            public BannerImageHolder createHolder() {
                return new BannerImageHolder();
            }
        }, Arrays.asList(goodsDetailModel.getImageUrls()));
        tvGoodsName.setText(goodsDetailModel.getGoodsName());
        String _temp = getResources().getString(R.string.sellNumber) + goodsDetailModel.getGoodSellNum();
        tvSellNumber.setText(_temp);
        tvDescriptionContent.setText(goodsDetailModel.getGoodsDescription());
        tvPriceNumber.setText(goodsDetailModel.getGoodsPrice());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_tab_left:
                setResult(AppConstants.BACK_WITHOUT_DATA);
                finish();
                break;
            case R.id.ll_tab_right:
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
            case R.id.iv_like:
                LikeDao likeDao = LikeDao.getInstance();
                if (goodsDetailModel.isLike()) {
                    ivLike.setImageDrawable(drawable_not_like);
                    goodsDetailModel.setLike(false);
                    likeDao.updateLike(goodsDetailModel.getProductId(), 0);
                } else {
                    ivLike.setImageDrawable(drawable_like);
                    goodsDetailModel.setLike(true);
                    likeDao.updateLike(goodsDetailModel.getProductId(), 1);
                }
                break;
            case R.id.iv_share:
                ToastHelper.getInstance()._toast("分享");
                break;
            case R.id.shopping_cart_with_number:
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
        setResult(AppConstants.BACK_WITHOUT_DATA);
        super.onBackPressed();
    }
}
