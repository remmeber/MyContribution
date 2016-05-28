package com.example.rhg.outsourcing;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.Fragment;
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
import com.example.rhg.outsourcing.activity.BaseActivity;
import com.example.rhg.outsourcing.application.InitApplication;
import com.example.rhg.outsourcing.constants.AppConstants;
import com.example.rhg.outsourcing.fragment.HomeFragment;
import com.example.rhg.outsourcing.fragment.MyFragment;
import com.example.rhg.outsourcing.fragment.SellerFragment;
import com.example.rhg.outsourcing.fragment.ShoppingCartFragment;
import com.example.rhg.outsourcing.impl.SearchListener;
import com.example.rhg.outsourcing.locationservice.LocationService;
import com.example.rhg.outsourcing.locationservice.MyLocationListener;
import com.example.rhg.outsourcing.ui.FragmentController;
import com.example.rhg.outsourcing.mvp.view.BaseView;
import com.example.rhg.outsourcing.mvp.presenter.TestPresenter;
import com.example.rhg.outsourcing.utils.ImageUtils;
import com.example.rhg.outsourcing.utils.ToastHelper;
import com.lapism.searchview.adapter.SearchAdapter;
import com.lapism.searchview.adapter.SearchItem;
import com.lapism.searchview.history.SearchHistoryTable;
import com.lapism.searchview.view.SearchCodes;
import com.lapism.searchview.view.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BaseView,SearchListener
//        implements NavigationView.OnNavigationItemSelectedListener  //TODO slideNavigationView
{
    private final static String TAG = "MainActivity";
    FragmentController fragmentController;
    //----------------------for rebound 弹簧效果---------------------------------------------------
    /*private final BaseSpringSystem mSpringSystem = SpringSystem.create();
    private final ExampleSpringListener exampleSpringListener = new ExampleSpringListener();
    private Spring mScaleSpring;
    //设置弹跳参数，默认为40，7
    private final double TENSION = 100;
    private final double FICTION = 4;*/
    //---------------------------------------------------------------------------------------------
    //for refresh 用来刷新整个页面，可以动态添加HeadView和FooterView
//    private MaterialRefreshLayout materialRefreshLayout;
    /*//TODO-------------------------for toolbar setting----------------------------------------------
    private RelativeLayout toolbar;
    ImageButton toolLeftButton;
    ImageButton toolCenterButton;
    LinearLayout toolRightLayout;
    TextView toolLeftText;
    TextView toolCenterText;
    TextView toolSwipeText;
    ImageButton toolRightButton;*/
    //---------------------------------------------------------------------------------------------

    //for searchView
    private SearchView searchView;
    private SearchHistoryTable msearchHistory;
    private List<SearchItem> mSuggestionsList;
    private TextView textView_search;
    //for banner
    private ConvenientBanner convenientBanner;
    //BottomNavigationBar
    BottomNavigationBar bottomNavigation;
    //---------getdata-----------
    private TestPresenter testPresenter;
    //--------------------------------------
    float x = 0;
    float y = 0;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    /*@Override
    public LocationService GetMapService() {
        return ((InitApplication) getApplication()).locationService;
    }

    @Override
    public void getLocation(LocationService locationService, MyLocationListener mLocationListener) {
        mLocationListener.getLocation(locationService);
    }

    @Override
    public MyLocationListener getLocationListener() {
        return new MyLocationListener(this);
    }*/

    @Override
    protected void initView() {
        searchView = (SearchView) findViewById(R.id.searchView);
        bottomNavigation = (BottomNavigationBar) findViewById(R.id.bottom_navigation);
    }

    @Override
    protected void initData() {
        testPresenter = new TestPresenter(this);

        Fragment[] fragments = new Fragment[4];
//        fragments[0] = new HomeFragment();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setSearchListener(this);
        fragments[0] = homeFragment;
        fragments[1] = new SellerFragment();
        fragments[2] = new MyFragment();
        fragments[3] = new ShoppingCartFragment();
        fragmentController = new FragmentController(getSupportFragmentManager(), testPresenter,
                fragments, R.id.content_fragment);
        //TODO--------------------------------------------------------------------------------------

        //TODO--------------------搜索框的一些配置操作-----------------------------------------------
        msearchHistory = new SearchHistoryTable(this);
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
//                fab.hide();
                //TODO show searchView
            }

            @Override
            public void onSearchViewClosed() {
//                fab.show();
                //TODO close searchView
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

        //TODO---------------------底部导航栏=-------------------------------------------------------

        bottomNavigation.setMode(BottomNavigationBar.MODE_CLASSIC);
        bottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigation
                .setActiveColor(R.color.colorActiveGreen)
                .setInActiveColor(R.color.colorInActive)
                .setBarBackgroundColor(R.color.colorBackground);
        bottomNavigation.addItem(new BottomNavigationItem(R.drawable.ic_home, R.string.Home))
                .addItem(new BottomNavigationItem(R.mipmap.ic_shop, "商家"))
                .addItem(new BottomNavigationItem(R.drawable.ic_user, "我的"))
                .addItem(new BottomNavigationItem(R.drawable.ic_shopping_car, "购物车"))
                .initialise();
        bottomNavigation.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            //当item被选中状态
            @Override
            public void onTabSelected(int position) {
                fragmentController.showFragment(position);
//                dealToolbarByPosition(position);
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
    }



    //TODO 定位
    private void doChangeLocation() {
        if (AppConstants.DEBUG)
            Toast.makeText(this, "修改区域", Toast.LENGTH_SHORT).show();
        reStartLocation();
    }

    /*数据回调*/
    @Override
    protected void showSuccess(Object s) {
        if(AppConstants.DEBUG)
            ToastHelper.getInstance()._toast(s.toString());
    }

    @Override
    protected void showError(Object s) {

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AppConstants.BACK_WITH_DELETE) {//// TODO: 商品详情返回购物车
            bottomNavigation.selectTab(data.getExtras().getInt(AppConstants.KEY_DELETE, 0), true);
        }
        super.onActivityResult(requestCode, resultCode, data);
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

    //--------------------------------------------------------------------------------------------


    @Override
    protected void onResume() {
        super.onResume();
//        mScaleSpring.addListener(exampleSpringListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mScaleSpring.removeListener(exampleSpringListener);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void doSearch() {
        showSearchViwe();
    }
}

/*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //for toolbar:Note:all settings need to be done before setSupportActionBar;


        *//*toolbar.setTitle("");
        toolbar.setSubtitle("杭州");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO back
            }
        });*//*
        //------------------------------------TabLayout---------------------------------------------
//        tabLayout = (TabLayout)findViewById(R.id.tabLayout);





        //TODO-----------------------------弹跳特效--------------------------------------------------
       *//* mScaleSpring = mSpringSystem.createSpring();
        //设置弹跳
        mScaleSpring.setSpringConfig(new SpringConfig(TENSION, FICTION));*//*
//        mScaleSpring.setVelocity(1);
        //可以作为点击事件的效果
        //-----------------------------------------------------------------------------------------

        *//*materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
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
        materialRefreshLayout.setWaveShow(true);*//*
//        materialRefreshLayout.setLoadMore(true);

       *//* fab = (FloatingActionButton) findViewById(R.id.fab);
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
                       *//**//* if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
                            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        else
                            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);*//**//*
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        //按钮的变化最大程度，1表示程度最大，0表示程度最小
                        mScaleSpring.setEndValue(0);
                        break;
                }
                return true;
            }
        });*//*
        *//*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                else
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });*//*

        //for DrawerLayout
        *//*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*//*


        //for bottomView
        *//*final View bottom_view = (View) findViewById(R.id.bottom_view);
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
        });*//*

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }
     //TODO for spring-------------------------------------------------------------------------------
    private class ExampleSpringListener extends SimpleSpringListener {
        @Override
        public void onSpringUpdate(Spring spring) {
            float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 0.5);
            fab.setScaleX(mappedValue);
            fab.setScaleY(mappedValue);
        }
    }
    */

