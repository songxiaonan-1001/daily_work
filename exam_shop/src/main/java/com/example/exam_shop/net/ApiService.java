package com.example.exam_shop.net;

import com.example.exam_shop.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    //https://cdwan.cn/api/topic/list
    String URL = "https://cdwan.cn/api/";

    @GET("topic/list")
    Observable<HomeBean> getData();
}
