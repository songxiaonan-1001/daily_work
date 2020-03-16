package com.example.exam_shop.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.exam_shop.R;
import com.example.exam_shop.adapter.RecyclerAdapter;
import com.example.exam_shop.bean.HomeBean;
import com.example.exam_shop.presenters.HomePresenter;
import com.example.exam_shop.view.HomeView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeView, RecyclerAdapter.OnItemClick {

    private RecyclerView mRecyclerHome;
    private View inflate;
    private RecyclerAdapter recyclerAdapter;
    private List<HomeBean.DataBeanX.DataBean> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView();//找到页面控件
        initRecy();//初始化RecyclerView
        initData();//初始化数据
        return inflate;
    }

    private void initRecy() {
        //创建并设置瀑布流布局管理器
        mRecyclerHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        //添加间隔线
        mRecyclerHome.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        //准备集合数据
        list = new ArrayList<>();
        //创建适配器
        recyclerAdapter = new RecyclerAdapter(getActivity(), list);
        //设置适配器
        mRecyclerHome.setAdapter(recyclerAdapter);
        //接口回调
        recyclerAdapter.setOnItemClick(this);
    }

    private void initView() {
        mRecyclerHome = inflate.findViewById(R.id.recycler_home);
    }

    private void initData() {
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.getData();
    }

    @Override
    public void setData(HomeBean homeBean) {
        List<HomeBean.DataBeanX.DataBean> results = homeBean.getData().getData();
        //把数据添加到集合中
        list.addAll(results);
        //刷新适配器
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        Log.e("tag", "showToast: "+message);
    }

    //接口回调
    @Override
    public void onItemClick(int position) {
        //收藏
    }
}
