package com.example.tomek.howlongapp.data.network;

import com.example.tomek.howlongapp.data.model.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by Tomek on 27.01.2018.
 */


public interface RestaurantsService {


    @FormUrlEncoded
    @POST("restaurants")
    Observable<ApiResponse> createRestaurant(@Field("name") String name, @Field("address") String address, @Field("google_id") String google_id, @Field("imageURL") String imageURL);

    @FormUrlEncoded
    @POST("reports")
    Observable<ApiResponse> addReport(@Field("restaurant_id") Integer restaurant_id, @Field("waiting_time") Integer wait_time, @Field("created_by") String created_by);

    @GET("restaurants")
    Observable<ApiResponse> getRR();

}
