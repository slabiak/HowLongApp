package com.slabiak.tomek.howlongapp;

import android.app.Application;
import android.content.Context;

import com.slabiak.tomek.howlongapp.data.AppDataManager;
import com.slabiak.tomek.howlongapp.data.network.RestaurantsService;
import com.slabiak.tomek.howlongapp.di.component.ApplicationComponent;
import com.slabiak.tomek.howlongapp.di.component.DaggerApplicationComponent;
import com.slabiak.tomek.howlongapp.di.module.ApplicationModule;

import javax.inject.Inject;

/**
 * Created by slabiaq on 04.02.2018.
 */

public class HowLongApplication extends Application {

    protected ApplicationComponent applicationComponent;
    @Inject
    RestaurantsService mRestaurantService;
    @Inject
    AppDataManager mAppDataManager;

    public static HowLongApplication get(Context context) {
        return (HowLongApplication) context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

}
