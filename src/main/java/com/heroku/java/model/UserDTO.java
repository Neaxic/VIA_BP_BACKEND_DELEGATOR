package com.heroku.java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.checkerframework.common.aliasing.qual.Unique;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class UserDTO {
    /**
     * This class is used to remove the layer "UserRoles" from: User -> UserRoles -> UserRolesLookup
     *
     * This is done to avoid an extra layer of json
     */

    private int userId;
    private String username;
    private List<UserRolesLookup> roles;
    String firstname;
    String lastname;
    LocalDateTime createDate;


    public UserDTO() {
    }

    public UserDTO(int userId, String username, List<UserRoles> roles,String firstname, String lastname, LocalDateTime createDate) {
        this.userId = userId;
        this.username = username;
        this.roles = roles.stream().map(UserRoles::getRole).collect(Collectors.toList());
        this.firstname = firstname;
        this.lastname = lastname;
        this.createDate = createDate;
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

    public List<UserRolesLookup> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoles> roles) {
        this.roles = roles.stream().map(UserRoles::getRole).collect(Collectors.toList());
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}

