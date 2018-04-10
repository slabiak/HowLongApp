package com.example.tomek.howlongapp.ui.addreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tomek.howlongapp.R;
import com.example.tomek.howlongapp.ui.base.BaseActivity;
import com.example.tomek.howlongapp.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddReportActivity extends BaseActivity implements AddReportContract.View {

    @Inject
    AddReportContract.Presenter presenter;

    @BindView(R.id.text_name) TextView tvName;
    @BindView(R.id.input_author) EditText etAuthor;
    @BindView(R.id.input_watingTime) EditText etWaitingTime;
    @BindView(R.id.btn_addReport) Button btnAddReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        presenter.attachView(this);
        presenter.setID(getID());
        presenter.start();

        btnAddReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddButtonClicked(etAuthor.getText().toString(), Integer.parseInt(etWaitingTime.getText().toString()));
            }
        });
    }

    @Override
    public void showRestaurantName(String name) {
        this.tvName.setText(name);
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(AddReportActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
