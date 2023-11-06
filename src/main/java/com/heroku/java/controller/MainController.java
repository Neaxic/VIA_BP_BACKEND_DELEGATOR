package com.heroku.java.controller;

import com.heroku.java.model.*;
import com.heroku.java.service.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
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
    public String registerMachineErrorHistory(@RequestParam int historyId, @RequestParam int machineId, @RequestParam int errorId,@RequestParam LocalDateTime timeForMistake) {
        return machineErrorHistoryService.registerMachineErrorHistory(historyId, machineId, errorId, timeForMistake);
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

}
