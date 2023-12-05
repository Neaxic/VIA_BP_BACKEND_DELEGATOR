package com.heroku.java.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "BatchInfo")
public class BatchInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BatchNo")
    private Integer batchNo;

    @Column(name = "MachineId")
    private Integer machineID;

    @Column(name = "BatchSize")
    private Integer batchSize;

    @Column(name = "StartTime")
    private LocalDateTime getStartTime;

    @Column(name = "EndTime")
    private LocalDateTime endTime;

    public BatchInfo() {
    }

    public BatchInfo(Integer machineID, Integer batchSize, LocalDateTime getStartTime, LocalDateTime endTime) {
        this.machineID = machineID;
        this.batchSize = batchSize;
        this.getStartTime = getStartTime;
        this.endTime = endTime;
    }

    public Integer getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Integer batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getMachineID() {
        return machineID;
    }

    public void setMachineID(Integer machineID) {
        this.machineID = machineID;
}

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public LocalDateTime getGetStartTime() {
        return getStartTime;
    }

    public void setGetStartTime(LocalDateTime getStartTime) {
        this.getStartTime = getStartTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getProductsMade() {
        //TODO: Lav denne. Lav sql query på Product, og se hvor mange produkter der har det her batchNo.
        //Overvej om dette skulle ligge i productService maybe.
        return 0;
    }
}
