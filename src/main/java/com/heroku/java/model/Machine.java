package com.heroku.java.model;

import jakarta.persistence.*;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.hibernate.type.TrueFalseConverter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "machine")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int machineID;
    private String machineName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "statusCode", referencedColumnName = "statusCodeID")
    private StatusCodes statusCode;

    @Column(name = "createFakeData", columnDefinition="BIT")
    private Boolean createFakeData = false;

    @OneToMany(fetch = FetchType.EAGER)
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
    }

}
