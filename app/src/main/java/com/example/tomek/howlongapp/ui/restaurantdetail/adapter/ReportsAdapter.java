package com.example.tomek.howlongapp.ui.restaurantdetail.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomek.howlongapp.R;
import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.Report;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by tslabiak on 03.03.2018.
 */

public class ReportsAdapter extends ArrayAdapter<Report> {


    private List<Report> mReports;

    @Inject
    public ReportsAdapter(Context context) {
        super(context, 0);
        this.mReports = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Report report = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_report, parent, false);
        }
        // Lookup view for data population
        TextView createdBy = convertView.findViewById(R.id.createdBy);
        TextView createdAt = convertView.findViewById(R.id.createdAt);
        TextView waitingTime = convertView.findViewById(R.id.waitingTime);
        ImageView image2 = convertView.findViewById(R.id.imageView2);
        // Populate the data into the template view using the data object
        createdBy.setText(report.getCreatedBy());
        String time = report.getCreatedAt().split(" ")[1].toString();
        createdAt.setText(time);

        waitingTime.setText(Integer.toString(report.getWaitingTime())+ " min");
        return convertView;
    }


    public List<Report> getReports() {
        return mReports;
    }

    public void setReports(List<Report> reports) {
        this.mReports = reports;
        this.addAll(reports);
    }


}