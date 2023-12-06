package com.heroku.java.model;

import java.util.HashSet;
import java.util.Set;


public class UserDTO {
    /**
     * This class is used to remove the layer "UserRoles" from: User -> UserRoles -> UserRolesLookup
     *
     * This is done to avoid an extra layer of json
     */


    private int userId;
    private String username;
    private Set<UserRolesLookup> roles;
    public UserDTO() {
    }

    public UserDTO(int userId, String username, Set<UserRoles> roles) {
        this.userId = userId;
        this.username = username;
        this.roles = getUserRolesLookUpFromUserRoles(roles);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<UserRolesLookup> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = getUserRolesLookUpFromUserRoles(roles);
    }


    private Set<UserRolesLookup> getUserRolesLookUpFromUserRoles(Set<UserRoles> userRoles) {
        Set<UserRolesLookup> userRolesLookups = new HashSet<>();
        for (UserRoles role : userRoles) {
            userRolesLookups.add(role.getRole());
        }
        return userRolesLookups;
    }
}

