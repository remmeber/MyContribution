package com.example.rhg.outsourcing.mvp.model;

import java.io.File;
import java.util.List;

import rx.Observable;

/**
 * 作者：rememberon 2016/5/31
 * 邮箱：1013773046@qq.com
 */
public interface UploadAndSaveImageModel {
    Observable<String> UploadAndSaveImage(File file, String userName, String pwd);
}
