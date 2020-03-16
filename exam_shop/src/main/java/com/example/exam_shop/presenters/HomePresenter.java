package com.example.exam_shop.presenters;

import com.example.exam_shop.bean.HomeBean;
import com.example.exam_shop.models.HomeModel;
import com.example.exam_shop.net.NetCallBack;
import com.example.exam_shop.view.HomeView;

public class HomePresenter implements NetCallBack {
    private HomeView homeView;
    private HomeModel homeModel;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        homeModel = new HomeModel();
    }

    public void getData() {
        homeModel.getData(this);
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        homeView.setData(homeBean);
    }

    @Override
    public void onFail(String message) {
        homeView.showToast(message);
    }
}
