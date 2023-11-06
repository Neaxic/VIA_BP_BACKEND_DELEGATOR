package com.heroku.java.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BatchInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer batchNo;

    @Column(nullable = false)
    private Integer machineID;

    @Column(nullable = false)
    private Integer producedItems;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    public BatchInfo(Integer batchNo, Integer machineID, Integer producedItems, LocalDateTime startTime, LocalDateTime endTime) {
        this.batchNo = batchNo;
        this.machineID = machineID;
        this.producedItems = producedItems;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public BatchInfo(int machineId, int producedItems, String startTime, String endTime) {
    }

    public BatchInfo() {

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

    public Integer getProducedItems() {
        return producedItems;
    }

    public void setProducedItems(Integer producedItems) {
        this.producedItems = producedItems;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
