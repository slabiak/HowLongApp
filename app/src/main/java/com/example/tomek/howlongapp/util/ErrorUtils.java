package com.example.tomek.howlongapp.util;

import com.example.tomek.howlongapp.data.model.ApiErrorResponse;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

import retrofit2.Response;

public class ErrorUtils {

    public static String getErrorMessage(Throwable e) {
        if(e instanceof HttpException){
            ApiErrorResponse apiErrorResponse;
            Response response = ((HttpException) e).response();
            try {
                apiErrorResponse = new Gson().fromJson(response.errorBody().string(),ApiErrorResponse.class);
                return apiErrorResponse.toString();
            } catch (Exception exception) {
                return exception.getMessage();
            }
        }
        else return e.getMessage();
    }
}
