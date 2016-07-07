package com.rhg.qf.activity;

import android.view.View;
import android.widget.ImageView;

import com.rhg.qf.R;
import com.rhg.qf.utils.ToastHelper;
import com.rhg.qf.widget.LineProgress;
import com.rhg.qf.widget.MyRatingBar;
import com.rhg.qf.widget.UIAlertView;

import java.util.Locale;

import butterknife.Bind;
import butterknife.OnClick;

/*
 *desc 
 *author rhg
 *time 2016/7/6 21:37
 *email 1013773046@qq.com
 */
public class DeliverStateNoneActivity extends BaseFragmentActivity {

    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.food_receive_progress)
    LineProgress foodDeliverProgress;
    @Bind(R.id.rb_mouth_feel)
    MyRatingBar rbMouthFeel;
    @Bind(R.id.rb_deliver_service)
    MyRatingBar rbDeliverService;


    @Override
    protected int getLayoutResId() {
        return R.layout.food_deliver_layout;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        rbDeliverService.setIsIndicator(false);
        rbMouthFeel.setIsIndicator(false);
    }

    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }


    @OnClick({R.id.tb_left_iv, R.id.bt_conform_receive, R.id.bt_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
            case R.id.bt_conform_receive:
                if (foodDeliverProgress.getState() == -1) {
                    ToastHelper.getInstance()._toast("当前商品还未下单！");

                    break;
                }
                if (foodDeliverProgress.getState() == LineProgress.STATE_LEFT) {
                    ToastHelper.getInstance()._toast("商家已经接单，请耐心等待！");
                    break;
                }
                if (foodDeliverProgress.getState() == LineProgress.STATE_CENTER) {
                    ToastHelper.getInstance()._toast("跑腿员努力为您送货中！");
                    break;
                }
                dialogShow();
                break;
            case R.id.bt_finish:
                String mouthFeelRate = String.format(Locale.ENGLISH, "%.2f", getRate(rbMouthFeel));
                String deliverServiceRate = String.format(Locale.ENGLISH, "%.2f", getRate(rbDeliverService));
                ToastHelper.getInstance()._toast("口感评分：" + mouthFeelRate +
                        ",送货服务：" + deliverServiceRate);
                break;
        }
    }


    private float getRate(MyRatingBar rateBar) {
        return rateBar.getStarRating();
    }

    private void dialogShow() {
        final UIAlertView delDialog = new UIAlertView(this, "温馨提示", "请在收到货后再确认收货!",
                "未收到货", "已收货");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           delDialog.dismiss();
                                           finish();
                                       }
                                   }
        );
    }

}
