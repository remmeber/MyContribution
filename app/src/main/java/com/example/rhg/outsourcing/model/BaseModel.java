package com.example.rhg.outsourcing.model;

import rx.Observable;
import rx.Observer;

/**
 * Created by remember on 2016/4/28.
 */
public interface BaseModel {
    Observable<Object> GetData();
}
