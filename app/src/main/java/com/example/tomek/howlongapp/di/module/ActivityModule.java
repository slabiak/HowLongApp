package com.example.tomek.howlongapp.di.module;

import android.app.Activity;
import android.content.Context;

import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.di.ActivityContext;
import com.example.tomek.howlongapp.di.ApplicationContext;
import com.example.tomek.howlongapp.di.PerActivity;
import com.example.tomek.howlongapp.ui.main.MainActivity;
import com.example.tomek.howlongapp.ui.main.MainContract;
import com.example.tomek.howlongapp.ui.main.MainPresenter;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by slabiaq on 06.02.2018.
 */

@Module
public class ActivityModule {

    private Activity mActivity;
    private MainContract.View mView;

    public ActivityModule(Activity activity, MainContract.View view) {
        mActivity = activity;
        mView = view;
    }



    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    MainContract.View provideView(){
        return mView;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    MainContract.Presenter providePresenter(MainContract.View mainView, AppDataManager appDataManager) {
        return new MainPresenter(mainView,appDataManager);
    }


}
