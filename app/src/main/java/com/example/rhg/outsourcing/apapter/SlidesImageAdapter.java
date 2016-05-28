package com.example.rhg.outsourcing.apapter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


import java.util.List;

import com.example.rhg.outsourcing.bean.FavorableFoodBean;
import com.example.rhg.outsourcing.impl.SlidesViewChangeListener;

/**
 *desc:todo 待使用适配器
 *author：remember
 *time：2016/5/28 16:21
 *email：1013773046@qq.com
 */
public class SlidesImageAdapter {
    private static final String TAG = "SlidesImageAdapter";

    private List<FavorableFoodBean> mList;
    private SlidesViewChangeListener mChangeListener;

    private int mCurrentPos = 0;
    private int mOldPos = 0;

    private FavorableFoodBean mCurrentModel;
    private static final long DELAYTIME = 5000;
    private static final int CHANGE_FLAG = 1;
    private boolean isStop = true;

    public int getCurrentPos() {
        return mOldPos;
    }

    private FavorableFoodBean getCurrentModel(){
        return mCurrentModel;
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHANGE_FLAG:
                    switchImage();
                    break;
            }
        }
    };

    public void setData(List<FavorableFoodBean> mList){
        this.mList = mList;
        startTimer();
    }

    public boolean getIsStop(){
        return isStop;
    }

    public void setIsStop(boolean stop){
        isStop = stop;
    }

    public void startTimer() {
        if (isStop){
            isStop = false;
        }
        mHandler.removeMessages(CHANGE_FLAG);
        mHandler.sendEmptyMessageDelayed(CHANGE_FLAG, 50);
    }

    /**
     * 停止切换
     */
    public void stopSwitchImage(){
        isStop = true;
        mHandler.removeMessages(CHANGE_FLAG);
    }

    /**
     * 切换下一张
     */
    public void switchNext(){
        mHandler.removeMessages(CHANGE_FLAG);
        mHandler.sendEmptyMessageDelayed(CHANGE_FLAG, 50);
    }

    private void switchImage(){
        Log.e(TAG,"switchImage() -> mCurrentPos:" + mCurrentPos);
        int imageRsid = -1;
        String imageUrl = "";
        String title = null;
        if (mCurrentPos >= mList.size()){
            mCurrentPos = 0;
        }
        mCurrentModel = mList.get(mCurrentPos);
        if (mCurrentModel != null){
//            imageRsid = mCurrentModel.getImageId();
//            title = mCurrentModel.getContent();
            imageUrl = mCurrentModel.getImageUrl();
            title = mCurrentModel.getTitle();
        }
        mOldPos = mCurrentPos;
        mCurrentPos++;
        mChangeListener.update(imageUrl,title, mOldPos);
        mHandler.sendEmptyMessageDelayed(CHANGE_FLAG, DELAYTIME);
    }

    public void setImageChangeListener(SlidesViewChangeListener l) {
        mChangeListener = l;
    }

}
