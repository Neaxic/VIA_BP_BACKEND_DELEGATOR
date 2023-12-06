package com.heroku.java.service;

import com.heroku.java.model.Constants;
import com.heroku.java.model.MachineUpTime;
import com.heroku.java.repository.MachineUpTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineUpTimeService {

    @Autowired
    private MachineUpTimeRepository machineUpTimeRepository;

    public void saveMachineUpTime(MachineUpTime machineUpTime) {
        machineUpTimeRepository.saveMachineUpTime(machineUpTime);
    }

    public double getMachineUpTime24HourProcentage(int machineId) {
        List<MachineUpTime> machineUpTimeList = machineUpTimeRepository.getAllMachineUpTime24Hour(machineId);
        double amountOfSnapshots = machineUpTimeList.size();
        double amountOfRunningSnapshots = 0;
        for (MachineUpTime machineUpTime : machineUpTimeList) {
            if(machineUpTime.getStatus().equals(Constants.MACHINE_STATUS_RUNNING)) {
                amountOfRunningSnapshots++;
            }
        }
        return (amountOfRunningSnapshots/amountOfSnapshots) * 100;
    }

    public int getMostDowntimeMachine24Hour(){
        int machine = machineUpTimeRepository.getMostDowntimeMachine24Hour();
        System.out.println("machine: "+machine);
        return machine;
    }
}
