package com.example.flowlayout.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flowlayout.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showKeyBord(etSearch);
    }

    private void initView() {
        ImageView ivReturn = findViewById(R.id.iv_return);
        etSearch = findViewById(R.id.et_search);
        TextView tvSearch = findViewById(R.id.tv_search);
        //设置显示
        ivReturn.setVisibility(View.VISIBLE);
        tvSearch.setVisibility(View.VISIBLE);
        //点击监听
        ivReturn.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return:
                finish();//返回
                break;
            case R.id.tv_search:
                //搜索
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 显示键盘
     *
     * @param searchEt
     */
    public static void showKeyBord(EditText searchEt) {
        InputMethodManager inputManager = (InputMethodManager) searchEt.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(searchEt, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏键盘
     *
     * @param searchEt
     * @param context
     */
    public static void hideKeyBord(EditText searchEt, Activity context) {
        ((InputMethodManager) searchEt.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(context
                                .getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
