package com.heroku.java.controller;

import com.heroku.java.model.Machine;
import com.heroku.java.model.MachineDTO;
import com.heroku.java.service.MachineService;
import com.heroku.java.service.MachineUpTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class MachineController {

    @Autowired
    private MachineService machineService;

    @Autowired
    private MachineUpTimeService machineUpTimeService;

    @GetMapping("/getAllMachines")
    public List<MachineDTO> getAllMachines() {
        return machineService.getAllMachines().stream().map(Machine::toDto).collect(Collectors.toList());
    }

    @PostMapping("/registerMachine")
    public String registerMachine(@RequestParam String machineName, @RequestParam String description, @RequestParam Integer statusCode) {
        return machineService.registerMachine(machineName,description,statusCode);
    }

    @GetMapping("/getMachineById")
    public MachineDTO getMachine(@RequestParam Integer id) {
        return machineService.getMachineById(id).toDto();
    }

    @GetMapping("/getMachineOverviewAllMachineLast24")
    public Map<String, List<Integer>> getMachineOverviewAllMachineLast24(){return  machineUpTimeService.getMachineOverviewAllMachineLast24();}

    @GetMapping("/getMachineOverviewByMachineLast24")
    public Map<String, List<Integer>> getMachineOverviewByMachineLast24(int machineId){return  machineUpTimeService.getMachineOverviewByMachineLast24(machineId);}

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

    @GetMapping("/getLastBreakdown")
    public String getLastBreakdown(@RequestParam Integer machineId){
        return machineUpTimeService.getLastBreakdown(machineId);
    }

    @GetMapping("/getMachineUpTime24HourProcentage")
    public double getMachineUpTime24HourProcentage(@RequestParam int machineId) {
        return machineUpTimeService.getMachineUpTime24HourProcentage(machineId);
    }

    @GetMapping("/getMostProblematicMachine24hr")
    public int getMostProblematicMachine24Hr(){
        return machineUpTimeService.getMostDowntimeMachine24Hour();
    }
}
