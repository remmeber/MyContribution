package com.example.rhg.outsourcing.apapter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


import com.example.rhg.outsourcing.impl.ImageChangeListener;
import com.example.rhg.outsourcing.model.ImageModel;

import java.util.List;

/**
 * Created by whiskeyfei on 15-7-24.
 */
public class TimerImageAdapter {
    private static final String TAG = "TimerImageAdapter";

    private List<ImageModel> mList;
    ImageChangeListener mImageChangeListener;

    private int mCurrentPos = 0;
    private int mOldPos = 0;

    private ImageModel mCurrentModel;
    private static final long DELAYTIME = 5000;
    private static final int CHANGE_FLAG = 1;
    private boolean isStop = true;

    public int getCurrentPos() {
        return mOldPos;
    }

    private ImageModel getCurrentModel(){
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

    public void setData(List<ImageModel> mList){
        this.mList = mList;
//        startTimer();
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
        if (mCurrentPos >= mList.size()){
            mCurrentPos = 0;
        }
        mCurrentModel = mList.get(mCurrentPos);
        if (mCurrentModel != null){
            imageRsid = mCurrentModel.getImageId();
        }
        mOldPos = mCurrentPos;
        mCurrentPos++;
        mImageChangeListener.updateImage(imageRsid,mOldPos);
        mHandler.sendEmptyMessageDelayed(CHANGE_FLAG, DELAYTIME);
    }

    public void setImageChangeListener(ImageChangeListener l) {
        mImageChangeListener = l;
    }

}
