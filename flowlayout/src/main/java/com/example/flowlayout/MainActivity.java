package com.example.flowlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flowlayout.activity.SearchActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtSearch;
    private ImageView mIvPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mEtSearch = findViewById(R.id.et_search);
        mIvPhoto = findViewById(R.id.iv_photo);

        mEtSearch.setOnClickListener(this);
        mIvPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_search:
                goToActivity();
                break;
            case R.id.iv_photo:
                break;
        }
    }

    private void goToActivity() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
