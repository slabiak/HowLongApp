package com.example.tomek.howlongapp.data.network;

import com.example.tomek.howlongapp.data.model.ApiResponse;

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

    String BASE_URL = "http://slabiaq.ayz.pl/";

    @GET("crud/Api.php/")
    Call<ApiResponse> getRestaurants(@Query("apicall") String apicall);

    @FormUrlEncoded
    @POST("crud/Api.php?apicall=getRestaurant")
    Call<ApiResponse> getRestaurant(@Field("google_id") String google_id);

    @FormUrlEncoded
    @POST("crud/Api.php?apicall=createRestaurant")
    Call<ApiResponse> createRestaurant(@Field("name") String name, @Field("wait_time") String wait_time, @Field("update_date") String update_date, @Field("google_id") String google_id);


    /******** Helper class that sets up a new services *******/
    class Creator {


        public static RestaurantsService newRestaurantsService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(RestaurantsService.class);
        }
    }


}
