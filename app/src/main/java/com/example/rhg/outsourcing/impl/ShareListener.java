package com.example.rhg.outsourcing.impl;

/**
 * 作者：rememberon 2016/6/17
 * 邮箱：1013773046@qq.com
 */
public interface ShareListener {
    public void shareSuccess(String message);

    public void shareFailed(String message,String content);

    public void shareCancel(String message);
}
