package com.heroku.java.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ErrorLookup")
public class ErrorLookup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ErrorLookupId")
    private int errorLookupId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    public ErrorLookup() {
    }

    public int getErrorLookupId() {
        return errorLookupId;
    }

    public void setErrorLookupId(int errorLookupId) {
        this.errorLookupId = errorLookupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
