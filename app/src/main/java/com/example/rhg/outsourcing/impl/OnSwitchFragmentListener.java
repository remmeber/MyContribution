package com.example.rhg.outsourcing.impl;

import android.os.Bundle;

import com.example.rhg.outsourcing.fragment.BaseFragment;

/**
 * Created by whiskeyfei on 15-7-29.
 */
public interface OnSwitchFragmentListener {
    void onSwitchFragment(BaseFragment fragment, Bundle bundle);
    void onAttachActivity(BaseFragment fragment);
    void onDetachActivity(BaseFragment fragment);
}
