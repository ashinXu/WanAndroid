package com.ashin.wanandroid.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ashin.wanandroid.BaseActivity;
import com.ashin.wanandroid.BaseFragment;
import com.ashin.wanandroid.R;
import com.ashin.wanandroid.fragment.GuideFragment;
import com.ashin.wanandroid.fragment.HomeFragment;
import com.ashin.wanandroid.fragment.KnowledgeFragment;
import com.ashin.wanandroid.fragment.ProjectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    private ActionBarDrawerToggle mDrawerToggle;
    private List<BaseFragment> fragmentList = new ArrayList<>();
    private int lastIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initPage();
    }

    private void initPage() {
        initFragment();
    }

    private void initView() {

        tvTitle.setText(getString(R.string.homepage));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //为了生成，工具栏左上角的动态图标，要使用下面的方法
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.open,
                R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = drawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_main:
                        loadPage(0);
                        break;
                    case R.id.tab_knowledge:
                        loadPage(1);
                        break;
                    case R.id.tab_guide:
                        loadPage(2);
                        break;
                    case R.id.tab_project:
                        loadPage(3);
                        break;
                }
                return true;
            }
        });

    }

    private void initFragment() {
        HomeFragment homeFragment = HomeFragment.getInstance();
        KnowledgeFragment knowledgeFragment = KnowledgeFragment.getInstance();
        GuideFragment guideFragment = GuideFragment.getInstance();
        ProjectFragment projectFragment = ProjectFragment.getInstance();
        fragmentList.add(homeFragment);
        fragmentList.add(knowledgeFragment);
        fragmentList.add(guideFragment);
        fragmentList.add(projectFragment);
        lastIndex = 0;
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_group, homeFragment).commit();


    }

    private void loadPage(int position) {
        BaseFragment targetFragment = fragmentList.get(position);
        BaseFragment lastFragment = fragmentList.get(lastIndex);
        lastIndex = position;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(lastFragment);
        if (!targetFragment.isAdded()) {
            ft.add(R.id.fragment_group, targetFragment);
        }
        ft.show(targetFragment).commit();
    }
}
