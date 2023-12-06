package com.heroku.java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.checkerframework.common.aliasing.qual.Unique;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Unique
    @Column(name = "username")
    String username = ""; // Assigning this to avoid NPE

    @Column(name = "password_")
    String password = ""; // Assigning this to avoid NPE

    @Column(name = "firstname")
    String firstname = "";

    @Column(name = "lastname")
    String lastname = "";

    @Basic
    @Column(name = "createDate")
    LocalDateTime createDate = LocalDateTime.now();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "userRoles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private Set<UserRoles> roles = new HashSet<>();

    public User() {
    } // Needed for hibernate to create empty objects

    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRoles> getRoles() {
        return roles;
    }

    public void addRoleID(UserRoles role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

    public void removeRoleId(UserRoles role) {
        roles.remove(role);
    }

    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(this.getUserId());
        userDTO.setUsername(this.getUsername());
        userDTO.setRoles(this.getRoles());
        userDTO.setFirstname(this.getFirstname());
        userDTO.setLastname(this.getLastname());
        userDTO.setCreateDate(this.getCreateDate());
        return userDTO;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }
}
