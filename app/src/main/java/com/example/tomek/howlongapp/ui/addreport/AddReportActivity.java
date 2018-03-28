package com.example.tomek.howlongapp.ui.addreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tomek.howlongapp.HowLongApplication;
import com.example.tomek.howlongapp.R;
import com.example.tomek.howlongapp.di.component.ActivityComponent;
import com.example.tomek.howlongapp.di.component.DaggerActivityComponent;
import com.example.tomek.howlongapp.di.module.ActivityModule;
import com.example.tomek.howlongapp.ui.base.BaseActivity;
import com.example.tomek.howlongapp.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddReportActivity extends BaseActivity implements AddReportContract.View {

    @Inject AddReportContract.Presenter presenter;

    @BindView(R.id.name3) TextView name;
    @BindView(R.id.input_author)  EditText author;
    @BindView(R.id.input_watingTime) EditText waitingTime;
    @BindView(R.id.add_report)  Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);


        presenter.attachView(this);
        presenter.setID(getID());
        presenter.start();


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               presenter.onAddButtonClicked(author.getText().toString(), Integer.parseInt(waitingTime.getText().toString()));
            }
        });

    }

    @Override
    public void showRestaurantName(String name) {
        this.name.setText(name);
    }

    @Override
    public void startMainActivity(){
        Intent intent = new Intent(AddReportActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
