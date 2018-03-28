package com.example.tomek.howlongapp;

import android.app.Application;
import android.content.Context;

import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.network.RestaurantsService;
import com.example.tomek.howlongapp.di.component.ApplicationComponent;
import com.example.tomek.howlongapp.di.component.DaggerApplicationComponent;
import com.example.tomek.howlongapp.di.module.ApplicationModule;

import javax.inject.Inject;

/**
 * Created by slabiaq on 04.02.2018.
 */

public class HowLongApplication extends Application {

    @Inject RestaurantsService mRestaurantService;
    @Inject AppDataManager mAppDataManager;



    protected ApplicationComponent applicationComponent;



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

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }

}
