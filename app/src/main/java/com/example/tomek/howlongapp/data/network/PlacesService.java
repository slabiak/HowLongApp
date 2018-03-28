package com.example.tomek.howlongapp.data.network;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tslabiak on 16.03.2018.
 */

public interface PlacesService {

    @GET("maps/api/place/details/json")
    Observable<JsonObject> getPlaceJson(@Query("key") String key, @Query("placeid") String googleId);

}
