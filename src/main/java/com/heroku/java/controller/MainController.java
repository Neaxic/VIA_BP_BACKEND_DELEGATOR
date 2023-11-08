package com.heroku.java.controller;

import com.heroku.java.model.*;
import com.heroku.java.repository.UserRepository;
import com.heroku.java.service.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class MainController {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserService userService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private BatchInfoService batchInfoService;

    @Autowired
    private MachineErrorHistoryService machineErrorHistoryService;

    @Autowired
    private StatusCodeService statusCodeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ErrorCodeService errorCodeService;

    //User endpoints


    @RequestMapping("/testConnection")
    public String connectedToServer() {
        return "Connected to Server!";
    }


    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username,password);
    }

    @CrossOrigin
    @PostMapping("/registerUser")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam boolean isAdmin) {
        return userService.registerUser(username, password, isAdmin);
    }
    // ErrorCodes endpoints
    @PostMapping("/registerErrorCodes")
    public String registerErrorCode(@RequestParam String errorCode) {
        return errorCodeService.registerErrorCode(errorCode);
    }
    @PostMapping("/registerStatus")
    public String registerStatusCode(@RequestParam String statusCode) {
        return statusCodeService.registerStatusCode(statusCode);
    }
    @PostMapping("/registerBatch")
    public String registerBatch(@RequestParam Integer batchNo, @RequestParam Integer machineID, @RequestParam Integer producedItems, @RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime) {
        return batchInfoService.registerBatchInfo(batchNo,machineID,producedItems,startTime,endTime);
    }
    @PostMapping("/registerMachine")
    public String registerMachine(@RequestParam String machineName, @RequestParam String description,@RequestParam StatusCodes statusCode) {
        return machineService.registerMachine(machineName,description,statusCode);
    }
    @PostMapping("/registerMEH")
    public String registerMachineErrorHistory(@RequestParam int machineId, @RequestParam int errorId,@RequestParam LocalDateTime timeForMistake) {
        return machineErrorHistoryService.registerMachineErrorHistory(machineId, errorId, timeForMistake);
    }


    @GetMapping("/getBatchInfo")
    public String getBatchInfo(@RequestParam Integer batchno) {
        return batchInfoService.getBatchInfoById(batchno).toString();
    }

    // Retrieve an ErrorCode by ID
    @GetMapping("/getErrorCode")
    public ErrorCode getErrorCode(@RequestParam Integer id) {
        return errorCodeService.getErrorCodeById(id);
    }

    // Retrieve a Machine by ID
    @GetMapping("/getMachine")
    public Machine getMachine(@RequestParam Integer id) {
        return machineService.getMachineById(id);
    }

    // Retrieve a MachineErrorHistory by ID
    @GetMapping("/getMachineErrorHistory")
    public MachineErrorHistory getMachineErrorHistory(@RequestParam Integer id) {
        return machineErrorHistoryService.getMachineErrorHistoryById(id);
    }

    // Retrieve a StatusCode by ID
    @GetMapping("/getStatusCode")
    public StatusCodes getStatusCode(@RequestParam Integer id) {
        return statusCodeService.getStatusCodeById(id);
    }



//Virker på Backend
    @GetMapping("/getAllStatusCodes")
    public List<StatusCodes> getAllStatusCodes() {
        return statusCodeService.getAllStatusCodes();
    }

    @GetMapping("/getAllErrorCodes")
    public List<ErrorCode> getAllErrorCodes() {
        return errorCodeService.getAllErrorCodes();
    }
    // Virker På Backend
    @GetMapping("/getAllBatchs")
    public List<BatchInfo> getAllBatchs() {
        return batchInfoService.getAllBatchs();
    }
    //Virker PÅ backend
    @GetMapping("/getAllMachines")
    public List<Machine> getAllMachines() {
        return machineService.getAllMachines();
    }
    // Virker På BAckend
    @GetMapping("/getAllMEH")
    public List<MachineErrorHistory> getAllMEH() {
        return machineErrorHistoryService.getAllMEH();
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}


