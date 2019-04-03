package com.slabiak.tomek.howlongapp.di.module;

import android.app.Activity;
import android.content.Context;

import com.slabiak.tomek.howlongapp.data.AppDataManager;
import com.slabiak.tomek.howlongapp.ui.addreport.AddReportContract;
import com.slabiak.tomek.howlongapp.ui.addreport.AddReportPresenter;
import com.slabiak.tomek.howlongapp.ui.main.MainContract;
import com.slabiak.tomek.howlongapp.ui.main.MainPresenter;
import com.slabiak.tomek.howlongapp.ui.restaurantdetail.RestaurantDetailContract;
import com.slabiak.tomek.howlongapp.ui.restaurantdetail.RestaurantDetailPresenter;
import com.slabiak.tomek.howlongapp.util.schedulers.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by slabiaq on 06.02.2018.
 */

@Module
public class ActivityModule {

    private Activity mActivity;


    public ActivityModule(Activity activity) {
        mActivity = activity;
    }


    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    Context providesContext() {
        return mActivity;
    }

    @Provides
    MainContract.Presenter provideMainPresenter(AppDataManager appDataManager) {
        return new MainPresenter(appDataManager, SchedulerProvider.getInstance());
    }

    @Provides
    AddReportContract.Presenter provideAddReportPresenter(AppDataManager appDataManager) {
        return new AddReportPresenter(appDataManager, SchedulerProvider.getInstance());
    }

    @Provides
    RestaurantDetailContract.Presenter provideRestaurantDetailPresenter(AppDataManager appDataManager) {
        return new RestaurantDetailPresenter(appDataManager, SchedulerProvider.getInstance());
    }
}
