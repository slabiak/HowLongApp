package com.example.tomek.howlongapp.data.model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Tomek on 27.01.2018.
 */

public class Restaurant implements Comparable {

    private Integer id;
    private String name;
    private String address;
    private String googleId;
    private List<Report> reports;
    private Integer mean;
    private String photo_reference;

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

    public Integer getMean() {
        return mean;
    }

    public void setMean(Integer mean) {
        this.mean = mean;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Restaurant restaurant = (Restaurant) o;
        int cmp = Double.compare(((Restaurant) o).getMean(), this.mean);
        return cmp;
    }

    public String getPhotoReference() {
        return photo_reference;
    }

    public void setPhotoReference(String photo_reference) {
        this.photo_reference = photo_reference;
    }
}