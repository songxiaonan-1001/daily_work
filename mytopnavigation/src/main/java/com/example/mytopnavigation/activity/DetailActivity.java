package com.example.mytopnavigation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytopnavigation.R;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //获取点击列表的name
        name = getIntent().getStringExtra("name");
        initView();
    }

    private void initView() {
        //返回键<
        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setVisibility(View.VISIBLE);//显示
        ivReturn.setOnClickListener(this);
        //名字
        TextView txtWeChat = findViewById(R.id.txt_wechat);
        txtWeChat.setText(name);
        //选项菜单
        ImageView ivOption = findViewById(R.id.iv_option);
        ivOption.setVisibility(View.VISIBLE);//显示
        ivOption.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.iv_option:
                Toast.makeText(this, "分享功能", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
