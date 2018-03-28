package com.example.tomek.howlongapp.ui.base;

/**
 * Created by Tomek on 27.01.2018.
 */

public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
