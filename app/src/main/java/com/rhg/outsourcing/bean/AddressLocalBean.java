package com.rhg.outsourcing.bean;

/**
 *desc:
 *author：remember
 *time：2016/6/17 20:11
 *email：1013773046@qq.com
 */
public class AddressLocalBean extends AddressBean {

    boolean isChecked;

    private String CreateTime;

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
