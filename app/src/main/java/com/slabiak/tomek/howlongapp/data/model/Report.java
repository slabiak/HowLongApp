package com.slabiak.tomek.howlongapp.data.model;

import java.util.Date;

/**
 * Created by slabiaq on 02.02.2018.
 */

public class Report {

    private int waitingTime;
    private Date createdAt;
    private String createdBy;

    public Report(int waitingTime, String createdBy){
        this.waitingTime =waitingTime;
        this.createdBy = createdBy;
    }

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}