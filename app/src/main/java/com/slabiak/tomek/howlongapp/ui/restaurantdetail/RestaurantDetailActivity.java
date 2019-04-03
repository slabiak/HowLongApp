package com.slabiak.tomek.howlongapp.ui.restaurantdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.slabiak.tomek.howlongapp.R;
import com.slabiak.tomek.howlongapp.data.model.Report;
import com.slabiak.tomek.howlongapp.ui.addreport.AddReportActivity;
import com.slabiak.tomek.howlongapp.ui.base.BaseActivity;
import com.slabiak.tomek.howlongapp.ui.restaurantdetail.adapter.ReportsAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantDetailActivity extends BaseActivity implements RestaurantDetailContract.View {

    @Inject RestaurantDetailContract.Presenter presenter;
    @Inject ReportsAdapter reportsAdapter;

    @BindView(R.id.text_address) TextView tvAddress;
    @BindView(R.id.text_mean) TextView tvMean;
    @BindView(R.id.text_reports_number) TextView tvReportsNumber;
    @BindView(R.id.text_name2) TextView tvName;
    @BindView(R.id.list_reports) ListView lvReports;
    @BindView(R.id.btn_addReport) Button btnAddReport;
    @BindView(R.id.image_thumbnail_detail) ImageView imThumbnail;
    @BindView(R.id.toolbar_detail) Toolbar toolbar;
    @BindView(R.id.text_no_reports) TextView tvNoReports;
    @BindView(R.id.header) LinearLayout header;
    @BindView(R.id.image_no_reports) ImageView imNoReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_restaurant_detail);
        getActivityComponent().inject(this);

        ButterKnife.bind(this);

        presenter.attachView(this);
        presenter.setID(getID());
        presenter.start();

        toolbar.setTitle("Raporty restauracji");
        setSupportActionBar(toolbar);
        lvReports.setAdapter(reportsAdapter);

        btnAddReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddButtonClicked();
            }
        });
    }

    @Override
    public void showReports(List<Report> reports) {
        tvNoReports.setVisibility(View.GONE);
        reportsAdapter.clear();
        reportsAdapter.setReports(reports);
    }

    @Override
    public void showRetaurantsDetails(String name, String address, Integer reportsNumber, Integer meanTime) {
        tvName.setText(name);
        tvAddress.setText(address);
        tvReportsNumber.setText("Liczba raportów: " + reportsNumber.toString());
        tvMean.setText("Średni czas: " + meanTime.toString());
    }

    @Override
    public void startAddReportActivity() {
        Intent intent = new Intent(RestaurantDetailActivity.this, AddReportActivity.class);
        intent.putExtra("id", presenter.getID().toString());
        startActivity(intent);
    }

    @Override
    public void loadImage(String url) {
        Picasso
                .get()
                .load(url)
                .resize(150, 150)
                .centerCrop()
                .into(imThumbnail);
    }

    @Override
    public void showEmptyList(boolean empty) {

        if (empty){
            tvNoReports.setVisibility(View.VISIBLE);
            imNoReports.setVisibility(View.VISIBLE);
            header.setVisibility(View.GONE);
            lvReports.setVisibility(View.GONE);

        }
        else {
            tvNoReports.setVisibility(View.GONE);
            imNoReports.setVisibility(View.GONE);
            header.setVisibility(View.VISIBLE);
            lvReports.setVisibility(View.VISIBLE);

        }

    }
}