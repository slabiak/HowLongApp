package com.example.tomek.howlongapp.data.model;

import java.util.List;

/**
 * Created by tslabiak on 30.01.2018.
 */

public class ApiResponse {

    private Boolean error;
    private String message;
    private List<Restaurant> restaurants;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

}