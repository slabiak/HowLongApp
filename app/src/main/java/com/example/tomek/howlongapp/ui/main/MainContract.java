package com.example.tomek.howlongapp.ui.main;

import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.ui.base.MvpView;
import com.google.android.gms.location.places.Place;

import java.util.List;

/**
 * Created by Tomek on 27.01.2018.
 */

public interface MainContract {

    interface View extends MvpView {
        void showLoadingProgress(boolean active);

        void startPlacePicker();

        void showRestaurants(List<Restaurant> restaurants);

        void showMessage(String text);
    }

    interface Presenter extends com.example.tomek.howlongapp.ui.base.Presenter<View> {
        void start();

        void onAddPlaceButtonClicked();

        void onPlacePickerFinished(Place place);
    }

}