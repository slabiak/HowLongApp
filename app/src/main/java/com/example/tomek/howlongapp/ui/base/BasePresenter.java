package com.example.tomek.howlongapp.ui.base;

/**
 * Created by tslabiak on 15.03.2018.
 */

 public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T view) {
        mMvpView = view;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public T getMvpView() {
        return mMvpView;
    }




}
