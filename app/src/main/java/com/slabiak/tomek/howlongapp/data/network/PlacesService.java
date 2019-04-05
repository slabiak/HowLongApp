package com.slabiak.tomek.howlongapp.data.network;

import com.slabiak.tomek.howlongapp.data.model.Restaurant;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tslabiak on 16.03.2018.
 */

public interface PlacesService {

    @GET("maps/api/place/details/json?fields=id,name,formatted_address,photos")
    Observable<Restaurant> getPlaceJson(@Query("key") String key, @Query("placeid") String googleId);

}
