package com.heroku.java.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "machine")
public class Errors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ErrorId")
    private int errorId;

    @Column(name = "MachineId")
    private Integer machineID;

    @Basic
    @Column(name = "TimeStamp")
    private LocalDateTime timeStamp;

    @Column(name = "ErrorLookUpId")
    private Integer errorLookUpId;

    @Column(name = "isFakeGenerated", columnDefinition="BIT")
    private boolean isFakeData;

    public Errors() {
    }

    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }

    public Integer getMachineID() {
        return machineID;
    }

    public void setMachineID(Integer machineID) {
        this.machineID = machineID;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getErrorLookUpId() {
        return errorLookUpId;
    }

    public void setErrorLookUpId(Integer errorLookUpId) {
        this.errorLookUpId = errorLookUpId;
    }

    public boolean isFakeData() {
        return isFakeData;
    }

    public void setAsFakeData(boolean fakeData) {
        isFakeData = fakeData;
    }
}
