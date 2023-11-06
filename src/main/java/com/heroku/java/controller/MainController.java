package com.heroku.java.controller;

import com.heroku.java.model.Constants;
import com.heroku.java.model.EncryptionUtil;
import com.heroku.java.model.User;
import com.heroku.java.repository.UserRepository;
import com.heroku.java.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserService userService;

    @RequestMapping("/testConnection")
    public String connectedToServer() {
        return "Connected to Server!";
    }


    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username,password);
    }

    @PostMapping("/registerUser")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam boolean isAdmin) {
        return userService.registerUser(username, password, isAdmin);
    }
}
