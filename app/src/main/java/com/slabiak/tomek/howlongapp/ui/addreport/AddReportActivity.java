package com.slabiak.tomek.howlongapp.ui.addreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.slabiak.tomek.howlongapp.R;
import com.slabiak.tomek.howlongapp.ui.base.BaseActivity;
import com.slabiak.tomek.howlongapp.ui.main.MainActivity;

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
    @BindView(R.id.toolbar_add) Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        presenter.attachView(this);
        presenter.setID(getID());
        presenter.start();
        toolbar.setTitle("Dodawanie raportu");
        setSupportActionBar(toolbar);

        btnAddReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddButtonClicked(etAuthor.getText().toString(), etWaitingTime.getText().toString());
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

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
