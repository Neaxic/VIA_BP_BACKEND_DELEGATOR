package com.heroku.java.service;

import com.heroku.java.model.User;
import com.heroku.java.model.UserDTO;
import com.heroku.java.model.UserRoles;
import com.heroku.java.model.UserRolesLookup;
import com.heroku.java.repository.UserRepository;
import com.heroku.java.repository.UserRolesRepository;
import org.joda.time.DateTime;
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

    public String registerUser(String username, String password, String firstname, String lastname, int roleID) {
        //String encryptedPassword = EncryptionUtil.hashPassword(password);
        User user = new User(username, password, firstname, lastname);
        userRepository.saveUser(user);

        //Check role is a thing
        UserRolesLookup role = userRolesRepository.findRoleById(roleID);

        //Get the link
        UserRoles roleLinker = new UserRoles();
        roleLinker.setUser_id(userRepository.findUserByUsername(username).getUserId());

        if(role.getRoleName() != null){
            roleLinker.setRole(role);
        } else {
            roleLinker.setRole(new UserRolesLookup());
        }

        userRepository.addRoleLink(roleLinker);

        return "OK";
    }

    public List<User> getAllUsers()
    {
        return userRepository.getAllUsers();
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public List<UserRolesLookup> getRolesByUserId(int roleId){
        return userRepository.getRolesByUserId(roleId);
    }

    public List<UserDTO> getAllUsersDTO() {
        List<User> users = userRepository.getAllUsers();
        return users.stream().map(User::toDTO).collect(Collectors.toList());
    }
}
