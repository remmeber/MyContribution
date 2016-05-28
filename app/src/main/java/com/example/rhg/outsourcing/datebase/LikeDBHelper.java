package com.example.rhg.outsourcing.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rhg.outsourcing.bean.LikeBean;

/**
 *desc:Like数据库辅助类
 *author：remember
 *time：2016/5/28 16:41
 *email：1013773046@qq.com
 */
public class LikeDBHelper extends SQLiteOpenHelper {
    /**
     * 数据库名称常量
     */
    public static final String DATABASE_NAME = "Q_like.db3";
    /**
     * 数据库版本常量
     */
    private static final int DATABASE_VERSION = 3;
    /**
     * 购物车表
     */
    public static final String Q_LIKE = "Q_like";

    private static LikeDBHelper helper;

    private static Context APPLICATION_CONTEXT;

    public static void init(Context context) {
        APPLICATION_CONTEXT = context;
    }

    public LikeDBHelper() {
        super(APPLICATION_CONTEXT, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static LikeDBHelper getInstance() {
        if (helper == null) {
            helper = new LikeDBHelper();
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TB_Like = "create table " + Q_LIKE + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LikeBean.KEY_PRODUCT_ID + " text,"
                + LikeBean.KEY_LIKE + " text"
                + ");";
        db.execSQL(CREATE_TB_Like);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Q_LIKE);
        onCreate(db);
    }
}
