package com.slabiak.tomek.howlongapp.ui.restaurantdetail;

import com.slabiak.tomek.howlongapp.data.model.Report;
import com.slabiak.tomek.howlongapp.ui.base.MvpView;

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

        void showEmptyList(boolean empty);
    }

    interface Presenter extends com.slabiak.tomek.howlongapp.ui.base.Presenter<View> {

        void start();

        void onAddButtonClicked();

        int getID();

        void setID(int id);


    }

}