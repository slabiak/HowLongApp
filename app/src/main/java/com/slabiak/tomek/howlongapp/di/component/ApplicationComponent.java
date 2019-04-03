package com.slabiak.tomek.howlongapp.di.component;

import android.app.Application;
import android.content.Context;

import com.slabiak.tomek.howlongapp.HowLongApplication;
import com.slabiak.tomek.howlongapp.data.AppDataManager;
import com.slabiak.tomek.howlongapp.data.network.PlacesService;
import com.slabiak.tomek.howlongapp.data.network.RestaurantsService;
import com.slabiak.tomek.howlongapp.di.ApplicationContext;
import com.slabiak.tomek.howlongapp.di.module.ApplicationModule;

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
