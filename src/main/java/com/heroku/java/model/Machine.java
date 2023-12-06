package com.heroku.java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "machine")
@JsonIgnoreProperties("batches")
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

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private List<BatchInfo> batches = new ArrayList<>();


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

    @Transactional
    public BatchInfo getCurrentBatch() {
        LocalDateTime currentDate = LocalDateTime.now();
        return this.getBatches().stream()
                .filter(batch -> currentDate.isAfter(batch.getStartTime()) && currentDate.isBefore(batch.getEndTime()))
                .findFirst()
                .orElse(null);
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

    public List<BatchInfo> getBatches() {
        return batches;
    }

    public void setBatches(List<BatchInfo> batches) {
        this.batches = batches;
    }

    public void addBatch(BatchInfo batch) {
        batches.add(batch);
        batch.setMachine(this);
    }

    public MachineDTO toDto() {
        return new MachineDTO(machineID, machineName, description, status, createFakeData, enableSnapshot, batches, this.getCurrentBatch());
    }
}
