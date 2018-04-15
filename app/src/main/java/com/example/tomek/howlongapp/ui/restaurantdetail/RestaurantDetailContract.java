package com.example.tomek.howlongapp.ui.restaurantdetail;

import com.example.tomek.howlongapp.data.model.Report;
import com.example.tomek.howlongapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by tslabiak on 04.03.2018.
 */

public interface RestaurantDetailContract {

    interface View extends MvpView {
        void showReports(List<Report> reports);

        void showRetaurantsDetails(String Name, String Address, Integer reportsNumber, Integer meanTime);

        void startAddReportActivity();

        void loadImage(String url);

        void showNoReports();
    }

    interface Presenter extends com.example.tomek.howlongapp.ui.base.Presenter<View> {

        void start();

        void onAddButtonClicked();

        Integer getID();

        void setID(Integer id);


    }

}