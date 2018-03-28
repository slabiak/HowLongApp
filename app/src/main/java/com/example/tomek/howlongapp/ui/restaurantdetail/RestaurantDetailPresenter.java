package com.example.tomek.howlongapp.ui.restaurantdetail;

import com.example.tomek.howlongapp.BuildConfig;
import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by tslabiak on 04.03.2018.
 */

public class RestaurantDetailPresenter extends BasePresenter<RestaurantDetailContract.View> implements RestaurantDetailContract.Presenter {

    AppDataManager mAppDataManager;
    private Integer ID;

    @Inject
    public RestaurantDetailPresenter(AppDataManager appDataManager) {
        mAppDataManager = appDataManager;
    }

    @Override
    public void start() {
        String reference = findReastaurant(ID).getPhotoReference();
        String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=100&photoreference=" + reference + "&key=" + BuildConfig.GoogleSecAPIKEY;
        getMvpView().loadImage(url);
        getMvpView().showReports(findReastaurant(ID).getReports());
        getMvpView().showRetaurantsDetails(findReastaurant(ID).getName(), findReastaurant(ID).getAddress(), findReastaurant(ID).getReports().size(), findReastaurant(ID).getMean());
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public void onAddButtonClicked() {
        getMvpView().startAddReportActivity();
    }

    Restaurant findReastaurant(Integer ID) {
        for (Restaurant restaurant : mAppDataManager.getmLocalResponse().getRestaurants()) {
            if (restaurant.getId() == ID) {
                return restaurant;
            }
        }
        return null;
    }
}