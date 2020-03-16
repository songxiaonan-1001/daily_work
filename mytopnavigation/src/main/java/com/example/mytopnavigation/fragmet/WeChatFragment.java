package com.example.mytopnavigation.fragmet;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytopnavigation.R;
import com.example.mytopnavigation.activity.DetailActivity;
import com.example.mytopnavigation.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeChatFragment extends Fragment implements RecyclerAdapter.OnItemClick {

    private RecyclerView mRecyclerWechat;
    private View inflate;
    private RecyclerAdapter recyclerAdapter;
    private List<String> stringList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_we_chat, container, false);
        initView();
        initRecycler();
        return inflate;
    }

    private void initRecycler() {
        //准备数据
        stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("demo" + i);
        }
        //创建适配器
        recyclerAdapter = new RecyclerAdapter(getContext(), stringList);
        //创建并设置布局管理器
        mRecyclerWechat.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置间隔线
        mRecyclerWechat.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        //设置适配器
        mRecyclerWechat.setAdapter(recyclerAdapter);
        //条目的点击监听
        recyclerAdapter.setOnItemClick(this);
    }

    private void initView() {
        mRecyclerWechat = inflate.findViewById(R.id.recycler_wechat);
    }

    //接口回调
    @Override
    public void onItemClick(int position) {
        Log.i("tag", "onItemClick: 点击条目" + position);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        startActivity(intent);
    }
}
