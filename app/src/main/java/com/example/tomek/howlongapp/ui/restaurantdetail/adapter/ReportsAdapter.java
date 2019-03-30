package com.example.tomek.howlongapp.ui.restaurantdetail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomek.howlongapp.R;
import com.example.tomek.howlongapp.data.model.Report;

import java.util.ArrayList;
import java.util.Date;
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
        TextView createdBy = convertView.findViewById(R.id.text_createdBy);
        TextView createdAt = convertView.findViewById(R.id.text_createdAt);
        TextView waitingTime = convertView.findViewById(R.id.text_waitingTime);
        ImageView image2 = convertView.findViewById(R.id.image_thumbnail_detail);
        // Populate the data into the template view using the data object
        createdBy.setText(report.getCreatedBy());
        Date time = report.getCreatedAt();
        createdAt.setText(time.toString());

        waitingTime.setText(Integer.toString(report.getWaitingTime()) + " min");
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