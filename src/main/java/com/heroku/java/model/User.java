package com.heroku.java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import scala.Int;

import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "username")
    String username = ""; // Assigning this to avoid NPE

    @Column(name = "password_")
    String password = ""; // Assigning this to avoid NPE

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userRoles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRoles> roles = new HashSet<>();

    public User() {
    } // Needed for hibernate to create empty objects

    public User(String username, String password, UserRoles role) {
        this.username = username;
        this.password = password;
        this.roles.add(role);
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

    public String toJsonString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}"; // Return an empty JSON object in case of an error
        }
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
}
