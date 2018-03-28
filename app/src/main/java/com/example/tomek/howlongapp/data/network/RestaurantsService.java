package com.example.tomek.howlongapp.data.network;

import com.example.tomek.howlongapp.data.model.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by Tomek on 27.01.2018.
 */


public interface RestaurantsService {


    @FormUrlEncoded
    @POST("crud/Api.php?apicall=createRestaurant")
    Observable<ApiResponse> createRestaurant(@Field("name") String name, @Field("address") String address, @Field("google_id") String google_id, @Field("imageURL") String imageURL);

    @FormUrlEncoded
    @POST("crud/Api.php?apicall=addReport")
    Observable<ApiResponse> addReport(@Field("restaurant_id") Integer restaurant_id, @Field("waiting_time") Integer wait_time, @Field("created_by") String created_by);

    @POST("crud/Api.php?apicall=getRR")
    Observable<ApiResponse> getRR();

}
