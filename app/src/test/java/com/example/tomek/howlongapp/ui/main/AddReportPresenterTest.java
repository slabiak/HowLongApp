package com.example.tomek.howlongapp.ui.main;

import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.ApiResponse;
import com.example.tomek.howlongapp.data.model.Report;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.ui.addreport.AddReportContract;
import com.example.tomek.howlongapp.ui.addreport.AddReportPresenter;
import com.example.tomek.howlongapp.ui.restaurantdetail.RestaurantDetailContract;
import com.example.tomek.howlongapp.ui.restaurantdetail.RestaurantDetailPresenter;
import com.example.tomek.howlongapp.util.schedulers.BaseSchedulerProvider;
import com.example.tomek.howlongapp.util.schedulers.ImmediateSchedulerProvider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by tslabiak on 19.03.2018.
 */

public class AddReportPresenterTest {
    @Mock AddReportContract.View mockView;
    @Mock AppDataManager mockAppDataManaer;

    private AddReportPresenter mPresenter;
    private BaseSchedulerProvider mBaseSchedulerProvider;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mBaseSchedulerProvider = new ImmediateSchedulerProvider();
        mPresenter = new AddReportPresenter(mockAppDataManaer, mBaseSchedulerProvider);
        mPresenter.attachView(mockView);

    }

    @Test
    public void shouldFindRestaurant(){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        ArrayList<Restaurant> list = new ArrayList<Restaurant>();
        list.add(restaurant);
        ApiResponse reponse = new ApiResponse();
        reponse.setRestaurants(list);
        doReturn(reponse).when(mockAppDataManaer).getmLocalResponse();
        Restaurant restaurant2 = mPresenter.findReastaurant(1);
        Assert.assertEquals(restaurant, restaurant2);
    }

    @Test
    public void shouldAddReportWhenButtonClicked(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(false);
        String author = "author";
        doReturn(Observable.just(apiResponse)).when(mockAppDataManaer).addReport(1,15, author);
        mPresenter.addReport(1,15, author);
        verify(mockView).startMainActivity();

    }
}
