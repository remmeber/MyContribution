package com.rhg.qf.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.easemob.EMCallBack;
import com.easemob.EMError;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.ui.EaseChatFragment;
import com.easemob.exceptions.EaseMobException;
import com.rhg.qf.R;
import com.rhg.qf.utils.AccountUtil;
import com.rhg.qf.utils.ToastHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatActivity extends EaseBaseActivity {
    public static ChatActivity activityInstance;
    String toChatUsername;
    String uname;
    private EaseChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_chat);
        activityInstance = this;
        uname = "QF" + dealUName(AccountUtil.getInstance().getNickName());
        if (EMChat.getInstance().isLoggedIn()) {
            initView();
        } else
            createAccountToServer(uname, getPwd(uname), new EMCallBack() {
                @Override
                public void onSuccess() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loginToServer(uname, getPwd(uname));
                        }
                    });
                }

                @Override
                public void onError(final int i, String s) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (i == EMError.NONETWORK_ERROR) {
                                ToastHelper.getInstance()._toast("网络不可用");
                            } else if (i == EMError.USER_ALREADY_EXISTS) {
                                loginToServer(uname, getPwd(uname));
                            }
                        }
                    });
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
    }

    private void initView() {
        toChatUsername = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);
        chatFragment = new EaseChatFragment();
        //传入参数
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }

    private void loginToServer(String nickName, String pwd) {
        EMChatManager.getInstance().login(nickName, pwd, new EMCallBack() {
            @Override
            public void onSuccess() {
                //Log.i("RHG", "登录成功");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initView();
                    }
                });
            }

            @Override
            public void onError(final int i, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                //Log.i("RHG", "登录失败" + " i= " + i + " s= " + s);
            }

            @Override
            public void onProgress(int i, String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastHelper.getInstance()._toast("正在获取客服聊天...");

                    }
                });
            }
        });
    }

    private String getPwd(String nickName) {
        return "jjms" + nickName + "jjms";
    }

    private String dealUName(String uname) {
        String reg = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(uname);
        String s = mat.replaceAll("");
        return s.replace(" ", "");
    }

    private void createAccountToServer(final String uname, final String pwd, final EMCallBack callBack) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMChatManager.getInstance().createAccountOnServer(uname, pwd);
                    if (callBack != null)
                        callBack.onSuccess();
                } catch (EaseMobException e) {
                    if (callBack != null)
                        callBack.onError(e.getErrorCode(), e.getMessage());
                }
            }
        });
        thread.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // 点击notification bar进入聊天页面，保证只有一个聊天页面
        String username = intent.getStringExtra(EaseConstant.EXTRA_USER_ID);
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        if (chatFragment != null)
            chatFragment.onBackPressed();
        else finish();
    }

    public String getToChatUsername() {
        return toChatUsername;
    }
}
