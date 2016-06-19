package com.example.rhg.outsourcing.datebase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.rhg.outsourcing.bean.AddressBean;
import com.example.rhg.outsourcing.bean.AddressLocalBean;
import com.example.rhg.outsourcing.bean.ShoppingCartBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:购物车数据口DAO
 * author：remember
 * time：2016/5/28 16:41
 * email：1013773046@qq.com
 */
public class AccountDao {

    private volatile static AccountDao instance = null;
    private SQLiteDatabase db;
    private Cursor cursor;

    /**
     * 获取SimpleDemoDB实例
     */
    public static AccountDao getInstance() {
        if (instance == null) {
            synchronized (AccountDao.class) {
                if (instance == null) {
                    instance = new AccountDao();
                }
            }
        }
        return instance;
    }


    public void close() {
        if (db != null) {
            db.close();
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    public int getTableCountByName(String tableName) {
        db = AccountDBHelper.getInstance().getReadableDatabase();
        cursor = db.rawQuery("select count(*) from " + tableName, null);
        int count = 0;
        //游标移到第一条记录准备获取数据
        if (cursor.moveToFirst()) {
            // 获取数据中的LONG类型数据
            count = (int) cursor.getLong(0);
        }
        close();
        return count;
    }

   /* public int getGoodsCount() {
        db = AccountDBHelper.getInstance().getReadableDatabase();
        cursor = db.rawQuery("select count(*) from " + AccountDBHelper.Q_SHOPPING_CART_TABLE, null);
        int count = 0;
        //游标移到第一条记录准备获取数据
        if (cursor.moveToFirst()) {
            // 获取数据中的LONG类型数据
            count = (int) cursor.getLong(0);
        }
        close();
        return count;
    }*/


    public boolean isExistGood(String productID) {
        if (productID == null) {
            return false;
        }
        db = AccountDBHelper.getInstance().getReadableDatabase();
        cursor = db.query(AccountDBHelper.Q_SHOPPING_CART_TABLE, null, ShoppingCartBean.KEY_PRODUCT_ID + "=?", new String[]{productID}, null, null, null);
        boolean isExist = cursor.moveToFirst();
        close();
        return isExist;
    }

    /**
     * 添加购物车商品信息
     *
     * @param productID 规格ID
     * @param num       商品数量
     */
    public void saveShoppingInfo(String productID, String num) {
        if (productID == null || "".equals(productID) || num == null || "".equals(num)) {
            return;
        }
        db = AccountDBHelper.getInstance().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(ShoppingCartBean.KEY_PRODUCT_ID, productID);
        values.put(ShoppingCartBean.KEY_NUM, num);
        db.insert(AccountDBHelper.Q_SHOPPING_CART_TABLE, null, values);
        close();
    }

    public void saveAddress(AddressLocalBean addressBean) {
        if (addressBean == null) {
            return;
        }
        db = AccountDBHelper.getInstance().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppConstants.ADDRESS_CREATE_TIME, addressBean.getCreateTime());
        values.put(AppConstants.NAME_FOR_ADDRESS, addressBean.getName());
        values.put(AppConstants.PHONE_FOR_ADDRESS, addressBean.getPhone());
        values.put(AppConstants.ADDRESS_CONTENT, addressBean.getAddress());
        db.insert(AccountDBHelper.Q_ADDRESS_TABLE, null, values);
        close();
    }


    public void deleteItemInTableById(String Table, String conditionId, String condition) {
        if (condition == null) {
            return;
        }
        db = AccountDBHelper.getInstance().getReadableDatabase();
        db.delete(Table, conditionId + " =?", new String[]{condition});
        close();
    }

    public void deleteAllItemInTable(String Table) {
        db = AccountDBHelper.getInstance().getReadableDatabase();
        db.delete(Table, null, null);
        close();
    }

    /*public void deleteItemList(String Table, List<String> itemList) {
        if (itemList == null) {
            return;
        }
        db = AccountDBHelper.getInstance().getReadableDatabase();
        for (int i = 0; i < itemList.size(); i++) {
            db.delete(AccountDBHelper.Q_SHOPPING_CART_TABLE, ShoppingCartBean.KEY_PRODUCT_ID + " =?", new String[]{itemList.get(i)});
        }
        close();
    }*/

    /**
     * 修改购物车中某件商品的信息
     *
     * @param productID 规格ID
     * @param num       商品数量
     */
    public void updateGoodsNum(String productID, String num) {
        if (productID == null || "".equals(productID) || num == null || "".equals(num)) {
            return;
        }
        db = AccountDBHelper.getInstance().getReadableDatabase();
        ContentValues values = new ContentValues();
        if (!"".equals(productID) && !"".equals(num)) {
            values.put(ShoppingCartBean.KEY_NUM, num);
            db.update(AccountDBHelper.Q_SHOPPING_CART_TABLE, values, ShoppingCartBean.KEY_PRODUCT_ID + "=?", new String[]{productID});
        }
        close();
    }

    public void updateAddress(String whereArg, AddressBean updateItems) {
        if (whereArg == null || "".equals(whereArg) || updateItems == null) {
            return;
        }
        db = AccountDBHelper.getInstance().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppConstants.NAME_FOR_ADDRESS, updateItems.getName());
        values.put(AppConstants.PHONE_FOR_ADDRESS, updateItems.getPhone());
        values.put(AppConstants.ADDRESS_CONTENT, updateItems.getAddress());
        db.update(AccountDBHelper.Q_ADDRESS_TABLE, values, AppConstants.ADDRESS_CREATE_TIME + "=?",
                new String[]{whereArg});
        close();
    }

