package com.slabiak.tomek.howlongapp.ui.restaurantdetail;

import com.slabiak.tomek.howlongapp.BuildConfig;
import com.slabiak.tomek.howlongapp.data.AppDataManager;
import com.slabiak.tomek.howlongapp.data.model.Restaurant;
import com.slabiak.tomek.howlongapp.ui.base.BasePresenter;
import com.slabiak.tomek.howlongapp.util.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

/**
 * Created by tslabiak on 04.03.2018.
 */

public class RestaurantDetailPresenter extends BasePresenter<RestaurantDetailContract.View> implements RestaurantDetailContract.Presenter {

    AppDataManager mAppDataManager;
    private int restaurantId;
    private final BaseSchedulerProvider mBaseSchedulerProvider;

    @Inject
    public RestaurantDetailPresenter(AppDataManager appDataManager,  BaseSchedulerProvider baseSchedulerProvider) {
        mAppDataManager = appDataManager;
        mBaseSchedulerProvider = baseSchedulerProvider;
    }

    @Override
    public void start() {
        String reference = findReastaurant(restaurantId).getPhotoReference();
        String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=100&photoreference=" + reference + "&key=" + BuildConfig.GoogleSecAPIKEY;
        getMvpView().loadImage(url);

        if(findReastaurant(restaurantId).getReports().isEmpty()){
                getMvpView().showEmptyList(true);
        }else{
        getMvpView().showEmptyList(false);
        getMvpView().showReports(findReastaurant(restaurantId).getReports());
        }

        getMvpView().showRetaurantsDetails(findReastaurant(restaurantId).getName(), findReastaurant(restaurantId).getAddress(), findReastaurant(restaurantId).getReports().size(), findReastaurant(restaurantId).getMeanWaitingTime());
    }

    @Override
    public int getID() {
        return restaurantId;
    }

    @Override
    public void setID(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public void onAddButtonClicked() {
        getMvpView().startAddReportActivity();
    }

    public Restaurant findReastaurant(int restaurantId) {
        for (Restaurant restaurant : mAppDataManager.getLocalRestaurantsList()) {
            if (restaurant.getId() == restaurantId) {
                return restaurant;
            }
        }
        return null;
    }
}