package com.example.rhg.outsourcing.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhg.outsourcing.R;

import org.w3c.dom.Text;

/**
 * Created by remember on 2016/4/28.
 */
public class MyFragment extends SuperFragment implements View.OnClickListener {
    private static final String TAG = "MyFragment";
    private static final int[] ID = {R.id.bottom_navigation, R.id.toolbarRightButton, R.id.cardView,
            R.id.bottom_navigation_bar_container, R.id.adjust_height, R.id.action_settings, R.id.toolbar,
            R.id.toolLeftTextview, R.id.toolbarRightLayout};

    public MyFragment() {
        Log.i(TAG, "MyFragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.userprofilelayout, container, false);
        ImageView userHeader = (ImageView) view.findViewById(R.id.userHeader);
        userHeader.setOnClickListener(this);
        userHeader.setTag(R.id.userHeader);
        TextView userName = (TextView) view.findViewById(R.id.userName);
//        userName.setText();//TODO 此处需要根据本地账户来判断显示
        userName.setOnClickListener(this);//TODO 如果本地有账户则直接登录，否则需要点击登录
        userName.setTag(R.id.userName);


        //TODO-------------------------------我的订单栏---------------------------------------------
        TextView myInfo = (TextView) getViewById(view, R.id.profileOrder, R.id.profileInfo);
        ImageView myForward = (ImageView) getViewById(view, R.id.profileOrder, R.id.profileForward);
        TextView myPay = (TextView) getViewById(view, R.id.profileOrder, R.id.profileDealleft);
        TextView myCancel = (TextView) getViewById(view, R.id.profileOrder, R.id.profileDealcenter);
        TextView myComplete = (TextView) getViewById(view, R.id.profileOrder, R.id.profileDealright);
        //TODO---------------------------------我是跑腿员-------------------------------------------
        TextView workerInfo = (TextView) getViewById(view, R.id.profileWorker, R.id.profileInfo);
        ImageView workerForward = (ImageView) getViewById(view, R.id.profileWorker, R.id.profileForward);
        TextView workerSignIn = (TextView) getViewById(view, R.id.profileWorker, R.id.profileDealleft);
        TextView workerSignUp = (TextView) getViewById(view, R.id.profileWorker, R.id.profileDealcenter);
        TextView workerModify = (TextView) getViewById(view, R.id.profileWorker, R.id.profileDealright);
        //TODO---------------------------------我的地址-------------------------------------------
        TextView addrInfo = (TextView) getViewById(view, R.id.profileAddress, R.id.profileInfo);
        ImageView addrForward = (ImageView) getViewById(view, R.id.profileAddress, R.id.profileForward);
        TextView addrCustome = (TextView) getViewById(view, R.id.profileAddress, R.id.profileDealleft);
        TextView addrAdd = (TextView) getViewById(view, R.id.profileAddress, R.id.profileDealcenter);
        TextView addrModify = (TextView) getViewById(view, R.id.profileAddress, R.id.profileDealright);

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

        addrInfo.setText(R.string.addrInfo);

        addrForward.setOnClickListener(this);
        addrForward.setTag(R.id.profileAddress);

        addrCustome.setText(R.string.addrCustome);
        addrCustome.setOnClickListener(this);
        addrCustome.setTag(6);

        addrAdd.setText(R.string.addrAdd);
        addrAdd.setOnClickListener(this);
        addrAdd.setTag(7);

        addrModify.setText(R.string.wokerAndAddrModify);
        addrModify.setOnClickListener(this);
        addrModify.setTag(8);
        return view;
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

        switch ((int) v.getTag()) {
            case R.id.profileInfo://TODO 我的订单右箭头
                Toast.makeText(getContext(), R.string.myOrder, Toast.LENGTH_SHORT).show();
                break;
            case R.id.profileWorker://TODO 我是跑腿员右箭头
                Toast.makeText(getContext(), R.string.workerInfo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.profileAddress://TODO 我的地址右箭头
                Toast.makeText(getContext(), R.string.addrInfo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.userHeader://TODO 更改头像
                Toast.makeText(getContext(), R.string.modifyHeader, Toast.LENGTH_SHORT).show();
                break;
            case R.id.userName://TODO 点击登录
                Toast.makeText(getContext(),R.string.userSignIn,Toast.LENGTH_SHORT).show();
                break;
            case 0://TODO 待付款
                Toast.makeText(getContext(),R.string.myPay,Toast.LENGTH_SHORT).show();
                break;
            case 1://TODO  取消
                Toast.makeText(getContext(),R.string.myCancel,Toast.LENGTH_SHORT).show();
                break;
            case 2://TODO 修改
                Toast.makeText(getContext(),R.string.myComplete,Toast.LENGTH_SHORT).show();
                break;
            case 3://TODO 登录
                Toast.makeText(getContext(),R.string.workerSignIn,Toast.LENGTH_SHORT).show();
                break;
            case 4://TODO 注册
                Toast.makeText(getContext(),R.string.wokerSignUp,Toast.LENGTH_SHORT).show();
                break;
            case 5://TODO 修改
                Toast.makeText(getContext(),R.string.wokerAndAddrModify,Toast.LENGTH_SHORT).show();
                break;
            case 6://TODO 常用
                Toast.makeText(getContext(),R.string.addrCustome,Toast.LENGTH_SHORT).show();
                break;
            case 7://TODO 添加
                Toast.makeText(getContext(),R.string.addrAdd,Toast.LENGTH_SHORT).show();
                break;
            case 8://TODO 修改
                Toast.makeText(getContext(),R.string.wokerAndAddrModify,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
