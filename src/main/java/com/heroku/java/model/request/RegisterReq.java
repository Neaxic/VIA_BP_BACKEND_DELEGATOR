package com.heroku.java.model.request;

public class RegisterReq {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int roleId;

    public RegisterReq(String username, String password, String firstname, String lastname, int roleId) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setRoleId(int sroleId) {
        this.roleId = roleId;
    }
}

