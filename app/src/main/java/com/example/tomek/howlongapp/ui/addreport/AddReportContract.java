package com.example.tomek.howlongapp.ui.addreport;

import com.example.tomek.howlongapp.ui.base.MvpView;

/**
 * Created by tslabiak on 04.03.2018.
 */

public interface AddReportContract {

    interface View extends MvpView {

        void showRestaurantName(String name);

        void startMainActivity();

        public void showMessage(String text);
    }

    interface Presenter extends com.example.tomek.howlongapp.ui.base.Presenter<View> {

        void start();

        void onAddButtonClicked(String author, String waitingTime);

        void setID(Integer id);


    }

}