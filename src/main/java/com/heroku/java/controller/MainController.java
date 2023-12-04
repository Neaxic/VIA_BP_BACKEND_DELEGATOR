package com.heroku.java.controller;

import com.heroku.java.model.*;
import com.heroku.java.repository.UserRepository;
import com.heroku.java.service.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class MainController {

    /*NOTES/TODO:
        - Consider making all endpoints return JSON. Double check and see if this is already what's being done
        - Check TODO's
        - Overvej de steder hvor der gemmes objekter til DB. Her returneres en String med "OK" Eller "failed".
          Overvej her at returnere hele objekterne så de også modtager id'et frontend.
        - Lav MachineUpTime (Skal tage snapshots af Machine og gemme historisk data)

     */

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserService userService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private BatchInfoService batchInfoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private ErrorLookupService errorLookupService;

    @Autowired
    private MachineUpTimeService machineUpTimeService;


    //User endpoints
    @RequestMapping("/testConnection")
    public String connectedToServer() {
        return "Connected to Server!";
    }



    @PostMapping("/registerErrorLookup")
    public String registerErrorLookup(@RequestParam String name, String description) {
        return errorLookupService.registerErrorLookup(name, description);
    }

    @GetMapping("/getErrorLookupById")
    public ErrorLookup getErrorLookupById(@RequestParam Integer id) {
        return errorLookupService.getErrorLookupById(id);
    }

    @GetMapping("/getRandomErrorLookup")
    public ErrorLookup getRandomErrorLookup() {
        return errorLookupService.getRandomErrorLookup();
    }

    @GetMapping("/getAllErrorLookups")
    public List<ErrorLookup> getAllErrorLookups() {
        return errorLookupService.getAllErrorLookups();
    }

    @PostMapping("/registerMachine")
    public String registerMachine(@RequestParam String machineName, @RequestParam String description, @RequestParam Integer statusCode) {
        return machineService.registerMachine(machineName,description,statusCode);
    }

    @GetMapping("/getBatchInfo")
    public BatchInfo getBatchInfo(@RequestParam Integer batchNo) {
        return batchInfoService.getBatchInfoById(batchNo);
    }

    @PostMapping("/registerBatch")
    public String registerBatch(@RequestParam Integer machineID, @RequestParam Integer producedItems, @RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime) {
        return batchInfoService.registerBatchInfo(machineID,producedItems,startTime,endTime);
    }

    // Retrieve a Machine by ID
    @GetMapping("/getMachineById")
    public Machine getMachine(@RequestParam Integer id) {
        return machineService.getMachineById(id);
    }


    // Retrieve an ErrorCode by ID
    @GetMapping("/getErrorCode")
    public Errors getErrorCode(@RequestParam Integer errorId) {
        return errorService.getErrorCodeById(errorId);
    }

    @GetMapping("/getAllErrorCodes")
    public List<Errors> getAllErrorCodes() {
        return errorService.getAllErrorCodes();
    }
    // Virker På Backend
    @GetMapping("/getAllBatches")
    public List<BatchInfo> getAllBatchs() {
        return batchInfoService.getAllBatches();
    }
    //Virker PÅ backend

    @GetMapping("/getAllMachines")
    public List<Machine> getAllMachines() {
        return machineService.getAllMachines();
    }

    @GetMapping("/getMachineUpTime24HourProcentage")
    public double getMachineUpTime24HourProcentage(@RequestParam int machineId) {
        return machineUpTimeService.getMachineUpTime24HourProcentage(machineId);
    }


    //TODO Burdes Flyttes TOL AUTH DEt her intet med maskine at gøre
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    //TODO Burdes Flyttes TOL AUTH DEt her intet med maskine at gøre
    @PostMapping("/registerUser")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam boolean isAdmin) {
        return userService.registerUser(username, password, isAdmin);
    }
}


