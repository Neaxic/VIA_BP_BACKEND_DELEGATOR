package com.heroku.java.model;

import jakarta.persistence.*;

@Entity
@Table(name = "errorcode")
public class ErrorCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int errorID;
    private String errorName;

    public ErrorCode() {}

    public ErrorCode(String errorName) {
        this.errorName = errorName;
    }

    public int getErrorID() {
        return errorID;
    }

    public void setErrorID(int errorID) {
        this.errorID = errorID;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }
}
