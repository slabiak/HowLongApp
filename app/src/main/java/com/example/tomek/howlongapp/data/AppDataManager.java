package com.example.tomek.howlongapp.data;

import com.example.tomek.howlongapp.data.model.ApiResponse;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.data.network.RestaurantsService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Tomek on 27.01.2018.
 */

public class AppDataManager {


    RestaurantsService mRestaurantService;


    @Inject
    public AppDataManager(RestaurantsService mRestaurantService) {
        this.mRestaurantService = mRestaurantService;
    }




    public Observable<ApiResponse> getRestaurants() {
        return mRestaurantService.getRestaurants("getRestaurants");
    }

    public Observable<ApiResponse> getRestaurant(String google_id) {
        return mRestaurantService.getRestaurant(google_id);
    }

    public Observable<ApiResponse> createRestaurant(String name, String wait_time, String google_id) {
        return mRestaurantService.createRestaurant(name, wait_time, google_id);
    }

    public Observable<ApiResponse> addReport(String restaurant_id, int wait_time, String created_by) {
        return mRestaurantService.addReport(restaurant_id, wait_time, created_by, "lol");
    }



}
