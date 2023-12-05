package com.heroku.java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "userRoles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRolesLookup role;

    @Column(name = "user_id")
    @JsonIgnore
    private Integer user_id; // Change int to Integer

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRolesLookup getRole() {
        return role;
    }

    public void setRole(UserRolesLookup role) {
        this.role = role;
    }
}
