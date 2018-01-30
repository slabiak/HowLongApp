package com.example.tomek.howlongapp.data;

import com.example.tomek.howlongapp.data.model.ApiResponse;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.data.network.RestaurantsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tomek on 27.01.2018.
 */

public class AppDataManager {

    private static AppDataManager mAppDataManager = null;
    private final RestaurantsService mRestaurantService;

    protected AppDataManager() {
        mRestaurantService = RestaurantsService.Creator.newRestaurantsService();
    }

    public static AppDataManager getInstance() {
        if (mAppDataManager == null) {
            mAppDataManager = new AppDataManager();
        }
        return mAppDataManager;
    }


    public void getRestaurants(Callback<ApiResponse> listener) {
        mRestaurantService.getRestaurants("getRestaurants").enqueue(listener);
    }

    public void getRestaurant(Callback<ApiResponse> listener, String google_id) {
        mRestaurantService.getRestaurant(google_id).enqueue(listener);
    }

    public void createRestaurant(Callback<ApiResponse> listener,String name, String wait_time, String update_date, String google_id) {
        mRestaurantService.createRestaurant(name, wait_time, update_date, google_id).enqueue(listener);
    }


}
