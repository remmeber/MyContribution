package com.rhg.qf;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.rhg.qf.activity.BaseFragmentActivity;
import com.rhg.qf.constants.AppConstants;
import com.rhg.qf.fragment.HomeFragment;
import com.rhg.qf.fragment.MyFragment;
import com.rhg.qf.fragment.SellerFragment;
import com.rhg.qf.fragment.ShoppingCartFragment;
import com.rhg.qf.mvp.view.BaseView;
import com.rhg.qf.ui.FragmentController;

import java.util.ArrayList;
import java.util.List;

/*http://www.biaonimeia.com/sharelink/privateproject/u1hy06pJRJkvjBQ*/
public class MainActivity extends BaseFragmentActivity implements BaseView
//        implements NavigationView.OnNavigationItemSelectedListener  //TODO slideNavigationView
{
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
   /* private SearchView searchView;
    private SearchHistoryTable mSearchHistory;
    private List<SearchItem> mSuggestionsList;*/
    //BottomNavigationBar
    BottomNavigationBar bottomNavigation;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(View view) {
        bottomNavigation = (BottomNavigationBar) findViewById(R.id.bottom_navigation);
    }

    @Override
    protected void initData() {
//        loadRootFragment(R.id.content_fragment, HomeFragment.newInstance());
        List<Fragment> fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        fragments.add(homeFragment);
        fragments.add(new SellerFragment());
        fragments.add(new MyFragment());
        fragments.add(new ShoppingCartFragment());
        fragmentController = new FragmentController(getSupportFragmentManager(),
                fragments, R.id.content_fragment);

        //TODO---------------------底部导航栏=-------------------------------------------------------

        bottomNavigation.setMode(BottomNavigationBar.MODE_CLASSIC);
        bottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigation
                .setActiveColor(R.color.colorBlueNormal)
                .setInActiveColor(R.color.colorInActive)
                .setBarBackgroundColor(R.color.colorBackground);
        bottomNavigation.addItem(new BottomNavigationItem(R.drawable.ic_home, R.string.Home))
                .addItem(new BottomNavigationItem(R.drawable.ic_shop, R.string.Merchant))
                .addItem(new BottomNavigationItem(R.drawable.ic_user, R.string.User))
                .addItem(new BottomNavigationItem(R.drawable.ic_shopping_cart_black, R.string.shoppingCart))
                .initialise();
        bottomNavigation.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            //当item被选中状态
            @Override
            public void onTabSelected(int position) {
                fragmentController.showFragment(position);
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

    /*数据回调*/
    @Override
    protected void showSuccess(Object s) {
    }

    @Override
    protected void showError(Object s) {
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("RHG", "resultCode: " + resultCode + "data");
        if (resultCode == AppConstants.BACK_WITH_DELETE) {//// TODO: 商品详情返回购物车
            bottomNavigation.selectTab(data.getExtras().getInt(AppConstants.KEY_DELETE, 0), true);
            return;
        }
        fragmentController.getCurrentFM().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    //--------------------------------------------------------------------------------------------


}