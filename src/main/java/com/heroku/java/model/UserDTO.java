package com.heroku.java.model;

import java.util.Set;


public class UserDTO {
    private int userId;
    private String username;
    private Set<UserRoles> roles;
    public UserDTO() {
    }

    public UserDTO(int userId, String username, Set<UserRoles> roles) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
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

    public Set<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }
}

