package com.example.tomek.howlongapp.ui.main;

import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.ui.base.BasePresenter;
import com.example.tomek.howlongapp.util.ErrorUtils;
import com.example.tomek.howlongapp.util.schedulers.BaseSchedulerProvider;
import com.google.android.gms.location.places.Place;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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

    public static boolean empty(final String s) {
        // Null-safe, short-circuit evaluation.
        return s == null || s.trim().isEmpty();
    }

    @Override
    public void start() {
        loadRestaurants();
    }

    @Override
    public void onAddPlaceButtonClicked() {
        getMvpView().startPlacePicker();
    }

    @Override
    public void onPlacePickerFinished(Place place) {
        boolean isAnRestaurant = false;
        for (int i : place.getPlaceTypes()) {
            if (i == Place.TYPE_RESTAURANT) {
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

    public void createRestaurant(String name, String address, String googleID, String imageURL) {

        if (!(empty(name) || empty(address) || empty(googleID) || empty(imageURL))) {
            Restaurant restaurant = new Restaurant(name,address,googleID,imageURL);
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
                                getMvpView().showRestaurants(restaurants);
                                getMvpView().showMessage("Restauracja dodana pomyślnie!");
                            }

                        @Override
                        public void onError(Throwable e) {
                            getMvpView().showMessage(ErrorUtils.getErrorMessage(e));
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        } else {
            getMvpView().showMessage("Błąd! Restauracja nie ma kompletnych danych");
        }
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
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(JsonObject response) {

                        JsonObject result = response.getAsJsonObject("result");
                        JsonElement photos = result.get("photos");
                        JsonElement jsonName = result.get("name");
                        JsonElement jsonGoogle_id = result.get("id");
                        JsonElement jsonAddres = result.get("formatted_address");

                        String photo_reference = photos.getAsJsonArray().get(0).getAsJsonObject().get("photo_reference").getAsString();
                        String name = jsonName.getAsString();
                        String google_id = jsonGoogle_id.getAsString();
                        String address = jsonAddres.getAsString();

                        createRestaurant(name, address, google_id, photo_reference);
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