package com.example.rhg.outsourcing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.rhg.outsourcing.Constants.AppConstants;
import com.example.rhg.outsourcing.View.BaseView;
import com.example.rhg.outsourcing.presenter.TestPresenter;
import com.example.rhg.outsourcing.ui.HomeController;
import com.example.rhg.outsourcing.utils.ImageUtils;
import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.lapism.searchview.adapter.SearchAdapter;
import com.lapism.searchview.adapter.SearchItem;
import com.lapism.searchview.history.SearchHistoryTable;
import com.lapism.searchview.view.SearchCodes;
import com.lapism.searchview.view.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseView, OnClickListener
//        implements NavigationView.OnNavigationItemSelectedListener  //TODO slideNavigationView
{
    private final static String TAG = "MainActivity";
    private final static int TABKEY=0;
    HomeController homeController;
    //----------------------for rebound 弹簧效果---------------------------------------------------
    private final BaseSpringSystem mSpringSystem = SpringSystem.create();
    private final ExampleSpringListener exampleSpringListener = new ExampleSpringListener();
    private Spring mScaleSpring;
    //设置弹跳参数，默认为40，7
    private final double TENSION = 100;
    private final double FICTION = 4;
    //---------------------------------------------------------------------------------------------
    //for refresh 用来刷新整个页面，可以动态添加HeadView和FooterView
//    private MaterialRefreshLayout materialRefreshLayout;
    //TODO-------------------------for toolbar setting----------------------------------------------
    private RelativeLayout toolbar;
    ImageButton toolLeftButton;
    ImageButton toolCenterButton;
    LinearLayout toolRightLayout;
    TextView toolLeftText;
    TextView toolCenterText;
    TextView toolSwipeText;
    ImageButton toolRightButton;
    //---------------------------------------------------------------------------------------------

    //for searchView
    private SearchView searchView;
    private SearchHistoryTable msearchHistory;
    private List<SearchItem> mSuggestionsList;
    private TextView textView_search;
    //for banner
    private ConvenientBanner convenientBanner;
    //BottomNavigationBar当前还不能隐藏(private)，修改完bug后可以修改(public)
    private BottomNavigationBar bottomNavigation;
    //底部弹页
    private BottomSheetBehavior behavior;
    //浮动按钮
    private FloatingActionButton fab;

    //---------getdata-----------
    private TestPresenter testPresenter;
    //--------------------------------------
    float x = 0;
    float y = 0;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testPresenter = new TestPresenter(this);

        homeController = new HomeController(this, testPresenter);
        //for toolbar:Note:all settings need to be done before setSupportActionBar;
        //TODO-------------------------toolbar的一些设置---------------------------------------------
        toolbar = (RelativeLayout) findViewById(R.id.toolbar);

        toolCenterButton = (ImageButton)toolbar.findViewById(R.id.toolbarCenterButton);
        toolLeftButton = (ImageButton) toolbar.findViewById(R.id.toolbarLeftButton);
        toolLeftText = (TextView) toolbar.findViewById(R.id.toolLeftTextview);
        toolCenterText = (TextView) toolbar.findViewById(R.id.toolbarCenterView);
        toolRightLayout = (LinearLayout) toolbar.findViewById(R.id.toolbarRightLayout);
        toolSwipeText = (TextView) toolRightLayout.findViewById(R.id.homeSwipeText);
        toolRightButton = (ImageButton) toolRightLayout.findViewById(R.id.toolbarRightButton);
        toolLeftButton.setOnClickListener(this);
        toolRightLayout.setOnClickListener(this);
        toolRightButton.setOnClickListener(this);
        toolCenterText.setOnClickListener(this);
        toolCenterButton.setOnClickListener(this);
        dealToolbarByPosition(0);
        //TODO--------------------------------------------------------------------------------------

        /*toolbar.setTitle("");
        toolbar.setSubtitle("杭州");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO back
            }
        });*/
        //------------------------------------TabLayout---------------------------------------------
//        tabLayout = (TabLayout)findViewById(R.id.tabLayout);

        setupTabLayout();
        //TODO--------------------搜索框的一些配置操作-----------------------------------------------
        msearchHistory = new SearchHistoryTable(this);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setVersion(SearchCodes.VERSION_MENU_ITEM);
        searchView.setStyle(SearchCodes.STYLE_MENU_ITEM_CLASSIC);
        searchView.setTheme(SearchCodes.THEME_LIGHT);
        searchView.setDivider(false);
        searchView.setVoice(false);
        searchView.setAnimationDuration(300);
        searchView.setShadowColor(ContextCompat.getColor(this, R.color.searchShadow));
//        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.hide(false);
                msearchHistory.addItem(new SearchItem(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnSearchViewListener(new SearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                fab.hide();
            }

            @Override
            public void onSearchViewClosed() {
                fab.show();
            }
        });
        mSuggestionsList = new ArrayList<>();
        List<SearchItem> mResultsList = new ArrayList<>();
        SearchAdapter mSearchAdapter = new SearchAdapter(this, mResultsList, mSuggestionsList, SearchCodes.THEME_LIGHT);
        mSearchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                searchView.hide(false);
                TextView textView = (TextView) view.findViewById(R.id.textView_item_text);
                CharSequence text = textView.getText();
                msearchHistory.addItem(new SearchItem(text));
            }
        });
        searchView.setAdapter(mSearchAdapter);
        //TODO--------------------------------------------------------------------------------------
        //TODO-----------------------------弹跳特效--------------------------------------------------
        mScaleSpring = mSpringSystem.createSpring();
        //设置弹跳
        mScaleSpring.setSpringConfig(new SpringConfig(TENSION, FICTION));
