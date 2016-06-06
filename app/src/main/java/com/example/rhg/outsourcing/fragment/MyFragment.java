package com.example.rhg.outsourcing.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhg.outsourcing.R;
import com.example.rhg.outsourcing.activity.AddressActivity;
import com.example.rhg.outsourcing.activity.DeliverInfoActivity;
import com.example.rhg.outsourcing.activity.OrderActivity;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.utils.AccountUtil;
import com.example.rhg.outsourcing.utils.ToastHelper;

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
        flTAB.setBackgroundColor(getResources().getColor(R.color.colorActiveGreen));
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

        myPay.setText(R.string.myPay);
        myPay.setOnClickListener(this);
        myPay.setTag(0);

        myCancel.setText(R.string.myCancel);
        myCancel.setOnClickListener(this);
        myCancel.setTag(1);

        myComplete.setText(R.string.myComplete);
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

        addressCustome.setText(R.string.addrCustome);
        addressCustome.setOnClickListener(this);
        addressCustome.setTag(6);

        addressAdd.setText(R.string.addrAdd);
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
                startActivity(new Intent(getContext(), OrderActivity.class));
                break;
            case R.id.profileWorker://TODO 我是跑腿员右箭头
//                Toast.makeText(getContext(), R.string.workerInfo, Toast.LENGTH_SHORT).show();
//                if (isSignIn)
                startActivity(new Intent(getContext(), DeliverInfoActivity.class));
                /*else
                    ToastHelper.getInstance()._toast("请登录");*/
                break;
            case R.id.userHeader://TODO 更改头像
                Toast.makeText(getContext(), R.string.modifyHeader, Toast.LENGTH_SHORT).show();
                break;
            case R.id.userName://TODO 点击登录
                Toast.makeText(getContext(), R.string.userSignIn, Toast.LENGTH_SHORT).show();
                break;
            case 0://TODO 待付款
                Toast.makeText(getContext(), R.string.myPay, Toast.LENGTH_SHORT).show();
                break;
            case 1://TODO  取消
                Toast.makeText(getContext(), R.string.myCancel, Toast.LENGTH_SHORT).show();
                break;
            case 2://TODO 修改
                Toast.makeText(getContext(), R.string.myComplete, Toast.LENGTH_SHORT).show();
                break;
            case 3://TODO 登录
                Toast.makeText(getContext(), R.string.workerSignIn, Toast.LENGTH_SHORT).show();
                break;
            case 4://TODO 注册
                Toast.makeText(getContext(), R.string.wokerSignUp, Toast.LENGTH_SHORT).show();
                break;
            case 5://TODO 修改
                Toast.makeText(getContext(), R.string.wokerAndAddrModify, Toast.LENGTH_SHORT).show();
                break;
            case R.id.profileAddress://TODO 我的地址右箭头
                /*Toast.makeText(getContext(), R.string.addrInfo, Toast.LENGTH_SHORT).show();
                break;*/
            case 6://TODO 常用
                /*Toast.makeText(getContext(), R.string.addrCustome, Toast.LENGTH_SHORT).show();
                break;*/
            case 7://TODO 添加
//                break;
            case 8://TODO 修改
                intent.setClass(getActivity(), AddressActivity.class);
                startActivity(intent);
//                Toast.makeText(getContext(), R.string.wokerAndAddrModify, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
