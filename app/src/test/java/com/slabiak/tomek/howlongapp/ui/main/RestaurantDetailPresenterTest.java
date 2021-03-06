package com.slabiak.tomek.howlongapp.ui.main;

import com.slabiak.tomek.howlongapp.data.AppDataManager;
import com.slabiak.tomek.howlongapp.ui.restaurantdetail.RestaurantDetailContract;
import com.slabiak.tomek.howlongapp.ui.restaurantdetail.RestaurantDetailPresenter;
import com.slabiak.tomek.howlongapp.util.schedulers.BaseSchedulerProvider;
import com.slabiak.tomek.howlongapp.util.schedulers.ImmediateSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

/*    @Test
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
    }*/


   /* @Test
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
    }*/


/*    @Test
    public void shouldShowNoReportsTxtWhenReportsListEmpty(){
        mPresenter.setID(1);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("Name");
        restaurant.setAddress("address");
        restaurant.setMean(15);
        restaurant.setPhotoReference("photo_reference");
        ArrayList<Report> reports = new ArrayList<Report>();
        restaurant.setReports(reports);
        ArrayList<Restaurant> list = new ArrayList<Restaurant>();
        list.add(restaurant);
        ApiResponse reponse = new ApiResponse();
        reponse.setRestaurants(list);
        doReturn(reponse).when(mockAppDataManaer).getLocalResponse();
        mPresenter.start();
        verify(mockView).showEmptyList(true);
    }*/



}
