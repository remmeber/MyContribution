package com.example.rhg.outsourcing.impl;

import java.util.Map;

/**
 * 作者：rememberon 2016/6/17
 * 邮箱：1013773046@qq.com
 */
public interface SignInListener {
    public void signSuccess(Map<String, String> infoMap);

    public void signFail(String errorMessage);
}
