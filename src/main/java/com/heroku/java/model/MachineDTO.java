package com.heroku.java.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MachineDTO {

    private int machineID;
    private String machineName;
    private String description;
    private Integer status = null;
    private Boolean createFakeData = false;
    private Boolean enableSnapshot = false;
    private List<BatchInfo> batches = new ArrayList<>();
    private BatchInfo currentBatch = null;

    public MachineDTO(int machineID, String machineName, String description, Integer status, Boolean createFakeData, Boolean enableSnapshot, List<BatchInfo> batches, BatchInfo currentBatch) {
        this.machineID = machineID;
        this.machineName = machineName;
        this.description = description;
        this.status = status;
        this.createFakeData = createFakeData;
        this.enableSnapshot = enableSnapshot;
        this.batches = batches;
        this.currentBatch = currentBatch;
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getCreateFakeData() {
        return createFakeData;
    }

    public void setCreateFakeData(Boolean createFakeData) {
        this.createFakeData = createFakeData;
    }

    public Boolean getEnableSnapshot() {
        return enableSnapshot;
    }

    public void setEnableSnapshot(Boolean enableSnapshot) {
        this.enableSnapshot = enableSnapshot;
    }

    public List<BatchInfo> getBatches() {
        return batches;
    }

    public void setBatches(List<BatchInfo> batches) {
        this.batches = batches;
    }
}
