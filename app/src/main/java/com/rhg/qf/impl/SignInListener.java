package com.rhg.qf.impl;

import java.util.Map;
/**
 *desc:
 *author：remember 
 *time：2016/8/8 2:17
 *email：1013773046@qq.com
 */
public interface SignInListener {
    public void signSuccess(Map<String, String> infoMap);

    public void signFail(String errorMessage);
}
