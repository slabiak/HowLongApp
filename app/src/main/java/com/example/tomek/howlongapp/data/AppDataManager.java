package com.example.tomek.howlongapp.data;

import com.example.tomek.howlongapp.BuildConfig;
import com.example.tomek.howlongapp.data.model.Report;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.data.network.PlacesService;
import com.example.tomek.howlongapp.data.network.RestaurantsService;
import com.google.gson.JsonObject;

import java.util.Collections;
import java.util.List;

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
    private List<Restaurant> localRestaurantsList;

    @Inject
    public AppDataManager(RestaurantsService mRestaurantService, PlacesService placesService) {
        this.mRestaurantService = mRestaurantService;
        this.mPlacesService = placesService;
    }

    public Observable<Restaurant> createRestaurant(Restaurant restaurant) {
        return mRestaurantService.createRestaurant(restaurant);
    }

    public Observable<Report> addReport(Report report, int restaurantId) {
        return mRestaurantService.addReport(report,restaurantId);
    }

    public Observable<List<Restaurant>> getRR() {
        return mRestaurantService.getRR();
    }

    public Observable<JsonObject> getPlaceDetails(String googleId) {
        return mPlacesService.getPlaceJson(BuildConfig.GoogleSecAPIKEY, googleId);
    }

    public List<Restaurant> getLocalRestaurantsList() {
        return localRestaurantsList;
    }

    public void setLocalRestaurantsList(List<Restaurant> localRestaurantsList) {
        Collections.sort(localRestaurantsList);
        this.localRestaurantsList = localRestaurantsList;
    }

    public void addRestaurantToLocalRestauantsList(Restaurant restaurant){
        localRestaurantsList.add(restaurant);
        Collections.sort(localRestaurantsList);

    }
}
