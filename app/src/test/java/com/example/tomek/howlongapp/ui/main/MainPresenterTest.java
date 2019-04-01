package com.example.tomek.howlongapp.ui.main;

import com.example.tomek.howlongapp.data.AppDataManager;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.example.tomek.howlongapp.util.schedulers.BaseSchedulerProvider;
import com.example.tomek.howlongapp.util.schedulers.ImmediateSchedulerProvider;
import com.google.android.gms.location.places.Place;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by tslabiak on 18.03.2018.
 */

public class MainPresenterTest {
    
    @Mock MainContract.View mockView;
    @Mock AppDataManager mockAppDataManaer;
    @Mock Place mockPlace;
    private MainPresenter mPresenter;
    private BaseSchedulerProvider mBaseSchedulerProvider;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mBaseSchedulerProvider = new ImmediateSchedulerProvider();
        mPresenter = new MainPresenter(mockAppDataManaer, mBaseSchedulerProvider);
        mPresenter.attachView(mockView);

    }


    @Test
    public void shouldShowLoadingProgressOnStart(){

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(false);
        ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
        restaurantList.add((new Restaurant()));
        apiResponse.setRestaurants(restaurantList);

        doReturn(Observable.just(apiResponse)).when(mockAppDataManaer).getRR();
        mPresenter.start();
        verify(mockView).showLoadingProgress(true);
    }


    @Test
    public void shouldShowRestaurants(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(false);
        ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
        restaurantList.add((new Restaurant()));
        apiResponse.setRestaurants(restaurantList);

        doReturn(Observable.just(apiResponse)).when(mockAppDataManaer).getRR();
        mPresenter.start();
        verify(mockView).showRestaurants(restaurantList);
    }

    @Test
    public void shouldHandleErrorFromApi(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(true);
        String msg = "Error message from API";
        apiResponse.setMessage(msg);

        doReturn(Observable.just(apiResponse)).when(mockAppDataManaer).getRR();
        mPresenter.start();

        verify(mockView).showMessage(msg);
    }


    @Test
    public void errorLoadingRestaurants_ShowsError(){
        doReturn(Observable.error(new RuntimeException())).when(mockAppDataManaer).getRR();
        mPresenter.loadRestaurants();
        verify(mockView).showLoadingProgress(false);
        //verify(mockView).showMessage(anyString());
    }

    @Test
    public void shouldNotCreateRestaurantWhitoutAllData(){
        String name = "";   // empty field
        String address = "address";
        String googleID = "googleID";
        String photo_reference = "photo_reference";

        mPresenter.createRestaurant(name, address, googleID, photo_reference);
        verify(mockView).showMessage(anyString());
    }


    @Test
    public void shouldCreateAndShowRestaurantWhenHasData(){
        String name = "name";
        String address = "address";
        String googleID = "googleID";
        String photo_reference = "photo_reference";

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(false);
        ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
        restaurantList.add((new Restaurant()));
        apiResponse.setRestaurants(restaurantList);

        doReturn(Observable.just(apiResponse)).when(mockAppDataManaer).createRestaurant(name, address, googleID, photo_reference);
        mPresenter.createRestaurant(name, address, googleID, photo_reference);

        verify(mockView).showRestaurants(restaurantList);
    }


    @Test
    public void shouldNotLoadDetailsWhenIsNotRestaurant(){
        CharSequence name = "name";
        CharSequence address = "addres";
        String googleID = "googleID";
        Integer place_type = 8; // type: bank (restaurant is 79)
        ArrayList<Integer> types_list = new ArrayList<Integer>();
        types_list.add(place_type);

        doReturn(name).when(mockPlace).getName();
        doReturn(address).when(mockPlace).getAddress();
        doReturn(googleID).when(mockPlace).getId();
        doReturn(types_list).when(mockPlace).getPlaceTypes();

        mPresenter.onPlacePickerFinished(mockPlace);

        verify(mockView).showMessage(anyString());

    }

    @Test
    public void shouldLoadDetailsWhenIsRestaurant(){
        CharSequence name = "name";
        CharSequence address = "addres";
        String googleID = "googleID";
        String photo_reference = "ddd";
        Integer place_type = 79; // type: restaurant
        ArrayList<Integer> types_list = new ArrayList<Integer>();
        types_list.add(place_type);
        doReturn(name).when(mockPlace).getName();
        doReturn(address).when(mockPlace).getAddress();
        doReturn(googleID).when(mockPlace).getId();
        doReturn(types_list).when(mockPlace).getPlaceTypes();


        String json = "{\"result\": {\"name\": \"name\",\"formatted_address\": \"address\",\"id\": \"id\",\"photos\": [{\"photo_reference\" : \"ddd\"}]}}";
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(json);
        doReturn(Observable.just(jo)).when(mockAppDataManaer).getPlaceDetails(googleID);


        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(false);
        ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
        restaurantList.add((new Restaurant()));
        apiResponse.setRestaurants(restaurantList);
        JsonElement photos = jo.get("photos");
        doReturn(Observable.just(apiResponse)).when(mockAppDataManaer).createRestaurant(anyString(),anyString(),anyString(),anyString());

        mPresenter.onPlacePickerFinished(mockPlace);

        verify(mockAppDataManaer).getPlaceDetails(googleID);
    }




}