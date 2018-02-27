package com.example.tomek.howlongapp.di.module;

import android.app.Application;
import android.content.Context;

import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.network.RestaurantsService;
import com.example.tomek.howlongapp.di.ApplicationContext;

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

    String BASE_URL = "http://slabiaq.ayz.pl/";

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
    RestaurantsService provideRestaurantService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RestaurantsService.class);
    }




}