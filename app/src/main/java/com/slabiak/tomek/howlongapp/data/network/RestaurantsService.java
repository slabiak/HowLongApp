package com.slabiak.tomek.howlongapp.data.network;

import com.slabiak.tomek.howlongapp.data.model.Report;
import com.slabiak.tomek.howlongapp.data.model.Restaurant;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by Tomek on 27.01.2018.
 */


public interface RestaurantsService {

    @POST("restaurants")
    Observable<Restaurant> createRestaurant(@Body Restaurant restaurant);

    @POST("restaurants/{restaurantId}/reports")
    Observable<Report> addReport(@Body Report report,@Path("restaurantId") int restaurantId);

    @GET("restaurants")
    Observable<List<Restaurant>> getRR();

}
