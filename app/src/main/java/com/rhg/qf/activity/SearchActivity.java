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
import com.rhg.qf.adapter.SearchHistoryAdapter;
import com.rhg.qf.bean.RestaurantSearchUrlBean;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.impl.RcvItemClickListener;
import com.rhg.qf.mvp.presenter.HotFoodSearchPresenter;
import com.rhg.qf.mvp.presenter.RestaurantSearchPresenter;
import com.rhg.qf.utils.DpUtil;
import com.rhg.qf.utils.SearchHistoryUtil;
import com.rhg.qf.utils.ToastHelper;
import com.rhg.qf.widget.RecycleViewDivider;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * desc:搜索页面
 * author：remember
 * time：2016/6/18 13:10
 * email：1013773046@qq.com
 */
public class SearchActivity extends BaseFragmentActivity implements RcvItemClickListener,
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
    SearchHistoryAdapter searchHistoryAdapter;
    private List<String> searchHistoryData;
    RestaurantSearchPresenter restaurantSearchPresenter;
    HotFoodSearchPresenter hotFoodSearchPresenter;
    private int searchTag;
    private int searchIndex;

    @Override
    public void dataReceive(Intent intent) {
        searchIndex = intent.getIntExtra(AppConstants.KEY_SEARCH_INDEX, -1);
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecycleViewDivider divider = new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL,
                DpUtil.dip2px(1), getResources().getColor(R.color.colorInActive));

        itemResultsRcv.setHasFixedSize(false);
        itemResultsRcv.setLayoutManager(linearLayoutManager);
        itemResultsRcv.addItemDecoration(divider);

        historyResultsRcv.setLayoutManager(linearLayoutManager);
        historyResultsRcv.setHasFixedSize(false);
        divider.setLeftAndRightPadding(DpUtil.dip2px(16), 0);
        historyResultsRcv.addItemDecoration(divider);
        searchHistoryAdapter = new SearchHistoryAdapter(this, searchHistoryData);
        searchHistoryAdapter.setOnSearchHistoryClickListener(this);
        historyResultsRcv.setAdapter(searchHistoryAdapter);
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
                        tvHistoryResult.setVisibility(View.GONE);
                        tvSearchResult.setVisibility(View.VISIBLE);
                        historyResultsRcv.setVisibility(View.GONE);
                        itemResultsRcv.setVisibility(View.VISIBLE);/*TODO 写到这里*/
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
                    searchHistoryAdapter.setSearchedHistory(null);
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
                searchHistoryAdapter.setSearchedHistory(searchHistoryData);
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
        if (TextUtils.isEmpty(searchEt.getText().toString())) {
            ToastHelper.getInstance()._toast("搜索内容为空");
            return;
        }
        String _str = "";
        try {
            _str = URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        switch (searchTag) {
            case AppConstants.KEY_RESTAURANT_SEARCH:
                if (restaurantSearchPresenter == null)
                    restaurantSearchPresenter = new RestaurantSearchPresenter(this);
                restaurantSearchPresenter.getSearchRestaurant(AppConstants.SEARCHRESTAURANTS, _str, searchIndex);
                break;
            case AppConstants.KEY_HOTFOOD_SEARCH:
                if (hotFoodSearchPresenter == null)
                    hotFoodSearchPresenter = new HotFoodSearchPresenter(this);
                hotFoodSearchPresenter.getSearchHotFood(AppConstants.HOTFOOD, _str, searchIndex);
                break;
        }
    }

    @Override
    protected void showSuccess(Object s) {
        if (s instanceof RestaurantSearchUrlBean.RestaurantSearchBean) {

        } else {

        }
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
