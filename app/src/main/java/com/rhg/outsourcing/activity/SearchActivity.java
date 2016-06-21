package com.rhg.outsourcing.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rhg.outsourcing.R;
import com.rhg.outsourcing.apapter.SearchHistoryAdapter;
import com.rhg.outsourcing.utils.DpUtil;
import com.rhg.outsourcing.utils.SearchHistoryUtil;
import com.rhg.outsourcing.widget.RecycleViewDivider;

import java.util.List;

/**
 * desc:搜索页面
 * author：remember
 * time：2016/6/18 13:10
 * email：1013773046@qq.com
 */
public class SearchActivity extends BaseActivity implements SearchHistoryAdapter.SearchHistoryClickListener {
    //    SearchView searchView;
    FrameLayout flTab;
    ImageView ivLeft;
    EditText etSearch;
    TextView tvHistory;
    TextView tvResult;

    RecyclerView searchHistoryRcv;
    SearchHistoryAdapter searchHistoryAdapter;
    private List<String> searchHistoryData;

    @Override
    public void loadingData() {
        searchHistoryData = SearchHistoryUtil.getAllHistory();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.search_layout;
    }

    @Override
    protected void initView(View view) {
        flTab = (FrameLayout) findViewById(R.id.fl_tab);
        ivLeft = (ImageView) findViewById(R.id.tb_left_iv);
        etSearch = (EditText) findViewById(R.id.search_et);
        tvHistory = (TextView) findViewById(R.id.tv_history_result);
        tvResult = (TextView) findViewById(R.id.tv_search_result);
        searchHistoryRcv = (RecyclerView) findViewById(R.id.searchHistoryRcv);
    }


    @Override
    protected void initData() {
        flTab.setBackgroundColor(getResources().getColor(R.color.colorActiveGreen));
        ivLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));
        ivLeft.setOnClickListener(this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        searchHistoryRcv.setHasFixedSize(false);
        searchHistoryRcv.setLayoutManager(lm);
        RecycleViewDivider divider = new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL,
                DpUtil.dip2px(1), getResources().getColor(R.color.colorInActive));
        divider.setLeftAndRightPadding(DpUtil.dip2px(16), 0);
        searchHistoryRcv.addItemDecoration(divider);
        searchHistoryAdapter = new SearchHistoryAdapter(this, searchHistoryData);
        searchHistoryAdapter.setOnSearchHistoryClickListener(this);
        searchHistoryRcv.setAdapter(searchHistoryAdapter);
        etSearch.setVisibility(View.VISIBLE);
        etSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (etSearch.getCompoundDrawables()[2] == null)
                    return false;
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return true;
                }
                if (event.getRawX() > etSearch.getWidth() -
                        etSearch.getCompoundDrawables()[2].getBounds().width()) {
                    if (!TextUtils.isEmpty(etSearch.getText().toString().trim())
                            && searchHistoryData.size() == 0)
                        SearchHistoryUtil.insertSearchHistory(etSearch.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });
        tvHistory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tvHistory.getCompoundDrawables()[2] == null)
                    return false;
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return true;
                }
                if (event.getRawX() > tvHistory.getWidth() -
                        tvHistory.getCompoundDrawables()[2].getBounds().width()) {
                    SearchHistoryUtil.deleteAllHistory();
                    searchHistoryAdapter.setSearchHistory(null);
                    return true;
                }
                return false;
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                searchHistoryData.clear();
                if (s.toString().trim().length() != 0) {
                    searchHistoryData = SearchHistoryUtil.getHistoryByName(s.toString().trim());
                } else {
                    searchHistoryData = SearchHistoryUtil.getAllHistory();
                }
                searchHistoryAdapter.setSearchHistory(searchHistoryData);
            }
        });
    }


    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
        }
    }

    @Override
    public void onSearchItemClick(int position) {

    }

}
