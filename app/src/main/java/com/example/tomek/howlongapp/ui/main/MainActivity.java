package com.example.tomek.howlongapp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tomek.howlongapp.HowLongApplication;
import com.example.tomek.howlongapp.R;
import com.example.tomek.howlongapp.di.component.ActivityComponent;
import com.example.tomek.howlongapp.di.component.DaggerActivityComponent;
import com.example.tomek.howlongapp.di.module.ActivityModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;


    private ActivityComponent activityComponent;
    TextView textView;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddButtonClicked();
            }
        });
        getActivityComponent().inject(this);

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showText(String text) {
        textView.setText(text);
    }


    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this,this))
                    .applicationComponent(HowLongApplication.get(this).getComponent())
                    .build();
        }
        return activityComponent;
    }

}
