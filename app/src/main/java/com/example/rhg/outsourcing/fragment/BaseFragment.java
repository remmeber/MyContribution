package com.example.rhg.outsourcing.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhg.outsourcing.impl.OnSwitchFragmentListener;

public class BaseFragment extends Fragment {
	
	private static final String TAG = "BaseFragment";
	protected Context mContext;
	protected OnSwitchFragmentListener mOnSwitchFragmentListener;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
		try {
			mOnSwitchFragmentListener = (OnSwitchFragmentListener) activity;
		} catch (Exception e) {
			throw new IllegalStateException("your activity must implements mOnSwitchFragmentListener!");
		}
		mOnSwitchFragmentListener.onAttachActivity(this);
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.e(TAG, TAG + "---onDetach()---");
		mContext = null;
		mOnSwitchFragmentListener = null;
	}
	
}
