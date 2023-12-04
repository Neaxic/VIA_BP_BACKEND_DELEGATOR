package com.heroku.java.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "machine")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MachineId")
    private int machineID;

    @Column(name = "MachineName")
    private String machineName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Status")
    private Integer status = null;

    @Column(name = "createFakeData", columnDefinition="BIT")
    private Boolean createFakeData = false;

    @Column(name = "enableSnapshot")
    private Boolean enableSnapshot = false;


    public Machine() {}

    public Machine(String machineName, String description, Integer status, Boolean createFakeData) {
        this.machineName = machineName;
        this.description = description;
        this.status = status;
        this.createFakeData = createFakeData;
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

    public Boolean shouldCreateFakeData() {
        return createFakeData;
    }

    public void setCreateFakeData(Boolean createFakeData) {
        this.createFakeData = createFakeData;
    }

    public boolean isMachineRunning() {
        return this.getStatus() == Constants.MACHINE_STATUS_RUNNING;
    }

    public BatchInfo getCurrentBatch() {
        //TODO: LAV DENNE
        return null;
    }

    public MachineUpTime toMachineUpTime() {
        //TODO: Overvej og lav unitTest på den her (TTD)
        MachineUpTime machineUpTime = new MachineUpTime();
        machineUpTime.setMachineId(this.getMachineID());
        machineUpTime.setMachineName(this.getMachineName());
        machineUpTime.setStatus(this.getStatus());
        machineUpTime.setTimeOfLog(LocalDateTime.now());
        return machineUpTime;
    }

    /* @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "BatchInfo",
            joinColumns = @JoinColumn(name = "machineID"),
            inverseJoinColumns = @JoinColumn(name = "batchNo")
    )
    private List<BatchInfo> batches;

    public Machine() {}

    public Machine(String machineName, String description, StatusCodes statusCode) {
        this.machineName = machineName;
        this.description = description;
        this.statusCode = statusCode;
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

    public StatusCodes getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCodes statusCode) {
        this.statusCode = statusCode;
    }

    public boolean shouldCreateFakeData() {
        return createFakeData == null ? false : createFakeData;
    }

    public void setCreateFakeData(Boolean createFakeData) {
        this.createFakeData = createFakeData;
    }

    public List<BatchInfo> getBatches() {
        return batches;
    }

    private void setBatches(List<BatchInfo> batches) {
        this.batches = batches;
    }

    public BatchInfo getCurrentBatch() {
        LocalDateTime currentDate = LocalDateTime.now();
        BatchInfo currentBatch = batches.stream()
                .filter(batch -> currentDate.isAfter(batch.getStartTime()) && currentDate.isBefore(batch.getEndTime()))
                .findFirst().orElse(null);
        return currentBatch;
    }

    public boolean isMachineRunning() {
        return getStatusCode().getStatusCodeID() == Constants.MACHINE_STATUS_RUNNING;
    }*/

}
