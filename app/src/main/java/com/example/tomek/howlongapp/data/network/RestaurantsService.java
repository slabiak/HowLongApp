package com.example.tomek.howlongapp.data.network;

import com.example.tomek.howlongapp.data.model.ApiResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by Tomek on 27.01.2018.
 */

public interface RestaurantsService {



    @GET("crud/Api.php/")
    Observable<ApiResponse> getRestaurants(@Query("apicall") String apicall);

    @FormUrlEncoded
    @POST("crud/Api.php?apicall=getRestaurant")
    Observable<ApiResponse> getRestaurant(@Field("google_id") String google_id);

    @FormUrlEncoded
    @POST("crud/Api.php?apicall=createRestaurant")
    Observable<ApiResponse> createRestaurant(@Field("name") String name, @Field("wait_time") String wait_time, @Field("google_id") String google_id);

    @FormUrlEncoded
    @POST("crud/Api.php?apicall=addReport")
    Observable<ApiResponse> addReport(@Field("restaurant_id") String restaurant_id, @Field("waiting_time") Integer wait_time, @Field("created_by") String created_by, @Field("created_at") String created_at);


}
