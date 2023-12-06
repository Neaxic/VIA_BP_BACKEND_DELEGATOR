package com.heroku.java.model;

import jakarta.persistence.*;

@Entity
@Table(name = "userRolesLookUp")
public class UserRolesLookup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRolesLookUpId;

    @Column(name = "roleName")
    private String roleName;

    @Column(name = "description")
    private String description;

    public UserRolesLookup() {}

    public int getUserRolesLookUpId() {
        return userRolesLookUpId;
    }

    public void setUserRolesLookUpId(int userRolesLookUpId) {
        this.userRolesLookUpId = userRolesLookUpId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
