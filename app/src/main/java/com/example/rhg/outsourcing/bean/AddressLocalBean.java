package com.example.rhg.outsourcing.bean;

import com.example.rhg.outsourcing.widget.SlideView;

/**
 * 作者：rememberon 2016/6/5
 * 邮箱：1013773046@qq.com
 */
public class AddressLocalBean extends AddressBean {
    SlideView slideView;

    boolean isChecked;

    public SlideView getSlideView() {
        return slideView;
    }

    public void setSlideView(SlideView slideView) {
        this.slideView = slideView;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
