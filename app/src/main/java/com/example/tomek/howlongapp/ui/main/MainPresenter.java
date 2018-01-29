package com.example.tomek.howlongapp.ui.main;


import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    public void onAddButtonClicked(){
        mDataManager.getRestaurants(new Callback<List<Restaurant>>() {

            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                mMainView.showText("poszlo ok!");
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                mMainView.showText(t.toString());
            }
        });
    }
}
