package com.example.mytopnavigation.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mytopnavigation.R;

/**
 * 自定义标题栏的类
 */
public class MyTitleBar extends ConstraintLayout implements View.OnClickListener {

    private boolean shared_visible;//自定义属性是否显示分享按钮
    private String title;//自定义属性标题的内容
    private ImageView imgReturn;
    private TextView txtTitle;
    private ImageView ivOption;

    Context context;
    AppCompatActivity activity;

    //设置activity
    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    private ITitleClick clickFun; //接口回调

    //设置接口回调
    public void setClickFun(ITitleClick clickFun) {
        this.clickFun = clickFun;
    }

    public MyTitleBar(Context context) {
        super(context);
    }

    public MyTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);

    }

    public MyTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
        shared_visible = typedArray.getBoolean(R.styleable.MyTitleBar_title_shared_visible, false);
        title = typedArray.getString(R.styleable.MyTitleBar_title_word);
        //添加布局内容
        View view = LayoutInflater.from(context).inflate(R.layout.mytitlebar, null);
        addView(view, new ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        imgReturn = view.findViewById(R.id.iv_return);
        txtTitle = view.findViewById(R.id.txt_title);
        ivOption = view.findViewById(R.id.iv_option);
        //设置返回按钮的监听
        imgReturn.setOnClickListener(this);

        if (!TextUtils.isEmpty(title)) {
            txtTitle.setText(title);
        }
        //初始化分享按钮的属性和事件
        ivOption.setVisibility(shared_visible ? View.VISIBLE : View.GONE);
        if (shared_visible) {
            ivOption.setOnClickListener(this);
        }
    }


    //点击监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return:
                //可以把Activity传进来 直接finish, 还可以通过接口回调
                if (activity != null && !activity.isFinishing()) {
                    activity.finish();
                    activity = null;
                }
                break;
            case R.id.iv_option:
                //1.把分享的内容传进来，  通过接口回调
                Toast.makeText(context, "点击分享", Toast.LENGTH_SHORT).show();
                //2.通过接口回调把点击事件传出去
                if (clickFun != null) {
                    clickFun.titleClick(v);
                }
                break;
        }
    }

    //点击事件触发的接口回调
    interface ITitleClick {
        void titleClick(View view);
    }
}
