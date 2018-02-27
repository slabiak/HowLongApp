package com.example.tomek.howlongapp.di.component;

import com.example.tomek.howlongapp.HowLongApplication;
import com.example.tomek.howlongapp.di.ActivityContext;
import com.example.tomek.howlongapp.di.PerActivity;
import com.example.tomek.howlongapp.di.module.ActivityModule;
import com.example.tomek.howlongapp.di.module.ApplicationModule;
import com.example.tomek.howlongapp.ui.main.MainActivity;
import com.example.tomek.howlongapp.ui.main.MainContract;
import com.example.tomek.howlongapp.ui.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by slabiaq on 06.02.2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(MainActivity mainActivity);


    MainContract.Presenter getPresenter();
}
