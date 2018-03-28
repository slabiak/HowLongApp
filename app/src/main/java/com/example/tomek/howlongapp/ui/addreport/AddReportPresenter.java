package com.example.tomek.howlongapp.ui.addreport;

import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.ApiResponse;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.ui.base.BasePresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tslabiak on 04.03.2018.
 */

public class AddReportPresenter extends BasePresenter<AddReportContract.View> implements AddReportContract.Presenter {


    Integer ID;
    AppDataManager mAppDataManager;


    public AddReportPresenter(AppDataManager mAppDataManager) {
        this.mAppDataManager = mAppDataManager;
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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

    Restaurant findReastaurant(Integer ID) {

        for (Restaurant restaurant : mAppDataManager.getmLocalResponse().getRestaurants()) {
            if (restaurant.getId() == ID) {
                return restaurant;
            }
        }
        return null;
    }
}
