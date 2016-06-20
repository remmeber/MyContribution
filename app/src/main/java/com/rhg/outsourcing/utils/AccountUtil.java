package com.rhg.outsourcing.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.rhg.outsourcing.bean.UserBean;
import com.rhg.outsourcing.constants.AppConstants;

/**
 * desc:账户工具
 * author：remember
 * time：2016/6/5 9:57
 * email：1013773046@qq.com
 */
public class AccountUtil {
    private static final String USER_ACCOUNT = "user_account";
    private static AccountUtil accountUtil;

    private SharedPreferences userInfoSP;

    public AccountUtil() {
    }

    public static AccountUtil getInstance() {
        if (accountUtil == null) {
            accountUtil = new AccountUtil();
        }
        return accountUtil;
    }

    /**
     * 必须初始化
     */
    public void init(Context context) {
        this.userInfoSP = context.getSharedPreferences(USER_ACCOUNT, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor() {
        return this.userInfoSP.edit();
    }


    private SharedPreferences getSP() {
        return this.userInfoSP;
    }

    private void putStringByKey(String key, String string) {
        getEditor().putString(key, string).commit();
    }

    private String getStringByKey(String key) {
        return getSP().getString(key, "");
    }

    public boolean hasAccount() {
        return !TextUtils.isEmpty(getStringByKey(AppConstants.SP_USER_ID));
    }

    public UserBean.User getActiveAccount() {
        UserBean.User user = new UserBean.User();
        user.setID(getStringByKey(AppConstants.SP_USER_ID));
        user.setCName(getStringByKey(AppConstants.SP_USER_NAME));
        user.setPhonenumber(getStringByKey(AppConstants.KEY_OR_SP_PHONE));
//        user.setPwd(getStringByKey(AppConstants.SP_PASSWORD));
        user.setPic(getStringByKey(AppConstants.HEAD_IMAGE_NAME));
        return user;
    }

    public void saveAccount(UserBean.User account) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(AppConstants.SP_USER_ID, account.getID());
        editor.putString(AppConstants.SP_USER_NAME, account.getCName());
        editor.putString(AppConstants.KEY_OR_SP_PHONE, account.getPhonenumber());
        editor.putString(AppConstants.SP_HEAD_IMAGE, account.getPic());
        editor.apply();
    }

    public void setUserID(String userID) {
        putStringByKey(AppConstants.SP_USER_ID, userID);
    }

    public String getUserID() {
        return getStringByKey(AppConstants.SP_USER_ID);
    }

    public void setUserName(String userName) {
        putStringByKey(AppConstants.SP_USER_NAME, userName);
    }

    public String getUserName() {
        return getStringByKey(AppConstants.SP_USER_NAME);
    }

    public void setPhoneNumber(String phoneNumber) {
        putStringByKey(AppConstants.KEY_OR_SP_PHONE, phoneNumber);
    }

    public String getPhoneNumber() {
        return getStringByKey(AppConstants.KEY_OR_SP_PHONE);
    }

    public void setHeadImageUrl(String headImageUrl) {
        putStringByKey(AppConstants.SP_HEAD_IMAGE, headImageUrl);
    }

    public String getHeadImageUrl() {
        return getStringByKey(AppConstants.SP_HEAD_IMAGE);
    }


    public void setLocation(String s) {
        putStringByKey(AppConstants.SP_LOCATION, s);
    }

    public String getLocation() {
        return getStringByKey(AppConstants.SP_LOCATION);
    }
}