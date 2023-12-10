package com.heroku.java.controller;

import com.heroku.java.model.ErrorLookup;
import com.heroku.java.model.Errors;
import com.heroku.java.service.ErrorLookupService;
import com.heroku.java.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ErrorController {

    @Autowired
    private ErrorService errorService;

    @Autowired
    private ErrorLookupService errorLookupService;

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

    @GetMapping("/getErrorCode")
    public Errors getErrorCode(@RequestParam Integer errorId) {
        return errorService.getErrorCodeById(errorId);
    }

    @GetMapping("/getMostCommonMachineErrorsAndTheirFrequency")
    public String getMostCommonMachineErrorsAndTheirFrequency() {
        return errorService.getMostCommonMachineErrorsAndTheirFrequency();
    }

    @GetMapping("/getMostCommonMachineErrorsAndTheirFrequencyForMachine")
    public String getMostCommonMachineErrorsAndTheirFrequency(@RequestParam Integer machineId) {
        return errorService.getMostCommonMachineErrorsAndTheirFrequencyForMachine(machineId);
    }
}
