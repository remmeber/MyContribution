package com.example.rhg.outsourcing.datebase;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rhg.outsourcing.model.ShoppingCartBean;

/**
 * Created by remember on 2016/5/9.
 */
public class DBHelper extends SQLiteOpenHelper {
    /** 数据库名称常量 */
    public static final String DATABASE_NAME = "Q_shopping_cart.db3";
    /** 数据库版本常量 */
    private static final int DATABASE_VERSION = 3;
    /** 购物车表 */
    public static final String Q_SHOPPING_CART = "Q_shopping_cart";

    private static DBHelper helper;

    private static Context APPLICATION_CONTEXT;
    public static void init(Context context) {
        APPLICATION_CONTEXT = context;
    }

    public DBHelper() {
        super(APPLICATION_CONTEXT, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getInstance() {
        if (helper == null) {
            helper = new DBHelper();
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
        db.execSQL(CREATE_TB_SHOPPING_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Q_SHOPPING_CART);
        onCreate(db);
    }
}
