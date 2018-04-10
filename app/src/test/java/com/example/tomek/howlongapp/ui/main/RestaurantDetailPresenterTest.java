package com.example.tomek.howlongapp.ui.main;

import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.ApiResponse;
import com.example.tomek.howlongapp.data.model.Report;
import com.example.tomek.howlongapp.data.model.Restaurant;
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

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by tslabiak on 19.03.2018.
 */

public class RestaurantDetailPresenterTest {

    @Mock RestaurantDetailContract.View mockView;
    @Mock AppDataManager mockAppDataManaer;

    private RestaurantDetailPresenter mPresenter;
    private BaseSchedulerProvider mBaseSchedulerProvider;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mBaseSchedulerProvider = new ImmediateSchedulerProvider();
        mPresenter = new RestaurantDetailPresenter(mockAppDataManaer, mBaseSchedulerProvider);
        mPresenter.attachView(mockView);

    }

    @Test
    public void shouldStartNewActivityWhenButtonPressed(){
        mPresenter.onAddButtonClicked();
        verify(mockView).startAddReportActivity();
    }

    @Test
    public void shouldFindRestaurant(){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        ArrayList<Restaurant> list = new ArrayList<Restaurant>();
        list.add(restaurant);
        ApiResponse reponse = new ApiResponse();
        reponse.setRestaurants(list);
        doReturn(reponse).when(mockAppDataManaer).getLocalResponse();
        Restaurant restaurant2 = mPresenter.findReastaurant(1);
        Assert.assertEquals(restaurant, restaurant2);
    }


    @Test
    public void shoulShowRestaurantDetailsOnStart(){
        mPresenter.setID(1);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("Name");
        restaurant.setAddress("address");
        restaurant.setMean(15);
        restaurant.setPhotoReference("photo_reference");
        Report report = new Report();
        report.setCreatedAt("2018-03-30 10:35:31");
        report.setCreatedBy("author");
        report.setWaitingTime(15);
        ArrayList<Report> reports = new ArrayList<Report>();
        reports.add(report);
        restaurant.setReports(reports);
        ArrayList<Restaurant> list = new ArrayList<Restaurant>();
        list.add(restaurant);
        ApiResponse reponse = new ApiResponse();
        reponse.setRestaurants(list);
        doReturn(reponse).when(mockAppDataManaer).getLocalResponse();
        mPresenter.start();

        verify(mockView).showReports(reports);
        verify(mockView).loadImage(anyString());
        verify(mockView).showRetaurantsDetails(anyString(),anyString(), anyInt(), anyInt());
    }



}
