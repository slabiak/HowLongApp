package com.example.tomek.howlongapp.ui.main;


import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tomek on 27.01.2018.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mMainView;
    private final AppDataManager mDataManager;

    public MainPresenter(MainContract.View MainView, AppDataManager dataManager) {
        mMainView = MainView;
        mDataManager = dataManager;
        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
    }


    @Override
    public void onAddButtonClicked() {

        //getRestaurants();
        getRestaurant("ChIJ2V8bnRkx_UYROADo3AgiiL4");
        //createRestaurant();
    }

    public void getRestaurants() {
        mDataManager.getRestaurants(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse res = response.body();
                if (res.getError() == false) {
                    mMainView.showText(res.getRestaurants().get(1).getName());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
            }
        });
    }

    public void getRestaurant(String id) {
        mDataManager.getRestaurant(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse res = response.body();
                if (res.getError() == false) {
                    mMainView.showText(res.getRestaurants().get(0).getName());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
            }
        }, id);
    }

    public void createRestaurant() {
        mDataManager.createRestaurant(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse res = response.body();
                if (res.getError() == false) {
                    mMainView.showText(res.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
            }
        }, "rest", "10", "11", "ddd");
    }


}
