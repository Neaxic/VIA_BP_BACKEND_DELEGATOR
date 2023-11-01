package com.heroku.java.controller;

import com.heroku.java.model.Constants;
import com.heroku.java.model.EncryptionUtil;
import com.heroku.java.model.User;
import com.heroku.java.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/testConnection")
    public String connectedToServer() {
        return "Connected to Server!";
    }


    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.login(username,password);

        return user == null ? Constants.STATUS_FAILED : user.toJsonString();
    }

    @PostMapping("/registerUser")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam boolean isAdmin) {
      String encryptedPassword = EncryptionUtil.hashPassword(password);
      User user = new User(username, encryptedPassword, isAdmin);
      return userRepository.saveUser(user);
    }
}
