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

    private RestaurantsService mRestaurantService;
    private PlacesService mPlacesService;
    ApiResponse mLocalResponse;

    @Inject
    public AppDataManager(RestaurantsService mRestaurantService, PlacesService placesService) {
        this.mRestaurantService = mRestaurantService;
        this.mPlacesService = placesService;
    }

    public Observable<ApiResponse> createRestaurant(String name, String address, String googleId, String photo_reference) {
        return mRestaurantService.createRestaurant(name, address, googleId, photo_reference);
    }

    public Observable<ApiResponse> addReport(Integer id, Integer waitingTime, String createdBy) {
        return mRestaurantService.addReport(id, waitingTime, createdBy);
    }

    public Observable<ApiResponse> getRR() {
        return mRestaurantService.getRR();
    }

    public Observable<JsonObject> getPlaceDetails(String googleId) {
        return mPlacesService.getPlaceJson(BuildConfig.GoogleSecAPIKEY, googleId);
    }

    public ApiResponse getLocalResponse() {
        return mLocalResponse;
    }

    public void setLocalResponse(ApiResponse localResponse) {
        mLocalResponse = localResponse;
    }


}
