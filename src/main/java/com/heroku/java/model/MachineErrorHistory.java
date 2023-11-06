package com.heroku.java.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "machine_error_history")
public class MachineErrorHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyId;

    @Column(name = "machine_id")
    private int machineId;

    @Column(name = "error_id")
    private int errorId;

    @Column(name = "time_for_mistake")
    private LocalDateTime timeForMistake;

    public MachineErrorHistory(int historyId, int machineId, int errorId, LocalDateTime timeForMistake) {
        this.historyId = historyId;
        this.machineId = machineId;
        this.errorId = errorId;
        this.timeForMistake = timeForMistake;
    }

    public MachineErrorHistory(int machineId, int errorId, String timeOfMistake) {
    }

    public MachineErrorHistory() {

    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }

    public LocalDateTime getTimeForMistake() {
        return timeForMistake;
    }

    public void setTimeForMistake(LocalDateTime timeForMistake) {
        this.timeForMistake = timeForMistake;
    }
}
