package com.example.tomek.howlongapp.data.model;

/**
 * Created by slabiaq on 02.02.2018.
 */

public class Report {

    private Integer waitingTime;
    private String createdAt;
    private String createdBy;

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}