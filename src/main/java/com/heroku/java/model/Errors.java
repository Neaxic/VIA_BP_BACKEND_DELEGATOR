package com.heroku.java.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "error")
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

    @ManyToOne
    @JoinColumn(name = "ErrorLookUpId")
    private ErrorLookup errorLookUp;

    @Column(name = "isFakeGenerated")
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

    public ErrorLookup getErrorLookUp() {
        return errorLookUp;
    }

    public void setErrorLookUp(ErrorLookup errorLookUp) {
        this.errorLookUp = errorLookUp;
    }

    public void setFakeData(boolean fakeData) {
        isFakeData = fakeData;
    }

    public boolean isFakeData() {
        return isFakeData;
    }

    public void setAsFakeData(boolean fakeData) {
        isFakeData = fakeData;
    }
}
