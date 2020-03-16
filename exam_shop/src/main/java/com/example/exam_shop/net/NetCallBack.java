package com.example.exam_shop.net;

import com.example.exam_shop.bean.HomeBean;

public interface NetCallBack {
    void onSuccess(HomeBean homeBean);

    void onFail(String message);
}
