package com.example.exam_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.exam_shop.adapter.ViewPagerAdapter;
import com.example.exam_shop.fragment.HomeFragment;
import com.example.exam_shop.fragment.MeFragment;
import com.example.exam_shop.fragment.ShoppingFragment;
import com.example.exam_shop.fragment.SortFragment;
import com.example.exam_shop.fragment.TopicFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewpagerMain;
    private TabLayout mTablayoutMain;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化布局
        initContent();//初始化主内容区域
    }

    private void initContent() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new TopicFragment());
        fragmentList.add(new SortFragment());
        fragmentList.add(new ShoppingFragment());
        fragmentList.add(new MeFragment());
        //创建适配器
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        //设置适配器
        mViewpagerMain.setAdapter(viewPagerAdapter);
        //设置tablayout
        mTablayoutMain.addTab(mTablayoutMain.newTab().setText(R.string.home).setIcon(R.drawable.selector_home));
        mTablayoutMain.addTab(mTablayoutMain.newTab().setText(R.string.topic).setIcon(R.drawable.selector_topic));
        mTablayoutMain.addTab(mTablayoutMain.newTab().setText(R.string.sort).setIcon(R.drawable.selector_sort));
        mTablayoutMain.addTab(mTablayoutMain.newTab().setText(R.string.shopping).setIcon(R.drawable.selector_shopping));
        mTablayoutMain.addTab(mTablayoutMain.newTab().setText(R.string.me).setIcon(R.drawable.selector_me));
        mTablayoutMain.setupWithViewPager(mViewpagerMain);
        mTablayoutMain.getTabAt(0).setText(R.string.home).setIcon(R.drawable.selector_home);
        mTablayoutMain.getTabAt(1).setText(R.string.topic).setIcon(R.drawable.selector_topic);
        mTablayoutMain.getTabAt(2).setText(R.string.sort).setIcon(R.drawable.selector_sort);
        mTablayoutMain.getTabAt(3).setText(R.string.shopping).setIcon(R.drawable.selector_shopping);
        mTablayoutMain.getTabAt(4).setText(R.string.me).setIcon(R.drawable.selector_me);
    }

    private void initView() {
        mViewpagerMain = (ViewPager) findViewById(R.id.viewpager_main);
        mTablayoutMain = (TabLayout) findViewById(R.id.tablayout);
    }
}
