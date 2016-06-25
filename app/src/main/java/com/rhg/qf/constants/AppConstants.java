package com.rhg.qf.constants;

import android.os.Environment;

import com.rhg.qf.R;

/**
 * desc:APP的一些常量
 * author：remember
 * time：2016/5/28 16:40
 * email：1013773046@qq.com
 */
public class AppConstants {
    public static final boolean DEBUG = true;
    public static final String f_Path = Environment.getExternalStorageDirectory() + "/QFood";

    public static final String[] images = {
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
    };
    public static final int[] IMAGE_INDICTORS = new int[]{R.drawable.ic_page_indicator,
            R.drawable.ic_page_indicator_focused};
    public static final String[] SHOP_DETAIL_TITLES = new String[]{"菜品", "店铺详情"};
    public static final String[] SELL_TITLES = new String[]{"按销量", "按距离", "按评分"};
    public static final String[] ORDER_TITLES = new String[]{"待付款", "进行中", "已完成", "已退款"};
    public static final String[] SHOP_TITLES = new String[]{"热销排行", "超值套餐", "店铺优惠", "必点饮品", "最新优惠"};
    public static final String[] HOT_SELL_TITLES = new String[]{"综合", "距离", "销量", "评分"};
    //---------------------------店铺复用------------------------------------------------------------
    public static final int TypeHome = 0;
    public static final int TypeSeller = 1;
    public static final int TypeOrder = 2;
    //---------------------------所有店铺页面中的header和body-----------------------------------------
    public static final int TypeHeader = 1;
    public static final int TypeBody = 2;

    /*SP KEY*/
    public static final String SP_LOCATION = "location";
    public static final String SP_LATITUDE = "latitude";
    public static final String SP_LONGITUDE = "longitude";
    public static final String SP_HEAD_IMAGE = "sp_head_image";
    public static final String SP_USER_ID = "user_id";
    public static final String SP_USER_NAME = "user_name";
    public static final String SP_PASSWORD = "password";
    /*SP KEY*/
    /**
     * 页面调起/销毁标志
     */
    public static final int START_0 = 0;
    public static final int BACK_WITH_DELETE = 4;
    public static final int BACK_WITHOUT_DATA = 5;
    public static final int PAY = 6;

    /*signin*/
    public static final String USERNAME_QQ = "screen_name";
    public static final String PROFILE_IMAGE_QQ = "profile_image_url";
    /*signin*/

    /**
     * Intent/Bundle 传递Bean的KEY
     */
    public static final String KEY_INTENT_BEAN = "bean";
    public static final String KEY_MERCHANT_NAME = "merchant_name";
    public static final String KEY_PRODUCT_NAME = "product_name";
    public static final String KEY_MERCHANT_ID = "merchant_id";
    public static final String KEY_PRODUCT_ID = "product_id";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_MERCHANT_LOGO = "merchant_logo";
    public static final String KEY_PRODUCT_PRICE = "product_price";
    public static final String KEY_OR_SP_PHONE = "phone";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_NOTE = "note";
    public static final String KEY_DELETE = "delete";
    public static final String KEY_ORDER_TAG = "order_tag";
    public static final int KEY_SHOPPING_CART = 3;// TODO: 跳转到购物车的KEY
    /*for headImage*/
    public static final int CODE_GALLERY_REQUEST = 10;//TODO:From Local
    public static final int CODE_GALLERY_REQUEST_KITKAT = 20;//TODO:From Local
    public static final int CODE_CAMERA_REQUEST = 11;//TODO: From Camera
    public static final int CODE_RESULT_REQUEST = 12;//TODO:From Crop
    public static final String HEAD_IMAGE_NAME = "head_image.png";

    /*Address*/
    public static final String NAME_FOR_ADDRESS = "address_name";
    public static final String PHONE_FOR_ADDRESS = "address_phone";
    public static final String ADDRESS_CONTENT = "address_content";
    public static final String ADDRESS_CREATE_TIME = "address_time";

