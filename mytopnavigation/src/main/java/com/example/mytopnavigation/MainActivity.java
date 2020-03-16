package com.example.mytopnavigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.mytopnavigation.adapter.ViewPagerAdapter;
import com.example.mytopnavigation.fragmet.AddressBookFragment;
import com.example.mytopnavigation.fragmet.FindFragment;
import com.example.mytopnavigation.fragmet.MeFragment;
import com.example.mytopnavigation.fragmet.WeChatFragment;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ImageView mIvReturn;
    private ImageView mIvOption;
    private ImageView mIvOptionVertical;
    private ImageView mIvPerson;
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private TextView txt_wechat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化界面
        initContent();//初始化内容
    }

    private void initContent() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new WeChatFragment());
        fragmentList.add(new AddressBookFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new MeFragment());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(viewPagerAdapter);

        mTablayout.addTab(mTablayout.newTab().setText(R.string.wechat).setIcon(R.drawable.selector_wechat));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.address_book).setIcon(R.drawable.selector_address_book));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.find).setIcon(R.drawable.selector_find));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.me).setIcon(R.drawable.selector_me));
        mTablayout.setupWithViewPager(mViewPager);
        mTablayout.getTabAt(0).setText(R.string.wechat).setIcon(R.drawable.selector_wechat);
        mTablayout.getTabAt(1).setText(R.string.address_book).setIcon(R.drawable.selector_address_book);
        mTablayout.getTabAt(2).setText(R.string.find).setIcon(R.drawable.selector_find);
        mTablayout.getTabAt(3).setText(R.string.me).setIcon(R.drawable.selector_me);

        //viewpager的滑动监听
        mViewPager.addOnPageChangeListener(this);
        //tablayout的点击监听
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中时触发
                txt_wechat.setText(tab.getText());
                //Log.i("tag", "onTabSelected: 点击了"+tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中时触发
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //选中后再次点击即复选时触发
            }
        });
    }

    private void initView() {
        mIvReturn = (ImageView) findViewById(R.id.iv_return);
        mIvOption = (ImageView) findViewById(R.id.iv_option);
        mIvOptionVertical = (ImageView) findViewById(R.id.iv_option_vertical);
        mIvPerson = (ImageView) findViewById(R.id.iv_person);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);

        View view = LayoutInflater.from(this).inflate(R.layout.mytitlebar, null);
        txt_wechat = view.findViewById(R.id.txt_wechat);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                txt_wechat.setText(R.string.wechat);
                break;
            case 1:
                txt_wechat.setText(R.string.address_book);
                break;
            case 2:
                txt_wechat.setText(R.string.find);
                break;
            case 3:
                txt_wechat.setText(R.string.me);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
