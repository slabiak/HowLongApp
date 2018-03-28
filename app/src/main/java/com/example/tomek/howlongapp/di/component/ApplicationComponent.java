package com.example.tomek.howlongapp.di.component;

import android.app.Application;
import android.content.Context;

import com.example.tomek.howlongapp.HowLongApplication;
import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.network.PlacesService;
import com.example.tomek.howlongapp.data.network.RestaurantsService;
import com.example.tomek.howlongapp.di.ApplicationContext;
import com.example.tomek.howlongapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by slabiaq on 04.02.2018.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(HowLongApplication howLongApplication);

    @ApplicationContext
    Application getApplication();

    @ApplicationContext
    Context getContext();

    AppDataManager getAppDataManager();

    RestaurantsService getRestaurantService();

    PlacesService getPlacesService();
}
