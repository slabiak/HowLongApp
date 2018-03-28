package com.example.tomek.howlongapp.di.component;

import com.example.tomek.howlongapp.di.PerActivity;
import com.example.tomek.howlongapp.di.module.ActivityModule;
import com.example.tomek.howlongapp.ui.addreport.AddReportActivity;
import com.example.tomek.howlongapp.ui.main.MainActivity;
import com.example.tomek.howlongapp.ui.restaurantdetail.RestaurantDetailActivity;

import dagger.Component;

/**
 * Created by slabiaq on 06.02.2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(MainActivity mainActivity);

    void inject(RestaurantDetailActivity restaurantDetailActivity);

    void inject(AddReportActivity addReportActivity);


}
