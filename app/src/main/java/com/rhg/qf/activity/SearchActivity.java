package com.rhg.qf.activity;

import android.content.Intent;
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

import com.rhg.qf.R;
import com.rhg.qf.apapter.SearchAdapter;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.impl.RcvItemClickListener;
import com.rhg.qf.utils.DpUtil;
import com.rhg.qf.utils.SearchHistoryUtil;
import com.rhg.qf.utils.ToastHelper;
import com.rhg.qf.widget.RecycleViewDivider;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * desc:搜索页面
 * author：remember
 * time：2016/6/18 13:10
 * email：1013773046@qq.com
 */
public class SearchActivity extends BaseActivity implements RcvItemClickListener,
        View.OnClickListener {

    @Bind(R.id.tb_left_iv)
    ImageView tbLeftIv;
    @Bind(R.id.search_et)
    EditText searchEt;
    @Bind(R.id.fl_tab)
    FrameLayout flTab;
    @Bind(R.id.tv_history_result)
    TextView tvHistoryResult;
    @Bind(R.id.tv_search_result)
    TextView tvSearchResult;
    @Bind(R.id.historyResultsRcv)
    RecyclerView historyResultsRcv;
    @Bind(R.id.itemResultsRcv)
    RecyclerView itemResultsRcv;
    SearchAdapter searchAdapter;
    private List<String> searchHistoryData;
    private int searchTag;

    @Override
    public void dataReceive(Intent intent) {
        searchTag = intent.getIntExtra(AppConstants.KEY_SEARCH_TAG, -1);
    }

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
    }


    @Override
    protected void initData() {
        flTab.setBackgroundColor(getResources().getColor(R.color.colorGreenNormal));
        tbLeftIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_chevron_left_black));

        historyResultsRcv.setLayoutManager(new LinearLayoutManager(this));
        historyResultsRcv.setHasFixedSize(false);
        RecycleViewDivider divider = new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL,
                DpUtil.dip2px(1), getResources().getColor(R.color.colorInActive));
        divider.setLeftAndRightPadding(DpUtil.dip2px(16), 0);
        historyResultsRcv.addItemDecoration(divider);
        searchAdapter = new SearchAdapter(this, searchHistoryData);
        searchAdapter.setOnSearchHistoryClickListener(this);
        historyResultsRcv.setAdapter(searchAdapter);
        searchEt.setVisibility(View.VISIBLE);
        searchEt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (searchEt.getCompoundDrawables()[2] == null)
                    return false;
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return true;
                }
                if (event.getRawX() > searchEt.getWidth() -
                        searchEt.getCompoundDrawables()[2].getBounds().width()) {
                    if (!TextUtils.isEmpty(searchEt.getText().toString().trim())
                            && searchHistoryData.size() == 0) {
                        SearchHistoryUtil.insertSearchHistory(searchEt.getText().toString().trim());
                        doSearch(searchEt.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
        /*清空搜索历史*/
        tvHistoryResult.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tvHistoryResult.getCompoundDrawables()[2] == null)
                    return false;
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return true;
                }
                if (event.getRawX() > tvHistoryResult.getWidth() -
                        tvHistoryResult.getCompoundDrawables()[2].getBounds().width()) {
                    SearchHistoryUtil.deleteAllHistory();
                    searchAdapter.setSearchHistory(null);
                    return true;
                }
                return false;
            }
        });
        searchEt.addTextChangedListener(new TextWatcher() {
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
                searchAdapter.setSearchHistory(searchHistoryData);
            }
        });
    }

    /*
     *desc 搜索业务
     *author rhg
     *time 2016/7/6 21:29
     *email 1013773046@qq.com
     */
    private void doSearch(String s) {

    }


    @Override
    protected void showSuccess(Object s) {

    }

    @Override
    protected void showError(Object s) {

    }

    @OnClick({R.id.tb_left_iv, R.id.tb_right_iv})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb_left_iv:
                finish();
                break;
            case R.id.tb_right_iv:
                break;
        }
    }

    @Override
    public void onItemClickListener(int position, Object item) {
        if (item instanceof String) {
            searchEt.setText((String) item);
            historyResultsRcv.setVisibility(View.GONE);
            tvHistoryResult.setVisibility(View.GONE);
            itemResultsRcv.setVisibility(View.VISIBLE);
            tvSearchResult.setVisibility(View.VISIBLE);
            ToastHelper.getInstance()._toast("搜索");
        }
    }
}
