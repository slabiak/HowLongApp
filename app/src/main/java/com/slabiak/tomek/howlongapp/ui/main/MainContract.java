package com.slabiak.tomek.howlongapp.ui.main;

import com.slabiak.tomek.howlongapp.data.model.Restaurant;
import com.slabiak.tomek.howlongapp.ui.base.MvpView;
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

        void startRestaurantDetailActivity(int restaurantId);
    }

    interface Presenter extends com.slabiak.tomek.howlongapp.ui.base.Presenter<View> {
        void start();

        void onAddPlaceButtonClicked();

        void onPlacePickerFinished(Place place);

        void refreshRestaurantsList();
    }

}