package com.heroku.java.service;

import com.heroku.java.model.User;
import com.heroku.java.model.UserDTO;
import com.heroku.java.model.UserRoles;
import com.heroku.java.repository.UserRepository;
import com.heroku.java.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRolesRepository userRolesRepository;
    /*
    public String login(String username, String password) {
        User user = userRepository.login(username, password);
        return user == null ? Constants.STATUS_FAILED : user.toJsonString();
    }
    */

    public String registerUser(String username, String password, int roleID) {
        UserRoles role = userRolesRepository.findRoleById(roleID);
        //String encryptedPassword = EncryptionUtil.hashPassword(password);
        User user = new User(username, password, role); //TODO: Tjek om dette også gemmer UserRole'en og ikke bare useren.
        return userRepository.saveUser(user);
    }

    public List<User> getAllUsers()
    {
        return userRepository.getAllUsers();
    }

    public List<UserDTO> getAllUsersDTO() {
        List<User> users = userRepository.getAllUsers();
        return users.stream().map(User::toDTO).collect(Collectors.toList());
    }
}
