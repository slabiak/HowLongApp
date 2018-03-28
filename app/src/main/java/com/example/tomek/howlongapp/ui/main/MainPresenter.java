package com.example.tomek.howlongapp.ui.main;

import android.util.Log;

import com.example.tomek.howlongapp.BuildConfig;
import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.ApiResponse;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.ui.base.BasePresenter;
import com.example.tomek.howlongapp.util.schedulers.BaseSchedulerProvider;
import com.google.android.gms.location.places.Place;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.reactivestreams.Subscription;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Tomek on 27.01.2018.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private static final String TAG = "MyActivity";
    private final BaseSchedulerProvider mBaseSchedulerProvider;
    public Subscription mSubscription;
    private AppDataManager mAppDataManager;
    private ApiResponse mApiResponseCached;

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
        Log.d("ddd", BuildConfig.GoogleSecAPIKEY);
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
            mAppDataManager.createRestaurant(name, address, googleID, imageURL)
                    .subscribeOn(mBaseSchedulerProvider.io())
                    .observeOn(mBaseSchedulerProvider.ui())
                    .subscribe(new Observer<ApiResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(ApiResponse apiResponse) {
                            if (apiResponse.getError() == false) {
                                mAppDataManager.setmLocalResponse(apiResponse);
                                List<Restaurant> list = apiResponse.getRestaurants();
                                Collections.sort(list);
                                getMvpView().showRestaurants(list);
                                getMvpView().showMessage("Restauracja dodana pomyślnie!");
                            } else {
                                getMvpView().showMessage("Wybrana restauracja jest już w bazie!");
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
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

        getMvpView().showLoadingProgress(true);
        mAppDataManager.getRR()
                .subscribeOn(mBaseSchedulerProvider.io())
                .observeOn(mBaseSchedulerProvider.ui())
                .subscribe(new Observer<ApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        if (apiResponse.getError() == false) {
                            mAppDataManager.setmLocalResponse(apiResponse);
                            List<Restaurant> list = apiResponse.getRestaurants();
                            Collections.sort(list);
                            getMvpView().showRestaurants(list);
                        } else {
                            getMvpView().showMessage(apiResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showLoadingProgress(false);
                        getMvpView().showMessage("Błąd łączenia z bazą danych! Spróbuj pózniej");
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