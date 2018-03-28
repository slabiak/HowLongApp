package com.example.tomek.howlongapp.data;

import com.example.tomek.howlongapp.BuildConfig;
import com.example.tomek.howlongapp.data.model.ApiResponse;
import com.example.tomek.howlongapp.data.network.PlacesService;
import com.example.tomek.howlongapp.data.network.RestaurantsService;
import com.google.gson.JsonObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * Created by Tomek on 27.01.2018.
 */

@Singleton
public class AppDataManager {


    RestaurantsService mRestaurantService;
    PlacesService placesService;
    ApiResponse mLocalResponse;

    //    TODO make cahing function
    @Inject
    public AppDataManager(RestaurantsService mRestaurantService, PlacesService placesService) {
        this.mRestaurantService = mRestaurantService;
        this.placesService = placesService;
    }

    public Observable<ApiResponse> createRestaurant(String name, String address, String googleID, String imageURL) {
        return mRestaurantService.createRestaurant(name, address, googleID, imageURL);
    }

    public Observable<ApiResponse> addReport(Integer restaurant_id, Integer wait_time, String created_by) {
        return mRestaurantService.addReport(restaurant_id, wait_time, created_by);
    }

    public Observable<ApiResponse> getRR() {
        return mRestaurantService.getRR();
    }

    public Observable<JsonObject> getPlaceDetails(String google_id) {
        return placesService.getPlaceJson(BuildConfig.GoogleSecAPIKEY, google_id);
    }

    public ApiResponse getmLocalResponse() {
        return mLocalResponse;
    }

    public void setmLocalResponse(ApiResponse localResponse) {
        mLocalResponse = localResponse;
    }


}
