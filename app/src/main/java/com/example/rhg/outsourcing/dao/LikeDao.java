package com.example.rhg.outsourcing.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rhg.outsourcing.datebase.LikeDBHelper;
import com.example.rhg.outsourcing.bean.LikeBean;

/**
 *desc:Like数据库DAO
 *author：remember
 *time：2016/5/28 16:40
 *email：1013773046@qq.com
 */
public class LikeDao {
    private volatile static LikeDao instance = null;
    private SQLiteDatabase db;

    public static LikeDao getInstance() {
        if (instance == null) {
            synchronized (LikeDao.class) {
                if (instance == null) {
                    instance = new LikeDao();
                }
            }
        }
        return instance;
    }

    private Cursor cursor;

    public void close() {
        if (db != null) {
            db.close();
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    public int getLikeCount() {
        db = LikeDBHelper.getInstance().getReadableDatabase();
        cursor = db.rawQuery("select count(*) from " + LikeDBHelper.Q_LIKE, null);
        int count = 0;
        //游标移到第一条记录准备获取数据
        if (cursor.moveToFirst()) {
            // 获取数据中的LONG类型数据
            count = (int) cursor.getLong(0);
        }
        close();
        return count;
    }

    public boolean isExistLike(String productID) {
        if (productID == null) {
            return false;
        }
        db = LikeDBHelper.getInstance().getReadableDatabase();

        cursor = db.query(LikeDBHelper.Q_LIKE, null, LikeBean.KEY_PRODUCT_ID + "=?", new String[]{productID}, null, null, null);
        boolean isExist = cursor.moveToFirst();
        close();
        return isExist;
    }

    /**
     * 添加商品的收藏情况
     * @param productID
     * @param like
     */
    public LikeDao saveGoodsLikeInfo(String productID, Integer like) {
        if (productID == null || "".equals(productID)) {
            return this;
        }
        db = LikeDBHelper.getInstance().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(LikeBean.KEY_PRODUCT_ID, productID);
        values.put(LikeBean.KEY_LIKE, like);
        db.insert(LikeDBHelper.Q_LIKE, null, values);
        close();
        return this;
    }

    /**
     * 删除商品的收藏
     * @param productID
     */
    public void deleteGoodsLikeInfo(String productID) {
        if (productID == null) {
            return;
        }
        db = LikeDBHelper.getInstance().getReadableDatabase();
        db.delete(LikeDBHelper.Q_LIKE, LikeBean.KEY_PRODUCT_ID + " =?", new String[]{productID});
        close();
    }

    /**
     * 通过productId来获取属性
     * @param productID
     * @return
     */
    public int getNumByProductID(String productID) {
        if (productID == null) {
            return -1;
        }
        db = LikeDBHelper.getInstance().getReadableDatabase();
        cursor = db.query(LikeDBHelper.Q_LIKE, new String[]{LikeBean.KEY_LIKE}, LikeBean.KEY_PRODUCT_ID + "=?", new String[]{productID}, null, null, null);
        if (cursor.moveToFirst()) {
            Log.i("RHG",cursor.getInt(0)+"");
            return cursor.getInt(0);
        }
        close();
        return -1;
    }

    /**
     * 根据productId更新属性
     * @param productID
     * @param like
     */
    public void updateLike(String productID, Integer like) {
        if (productID == null || "".equals(productID)) {
            return;
        }
        db = LikeDBHelper.getInstance().getReadableDatabase();
        ContentValues values = new ContentValues();
        if (!"".equals(productID)) {
            values.put(LikeBean.KEY_LIKE, like);
            db.update(LikeDBHelper.Q_LIKE, values, LikeBean.KEY_PRODUCT_ID + "=?", new String[]{productID});
        }
        close();
    }

}
