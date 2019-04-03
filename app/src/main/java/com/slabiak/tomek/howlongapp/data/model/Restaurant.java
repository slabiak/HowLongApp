package com.slabiak.tomek.howlongapp.data.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomek on 27.01.2018.
 */

public class Restaurant implements Comparable {

    private int id;
    private String name;
    private String address;
    private String googleId;
    private List<Report> reports;
    private String photoReference;

    public Restaurant(String name, String address, String googleId, String photoReference){
        this.name = name;
        this.address = address;
        this.googleId = googleId;
        this.photoReference = photoReference;
        this.reports = new ArrayList<Report>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public int getMeanWaitingTime(){
        int mean = 0;
        if(reports!=null){
            if(reports.size()>0){
                for(Report report : reports){
                    mean += report.getWaitingTime();
                }
                mean = mean/reports.size();
                return mean;
            }
        }
        return mean;
    }


    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Restaurant restaurant = (Restaurant) o;
        return restaurant.getMeanWaitingTime() - this.getMeanWaitingTime();
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photo_reference) {
        this.photoReference = photo_reference;
    }
}