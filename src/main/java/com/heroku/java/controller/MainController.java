package com.heroku.java.controller;

import com.heroku.java.model.*;
import com.heroku.java.model.request.RegisterReq;
import com.heroku.java.repository.UserRepository;
import com.heroku.java.service.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class MainController {

    /*NOTES/TODO:
        - Check TODO's
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

    @Autowired
    private ProductService productService;


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
    public MachineDTO getMachine(@RequestParam Integer id) {
        return machineService.getMachineById(id).toDto();
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
    public List<MachineDTO> getAllMachines() {
        return machineService.getAllMachines().stream().map(Machine::toDto).collect(Collectors.toList());
    }


    @GetMapping("/getMachineUpTime24HourProcentage")
    public double getMachineUpTime24HourProcentage(@RequestParam int machineId) {
        return machineUpTimeService.getMachineUpTime24HourProcentage(machineId);
    }

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

    @GetMapping("/getMostFrequentStatusForBatch")
    public String getMostFrequentStatusForBatch(@RequestParam Integer batchNo){
        return productService.getMostFrequentStatusForBatch(batchNo);
    }

    @GetMapping("/getCurrentOeeFromBatch")
    public Double getCurrentOeeFromBatch(@RequestParam Integer batchNo){
        return productService.getCurrentOeeFromBatch(batchNo);
    }

    @GetMapping("/getMostFrequentStatusForMachine")
    public String getMostFrequentStatusForMachine(int machineId ){
        return productService.getMostFrequentStatusForMachine(machineId);
    }

    @GetMapping("/getHistoryBatchData")
    public String getHistoryBatchData(int machineId){
        return productService.getHistoryBatchData(machineId);
    }

    @DeleteMapping("/deleteUser")
    public Boolean deleteUser(@RequestParam Integer userId){
        return userService.deleteUser(userId);
    }

    @GetMapping("/getMostProblematicMachine24hr")
    public int getMostProblematicMachine24Hr(){
        return machineUpTimeService.getMostDowntimeMachine24Hour();
    }



    @GetMapping("/getMachineOverviewAllMachineLast24")
    public List<Object[]> getMachineOverviewAllMachineLast24(){return  machineUpTimeService.getMachineOverviewAllMachineLast24();}

    @GetMapping("/getMachineOverviewByMachineLast24")
    public List<Object[]> getMachineOverviewByMachineLast24(int machineId){return  machineUpTimeService.getMachineOverviewByMachineLast24(machineId);}
    @GetMapping("/getMostCommonProductErrorsAndTheirFrequency")
    public String getMostCommonProductErrorsAndTheirFrequency() {
        return productService.getMostCommonProductErrorsAndTheirFrequency();
    }

    @GetMapping("/amountOfBreakdowns24hrByMachine")
    public int getNumDowntimeLast24Hour(@RequestParam int machineId){
        return machineUpTimeService.getNumDowntimeLast24Hour(machineId);
    }

    @GetMapping("/amountOfBreakdowns24hrForAllMachines")
    public int getNumDowntimeLast24Hour(){
        return machineUpTimeService.getNumDowntimeLast24Hour(null);
    }

    @GetMapping("/getTimeSinceLastBreakdown")
    public long getTimeSinceLastBreakdown(@RequestParam Integer machineId){
        return machineUpTimeService.getTimeSinceLastBreakdown(machineId);
    }

    @GetMapping("/getMostCommonMachineErrorsAndTheirFrequency")
    public String getMostCommonMachineErrorsAndTheirFrequency() {
        return errorService.getMostCommonMachineErrorsAndTheirFrequency();
    }

    @GetMapping("/getMostCommonMachineErrorsAndTheirFrequencyForMachine")
    public String getMostCommonMachineErrorsAndTheirFrequency(@RequestParam Integer machineId) {
        return errorService.getMostCommonMachineErrorsAndTheirFrequencyForMachine(machineId);
    }


    @GetMapping("/getLastBreakdown")
    public String getLastBreakdown(@RequestParam Integer machineId){
        return machineUpTimeService.getLastBreakdown(machineId);
    }

    @GetMapping("/getAllProductsMadeInTheLast24Hours")
    public Integer getAllProductsMadeInTheLast24Hours() {
        return productService.getNumberOfProductsMadeInTheLast24Hours();
    }

    @GetMapping("/getProductsMadeEachDay30DayInterval")
    public String getProductsMadeEachDay30DayInterval() {
        return productService.getProductsMadeEachDay30DayInterval();
    }


}


