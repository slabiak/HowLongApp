package com.example.tomek.howlongapp.ui.addreport;

import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.ApiResponse;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.ui.base.BasePresenter;
import com.example.tomek.howlongapp.util.schedulers.BaseSchedulerProvider;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by tslabiak on 04.03.2018.
 */

public class AddReportPresenter extends BasePresenter<AddReportContract.View> implements AddReportContract.Presenter {


    private Integer ID;
    private AppDataManager mAppDataManager;
    private final BaseSchedulerProvider mBaseSchedulerProvider;

    public AddReportPresenter(AppDataManager mAppDataManager, BaseSchedulerProvider baseSchedulerProvider) {
        this.mAppDataManager = mAppDataManager;
        mBaseSchedulerProvider = baseSchedulerProvider;
    }

    @Override
    public void start() {
        getMvpView().showRestaurantName(findReastaurant(ID).getName());
    }


    @Override
    public void onAddButtonClicked(String author, Integer waitingTime) {
        addReport(ID, waitingTime, author);
    }

    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void addReport(Integer id, Integer waitingTime, String createdBy) {
        mAppDataManager.addReport(id, waitingTime, createdBy)
                .subscribeOn(mBaseSchedulerProvider.io())
                .observeOn(mBaseSchedulerProvider.ui())
                .subscribe(new Observer<ApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ApiResponse value) {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().startMainActivity();
                    }
                });

    }

    public Restaurant findReastaurant(Integer ID) {
        for (Restaurant restaurant : mAppDataManager.getLocalResponse().getRestaurants()) {
            if (restaurant.getId() == ID) {
                return restaurant;
            }
        }
        return null;
    }
}
