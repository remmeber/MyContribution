package com.rhg.qf.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rhg.qf.R;
import com.rhg.qf.activity.AddressActivity;
import com.rhg.qf.activity.DeliverInfoActivity;
import com.rhg.qf.activity.DeliverRegisterActivity;
import com.rhg.qf.activity.NewAddressActivity;
import com.rhg.qf.activity.OrderListActivity;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.impl.SignInListener;
import com.rhg.qf.third.UmengUtil;
import com.rhg.qf.utils.AccountUtil;
import com.rhg.qf.utils.ToastHelper;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * desc:我的fm
 * author：remember
 * time：2016/5/28 16:44
 * email：1013773046@qq.com
 */
public class MyFragment extends SuperFragment implements View.OnClickListener {
    boolean hasAccount = true;

    FrameLayout flTAB;
    ImageView userHeader;
    TextView userName;
    //TODO-------------------------------我的订单栏---------------------------------------------
    TextView myInfo;
    ImageView myForward;
    TextView myPay;
    TextView myCancel;
    TextView myComplete;
    //TODO---------------------------------我是跑腿员-------------------------------------------
    TextView workerInfo;
    ImageView workerForward;
    TextView workerSignIn;
    TextView workerSignUp;
    TextView workerModify;
    //TODO---------------------------------我的地址-------------------------------------------
    TextView addressInfo;
    ImageView addressForward;
    TextView addressCustome;
    TextView addressAdd;
    TextView addressModify;

    boolean isSignIn;
    UMShareAPI mShareAPI;
    UmengUtil signUtil = null;

