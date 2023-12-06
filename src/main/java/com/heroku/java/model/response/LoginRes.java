package com.heroku.java.model.response;

import com.heroku.java.model.User;
import com.heroku.java.model.UserDTO;

public class LoginRes {
    private String username;
    private String token;
    private UserDTO userData;

    public LoginRes(String username, String token, User userData) {
        this.username = username;
        this.token = token;
        this.userData = userData.toDTO();
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

    public UserDTO getUserData() {
        return userData;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserData(User userData) {
        this.userData = userData.toDTO();
    }

}
