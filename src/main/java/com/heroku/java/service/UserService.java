package com.heroku.java.service;

import com.heroku.java.model.*;
import com.heroku.java.repository.RoleLookupRepository;
import com.heroku.java.repository.UserRepository;
import com.heroku.java.repository.UserRolesRepository;
import com.heroku.java.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRolesRepository userRolesRepository;

    @Autowired
    RoleLookupRepository roleLookupRepository;

    public String registerUser(String username, String password, String firstname, String lastname, int roleID) { //TODO: Overvej og lav om så den returner User (Skal snakke med andreas om frontenden så)
        System.out.println("Registering: " +username);
        //Check role is a thing
        UserRolesLookup role = userRolesRepository.findRoleById(roleID);
        if(role == null || role.getRoleName() == null || Objects.equals(role.getRoleName(), "")){
            return Constants.ROLE_INVALID;
        }

        //Check if username is taken
        User usernameCheck = userRepository.findUserByUsername(username);
        if(usernameCheck != null){
            return Constants.USERNAME_EXSIST;
        }

        //String encryptedPassword = EncryptionUtil.hashPassword(password);
        User user = new User(username, password, firstname, lastname);
        String responseUser = userRepository.saveUser(user);

        //Get the link
        UserRoles roleLinker = new UserRoles();
        roleLinker.setUser(userRepository.findUserByUsername(username));
        roleLinker.setRole(role);

        String responseRole = userRepository.addRoleLink(roleLinker);

        if(Objects.equals(responseRole, Constants.STATUS_SUCCESS) && Objects.equals(responseUser, Constants.STATUS_SUCCESS)){
            return Constants.STATUS_SUCCESS;
        } else {
            return Constants.STATUS_FAILED;
        }
    }

    public List<User> getAllUsers()
    {
        return userRepository.getAllUsers();
    }

    public List<UserRolesLookup> getAllLookupRoles() {
        return roleLookupRepository.getAllLookupRoles();
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public List<UserRolesLookup> getRolesByUserId(int roleId){
        return userRepository.getRolesByUserId(roleId);
    }

    public List<UserDTO> getAllUsersDTO() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : userRepository.getAllUsers()) {
            userDTOS.add(getUserDTO(user));
        }
        return userDTOS;
    }

    public boolean deleteUser(int userID){
        userRolesRepository.deleteAllRolesByUserId(userID);
        return userRepository.deleteUser(userID);
    }

    public UserDTO getUserDTO(User user) {
        UserDTO userDTO = user.toDTO();
        userDTO.setRoles(userRolesRepository.getAllRolesByUser(user.getUserId()));
        return userDTO;
    }
}
