package com.example.tomek.howlongapp.ui.addreport;

import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.Report;
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
    public void onAddButtonClicked(String author, String waitingTime) {
        if(waitingTime.isEmpty() || author.isEmpty()){
            getMvpView().showMessage("Uzupełnij wszystkie dane!");
        } else {
            addReport(ID, Integer.parseInt(waitingTime), author);
        }
    }

    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void addReport(int restaurantId, int waitingTime, String createdBy) {
        Report report = new Report(waitingTime,createdBy);
        mAppDataManager.addReport(report,restaurantId)
                .subscribeOn(mBaseSchedulerProvider.io())
                .observeOn(mBaseSchedulerProvider.ui())
                .subscribe(new Observer<Report>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Report report) {

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
        for (Restaurant restaurant : mAppDataManager.getLocalRestaurantsList()) {
            if (restaurant.getId() == ID) {
                return restaurant;
            }
        }
        return null;
    }
}
