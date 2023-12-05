package com.heroku.java.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MachineUpTime")
public class MachineUpTime {
    //The idea of this, is to take a snapshot of the machine every 10 minutes (Or whatever interval),
    //So that we can save historical data about the machine.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MachineUpTimeId")
    private int machineUpTimeId;

    @Column(name = "MachineId")
    private Integer machineId;

    @Column(name = "MachineName")
    private String machineName;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TimeStamp")
    private LocalDateTime timeOfLog;

    @Column(name = "Status")
    private Integer status = null;

    public MachineUpTime() {
    }

    public int getMachineUpTimeId() {
        return machineUpTimeId;
    }

    public void setMachineUpTimeId(int machineUpTimeId) {
        this.machineUpTimeId = machineUpTimeId;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public LocalDateTime getTimeOfLog() {
        return timeOfLog;
    }

    public void setTimeOfLog(LocalDateTime timeOfLog) {
        this.timeOfLog = timeOfLog;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
