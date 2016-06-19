package com.example.rhg.outsourcing.utils;

import com.example.rhg.outsourcing.datebase.AccountDBHelper;
import com.example.rhg.outsourcing.datebase.AccountDao;

import java.util.List;

/**
 * 作者：rememberon 2016/6/18
 * 邮箱：1013773046@qq.com
 */
public class SearchHistoryUtil {

    /**
     * desc:插入一条历史记录
     * author：remember
     * time：2016/6/18 16:36
     * email：1013773046@qq.com
     */
    public static void insertSearchHistory(String content) {
        AccountDao.getInstance().saveSearchHistory(content);
    }

    /**
     * desc:清空历史记录
     * author：remember
     * time：2016/6/18 16:37
     * email：1013773046@qq.com
     */
    public static void deleteAllHistory() {
        AccountDao.getInstance().deleteAllItemInTable(AccountDBHelper.Q_SEARCH_HISTORY_TABLE);
    }

    /**
     * desc:获取历史记录
     * author：remember
     * time：2016/6/18 16:37
     * email：1013773046@qq.com
     */
    public static List<String> getAllHistory() {
        return AccountDao.getInstance().getSearchHistoryList();
    }

    public static List<String> getHistoryByName(String name) {
        return AccountDao.getInstance().queryHistory(name);
    }
}