//        mScaleSpring.setVelocity(1);
        //可以作为点击事件的效果
        //-----------------------------------------------------------------------------------------

        /*materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                Log.i(TAG, "onRefresh");
                //------------获取数据入口----------------------------------------------------------
                testPresenter.getData();
                //--------------------------------------------------------------------------------
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                Log.i(TAG, "onRefreshLoadMore");
            }

            @Override
            public void onfinish() {
                Log.i(TAG, "onfinish");
                //------------------结束数据获取出口------------------------------------------------

                //--------------------------------------------------------------------------------
            }
        });
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(true);*/
//        materialRefreshLayout.setLoadMore(true);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按钮的变化最大程度，1表示程度最大，0表示程度最小
                        mScaleSpring.setEndValue(0.5);
//                        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
                        //for BottomSheetView
                       /* if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
                            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        else
                            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);*/
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        //按钮的变化最大程度，1表示程度最大，0表示程度最小
                        mScaleSpring.setEndValue(0);
                        break;
                }
                return true;
            }
        });
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                else
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });*/

        //for DrawerLayout
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
        //TODO---------------------底部导航栏=-------------------------------------------------------
        bottomNavigation = (BottomNavigationBar) findViewById(R.id.bottom_navigation);
        bottomNavigation.setMode(BottomNavigationBar.MODE_CLASSIC);
        bottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigation
                .setActiveColor(R.color.colorActiveGreen)
                .setInActiveColor(R.color.colorInActive)
                .setBarBackgroundColor(R.color.colorBackground);
        bottomNavigation.addItem(new BottomNavigationItem(R.drawable.ic_home, R.string.Home))
                .addItem(new BottomNavigationItem(R.drawable.ic_exit, "商家"))
                .addItem(new BottomNavigationItem(R.drawable.ic_user, "我的"))
                .addItem(new BottomNavigationItem(R.drawable.ic_shopping_car, "购物车"))
                .initialise();
        bottomNavigation.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            //当item被选中状态
            @Override
            public void onTabSelected(int position) {
                homeController.showFragment(position);
                dealToolbarByPosition(position);
            }

            //当item不被选中状态
            @Override
            public void onTabUnselected(int position) {

            }

            //当item再次被选中状态
            @Override
            public void onTabReselected(int position) {
            }
        });
        //-----------------------------------------------------------------------------------------

        //for bottomView
        /*final View bottom_view = (View) findViewById(R.id.bottom_view);
        TextView tv = (TextView) bottom_view.findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        behavior = BottomSheetBehavior.from(bottom_view);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                Log.i("RHG", "" + newState);
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                ViewCompat.setScaleY(bottomSheet, slideOffset);
            }
        });*/

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    private void dealToolbarByPosition(int position) {
        //TODO-------------------对图片轮廓进行颜色填充----------------------------------------------
        /*ImageButton imageButton = (ImageButton)toolbar.findViewById(R.id.right_drawable);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_search_white);
        Drawable tintdrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(tintdrawable,getResources().getColor(R.color.colorActiveYellow));
        imageButton.setImageDrawable(tintdrawable);*/
        switch (position) {
            case 0:
                toolbar.setTag(AppConstants.TypeHome);
                toolbar.setBackgroundResource(R.color.white);
                toolCenterButton.setVisibility(View.VISIBLE);
                toolCenterText.setVisibility(View.VISIBLE);
                toolLeftText.setVisibility(View.VISIBLE);
                toolLeftButton.setVisibility(View.VISIBLE);
                toolRightLayout.setVisibility(View.VISIBLE);
                toolSwipeText.setVisibility(View.VISIBLE);
                toolRightButton.setVisibility(View.VISIBLE);
                toolCenterText.setClickable(true);
                ImageUtils.TintFill(toolLeftButton, getResources().getDrawable(R.mipmap.ic_place_white_24dp),
                        getResources().getColor(R.color.colorActiveGreen));

                ImageUtils.TintFill(toolCenterButton, getResources().getDrawable(R.mipmap.ic_search_white),
                        getResources().getColor(R.color.colorActiveGreen));
                ImageUtils.TintFill(toolRightButton, getResources().getDrawable(R.mipmap.ic_search_white),
                    getResources().getColor(R.color.colorActiveGreen));
                toolLeftText.setText("南京");//TODO 根据定位来定
                toolSwipeText.setText("扫一扫");
                toolSwipeText.setTextSize(12);
                toolSwipeText.setTextColor(getResources().getColor(R.color.colorActiveGreen));
                toolCenterText.setText("");
                toolCenterText.setBackgroundDrawable(getResources().getDrawable(R.drawable.searchview_background));
                break;
            case 1:
                toolbar.setTag(AppConstants.TypeSeller);
                toolbar.setBackgroundResource(R.color.white);
                toolCenterButton.setVisibility(View.GONE);
                toolCenterText.setVisibility(View.VISIBLE);
                toolLeftText.setVisibility(View.GONE);
                toolLeftButton.setVisibility(View.GONE);
                toolRightLayout.setVisibility(View.VISIBLE);
                toolSwipeText.setVisibility(View.GONE);
                toolRightButton.setVisibility(View.VISIBLE);
                toolCenterText.setClickable(false);
                toolSwipeText.setTextSize(12);
//                toolLeftButton.setImageDrawable(getResources().getDrawable(R.mipmap.ic_arrow_back_black_24dp));
                toolRightButton.setImageDrawable(getResources().getDrawable(R.mipmap.ic_search_black_24dp));
                toolCenterText.setText(getResources().getString(R.string.allstore));
                toolCenterText.setTextColor(getResources().getColor(R.color.black));
                toolCenterText.setBackgroundResource(R.color.white);
                break;
            case 2:
                toolbar.setTag(AppConstants.TypeMy);
                toolLeftText.setVisibility(View.GONE);
                toolCenterButton.setVisibility(View.GONE);
                toolCenterText.setVisibility(View.GONE);
                toolRightLayout.setVisibility(View.GONE);
                toolLeftButton.setVisibility(View.GONE);
                toolbar.setBackgroundResource(R.color.colorToolbarGreen);
//                toolLeftButton.setImageDrawable(getResources().getDrawable(R.mipmap.ic_arrow_back_black_24dp));
                break;
            case 3:
                toolbar.setTag(AppConstants.TypeShoppingCar);
                toolbar.setBackgroundResource(R.color.colorToolbarGreen);
                toolCenterButton.setVisibility(View.GONE);
                toolCenterText.setVisibility(View.VISIBLE);
                toolLeftText.setVisibility(View.GONE);
                toolLeftButton.setVisibility(View.GONE);
                toolRightLayout.setVisibility(View.VISIBLE);
                toolRightButton.setVisibility(View.GONE);
                toolSwipeText.setVisibility(View.VISIBLE);
                toolCenterText.setClickable(false);
                toolCenterText.setText(getResources().getString(R.string.shoppingcar));
                toolCenterText.setBackgroundResource(R.color.colorActiveGreen);
                toolSwipeText.setText("编辑");
                toolSwipeText.setTextSize(18);
                toolSwipeText.setTextColor(getResources().getColor(R.color.white));
//                toolLeftButton.setImageDrawable(getResources().getDrawable(R.mipmap.ic_arrow_back_black_24dp));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch ((int)toolbar.getTag()){
            case AppConstants.TypeHome:
                switch (v.getId()){
                    case R.id.toolbarLeftButton:
                        doChangeLocation();
                        break;
                    case R.id.toolbarRightLayout:
                        doSwipe();
                        break;
                    case R.id.toolbarCenterView:
                        showSearchViwe();
                        break;
                }
                break;
            case AppConstants.TypeSeller:
                if(v.getId()==R.id.toolbarRightButton)
                    showSearchViwe();
                break;
            case AppConstants.TypeMy:
                break;
            case AppConstants.TypeShoppingCar:
                if(v.getId()==R.id.toolbarRightLayout)
                    Toast.makeText(this,"编辑",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void doSwipe() {
        Toast.makeText(this,"扫一扫",Toast.LENGTH_SHORT).show();
    }

    private void doChangeLocation() {
        Toast.makeText(this,"修改区域",Toast.LENGTH_SHORT).show();
    }


    private void setupTabLayout() {
    }

    //----------------------------单独呼出搜索页面--------------------------------------------------
    private void showSearchViwe() {
        mSuggestionsList.clear();
        mSuggestionsList.addAll(msearchHistory.getAllItems());
        mSuggestionsList.add(new SearchItem("Google"));
        mSuggestionsList.add(new SearchItem("Android"));
        searchView.show(true);
    }
    //--------------------------------------------------------------------------------------------
    //------------------------回调显示统一区--------------------------------------------------------

    @Override
    public void showData(Object o) {
        Log.i(TAG, o.toString());
//        materialRefreshLayout.finishRefresh();
    }


    //---------------------------------------------------------------------------------------------
    private class ExampleSpringListener extends SimpleSpringListener {
        @Override
        public void onSpringUpdate(Spring spring) {
            float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 0.5);
            fab.setScaleX(mappedValue);
            fab.setScaleY(mappedValue);
        }
    }


    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        super.onBackPressed();
    }

    //--------------------------toolbar右上方menu---------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            final Snackbar snackbar = Snackbar.make(fab, item.getTitle(), Snackbar.LENGTH_SHORT);
            snackbar.setAction("Done", new OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            }).show();
            return true;
        }
        /*if (id == R.id.action_search) {
           *//* final Snackbar snackbar = Snackbar.make(fab, item.getTitle(), Snackbar.LENGTH_SHORT);
            snackbar.setAction("Done", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            }).show();*//*
            showSearchViwe();//TODO search

            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
    //--------------------------------------------------------------------------------------------

    /*@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ecit_password) {
            // Handle the camera action
        } else if (id == R.id.nav_persional_info) {

        } else if (id == R.id.nav_info_feedback) {

        } else if (id == R.id.nav_update) {

        } else if (id == R.id.nav_exit) {

        } else if (id == R.id.nav_about_us) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
    @Override
    protected void onResume() {
        super.onResume();
        mScaleSpring.addListener(exampleSpringListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScaleSpring.removeListener(exampleSpringListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "Touch");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = event.getY();
                Log.i(TAG, " " + y);
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = event.getY() - y;
                if (dy < -0.2) {
                    fab.hide();
                    Log.i(TAG, " hide");
                } else {
                    if (!fab.isShown()) {
                        Log.i(TAG, " show");
                        fab.show();
                    }
                }
                y = event.getY();
                break;
        }
        return true;
    }
}
