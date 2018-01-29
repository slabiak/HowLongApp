package com.example.tomek.howlongapp.data.network;

import com.example.tomek.howlongapp.data.model.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Tomek on 27.01.2018.
 */

public interface RestaurantsService {

    String BASE_URL = "http://slabiaq.ayz.pl/crud/Api.php/";

    @GET("users/{username}")
    Call<List<Restaurant>> getRestaurants(@Query("apicall") String apicall);


    /******** Helper class that sets up a new services *******/
    class Creator {

        JsonParser parser = new JsonParser();
        JsonObject data = parser.parse(response).getAsJsonObject();
        Meta meta = gson.fromJson(data.get("meta"), Meta.class);
        Response myResponse = gson.fromJson(data.get("response"), Response.class);



        public static RestaurantsService newRestaurantsService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(RestaurantsService.class);
        }
    }


}
