package com.heroku.java.controller;

import com.heroku.java.model.UserDTO;
import com.heroku.java.model.UserRolesLookup;
import com.heroku.java.model.request.RegisterReq;
import com.heroku.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/getAllUsers")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsersDTO();
    }

    @ResponseBody
    @GetMapping("/getAllLookupRoles")
    public List<UserRolesLookup> getAllLookupRoles() {
        return userService.getAllLookupRoles();
    }

    @ResponseBody
    @PostMapping("/registerUser")
    public String registerUser(@RequestBody RegisterReq registerReq) {
        return userService.registerUser(registerReq.getUsername(), registerReq.getPassword(), registerReq.getFirstname(), registerReq.getLastname(), registerReq.getRoleId());
    }

    @DeleteMapping("/deleteUser")
    public Boolean deleteUser(@RequestParam Integer userId){
        return userService.deleteUser(userId);
    }
}
