package com.example.rhg.outsourcing.utils;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.android.gms.auth.GoogleAuthUtil;

/**
 * Created by rhg on 2016/5/24.
 */
public class AccountUtil {

    private static final String PREF_ACTIVE_ACCOUNT = "user_account" ;

    private static SharedPreferences getSharedPreferences(final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    /**
     * Specify whether the app has an active account set.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static boolean hasActiveAccount(final Context context) {
        return !TextUtils.isEmpty(getActiveAccountName(context));
    }
    /**
     * Return the accountName the app is using as the active Account.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getActiveAccountName(final Context context) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getString(PREF_ACTIVE_ACCOUNT, null);
    }
    /**
     * Return the {@code Account} the app is using as the active Account.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static Account getActiveAccount(final Context context) {
        String account = getActiveAccountName(context);
        if (account != null) {
            return new Account(account, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        } else {
            return null;
        }
    }

    public static boolean setActiveAccount(final Context context, final String accountName) {
        SharedPreferences sp = getSharedPreferences(context);
        sp.edit().putString(PREF_ACTIVE_ACCOUNT, accountName).apply();
        return true;
    }
}
