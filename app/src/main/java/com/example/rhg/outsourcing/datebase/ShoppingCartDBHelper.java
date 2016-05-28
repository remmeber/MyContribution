package com.example.rhg.outsourcing.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rhg.outsourcing.bean.ShoppingCartBean;

/**
 *desc:购物车数据库辅助类
 *author：remember
 *time：2016/5/28 16:41
 *email：1013773046@qq.com
 */
public class ShoppingCartDBHelper extends SQLiteOpenHelper {
    /** 数据库名称常量 */
    public static final String DATABASE_NAME = "Q_shopping_cart.db3";
    /** 数据库版本常量 */
    private static final int DATABASE_VERSION = 3;
    /** 购物车表 */
    public static final String Q_SHOPPING_CART = "Q_shopping_cart";
    /*收藏表*/
//    public static final String Q_LIKE = "Q_like";

    private static ShoppingCartDBHelper helper;

    private static Context APPLICATION_CONTEXT;
    public static void init(Context context) {
        APPLICATION_CONTEXT = context;
    }

    public ShoppingCartDBHelper() {
        super(APPLICATION_CONTEXT, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static ShoppingCartDBHelper getInstance() {
        if (helper == null) {
            helper = new ShoppingCartDBHelper();
        }
        return helper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("RHG","SQL");
        String CREATE_TB_SHOPPING_CART = "create table " + Q_SHOPPING_CART + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ShoppingCartBean.KEY_PRODUCT_ID + " text,"
                + ShoppingCartBean.KEY_NUM + " text"
                + ");";

        /*String CREATE_TB_Like = "create table " + Q_LIKE + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LikeBean.KEY_PRODUCT_ID + " text,"
                + LikeBean.KEY_LIKE + "integer"
                + ");";
        db.execSQL(CREATE_TB_Like);*/
        db.execSQL(CREATE_TB_SHOPPING_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Q_SHOPPING_CART);
//        db.execSQL("DROP TABLE IF EXISTS " + Q_SHOPPING_CART);
        onCreate(db);
    }
}
