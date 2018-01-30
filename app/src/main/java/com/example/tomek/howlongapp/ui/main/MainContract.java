package com.example.tomek.howlongapp.ui.main;

import com.example.tomek.howlongapp.BasePresenter;
import com.example.tomek.howlongapp.BaseView;

/**
 * Created by Tomek on 27.01.2018.
 */

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showText(String text);

    }

    interface Presenter extends BasePresenter {

        void onAddButtonClicked();


    }

}
