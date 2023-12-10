package com.heroku.java.controller;

import com.heroku.java.model.*;
import com.heroku.java.model.request.RegisterReq;
import com.heroku.java.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class BatchController {

    @Autowired
    private UserService userService;

    @Autowired
    private BatchInfoService batchInfoService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/testConnection")
    public String connectedToServer() {
        return "Connected to Server!";
    }

    @GetMapping("/getBatchInfo")
    public BatchInfo getBatchInfo(@RequestParam Integer batchNo) {
        return batchInfoService.getBatchInfoById(batchNo);
    }

    @PostMapping("/registerBatch")
    public String registerBatch(@RequestParam Integer machineID, @RequestParam Integer producedItems, @RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime) {
        return batchInfoService.registerBatchInfo(machineID,producedItems,startTime,endTime);
    }

    // Retrieve an ErrorCode by ID
    @GetMapping("/getAllBatches")
    public List<BatchInfo> getAllBatchs() {
        return batchInfoService.getAllBatches();
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

    @GetMapping("/getMostCommonProductErrorsAndTheirFrequency")
    public String getMostCommonProductErrorsAndTheirFrequency() {
        return productService.getMostCommonProductErrorsAndTheirFrequency();
    }

    @GetMapping("/getMostCommonProductErrorsAndTheirFrequencyForMachine")
    public String getMostCommonProductErrorsAndTheirFrequencyForMachine(@RequestParam Integer machineId) {
        return productService.getMostCommonProductErrorsAndTheirFrequency(machineId);
    }

    @GetMapping("/getAllProductsMadeInTheLast24Hours")
    public Integer getAllProductsMadeInTheLast24Hours() {
        return productService.getNumberOfProductsMadeInTheLast24Hours();
    }

    @GetMapping("/getNumberOfProductsMadeInTheLast24HoursPrHour")
    public String getNumberOfProductsMadeInTheLast24HoursPrHour() {
        return productService.getNumberOfProductsMadeInTheLast24HoursPrHour();
    }
    @GetMapping("/getNumberOfProductsMadeInTheLast24HoursPrHourPrMachine")
    public String getNumberOfProductsMadeInTheLast24HoursPrHour(@RequestParam Integer machineId) {
        return productService.getNumberOfProductsMadeInTheLast24HoursPrHourPrMachine(machineId);
    }

    @GetMapping("/getProductsMadeEachDay30DayInterval")
    public String getProductsMadeEachDay30DayInterval() {
        return productService.getProductsMadeEachDay30DayInterval();
    }


}


