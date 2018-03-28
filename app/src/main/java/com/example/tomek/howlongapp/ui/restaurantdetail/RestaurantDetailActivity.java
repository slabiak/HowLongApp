package com.example.tomek.howlongapp.ui.restaurantdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tomek.howlongapp.R;
import com.example.tomek.howlongapp.data.model.Report;
import com.example.tomek.howlongapp.ui.addreport.AddReportActivity;
import com.example.tomek.howlongapp.ui.base.BaseActivity;
import com.example.tomek.howlongapp.ui.restaurantdetail.adapter.ReportsAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantDetailActivity extends BaseActivity implements RestaurantDetailContract.View {

    @Inject
    RestaurantDetailContract.Presenter presenter;
    @Inject
    ReportsAdapter reportsAdapter;

    @BindView(R.id.address)
    TextView text_address;
    @BindView(R.id.meanTime)
    TextView text_meanTime;
    @BindView(R.id.reportsNumber)
    TextView text_reportsNumber;
    @BindView(R.id.name)
    TextView text_name;
    @BindView(R.id.reportsList)
    ListView reportsListView;
    @BindView(R.id.add_report)
    Button btn_addReport;
    @BindView(R.id.imageView2)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_restaurant_detail);
        getActivityComponent().inject(this);

        ButterKnife.bind(this);

        presenter.attachView(this);
        presenter.setID(getID());
        presenter.start();

        reportsListView.setAdapter(reportsAdapter);

        btn_addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddButtonClicked();
            }
        });
    }

    @Override
    public void showReports(List<Report> reports) {
        reportsAdapter.clear();
        reportsAdapter.setReports(reports);
    }

    @Override
    public void showRetaurantsDetails(String name, String address, Integer reportsNumber, Integer meanTime) {
        text_name.setText(name);
        text_address.setText(address);
        text_reportsNumber.setText("Liczba raportów: " + reportsNumber.toString());
        text_meanTime.setText("Średni czas: " + meanTime.toString());
    }

    @Override
    public void startAddReportActivity() {
        Intent intent = new Intent(RestaurantDetailActivity.this, AddReportActivity.class);
        intent.putExtra("id", presenter.getID().toString());
        startActivity(intent);
    }

    @Override
    public void loadImage(String url) {
        Picasso.get().load(url).into(imageView);
    }
}