    public String getNumByProductID(String productID) {
        if (productID == null) {
            return "1";
        }
        db = AccountDBHelper.getInstance().getReadableDatabase();
        cursor = db.query(AccountDBHelper.Q_SHOPPING_CART_TABLE, new String[]{ShoppingCartBean.KEY_NUM}, ShoppingCartBean.KEY_PRODUCT_ID + "=?", new String[]{productID}, null, null, null);
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        close();
        return "1";
    }

    /**
     * 查询数据库中的购物车中的商品信息
     *
     * @return 购物车中的商品信息
     */
    public List<String> getProductList() {
        db = AccountDBHelper.getInstance().getReadableDatabase();
        List<String> mList = new ArrayList<>();
        Cursor cursor = db.query(AccountDBHelper.Q_SHOPPING_CART_TABLE,
                new String[]{ShoppingCartBean.KEY_PRODUCT_ID},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String productID = cursor.getString(0);
                if (productID != null && !"".equals(productID)) {
                    mList.add(productID);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return mList;
    }

    public List<AddressLocalBean> getAddressList() {
        List<AddressLocalBean> addressBeanList = new ArrayList<>();
        String[] columns = new String[]{
                AppConstants.ADDRESS_CREATE_TIME,
                AppConstants.NAME_FOR_ADDRESS,
                AppConstants.PHONE_FOR_ADDRESS,
                AppConstants.ADDRESS_CONTENT};
        String selection = AppConstants.ADDRESS_CREATE_TIME + "=?";
        db = AccountDBHelper.getInstance().getReadableDatabase();
        cursor = db.query(AccountDBHelper.Q_ADDRESS_TABLE, columns, selection, null, null,
                null, null);
        if (cursor.moveToFirst()) {
            do {
                AddressLocalBean addressBean = new AddressLocalBean();
                String time = cursor.getString(0);
                String Name = cursor.getString(1);
                String phone = cursor.getString(2);
                String address = cursor.getString(3);
                if (time != null && !"".equals(time)) {
                    addressBean.setName(time);
                }
                if (Name != null && !"".equals(Name)) {
                    addressBean.setName(Name);
                }
                if (phone != null && !"".equals(phone)) {
                    addressBean.setName(phone);
                }
                if (address != null && !"".equals(address)) {
                    addressBean.setName(address);
                }
                addressBeanList.add(addressBean);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return addressBeanList;
    }

    public List<String> queryHistory(String searchContent) {
        if (searchContent == null) {
            return null;
        }
        List<String> mList = new ArrayList<>();
        db = AccountDBHelper.getInstance().getReadableDatabase();
        cursor = db.query(AccountDBHelper.Q_SEARCH_HISTORY_TABLE, new String[]{"searched"}, "searched like ?",
                new String[]{"%" + searchContent + "%"}, null, null, null);
        if (cursor.moveToLast()) {
            do {
                String historyContent = cursor.getString(0);
                if (historyContent != null && !"".equals(historyContent)) {
                    mList.add(historyContent);
                }
            } while (cursor.moveToPrevious());
        }
        cursor.close();
        return mList;
    }

    public List<String> getSearchHistoryList() {
        db = AccountDBHelper.getInstance().getReadableDatabase();
        List<String> mList = new ArrayList<>();
        Cursor cursor = db.query(AccountDBHelper.Q_SEARCH_HISTORY_TABLE,
                new String[]{"searched"},
                null, null, null, null, null);
        if (cursor.moveToLast()) {
            do {
                String productID = cursor.getString(0);
                if (productID != null && !"".equals(productID)) {
                    mList.add(productID);
                }
            } while (cursor.moveToPrevious());
        }
        cursor.close();
        return mList;
    }

    public void saveSearchHistory(String historyContent) {
        if (historyContent == null) {
            return;
        }
        db = AccountDBHelper.getInstance().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("searched", historyContent);
        ToastHelper.getInstance()._toast("插入第" + db.insert(AccountDBHelper.Q_SEARCH_HISTORY_TABLE, null, values));
        close();
    }


}
