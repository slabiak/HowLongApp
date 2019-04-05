package com.slabiak.tomek.howlongapp.ui.main;

import com.google.android.libraries.places.api.model.Place;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.slabiak.tomek.howlongapp.data.AppDataManager;
import com.slabiak.tomek.howlongapp.data.model.Restaurant;
import com.slabiak.tomek.howlongapp.ui.base.BasePresenter;
import com.slabiak.tomek.howlongapp.util.ErrorUtils;
import com.slabiak.tomek.howlongapp.util.schedulers.BaseSchedulerProvider;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Tomek on 27.01.2018.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private final BaseSchedulerProvider mBaseSchedulerProvider;
    private AppDataManager mAppDataManager;

    public MainPresenter(AppDataManager appDataManager, BaseSchedulerProvider baseSchedulerProvider) {
        mAppDataManager = appDataManager;
        mBaseSchedulerProvider = baseSchedulerProvider;
    }

    @Override
    public void start() {
    }

    @Override
    public void onAddPlaceButtonClicked() {
        getMvpView().startPlacePicker();
    }

    @Override
    public void onPlacePickerFinished(Place place) {
        boolean isAnRestaurant = false;
        for (Place.Type t : place.getTypes()) {
            if (t == Place.Type.RESTAURANT) {
                isAnRestaurant = true;
                break;
            }
        }
        if (isAnRestaurant) {
            loadPlaceDetails(place.getId());
        } else {
            getMvpView().showMessage("Wybrany obiekt nie jest restauracją!");
        }
    }

    @Override
    public void refreshRestaurantsList() {
        loadRestaurants();
    }

    public void createRestaurant(Restaurant restaurant) {
            mAppDataManager.createRestaurant(restaurant)
                    .subscribeOn(mBaseSchedulerProvider.io())
                    .observeOn(mBaseSchedulerProvider.ui())
                    .subscribe(new Observer<Restaurant>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                                      }

                        @Override
                        public void onNext(Restaurant restaurant) {
                                mAppDataManager.addRestaurantToLocalRestauantsList(restaurant);
                                List<Restaurant> restaurants = mAppDataManager.getLocalRestaurantsList();
                                getMvpView().showMessage("Restauracja dodana pomyślnie!");
                                getMvpView().startRestaurantDetailActivity(restaurant.getId());
                            }

                        @Override
                        public void onError(Throwable e) {
                                getMvpView().showMessage(ErrorUtils.getErrorMessage(e));
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
    }

    public void loadRestaurants() {

        mAppDataManager.getRR()
                .subscribeOn(mBaseSchedulerProvider.io())
                .observeOn(mBaseSchedulerProvider.ui())
                .subscribe(new Observer<List<Restaurant>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getMvpView().showLoadingProgress(true);
                    }

                    @Override
                    public void onNext(List<Restaurant> restaurants) {
                            mAppDataManager.setLocalRestaurantsList(restaurants);
                            getMvpView().showRestaurants(restaurants);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showLoadingProgress(false);
                        getMvpView().showMessage(ErrorUtils.getErrorMessage(e));
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showLoadingProgress(false);
                    }
                });
    }

    public void loadPlaceDetails(String google_id) {
        mAppDataManager.getPlaceDetails(google_id)
                .subscribeOn(mBaseSchedulerProvider.io())
                .observeOn(mBaseSchedulerProvider.ui())
                .subscribe(new Observer<Restaurant>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Restaurant restaurant) {
                        if(restaurant!=null){
                            createRestaurant(restaurant);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showMessage(e.toString());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}