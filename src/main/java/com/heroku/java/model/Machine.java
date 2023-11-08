package com.heroku.java.model;

import jakarta.persistence.*;
import org.hibernate.type.TrueFalseConverter;

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
}
