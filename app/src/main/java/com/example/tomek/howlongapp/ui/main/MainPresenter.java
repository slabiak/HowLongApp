package com.example.tomek.howlongapp.ui.main;


import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.ApiResponse;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Tomek on 27.01.2018.
 */

public class MainPresenter implements MainContract.Presenter {



    AppDataManager mAppDataManager;

    private final MainContract.View mMainView;


    @Inject
    public MainPresenter(MainContract.View mainView, AppDataManager appDataManager) {
        mMainView = mainView;
        mAppDataManager = appDataManager;
        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
    }






    @Override
    public void onAddButtonClicked() {

        //getRestaurants();
        //getRestaurant("ChIJ2V8bnRkx_UYROADo3AgiiL4");
        //createRestaurant();
        addReport();
    }

    public void getRestaurants() {
        mAppDataManager.getRestaurants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ApiResponse>() {
                               @Override
                               public void onSubscribe(Disposable d) {

                               }

                               @Override
                               public void onNext(ApiResponse value) {
                                    mMainView.showText(value.getRestaurants().get(0).getName());
                               }

                               @Override
                               public void onError(Throwable e) {

                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }

    public void getRestaurant(String id) {
        mAppDataManager.getRestaurant(id)
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

                    }
                });

    }

    public void createRestaurant() {
        mAppDataManager.createRestaurant("name", "10","2")
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

                    }
                });
    }

    public void addReport() {
        if(mAppDataManager != null) {
            mAppDataManager.addReport("1", 10, "slabiak")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ApiResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ApiResponse value) {
                            mMainView.showText(value.getMessage());

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else
        {
            mMainView.showText("null");
        }
    }


}
