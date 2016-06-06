package com.example.rhg.outsourcing.utils;

import com.example.rhg.outsourcing.bean.AddressBean;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.datebase.AccountDBHelper;
import com.example.rhg.outsourcing.datebase.AccountDao;

import java.util.List;

/**
 * 作者：rememberon 2016/6/5
 * 邮箱：1013773046@qq.com
 */
public class AddressUtil {
    /**
     * desc:插入保存地址
     * author：remember
     * time：2016/6/5 13:45
     * email：1013773046@qq.com
     */
    public void insertAddress(AddressBean addressBean) {
        AccountDao.getInstance().saveAddress(addressBean);
    }

    /**
     * desc:删除数据库中的一条地址
     * author：remember
     * time：2016/6/5 11:56
     * email：1013773046@qq.com
     */
    public void deleteOneAddress(String whereArg) {
        AccountDao.getInstance().deleteItemInTableById(AccountDBHelper.Q_ADDRESS_TABLE, AppConstants.ADDRESS_CREATE_TIME,
                whereArg);
    }

    /**
     * desc:删除数据库中所有的地址
     * author：remember
     * time：2016/6/5 11:58
     * email：1013773046@qq.com
     */
    public void deleteAllAddress() {
        AccountDao.getInstance().deleteAllItemInTable(AccountDBHelper.Q_ADDRESS_TABLE);
    }

    /**
     * desc:更新数据库一条地址
     * author：remember
     * time：2016/6/5 12:04
     * email：1013773046@qq.com
     */
    public void updateAddress(String whereArg, AddressBean addressBean) {
        AccountDao.getInstance().updateAddress(whereArg, addressBean);
    }

    /**
     * desc:获取地址列表
     * author：remember
     * time：2016/6/5 12:05
     * email：1013773046@qq.com
     */
    public List<AddressBean> getAddressList() {
        return AccountDao.getInstance().getAddressList();
    }
}
