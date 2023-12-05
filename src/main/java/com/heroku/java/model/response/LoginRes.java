package com.heroku.java.model.response;

import com.heroku.java.model.User;
import com.heroku.java.model.UserRolesLookup;

import java.util.List;

public class LoginRes {
    private String username;
    private String token;

    private User userData;

    private List<UserRolesLookup> userRoles;

    public LoginRes(String username, String token, User userData, List<UserRolesLookup> userRoles) {
        this.username = username;
        this.token = token;
        this.userData = userData;
        this.userRoles = userRoles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getToken() {
        return token;
    }


    public User getUserData() {
        return userData;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<UserRolesLookup> getUserRoles() {
        return userRoles;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

    public void setUserRoles(List<UserRolesLookup> userRoles) {
        this.userRoles = userRoles;
    }
}