    /*Merchant*/
    public static final int MERCHANT_SELLNUMBER = 0;
    public static final int MERCHANT_DISTANCE = 1;
    public static final int MERCHANT_RATE = 2;
    /*Order*/
    public static final String ORDER_ALL = "0";
    public static final String ORDER_UNPAID = "1";
    public static final String ORDER_DELIVERING = "2";
    public static final String ORDER_COMPLETE = "3";
    public static final String ORDER_DRAWBACK = "4";

    /*HotFood*/
    public static final String OVERALL = "0";
    public static final String SELLNUMBER = "1";
    public static final String DISTANCE = "2";
    public static final String RATE = "3";

    /*后台交互信息*/
    public static final String TABLE_CLIENT = "client";
    public static final String TABLE_FOODMESSAGE = "foodmessage";
    public static final String TABLE_FOOD = "food";
    public static final String TABLE_RESTAURANTS = "restaurants";
    public static final String TABLE_ORDER = "order";

    /**
     * 响应结果，正确
     */
    public static final int RESULT_SUCCESS = 0;

    /**
     * 登录已过期
     */
    public static final String RESULT_LOGIN_OUT_OF_DATE = "55";

    /**
     * 授权令牌的KEY
     */
    public static final String RESULT_KEY_ACCESS_TOKEN = "authToken";

    public static final String RESULT_KEY = "errCode";

    public static final String RESULT_MESSAGE_KEY = "errInfo";

    public static final String RESULT_CONTENT = "entity";

    public static final String RESULT_KEY_ENTITY = "entity";

    public static final String RESULT_KEY_PAGE_ENTITY = "pageEntity";

    public static final String RESULT_KEY_QINIU_TOKEN = "uptoken";

    /**
     * 网络错误
     */
    public static final String TEXT_ERROR_500 = "网络错误500";

    /**
     * 网络错误500
     */
    public static final int STATUS_NET_ERROR = 500;

    /**
     * 500的提示
     */
    public static final String JSON500 = "网络连接不稳定";

    /**
     * 更新标示
     **/
    public static final int UPDATE_SERVICE = 3;

    public static final String IMAGE_SPLITE = "##";

    public static final String CHAR_REPLACE = "$s$";

    public static final String LOADING = "正在加载，请稍后...";

    /**
     * 有效
     */
    public static final String STATE_VALID = "0";

    /**
     * 无效
     */
    public static final String STATE_INVALID = "1";

    /**
     * DES 加密key值
     */
    public static final String DES_KEY = "abcdefgh";

    public static final String IMAGE_PATH = "/wxt";
    /**
     * SharedPreferences的key
     */
    public static final String SP_NAME_PROJECT = "FatTail";
    public static final String SP_ACCOUNT_TYPE = "com.qhouse";
    /**
     * UMeng推送的key
     */
    public static final String SP_KEY_PUSH = "push";
    /*--------------------------------SP Key------------------------------------*/
    public static final String SP_KEY_OWER_ID = "owerID";
    public static final String SP_KEY_COMMUNITY_ID = "communityID";
    /**
     * 服务器返回的错误信息
     */
    public static final int WHAT_ERROR_HTTP = 500;
    /**
     * accessToken过期
     */
    public static final int WHAT_TOKEN_EXPIRED = 1011;
    /**
     * 忘记密码
     */
    public static final int WHAT_FORGET = 100;
    /**
     * 请求短信验证码
     */
    public static final int WHAT_VALIDATION_CODE = 101;
    /**
     * 倒计时
     */
    public static final int WHAT_COUNT_DOWN = 102;
    /**
     * 登录
     */
    public static final int WHAT_LOGIN = 103;
    /**
     * 评论列表
     */
    public static final int WHAT_COMMENT_LIST = 104;
    /**
     * 双击退出
     */
    public static final int WHAT_EXIT = 105;
    public static int SCREEN_WIDTH = 0;
    public static int PAGE_SIZE_DEFAULLT = 7;
    public static int PAGE_NO_DEFAULT = 1;

}