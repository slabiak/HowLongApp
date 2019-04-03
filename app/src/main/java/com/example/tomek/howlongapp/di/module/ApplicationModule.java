package com.example.tomek.howlongapp.di.module;

import android.app.Application;
import android.content.Context;

import com.example.tomek.howlongapp.BuildConfig;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.data.network.PlacesService;
import com.example.tomek.howlongapp.data.network.RestaurantsService;
import com.example.tomek.howlongapp.di.ApplicationContext;
import com.example.tomek.howlongapp.util.AppointmentDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by slabiaq on 04.02.2018.
 */

@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    RestaurantsService provideRestaurantService() {
       /* OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();*/
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.HowLongAppRestApiBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(RestaurantsService.class);
    }

    @Provides
    @Singleton
    PlacesService placesService() {
       /* OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();*/
        Gson gson = new GsonBuilder().registerTypeAdapter(Restaurant.class, new AppointmentDeserializer()).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.GoogleMapsApiBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(PlacesService.class);
    }

}