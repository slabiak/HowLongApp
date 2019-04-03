package com.slabiak.tomek.howlongapp.data.model;

import java.util.Date;

public class ApiErrorResponse {

    private String path;

    private String error;

    private String message;

    private Date timestamp;

    private int status;

    public ApiErrorResponse(){

    }


    public String getError ()
    {
        return error;
    }

    public void setError (String error)
    {
        this.error = error;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getPath ()
    {
        return path;
    }

    public void setPath (String path)
    {
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  "status=" + status + '\n' +
                "error=" + error + '\n' +
                "message=" + message;
    }
}
