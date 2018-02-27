package com.example.tomek.howlongapp.data.model;

import java.util.List;

/**
 * Created by Tomek on 27.01.2018.
 */

public class Restaurant {

    private Integer id;
    private String name;
    private String address;
    private String googleId;
    private List<Object> reports = null;
    private Integer mean;

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

    public List<Object> getReports() {
        return reports;
    }

    public void setReports(List<Object> reports) {
        this.reports = reports;
    }

    public Integer getMean() {
        return mean;
    }

    public void setMean(Integer mean) {
        this.mean = mean;
    }

}
