package com.heroku.java.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "BatchInfo")
public class BatchInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BatchNo")
    private Integer batchNo;

    @Column(name = "BatchSize")
    private Integer batchSize;

    @Column(name = "StartTime")
    private LocalDateTime startTime;

    @Column(name = "EndTime")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "MachineId")
    @JsonIgnore
    private Machine machine;

    @OneToMany(mappedBy = "batchInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Product> products;

    public BatchInfo() {
    }

    public BatchInfo(Machine machine, Integer batchSize, LocalDateTime startTime, LocalDateTime endTime) {
        this.machine = machine;
        this.batchSize = batchSize;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Integer batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime getStartTime) {
        this.startTime = getStartTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getProductsMade() {
        if (products != null) {
            return products.size();
        } else {
            return 0;
        }
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