    public MyFragment() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.user_profile_layout;
    }

    @Override
    protected void initView(View view) {
        flTAB = (FrameLayout) view.findViewById(R.id.fl_tab);

        userHeader = (ImageView) view.findViewById(R.id.userHeader);
        userName = (TextView) view.findViewById(R.id.userName);

        //TODO-------------------------------我的订单栏---------------------------------------------
        myInfo = (TextView) getViewById(view, R.id.profileOrder, R.id.profileInfo);
        myForward = (ImageView) getViewById(view, R.id.profileOrder, R.id.profileForward);
        myPay = (TextView) getViewById(view, R.id.profileOrder, R.id.profileDealleft);
        myCancel = (TextView) getViewById(view, R.id.profileOrder, R.id.profileDealcenter);
        myComplete = (TextView) getViewById(view, R.id.profileOrder, R.id.profileDealright);
        //TODO---------------------------------我是跑腿员-------------------------------------------
        workerInfo = (TextView) getViewById(view, R.id.profileWorker, R.id.profileInfo);
        workerForward = (ImageView) getViewById(view, R.id.profileWorker, R.id.profileForward);
        workerSignIn = (TextView) getViewById(view, R.id.profileWorker, R.id.profileDealleft);
        workerSignUp = (TextView) getViewById(view, R.id.profileWorker, R.id.profileDealcenter);
        workerModify = (TextView) getViewById(view, R.id.profileWorker, R.id.profileDealright);
        //TODO---------------------------------我的地址-------------------------------------------
        addressInfo = (TextView) getViewById(view, R.id.profileAddress, R.id.profileInfo);
        addressForward = (ImageView) getViewById(view, R.id.profileAddress, R.id.profileForward);
        addressCustome = (TextView) getViewById(view, R.id.profileAddress, R.id.profileDealleft);
        addressAdd = (TextView) getViewById(view, R.id.profileAddress, R.id.profileDealcenter);
        addressModify = (TextView) getViewById(view, R.id.profileAddress, R.id.profileDealright);
    }

    @Override
    protected void initData() {
        flTAB.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
        userHeader.setOnClickListener(this);
        userHeader.setTag(R.id.userHeader);
        if (AccountUtil.getInstance().hasAccount()) {
            userName.setText("账户存在");
            isSignIn = true;
        } else userName.setText("请登录");
//        userName.setText();//TODO 此处需要根据本地账户来判断显示
        userName.setOnClickListener(this);//TODO 如果本地有账户则直接登录，否则需要点击登录
        userName.setTag(R.id.userName);


        myInfo.setText(R.string.myOrder);

        myForward.setOnClickListener(this);
        myForward.setTag(R.id.profileInfo);

        myPay.setText(R.string.orderUnPaid);
        myPay.setOnClickListener(this);
        myPay.setTag(0);

        myCancel.setText(R.string.cancel);
        myCancel.setOnClickListener(this);
        myCancel.setTag(1);

        myComplete.setText(R.string.orderComplete);
        myComplete.setOnClickListener(this);
        myComplete.setTag(2);

        workerInfo.setText(R.string.workerInfo);

        workerForward.setOnClickListener(this);
        workerForward.setTag(R.id.profileWorker);

        workerSignIn.setText(R.string.workerSignIn);
        workerSignIn.setOnClickListener(this);
        workerSignIn.setTag(3);

        workerSignUp.setText(R.string.wokerSignUp);
        workerSignUp.setOnClickListener(this);
        workerSignUp.setTag(4);

        workerModify.setText(R.string.wokerAndAddrModify);
        workerModify.setOnClickListener(this);
        workerModify.setTag(5);

        addressInfo.setText(R.string.addrInfo);

        addressForward.setOnClickListener(this);
        addressForward.setTag(R.id.profileAddress);

        addressCustome.setText(R.string.custome);
        addressCustome.setOnClickListener(this);
        addressCustome.setTag(6);

        addressAdd.setText(R.string.add);
        addressAdd.setOnClickListener(this);
        addressAdd.setTag(7);

        addressModify.setText(R.string.wokerAndAddrModify);
        addressModify.setOnClickListener(this);
        addressModify.setTag(8);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden)
            Log.i("RHG", "My:hide");
        else
            Log.i("RHG", "My:show");
    }

    private View getViewById(View parent, int centerId, int targetId) {
        return parent.findViewById(centerId).findViewById(targetId);
    }

    @Override
    protected void showFailed() {

    }

    @Override
    public void showSuccess(Object o) {
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        switch ((int) v.getTag()) {
            case R.id.profileInfo://TODO 我的订单右箭头
                startActivity(new Intent(getContext(), OrderListActivity.class));
                break;
            case R.id.profileWorker://TODO 我是跑腿员右箭头
//                if (isSignIn)
                startActivity(new Intent(getContext(), DeliverInfoActivity.class));
                /*else
                    ToastHelper.getInstance()._toast("请登录");*/
                break;
            case R.id.userHeader://TODO 更改头像
                Toast.makeText(getContext(), R.string.modifyHeader, Toast.LENGTH_SHORT).show();
                break;
            case R.id.userName://TODO 点击登录
                doLogin();
                break;
            case 0://TODO 待付款
                Toast.makeText(getContext(), R.string.orderUnPaid, Toast.LENGTH_SHORT).show();
                break;
            case 1://TODO  取消
                Toast.makeText(getContext(), R.string.cancel, Toast.LENGTH_SHORT).show();
                break;
            case 2://TODO 修改
                Toast.makeText(getContext(), R.string.orderComplete, Toast.LENGTH_SHORT).show();
                break;
            case 3://TODO 登录
                Toast.makeText(getContext(), R.string.workerSignIn, Toast.LENGTH_SHORT).show();
                break;
            case 4://TODO 注册
                startActivity(new Intent(getContext(), DeliverRegisterActivity.class));
                break;
            case 5://TODO 修改
                Toast.makeText(getContext(), R.string.wokerAndAddrModify, Toast.LENGTH_SHORT).show();
                break;
            case R.id.profileAddress://TODO 我的地址右箭头
                /*获取所有地址*/
                intent.setClass(getActivity(), AddressActivity.class);
                startActivity(intent);
                break;
            case 6://TODO 常用
                break;
            case 7://TODO 添加
                intent.setClass(getActivity(), NewAddressActivity.class);
                startActivity(intent);
                break;
            case 8://TODO 修改
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        signUtil.onActivityResult(requestCode, resultCode, data);
    }

    /*TODO 登录*/
    private void doLogin() {
        signUtil = new UmengUtil(getActivity());
        signUtil.SignIn(SHARE_MEDIA.QQ, new SignInListener() {
            @Override
            public void signSuccess(Map<String, String> infoMap) {
                userName.setText(infoMap.get(AppConstants.USERNAME_QQ));
                ImageLoader.getInstance().displayImage(infoMap.get(AppConstants.PROFILE_IMAGE_QQ),
                        userHeader);
                signUtil.setActivity(null);
            }

            @Override
            public void signFail(String errorMessage) {
                ToastHelper.getInstance()._toast(errorMessage);
            }
        });
    }
}
