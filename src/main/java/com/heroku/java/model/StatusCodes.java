package com.heroku.java.model;

import jakarta.persistence.*;

@Entity
@Table(name = "statuscodes")
public class StatusCodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusCodeID;
    private String statusDescription;

    public StatusCodes() {}

    public StatusCodes(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public int getStatusCodeID() {
        return statusCodeID;
    }

    public void setStatusCodeID(int statusCodeID) {
        this.statusCodeID = statusCodeID;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}

