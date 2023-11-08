package com.heroku.java.service;

import com.heroku.java.model.BatchInfo;
import com.heroku.java.model.Constants;
import com.heroku.java.model.EncryptionUtil;
import com.heroku.java.model.User;
import com.heroku.java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String login(String username, String password) {
        User user = userRepository.login(username, password);

        return user == null ? Constants.STATUS_FAILED : user.toJsonString();
    }
    public String registerUser(String username, String password, boolean isAdmin) {
        String encryptedPassword = EncryptionUtil.hashPassword(password);
        User user = new User(username, encryptedPassword, isAdmin);
        return userRepository.saveUser(user);
    }

    public List<User> getAllUsers()
    {
        return userRepository.getAllUsers();
    }
}
