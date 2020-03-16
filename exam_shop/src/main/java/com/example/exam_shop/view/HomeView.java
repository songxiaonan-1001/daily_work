package com.example.exam_shop.view;

import com.example.exam_shop.bean.HomeBean;

public interface HomeView {
    void setData(HomeBean homeBean);

    void showToast(String message);
}
