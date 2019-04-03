package com.slabiak.tomek.howlongapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.slabiak.tomek.howlongapp.HowLongApplication;
import com.slabiak.tomek.howlongapp.di.component.ActivityComponent;
import com.slabiak.tomek.howlongapp.di.component.DaggerActivityComponent;
import com.slabiak.tomek.howlongapp.di.module.ActivityModule;

/**
 * Created by tslabiak on 15.03.2018.
 */

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(HowLongApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    public Integer getID() {
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("id") != null) {
            return Integer.parseInt(bundle.getString("id"));
        }
        return null;
    }
}